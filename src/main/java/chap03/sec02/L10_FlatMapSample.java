package chap03.sec02;

import java.util.concurrent.TimeUnit;

import io.reactivex.Flowable;

/** 예제 3-10 flatMap 메서드 내에서 서로 다른 스레드에서 작동하는 Flowable을 생성하는 예제 */
public class L10_FlatMapSample {
  
  public static void main(String[] args) throws Exception {
    Flowable<String> flowable =
        // Flowable을 생성한다
        Flowable.just("A", "B", "C")
            // 받은 데이터로 Flowable을 생성하고 이 Flowable의 데이터를 통지한다
            .flatMap(data -> {
              // 1000밀리초 늦게 데이터를 통지하는 Flowable을 생성한다
              return Flowable.just(data).delay(1000L, TimeUnit.MILLISECONDS);
            });
    
    // 구독한다
    flowable.subscribe(data -> {
      String threadName = Thread.currentThread().getName();
      System.out.println(threadName + ": " + data);
    });
    
    // 잠시 기다린다
    Thread.sleep(2000L);
  }
}

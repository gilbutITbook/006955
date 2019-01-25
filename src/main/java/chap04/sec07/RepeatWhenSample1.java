package chap04.sec07;

import java.util.concurrent.TimeUnit;

import chap04.DebugSubscriber;
import io.reactivex.Flowable;

/** 예제 4-99 repeatWhen(handler)의 예제(원래의 Flowable이 메인 스레드에서 처리가 될 때) */
public class RepeatWhenSample1 {
  
  public static void main(String[] args) throws Exception {
    
    Flowable<String> flowable =
        // Flowable을 생성한다
        Flowable.just(1, 2, 3)
            // 반복 통지를 제어한다
            .repeatWhen(completeHandler -> {
              return completeHandler
                  // 통지 시점을 늦춘다
                  .delay(1000L, TimeUnit.MILLISECONDS)
                  // 2번 반복한다
                  .take(3)
                  // 통지 시점에 정보를 출력한다  
                  .doOnNext(data -> System.out.println("emit: " + data))
                  .doOnComplete(() -> System.out.println("complete"));
            })
            // 데이터에 시스템 시각을 추가한다
            .map(data -> {
              long time = System.currentTimeMillis();
              return time + "ms: " + data;
            });
    
    // 구독한다
    flowable.subscribe(new DebugSubscriber<>());
    
    // 잠시 기다린다
    Thread.sleep(5000L);
  }
}

package chap04.sec07;

import java.util.concurrent.TimeUnit;

import chap04.DebugSubscriber;
import io.reactivex.Flowable;

/** 예제 4-100 repeatWhen(handler) 예제 - 기본 Flowable이 interval 메소드로부터 생성되는 경우 (원래의 Flowable이 다른 스레드에서 처리되는 경우) */
public class RepeatWhenSample2 {
  
  public static void main(String[] args) throws Exception {
    
    Flowable<String> flowable =
        // Flowable을 생성한다
        Flowable.interval(100L, TimeUnit.MILLISECONDS)
            // 3건까지 통지한다
            .take(3)
            // 반복 통지를 제어한다
            .repeatWhen(completeHandler -> {
              return completeHandler
                  // 통지 시점을 늦춘다
                  .delay(1000L, TimeUnit.MILLISECONDS)
                  // 2건까지 통지한다
                  .take(2)
                  // 통지 시점에 정보를 출력한다  
                  .doOnNext(data -> System.out.println("emit: " + data))
                  .doOnComplete(() -> System.out.println("complete"));
            })
            // 데이터에 시스템 시간을 추가한다
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

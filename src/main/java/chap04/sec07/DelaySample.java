package chap04.sec07;

import java.util.concurrent.TimeUnit;

import chap04.DebugSubscriber;
import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;

/** 예제 4-102 delay(time, unit) 예제  */
public class DelaySample {
  
  public static void main(String[] args) throws Exception {
    // 처리 시작 시간을 출력한다
    System.out.println("처리 시작： " + System.currentTimeMillis());
    
    Flowable<String> flowable =
        // Flowable을 생성한다
        Flowable.<String> create(emitter -> {
          // 구독 시작 시간을 출력한다
          System.out.println("구독 시작： " + System.currentTimeMillis());
          // 데이터를 통지한다
          emitter.onNext("A");
          emitter.onNext("B");
          emitter.onNext("C");
          // 완료를 통지한다
          emitter.onComplete();
        }, BackpressureStrategy.BUFFER)
            // 통지를 지연한다
            .delay(2000L, TimeUnit.MILLISECONDS)
            // 통지 시간을 출력한다
            .doOnNext(data -> System.out
                .println("통지 시각： " + System.currentTimeMillis()));
    // 구독한다
    flowable.subscribe(new DebugSubscriber<>());
    // 잠시 기다린다
    Thread.sleep(3000L);
  }
}

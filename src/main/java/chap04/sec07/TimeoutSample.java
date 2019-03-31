package chap04.sec07;

import java.util.concurrent.TimeUnit;

import chap04.DebugSubscriber;
import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;

/** 예제 4-105 timeout(time, unit) 예제 */
public class TimeoutSample {
  
  public static void main(String[] args) throws Exception {
    
    Flowable<Integer> flowable =
        // Flowable을 생성한다
        Flowable.<Integer> create(emitter -> {
          emitter.onNext(1);
          emitter.onNext(2);
          // 잠시 기다린다 ※시간이 많이 걸리는 작업을 처리한다고 가정한다
          try {
            Thread.sleep(1200L);
          } catch (InterruptedException e) {
            // InterruptedException이 발생하면 통지하고 완료한다
            emitter.onError(e);
            return;
          }
          emitter.onNext(3);
          emitter.onComplete();
        }, BackpressureStrategy.BUFFER)
            // 1000밀리초 후에 타임아웃이 발생하게 한다
            .timeout(1000L, TimeUnit.MILLISECONDS);
    
    // 구독한다
    flowable.subscribe(new DebugSubscriber<>());
    
    // 잠시 기다린다
    Thread.sleep(2000L);
  }
}

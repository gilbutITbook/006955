package chap04.sec03;

import java.util.concurrent.TimeUnit;

import chap04.DebugSubscriber;
import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;

/** 예제 4-73 debounce(debounceIndicator) 예제 */
public class DebounceSample {
  
  public static void main(String[] args) throws Exception {
    Flowable<String> flowable =
        // Flowable을 생성한다
        Flowable.<String> create(
            // 통지 처리
            emitter -> {
              // 데이터를 통지하고 잠시 기다린다
              emitter.onNext("A");
              Thread.sleep(1000L);
              
              emitter.onNext("B");
              Thread.sleep(300L);
              
              emitter.onNext("C");
              Thread.sleep(300L);
              
              emitter.onNext("D");
              Thread.sleep(1000L);
              
              emitter.onNext("E");
              Thread.sleep(100L);
              
              // 완료를 통지한다
              emitter.onComplete();
            }, BackpressureStrategy.BUFFER)
            // 지정 기간 안에 다음 데이터가 오지 않으면 통지한다
            .debounce(data -> Flowable.timer(500L, TimeUnit.MILLISECONDS));
    
    // 구독한다
    flowable.subscribe(new DebugSubscriber<>());
  }
}

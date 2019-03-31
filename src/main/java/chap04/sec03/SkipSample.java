package chap04.sec03;

import java.util.concurrent.TimeUnit;

import chap04.DebugSubscriber;
import io.reactivex.Flowable;

/** 예제 4-63 skip(count) 예제 */
public class SkipSample {
  
  public static void main(String[] args) throws Exception {
    Flowable<Long> flowable =
        // Flowable을 생성한다
        Flowable.interval(1000L, TimeUnit.MILLISECONDS)
            // 처음 2건은 통지하지 않는다
            .skip(2);
    
    // 구독한다
    flowable.subscribe(new DebugSubscriber<>());
    
    // 잠시 기다린다
    Thread.sleep(5000L);
  }
  
}

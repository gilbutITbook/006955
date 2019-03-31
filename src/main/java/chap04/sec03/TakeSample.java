package chap04.sec03;

import java.util.concurrent.TimeUnit;

import chap04.DebugSubscriber;
import io.reactivex.Flowable;

/** 예제 4-55 take(count) 예제  */
public class TakeSample {
  
  public static void main(String[] args) throws Exception {
    Flowable<Long> flowable =
        // Flowable을 생성한다
        Flowable.interval(1000L, TimeUnit.MILLISECONDS)
            // 3건까지 통지한다
            .take(3);
    
    // 구독한다
    flowable.subscribe(new DebugSubscriber<>());
    
    // 잠시 기다린다
    Thread.sleep(4000L);
  }
  
}

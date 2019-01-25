package chap04.sec03;

import java.util.concurrent.TimeUnit;

import chap04.DebugSubscriber;
import io.reactivex.Flowable;

/** 예제 4-64 skipUntil(other) 예제 */
public class SkipUntilSample {
  
  public static void main(String[] args) throws Exception {
    Flowable<Long> flowable =
        // Flowable을 생성한다
        Flowable.interval(300L, TimeUnit.MILLISECONDS)
            // 인자 Flowable이 통지할 때까지 데이터를 통지하지 않는다
            .skipUntil(Flowable.timer(1000L, TimeUnit.MILLISECONDS));
    
    // 구독한다
    flowable.subscribe(new DebugSubscriber<>());
    
    // 잠시 기다린다
    Thread.sleep(2000L);
  }
  
}

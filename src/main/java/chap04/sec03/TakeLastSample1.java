package chap04.sec03;

import java.util.concurrent.TimeUnit;

import chap04.DebugSubscriber;
import io.reactivex.Flowable;

/** 예제 4-61 takeLast(count) 예제 */
public class TakeLastSample1 {
  
  public static void main(String[] args) throws Exception {
    Flowable<Long> flowable =
        // Flowable을 생성한다
        Flowable.interval(800L, TimeUnit.MILLISECONDS)
            // 5건까지 통지한다
            .take(5)
            // 마지막 2건을 통지한다
            .takeLast(2);
    
    // 구독한다
    flowable.subscribe(new DebugSubscriber<>());
    
    // 잠시 기다린다
    Thread.sleep(5000L);
  }
  
}

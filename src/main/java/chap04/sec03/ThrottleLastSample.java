package chap04.sec03;

import java.util.concurrent.TimeUnit;

import chap04.DebugSubscriber;
import io.reactivex.Flowable;

/** 예제 4-69 throttleLast(time, unit) 예제 */
public class ThrottleLastSample {
  
  public static void main(String[] args) throws Exception {
    Flowable<Long> flowable =
        // Flowable을 생성한다
        Flowable.interval(300L, TimeUnit.MILLISECONDS)
            // 9건까지 통지한다
            .take(9)
            // 간격 내 가장 마지막 데이터를 통지한다
            .throttleLast(1000L, TimeUnit.MILLISECONDS);
    
    // 구독한다
    flowable.subscribe(new DebugSubscriber<>());
    
    // 잠시 기다린다
    Thread.sleep(3000L);
  }
}

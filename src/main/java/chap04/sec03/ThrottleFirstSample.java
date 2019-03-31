package chap04.sec03;

import java.util.concurrent.TimeUnit;

import chap04.DebugSubscriber;
import io.reactivex.Flowable;

/** 예제 4-68 throttleFirst(time, unit) 예제 */
public class ThrottleFirstSample {
  
  public static void main(String[] args) throws Exception {
    Flowable<Long> flowable =
        // Flowable을 생성한다
        Flowable.interval(300L, TimeUnit.MILLISECONDS)
            // 10건까지 통지한다
            .take(10)
            // 지정 시간 안에는 다음 데이터를 통지하지 않는다
            .throttleFirst(1000L, TimeUnit.MILLISECONDS);
    
    // 구독한다
    flowable.subscribe(new DebugSubscriber<>());
    
    // 잠시 기다린다
    Thread.sleep(4000L);
  }
}

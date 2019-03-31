package chap04.sec03;

import java.util.concurrent.TimeUnit;

import chap04.DebugSubscriber;
import io.reactivex.Flowable;

/** 예제 4-62 takeLast(count, time, unit) 예제 */
public class TakeLastSample2 {
  
  public static void main(String[] args) throws Exception {
    Flowable<Long> flowable =
        // Flowable을 생성한다
        Flowable.interval(300L, TimeUnit.MILLISECONDS)
            // 10건까지 통지한다
            .take(10)
            // 완료 전 1000밀리초 동안 통지된 데이터 중 끝에서부터 2건을 통지한다
            .takeLast(2, 1000L, TimeUnit.MILLISECONDS);
    
    // 구독한다
    flowable.subscribe(new DebugSubscriber<>());
    
    // 잠시 기다린다
    Thread.sleep(4000L);
  }
  
}

package chap04.sec03;

import java.util.concurrent.TimeUnit;

import chap04.DebugSubscriber;
import io.reactivex.Flowable;

/** 예제 4-57 takeUntil(stopPredicate) 예제 */
public class TakeUntilSample1 {
  
  public static void main(String[] args) throws Exception {
    Flowable<Long> flowable =
        // Flowable을 생성한다
        Flowable.interval(300L, TimeUnit.MILLISECONDS)
            // 받은 데이터가 '3'이 될 때까지 통지한다
            .takeUntil(data -> data == 3);
    
    // 구독한다
    flowable.subscribe(new DebugSubscriber<>());
    
    // 잠시 기다린다
    Thread.sleep(2000L);
  }
  
}

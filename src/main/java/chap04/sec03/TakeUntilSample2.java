package chap04.sec03;

import java.util.concurrent.TimeUnit;

import chap04.DebugSubscriber;
import io.reactivex.Flowable;

/** 예제 4-58 takeUntil(other) 예제  */
public class TakeUntilSample2 {
  
  public static void main(String[] args) throws Exception {
    Flowable<Long> flowable =
        // Flowable을 생성한다
        Flowable.interval(300L, TimeUnit.MILLISECONDS)
            // 인자 Flowable이 첫 데이터를 통지할 때까지 받은 데이터를 통지한다
            .takeUntil(Flowable.timer(1000L, TimeUnit.MILLISECONDS));
    
    // 구독한다
    flowable.subscribe(new DebugSubscriber<>());
    
    // 잠시 기다린다
    Thread.sleep(2000L);
  }
  
}

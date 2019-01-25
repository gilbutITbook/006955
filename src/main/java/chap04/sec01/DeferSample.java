package chap04.sec01;

import java.time.LocalTime;

import chap04.DebugSubscriber;
import io.reactivex.Flowable;

/** 예제 4-10 defer(supplier) 예제 */
public class DeferSample<T> {
  
  public static void main(String[] args) throws Exception {
    
    Flowable<LocalTime> flowable =
        Flowable.defer(() -> Flowable.just(LocalTime.now()));
    
    // 구독한다
    flowable.subscribe(new DebugSubscriber<>("No. 1"));
    
    // 잠시 기다린다
    Thread.sleep(2000L);
    
    // 다시 구독한다
    flowable.subscribe(new DebugSubscriber<>("No. 2"));
  }
  
}

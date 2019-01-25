package chap04.sec02;

import java.util.List;
import java.util.concurrent.TimeUnit;

import chap04.DebugSubscriber;
import io.reactivex.Flowable;

/** 예제 4-30 buffer(count) 예제 */
public class BufferSample1 {
  
  public static void main(String[] args) throws Exception {
    
    Flowable<List<Long>> flowable =
        // 100밀리초마다 숫자를 통지하는 Flowable을 생성한다
        Flowable.interval(100L, TimeUnit.MILLISECONDS)
            // 10건까지 통지한다
            .take(10)
            // 3건씩 모아 통지한다
            .buffer(3);
    
    //  구독한다
    flowable.subscribe(new DebugSubscriber<>());
    
    // 잠시 기다린다
    Thread.sleep(3000L);
  }
  
}

package chap04.sec02;

import java.util.List;
import java.util.concurrent.TimeUnit;

import chap04.DebugSubscriber;
import io.reactivex.Flowable;

/** 예제 4-32 buffer(boundaryIndicatorSupplier) 예제 */
public class BufferSample2 {
  
  public static void main(String[] args) throws Exception {
    Flowable<List<Long>> flowable =
        // 300밀리초마다 숫자를 통지하는 Flowable
        Flowable.interval(300L, TimeUnit.MILLISECONDS)
            // 7건까지 통지한다
            .take(7)
            // 버퍼 구간을 나누는 Flowable을 생성한다
            .buffer(
                // 1000밀리초 뒤에 데이터를 통지한다
                () -> Flowable.timer(1000L, TimeUnit.MILLISECONDS));
    
    // 구독한다
    flowable.subscribe(new DebugSubscriber<>());
    
    // 잠시 기다린다
    Thread.sleep(4000L);
  }
  
}

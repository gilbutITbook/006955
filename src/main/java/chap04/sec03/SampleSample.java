package chap04.sec03;

import java.util.concurrent.TimeUnit;

import chap04.DebugSubscriber;
import io.reactivex.Flowable;

/** 예제 4-70 sample(sampler) 예제 */
public class SampleSample {
  
  public static void main(String[] args) throws Exception {
    Flowable<Long> flowable =
        // Flowable을 생성한다
        Flowable.interval(300L, TimeUnit.MILLISECONDS)
            // 9건까지 통지한다
            .take(9)
            // 인자로 받은 Flowable이 데이터를 통지할 때 가장 마지막에 받은 데이터를 통지한다
            .sample(Flowable.interval(1000L, TimeUnit.MILLISECONDS));
    
    // 구독한다
    flowable.subscribe(new DebugSubscriber<>());
    
    // 잠시 기다린다
    Thread.sleep(3000L);
  }
}

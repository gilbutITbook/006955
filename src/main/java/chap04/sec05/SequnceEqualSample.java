package chap04.sec05;

import java.util.concurrent.TimeUnit;

import chap04.DebugSingleObserver;
import io.reactivex.Flowable;
import io.reactivex.Single;

/** 예제 4-89 sequenceEqual(source1, source2) 예제 */
public class SequnceEqualSample {
  
  public static void main(String[] args) throws Exception {
    // 비교대상
    Flowable<Long> flowable1 =
        Flowable.interval(1000L, TimeUnit.MILLISECONDS).take(3);
    
    // 비교대상
    Flowable<Long> flowable2 = Flowable.just(0L, 1L, 2L);
    
    // 같은 데이터가 같은 순서로 같은 수만큼 있는지를 판단한다
    Single<Boolean> single = Flowable.sequenceEqual(flowable1, flowable2);
    
    // 구독한다
    single.subscribe(new DebugSingleObserver<>());
    
    // 잠시 기다린다
    Thread.sleep(4000L);
  }
  
}

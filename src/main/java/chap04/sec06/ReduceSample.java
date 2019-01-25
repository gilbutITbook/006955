package chap04.sec06;

import chap04.DebugSingleObserver;
import io.reactivex.Flowable;
import io.reactivex.Single;

/** 예제 4-92 reduce(seed, reducer) 예제 */
public class ReduceSample {
  
  public static void main(String[] args) throws Exception {
    
    Single<Integer> single =
        // 인자의 데이터를 통지하는 Flowable을 생성한다
        Flowable.just(1, 10, 100, 1000, 10000)
            // reduce 메소드를 사용하여 받은 데이터를 더한다
            .reduce(0, (sum, data) -> sum + data);
    
    // 구독한다
    single.subscribe(new DebugSingleObserver<>());
  }
  
}

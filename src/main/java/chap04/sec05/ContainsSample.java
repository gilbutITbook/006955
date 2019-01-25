package chap04.sec05;

import java.util.concurrent.TimeUnit;

import chap04.DebugSingleObserver;
import io.reactivex.Flowable;
import io.reactivex.Single;

/** 예제 4-85 contains(item) 예제  */
public class ContainsSample {
  
  public static void main(String[] args) throws Exception {
    Single<Boolean> single =
        // Flowable을 생성한다
        Flowable.interval(1000L, TimeUnit.MILLISECONDS)
            // 인자의 데이터가 포함됐는지 판단한다
            .contains(3L);
    
    // 구독한다
    single.subscribe(new DebugSingleObserver<>());
    
    // 잠시 기다린다
    Thread.sleep(4000L);
  }
  
}

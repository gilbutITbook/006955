package chap04.sec05;

import java.util.concurrent.TimeUnit;

import chap04.DebugSingleObserver;
import io.reactivex.Flowable;
import io.reactivex.Single;

/** 예제 4-90 count 예제 */
public class CountSample {
  
  public static void main(String[] args) throws Exception {
    Single<Long> single =
        // Flowable을 생성한다
        Flowable.interval(1000L, TimeUnit.MILLISECONDS)
            // 3건까지 통지한다
            .take(3)
            // 데이터 개수를 통지한다
            .count();
    
    // 구독한다
    single.subscribe(new DebugSingleObserver<>());
    
    // 잠시 기다린다
    Thread.sleep(4000L);
  }
  
}

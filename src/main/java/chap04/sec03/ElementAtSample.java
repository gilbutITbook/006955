package chap04.sec03;

import java.util.concurrent.TimeUnit;

import chap04.DebugMaybeObserver;
import io.reactivex.Flowable;
import io.reactivex.Maybe;

/** 예제 4-74 elementAt(index) 예제 */
public class ElementAtSample {
  
  public static void main(String[] args) throws Exception {
    Maybe<Long> maybe =
        // Flowable을 생성한다
        Flowable.interval(100L, TimeUnit.MILLISECONDS)
            // 위치가 3(0부터 시작)인 데이터만을 통지한다
            .elementAt(3);
    
    // 구독한다
    maybe.subscribe(new DebugMaybeObserver<>());
    
    // 잠시 기다린다
    Thread.sleep(1000L);
  }
  
}

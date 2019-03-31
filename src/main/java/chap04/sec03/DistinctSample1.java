package chap04.sec03;

import chap04.DebugSubscriber;
import io.reactivex.Flowable;

/** 예제 4-49 distinct() 예제 */
public class DistinctSample1 {
  
  public static void main(String[] args) {
    Flowable<String> flowable =
        // Flowable을 생성한다
        Flowable.just("A", "a", "B", "b", "A", "a", "B", "b")
            // 같은 데이터를 제외하고 통지한다
            .distinct();
    
    // 구독한다
    flowable.subscribe(new DebugSubscriber<>());
  }
}

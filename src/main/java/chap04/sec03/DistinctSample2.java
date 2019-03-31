package chap04.sec03;

import chap04.DebugSubscriber;
import io.reactivex.Flowable;

/** 예제 4-50 distinct(keySelector) 예제 */
public class DistinctSample2 {
  
  public static void main(String[] args) {
    Flowable<String> flowable =
        // Flowable을 생성한다
        Flowable.just("A", "a", "B", "b", "A", "a", "B", "b")
            // 대소문자에 관계없이 같은 데이터를 제외하고 통지한다
            .distinct(data -> data.toLowerCase());
    
    // 구독한다
    flowable.subscribe(new DebugSubscriber<>());
  }
}

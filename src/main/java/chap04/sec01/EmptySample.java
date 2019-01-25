package chap04.sec01;

import chap04.DebugSubscriber;
import io.reactivex.Flowable;

/** 예제 4-11 empty() 예제 */
public class EmptySample<T> {
  
  public static void main(String[] args) {
    
    Flowable
        // 빈 Flowable을 생성한다
        .empty()
        // 구독한다
        .subscribe(new DebugSubscriber<>());
  }
  
}

package chap04.sec01;

import chap04.DebugSubscriber;
import io.reactivex.Flowable;

/** 예제 4-3 fromArray(items) 예제 */
public class FromArraySample<T> {
  
  public static void main(String[] args) {
    
    // 배열 데이터를 순서대로 통지하는 Flowable을 생성한다
    Flowable<String> flowable = Flowable.fromArray("A", "B", "C", "D", "E");
    
    // 구독을 시작한다
    flowable.subscribe(new DebugSubscriber<>());
  }
  
}

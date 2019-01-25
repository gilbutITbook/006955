package chap04.sec01;

import chap04.DebugSubscriber;
import io.reactivex.Flowable;

/** 예제 4-6 range(start, count) 예제 */
public class RangeSample<T> {
  
  public static void main(String[] args) {
    
    // 10부터 순서대로 데이터를 3건까지 통지하는 Flowable을 생성한다
    Flowable<Integer> flowable = Flowable.range(10, 3);
    
    // 구독을 시작한다
    flowable.subscribe(new DebugSubscriber<>());
  }
  
}

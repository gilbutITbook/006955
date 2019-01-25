package chap04.sec01;

import java.util.Arrays;
import java.util.List;

import chap04.DebugSubscriber;
import io.reactivex.Flowable;

public class FromIterableSample<T> {
  
  /**  예제 fromIterable(source) 예제 */
  public static void main(String[] args) {
    
    // 배열 데이터를 순서대로 통지하는 Flowable을 생성한다
    List<String> list = Arrays.asList("A", "B", "C", "D", "E");
    Flowable<String> flowable = Flowable.fromIterable(list);
    
    // 구독을 시작한다
    flowable.subscribe(new DebugSubscriber<>());
  }
  
}

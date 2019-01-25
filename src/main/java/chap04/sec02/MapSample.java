package chap04.sec02;

import chap04.DebugSubscriber;
import io.reactivex.Flowable;

/** 예제 4-16 map(mapper) 예제 */
public class MapSample {
  
  public static void main(String[] args) throws Exception {
    
    Flowable<String> flowable =
        // 인자로 받은 데이터를 순서대로 통지하는 Flowable을 생성한다
        Flowable.just("A", "B", "C", "D", "E")
            // map 메서드를 사용해 소문자로 변환한다
            .map(data -> data.toLowerCase());
    
    // 구독을 시작한다
    flowable.subscribe(new DebugSubscriber<>());
  }
}

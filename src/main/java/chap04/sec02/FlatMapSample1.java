package chap04.sec02;

import chap04.DebugSubscriber;
import io.reactivex.Flowable;

/** 예제 4-18 flatMap(mapper) 예제  */
public class FlatMapSample1 {
  
  public static void main(String[] args) throws Exception {
    // 인자의 데이터를 통지하는 Flowable을 생성한다
    Flowable<String> flowable = Flowable.just("A", "", "B", "", "C")
        // flatMap 메서드로 빈 문자를 제거하거나 소문자로 변환한다
        .flatMap(data -> {
          if ("".equals(data)) {
            // 빈 문자라면 빈 Flowable을 반환한다
            return Flowable.empty();
          } else {
            // 소문자로 변환한 데이터가 담긴 Flowable을 반환한다
            return Flowable.just(data.toLowerCase());
          }
        });
    
    // 구독을 시작한다
    flowable.subscribe(new DebugSubscriber<>());
  }
}

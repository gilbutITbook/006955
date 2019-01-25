package chap04.sec02;

import java.util.concurrent.TimeUnit;

import chap04.DebugSubscriber;
import io.reactivex.Flowable;

/** 예제 4-20 flatMap(mapper, combiner) 예제 */
public class FlatMapSample2 {
  
  public static void main(String[] args) throws Exception {
    Flowable<String> flowable = Flowable.range(1, 3)
        // mapper와 combiner를 인자로 받는 flatMap 메서드
        .flatMap(
            // 첫 번째 인자: 데이터를 받으면 새로운 Flowable을 생성한다
            data -> {
              return Flowable.interval(100L, TimeUnit.MILLISECONDS)
                  // 3건까지 통지한다
                  .take(3);
            },
            // 두 번째 인자: 원본 데이터와 변환한 데이터로 새로운 통지 데이터를 생성한다
            (sourceData, newData) -> "[" + sourceData + "] " + newData);
    
    // 구독을 시작한다
    flowable.subscribe(new DebugSubscriber<>());
    
    // 잠시 기다린다
    Thread.sleep(1000L);
  }
}

package chap04.sec03;

import java.math.BigDecimal;

import chap04.DebugSubscriber;
import io.reactivex.Flowable;

/** 예제 4-54 distinctUntilChanged(comparer) 예제  */
public class DistinctUntilChangedSample2 {
  
  public static void main(String[] args) {
    Flowable<String> flowable =
        // Flowable을 생성한다
        Flowable.just("1", "1.0", "0.1", "0.10", "1")
            // 같은 데이터가 연속되면 이 데이터를 제외하고 통지한다
            .distinctUntilChanged((data1, data2) -> {
              // BigDecimal로 변환한다
              BigDecimal convert1 = new BigDecimal(data1);
              BigDecimal convert2 = new BigDecimal(data2);
              
              // 변환한 값과 비교해 소수점 자릿수와 상관없이 같은지를 판단한다
              return (convert1.compareTo(convert2) == 0);
            });
    
    // 구독한다
    flowable.subscribe(new DebugSubscriber<>());
  }
}

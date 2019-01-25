package chap04.sec02;

import java.util.Map;

import chap04.DebugSingleObserver;
import io.reactivex.Flowable;
import io.reactivex.Single;

/** 예제 4-40 toMap(keySelector, valueSelector) 예제 */
public class ToMapSample2 {
  
  public static void main(String[] args) {
    
    Single<Map<Long, String>> single =
        // Flowable을 생성한다
        Flowable.just("1A", "2B", "3C", "1D", "2E")
            // 데이터에서 생성한 키와 값의 쌍이 담긴 Map을 통지한다
            .toMap(
                // 첫 번째 인자: 키 생성
                data -> Long.valueOf(data.substring(0, 1)),
                // 두 번째 인자: 값 생성
                data -> data.substring(1));
    
    // 구독한다
    single.subscribe(new DebugSingleObserver<>());
  }
}

package chap04.sec02;

import java.util.Map;

import chap04.DebugSingleObserver;
import io.reactivex.Flowable;
import io.reactivex.Single;

/** 예제 4-39 toMap(keySelector) 예제 */
public class ToMapSample1 {
  
  public static void main(String[] args) {
    
    Single<Map<Long, String>> single =
        // Flowable을 생성한다
        Flowable.just("1A", "2B", "3C", "1D", "2E")
            // 데이터로 생성한 키와 데이터 쌍을 담은 Map을 통지한다
            .toMap(data -> Long.valueOf(data.substring(0, 1)));
    
    // 구독한다
    single.subscribe(new DebugSingleObserver<>());
  }
}

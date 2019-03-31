package chap04.sec02;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import chap04.DebugSingleObserver;
import io.reactivex.Flowable;
import io.reactivex.Single;

/** 예제 4-45 toMultimap(keySelector) 예제 */
public class ToMultimapSample {
  
  public static void main(String[] args) throws Exception {
    
    Single<Map<String, Collection<Long>>> single =
        // Flowable을 생성한다
        Flowable.interval(500L, TimeUnit.MILLISECONDS)
            // 5건까지 통지한다
            .take(5)
            // 데이터로 생성한 키 각각에 데이터를 담은 Map을 통지한다
            .toMultimap(data -> {
              if (data % 2 == 0) {
                return "짝수";
              } else {
                return "홀수";
              }
            });
    
    // 구독한다
    single.subscribe(new DebugSingleObserver<>());
    
    // 잠시 기다린다
    Thread.sleep(3000L);
  }
}

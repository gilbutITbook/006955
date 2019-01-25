package chap04.sec02;

import java.util.List;

import chap04.DebugSingleObserver;
import io.reactivex.Flowable;
import io.reactivex.Single;

/** 예제 4-35 toList() 예제  */
public class ToListSample {
  
  public static void main(String[] args) {
    
    Single<List<String>> single =
        // Flowable을 생성한다
        Flowable.just("A", "B", "C", "D", "E")
            // 전체 데이터를 담은 리스트를 통지한다
            .toList();
    
    // 구독한다
    single.subscribe(new DebugSingleObserver<>());
  }
}

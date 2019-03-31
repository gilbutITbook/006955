package chap04.sec01;

import chap04.DebugSubscriber;
import io.reactivex.Flowable;

/** 예제 4-5 fromCallable(supplier) 예제 */
public class FromCallableSample<T> {
  
  public static void main(String[] args) {
    
    // Callable의 결과를 통지하는 Flowable을 생성한다
    Flowable<Long> flowable =
        Flowable.fromCallable(() -> System.currentTimeMillis());
    
    // 구독을 시작한다
    flowable.subscribe(new DebugSubscriber<>());
  }
}

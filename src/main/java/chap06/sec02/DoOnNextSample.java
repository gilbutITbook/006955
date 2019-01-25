package chap06.sec02;

import chap04.DebugSubscriber;
import io.reactivex.Flowable;

/** 예제 6-2 doOnNext(onNext) 예제 */
public class DoOnNextSample {
  
  public static void main(String[] args) throws Exception {
    
    // Flowable을 생성한다
    Flowable.range(1, 5)
        // 데이터 통지 시 로그를 출력한다
        .doOnNext(data -> System.out.println("--- 기존 데이터: " + data))
        // 짝수만 통지한다
        .filter(data -> data % 2 == 0)
        // 데이터 통지 시 로그를 출력한다
        .doOnNext(data -> System.out.println("------ filter 적용 후 데이터: " + data))
        // 구독한다
        .subscribe(new DebugSubscriber<>());
  }
}

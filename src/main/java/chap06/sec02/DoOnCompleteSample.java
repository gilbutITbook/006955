package chap06.sec02;

import chap04.DebugSubscriber;
import io.reactivex.Flowable;

/** 예제 6-4 doOnComplete(onComplete) 예제 */
public class DoOnCompleteSample {
  
  public static void main(String[] args) throws Exception {
    
    // Flowable을 생성한다
    Flowable.range(1, 5)
        // 완료 통지 시 로그를 출력한다
        .doOnComplete(() -> System.out.println("doOnComplete"))
        // 구독한다
        .subscribe(new DebugSubscriber<>());
  }
  
}

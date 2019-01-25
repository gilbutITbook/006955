package chap01.sec06;

import io.reactivex.Completable;
import io.reactivex.CompletableObserver;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/** 예제 1-32 Completable 예제 */
public class L32_CompletableSample {
  
  public static void main(String[] args) throws Exception {
    // Completable을 생성한다
    Completable completable = Completable.create(emitter -> {
      // …생략 (업무 로직 처리)
      
      // 완료를 통지한다
      emitter.onComplete();
    });
    
    completable
        // Completable을 비동기로 실행한다
        .subscribeOn(Schedulers.computation())
        // 구독한다
        .subscribe(new CompletableObserver() {
          
          /** 구독 준비가 되었을 때의 처리 */
          @Override
          public void onSubscribe(Disposable disposable) {
            // 아무것도 하지 않는다
          }
          
          /** 완료 통지를 받을 때의 처리 */
          @Override
          public void onComplete() {
            System.out.println("완료");
          }
          
          /** 에러 통지를 받을 때의 처리 */
          @Override
          public void onError(Throwable e) {
            System.out.println("에러=" + e);
          }
        });
    
    // 잠시 기다린다
    Thread.sleep(100L);
  }
  
}

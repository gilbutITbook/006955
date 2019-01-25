package chap03.sec02;

import io.reactivex.Flowable;
import io.reactivex.subscribers.ResourceSubscriber;

/** 예제 3-6 메인 스레드에서 처리 작업을 하는 Flowable 예제 */
public class L06_MainThreadSample {
  
  public static void main(String[] args) {
    System.out.println("start");
    
    Flowable.just(1, 2, 3)
        // 구독한다
        .subscribe(new ResourceSubscriber<Integer>() {
          
          @Override
          public void onNext(Integer data) {
            String threadName = Thread.currentThread().getName();
            System.out.println(threadName + ": " + data);
          }
          
          @Override
          public void onComplete() {
            String threadName = Thread.currentThread().getName();
            System.out.println(threadName + ": 완료");
          }
          
          @Override
          public void onError(Throwable error) {
            error.printStackTrace();
          }
        });
    
    System.out.println("end");
  }
  
}

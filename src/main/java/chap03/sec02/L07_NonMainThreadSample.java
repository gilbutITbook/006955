package chap03.sec02;

import java.util.concurrent.TimeUnit;

import io.reactivex.Flowable;
import io.reactivex.subscribers.ResourceSubscriber;

/** 3-7 메인 스레드가 아닌 스레드에서 처리 작업을 하는 Flowable 예제 */
public class L07_NonMainThreadSample {
  
  public static void main(String[] args) throws Exception {
    System.out.println("start");
    
    Flowable.interval(300L, TimeUnit.MILLISECONDS)
        // 구독한다
        .subscribe(new ResourceSubscriber<Long>() {
          
          @Override
          public void onNext(Long data) {
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
    
    // 잠시 기다린다
    Thread.sleep(1000L);
  }
  
}

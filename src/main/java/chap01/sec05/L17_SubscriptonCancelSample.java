package chap01.sec05;

import java.util.concurrent.TimeUnit;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import io.reactivex.Flowable;

/** 예제 1-17 구독을 중도 해지하는 예제 */
public class L17_SubscriptonCancelSample {
  
  public static void main(String[] args) throws Exception {
    
    // 200밀리초마다 값을 통지하는 Flowable
    Flowable.interval(200L, TimeUnit.MILLISECONDS)
        .subscribe(new Subscriber<Long>() {
          
          private Subscription subscription;
          private long startTime;
          
          @Override
          public void onSubscribe(Subscription subscription) {
            this.subscription = subscription;
            this.startTime = System.currentTimeMillis();
            this.subscription.request(Long.MAX_VALUE);
          }
          
          @Override
          public void onNext(Long data) {
            // 구독 시작부터 500밀리초가 지나면 구독을 해지하고 처리를 중지한다
            if ((System.currentTimeMillis() - startTime) > 500) {
              subscription.cancel(); // 구독을 해지한다
              System.out.println("구독 해지");
              return;
            }
            
            System.out.println("data=" + data);
          }
          
          @Override
          public void onComplete() {
            System.out.println("완료");
          }
          
          @Override
          public void onError(Throwable error) {
            error.printStackTrace();
          }
        });
    
    // 잠시 기다린다
    Thread.sleep(2000L);
  }
  
}

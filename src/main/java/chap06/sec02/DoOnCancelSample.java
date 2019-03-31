package chap06.sec02;

import java.util.concurrent.TimeUnit;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import io.reactivex.Flowable;

/** 예제 6-12 doOnCancel(onCancel) 예제 */
public class DoOnCancelSample {
  
  public static void main(String[] args) throws Exception {
    
    // Flowable을 생성한다
    Flowable.interval(100L, TimeUnit.MILLISECONDS)
        // 구독 해지 시 로그를 출력한다 
        .doOnCancel(() -> System.out.println("doOnCancel"))
        // 구독한다
        .subscribe(new Subscriber<Long>() {
          
          private long startTime;
          private Subscription subscription;
          
          @Override
          public void onSubscribe(Subscription subscription) {
            this.startTime = System.currentTimeMillis();
            this.subscription = subscription;
            this.subscription.request(Long.MAX_VALUE);
          }
          
          @Override
          public void onNext(Long data) {
            // 구독 시작 시각으로부터 300밀리초가 지나면 구독을 해지한다
            if (System.currentTimeMillis() - startTime > 300L) {
              System.out.println("구독 해지");
              subscription.cancel();
              return;
            }
            
            System.out.println(data);
          }
          
          @Override
          public void onComplete() {
            System.out.println("완료");
          }
          
          @Override
          public void onError(Throwable error) {
            System.out.println("에러： " + error);
          }
          
        });
    
    // 잠시 기다린다
    Thread.sleep(1000L);
  }
  
}

package chap06.sec02;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import io.reactivex.Flowable;

/** 예제 6-8 doOnSubscribe(onSubscribe) 예제 */
public class DoOnSubscribeSample {
  
  public static void main(String[] args) throws Exception {
    
    // Flowable을 생성한다
    Flowable.range(1, 5)
        // 구독 시작 시 로그를 출력한다
        .doOnSubscribe(subscription -> System.out.println("doOnSubscribe"))
        // 구독한다
        .subscribe(new Subscriber<Integer>() {
          
          @Override
          public void onSubscribe(Subscription subscription) {
            System.out.println("--- Subscriber: onSubscribe");
            subscription.request(Long.MAX_VALUE);
          }
          
          @Override
          public void onNext(Integer data) {
            System.out.println("--- Subscriber: onNext: " + data);
          }
          
          @Override
          public void onComplete() {
            // 아무것도 하지 않는다
          }
          
          @Override
          public void onError(Throwable error) {
            // 아무것도 하지 않는다
          }
        });
  }
  
}

package chap01.sec06;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import io.reactivex.Flowable;

/** 예제 1-21 oonSubscribe 메서드 처리 도중에 다른 처리가 시작되는 예제 */
public class L21_ViolatedReactiveStreamsSample {
  
  public static void main(String[] args) {
    Flowable.range(1, 3)
        // 구독한다
        .subscribe(new Subscriber<Integer>() {
          @Override
          public void onSubscribe(Subscription subscription) {
            System.out.println("onSubscribe: start");
            subscription.request(Long.MAX_VALUE);
            System.out.println("onSubscribe: end");
          }
          
          @Override
          public void onNext(Integer data) {
            System.out.println(data);
          }
          
          @Override
          public void onComplete() {
            System.out.println("완료");
          }
          
          @Override
          public void onError(Throwable error) {
            System.out.println("에러=" + error);
          }
        });
  }
  
}

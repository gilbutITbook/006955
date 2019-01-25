package chap06.sec02;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import io.reactivex.Flowable;
import io.reactivex.schedulers.Schedulers;

/** 예제 6-10 doOnRequest(size) 예제 */
public class DoOnRequestSample {
  
  public static void main(String[] args) throws Exception {
    
    // Flowable을 생성한다
    Flowable.range(1, 3)
        // 데이터 개수를 요청 시 로그를 출력한다
        .doOnRequest(size -> System.out.println("기존 데이터: size=" + size))
        // Subscriber 처리를 다른 스레드에서 실행한다
        .observeOn(Schedulers.computation())
        // 데이터 개수를 요청 시 로그를 출력한다
        .doOnRequest(size -> System.out.println("--- observeOn 적용 후: size=" + size))
        // 구독한다
        .subscribe(new Subscriber<Integer>() {
          
          private Subscription subscription;
          
          @Override
          public void onSubscribe(Subscription subscription) {
            this.subscription = subscription;
            this.subscription.request(1);
          }
          
          @Override
          public void onNext(Integer data) {
            System.out.println(data);
            subscription.request(1);
          }
          
          @Override
          public void onComplete() {
            System.out.println("완료");
          }
          
          @Override
          public void onError(Throwable error) {
            System.out.println("에러: " + error);
          }
        });
    
    // 잠시 기다린다
    Thread.sleep(500L);
  }
}

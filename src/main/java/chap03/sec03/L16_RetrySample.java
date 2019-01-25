package chap03.sec03;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;

/** 예제 3-16 재시도하는 예제 */
public class L16_RetrySample {
  
  public static void main(String[] args) throws Exception {
    Flowable<Integer> flowable = Flowable.<Integer> create(emitter -> {
      // Flowable 처리를 시작한다
      System.out.println("Flowable 처리 시작");
      // 통지 처리
      for (int i = 1; i <= 3; i++) {
        // 데이터가 2일 때 에러가 발생하게 한다
        if (i == 2) {
          throw new Exception("예외 발생");
        }
        // 데이터를 통지한다
        emitter.onNext(i);
      }
      // 완료를 통지한다
      emitter.onComplete();
      // Flowable 처리를 완료한다
      System.out.println("Flowable 처리 완료");
    }, BackpressureStrategy.BUFFER)
        // doOnSubscribe를 실행한다
        .doOnSubscribe(
            subscription -> System.out.println("flowable: doOnSubscribe"))
        // 에러가 발생하면 두 번까지 재시도한다
        .retry(2);
    
    // 구독한다
    flowable.subscribe(new Subscriber<Integer>() {
      
      @Override
      public void onSubscribe(Subscription subscription) {
        System.out.println("subscriber: onSubscribe");
        subscription.request(Long.MAX_VALUE);
      }
      
      @Override
      public void onNext(Integer data) {
        System.out.println("data=" + data);
      }
      
      @Override
      public void onError(Throwable error) {
        System.out.println("에러=" + error);
      }
      
      @Override
      public void onComplete() {
        System.out.println("완료");
      }
    });
  }
}

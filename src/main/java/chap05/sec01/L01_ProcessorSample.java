package chap05.sec01;

import org.reactivestreams.Processor;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import io.reactivex.processors.PublishProcessor;

/** 예제 5-1 Processor가 데이터를 받아 통지하는 예제 */
public class L01_ProcessorSample {
  
  public static void main(String[] args) {
    // Processor를 생성한다
    Processor<Integer, Integer> processor = PublishProcessor.create();
    
    // Processor를 구독한다
    processor.subscribe(new Subscriber<Integer>() {
      
      @Override
      public void onSubscribe(Subscription subscription) {
        subscription.request(Long.MAX_VALUE);
      }
      
      @Override
      public void onNext(Integer data) {
        System.out.println(data);
      }
      
      @Override
      public void onError(Throwable error) {
        System.err.println("에러: " + error);
      }
      
      @Override
      public void onComplete() {
        System.out.println("완료");
      }
    });
    
    // Processor에 데이터를 전달한다
    processor.onNext(1);
    processor.onNext(2);
    processor.onNext(3);
  }
  
}

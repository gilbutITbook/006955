package chap05.sec05;

import chap04.DebugSubscriber;
import io.reactivex.processors.AsyncProcessor;

/** 예제 5-5 AsyncProcessor 예제*/
public class L05_AsyncProcessorSample {
  
  public static void main(String[] args) throws Exception {
    
    // Processor을 생성한다
    AsyncProcessor<Integer> processor = AsyncProcessor.create();
    
    // 통지 전에 Subscriber가 구독한다
    processor.subscribe(new DebugSubscriber<>("No.1"));
    
    // 데이터를 통지한다
    processor.onNext(1);
    processor.onNext(2);
    processor.onNext(3);
    
    // 다른 Subscriber가 구독한다
    System.out.println("Subscriber No.2 추가");
    processor.subscribe(new DebugSubscriber<>("--- No.2"));
    
    // 데이터를 통지한다
    processor.onNext(4);
    processor.onNext(5);
    
    // 완료를 통지한다
    processor.onComplete();
    
    // 완료 후에도 다른 Subscriber가 구독한다
    System.out.println("Subscriber No.3 추가");
    processor.subscribe(new DebugSubscriber<>("------ No.3"));
  }
}

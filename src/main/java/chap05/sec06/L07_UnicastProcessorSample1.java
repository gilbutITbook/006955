package chap05.sec06;

import chap04.DebugSubscriber;
import io.reactivex.processors.UnicastProcessor;

/** 예제 5-7 UnicastProcessor 예제 */
public class L07_UnicastProcessorSample1 {
  
  public static void main(String[] args) throws Exception {
    
    // Processor을 생성한다
    UnicastProcessor<Integer> processor = UnicastProcessor.create();
    
    // 데이터를 통지한다
    processor.onNext(1);
    processor.onNext(2);
    
    // 도중에 Subscriber가 구독한다
    System.out.println("Subscriber No.1 추가");
    processor.subscribe(new DebugSubscriber<>("No.1"));
    
    // 다른 Subscriber가 구독한다
    System.out.println("Subscriber No.2 추가");
    processor.subscribe(new DebugSubscriber<>("--- No.2"));
    
    // 데이터를 통지한다
    processor.onNext(3);
    
    // 완료를 통지한다
    processor.onComplete();
  }
}

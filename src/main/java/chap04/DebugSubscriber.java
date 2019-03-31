package chap04;

import io.reactivex.subscribers.DisposableSubscriber;

/** 예제 4-1 디버그용 Subscriber */
public class DebugSubscriber<T> extends DisposableSubscriber<T> {
  
  private String label;
  
  public DebugSubscriber() {
    super();
  }
  
  public DebugSubscriber(String label) {
    super();
    this.label = label;
  }
  
  @Override
  public void onNext(T data) {
    // onNext 메서드 호출 시 출력한다
    String threadName = Thread.currentThread().getName();
    if (label == null) {
      System.out.println(threadName + ": " + data);
    } else {
      System.out.println(threadName + ": " + label + ": " + data);
    }
  };
  
  @Override
  public void onError(Throwable throwable) {
    // onError 메서드 호출 시 출력한다
    String threadName = Thread.currentThread().getName();
    if (label == null) {
      System.out.println(threadName + ": 에러= " + throwable);
    } else {
      System.out.println(threadName + ": " + label + ": 에러= " + throwable);
    }
  }
  
  @Override
  public void onComplete() {
    // onComplete 메서드 호출 시 출력한다
    String threadName = Thread.currentThread().getName();
    if (label == null) {
      System.out.println(threadName + ": 완료");
    } else {
      System.out.println(threadName + ": " + label + ": 완료");
    }
  }
}

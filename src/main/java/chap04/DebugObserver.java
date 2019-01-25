package chap04;

import io.reactivex.observers.DisposableObserver;

/** 디버깅용 Observer */
public class DebugObserver<T> extends DisposableObserver<T> {
  
  private String label;
  
  public DebugObserver() {
    super();
  }
  
  public DebugObserver(String label) {
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

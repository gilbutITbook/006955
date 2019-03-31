package chap04;

import io.reactivex.observers.DisposableSingleObserver;

/** 디버깅용 SingleObserver */
public class DebugSingleObserver<T> extends DisposableSingleObserver<T> {
  
  private String label;
  
  public DebugSingleObserver() {
    super();
  }
  
  public DebugSingleObserver(String label) {
    super();
    this.label = label;
  }
  
  @Override
  public void onSuccess(T data) {
    String threadName = Thread.currentThread().getName();
    if (label == null) {
      System.out.println(threadName + ": " + data);
    } else {
      System.out.println(threadName + ": " + label + ": " + data);
    }
  };
  
  @Override
  public void onError(Throwable throwable) {
    String threadName = Thread.currentThread().getName();
    if (label == null) {
      System.out.println(threadName + ": 에러= " + throwable);
    } else {
      System.out.println(threadName + ": " + label + ": 에러= " + throwable);
    }
  }
}

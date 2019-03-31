package chap04;

import io.reactivex.observers.DisposableCompletableObserver;

/** 디버깅용 CompletableObserver */
public class DebugCompletableObserver<T> extends DisposableCompletableObserver {
  
  private String label;
  
  public DebugCompletableObserver() {
    super();
  }
  
  public DebugCompletableObserver(String label) {
    super();
    this.label = label;
  }
  
  @Override
  public void onError(Throwable throwable) {
    String threadName = Thread.currentThread().getName();
    if (label == null) {
      System.out.println(threadName + ": 에러= " + throwable);
    } else {
      System.out.println(threadName + ": " + label + ": 에러= " + throwable);
    }
  }
  
  @Override
  public void onComplete() {
    String threadName = Thread.currentThread().getName();
    if (label == null) {
      System.out.println(threadName + ": 완료");
    } else {
      System.out.println(threadName + ": " + label + ": 완료");
    }
  }
}

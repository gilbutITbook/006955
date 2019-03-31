package chap04;

import io.reactivex.observers.DisposableMaybeObserver;

/** 디버깅용 MaybeObserver */
public class DebugMaybeObserver<T> extends DisposableMaybeObserver<T> {
  
  private String label;
  
  public DebugMaybeObserver() {
    super();
  }
  
  public DebugMaybeObserver(String label) {
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
    String threadName = Thread.currentThread().getName();
    if (label == null) {
      System.out.println(threadName + ": 완료");
    } else {
      System.out.println(threadName + ": " + label + ": 완료");
    }
  }
}

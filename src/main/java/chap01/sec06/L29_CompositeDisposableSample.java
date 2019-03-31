package chap01.sec06;

import io.reactivex.Flowable;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

/** 예제 1-29 CompositeDisposable 예제 */
public class L29_CompositeDisposableSample {
  
  public static void main(String[] args) throws Exception {
    // Disposable을 합친다
    CompositeDisposable compositeDisposable = new CompositeDisposable();
    
    compositeDisposable.add(Flowable.range(1, 3)
        // 구독 해지 시 로그를 출력한다
        .doOnCancel(() -> System.out.println("No.1 canceled"))
        // 비동기로 실행한다
        .observeOn(Schedulers.computation())
        // 구독한다
        .subscribe(data -> {
          try {
            Thread.sleep(100L);
            System.out.println("No.1: " + data);
          } catch (InterruptedException e) {
            // dispose() 시에 예외가 발생하지만 이번 예제의 검증 대상이 아니므로 아무것도 하지 않는다
          }
        }));
    
    compositeDisposable.add(Flowable.range(1, 3)
        // 구독 해지 시 로그를 출력한다
        .doOnCancel(() -> System.out.println("No.2 canceled"))
        // 비동기로 실행한다
        .observeOn(Schedulers.computation())
        // 구독한다
        .subscribe(data -> {
          try {
            Thread.sleep(100L);
            System.out.println("No.2: " + data);
          } catch (InterruptedException e) {
            // dispose() 시에 예외가 발생하지만 이번 예제의 검증 대상이 아니므로 아무것도 하지 않는다
          }
        }));
    
    // 잠시 기다린다
    Thread.sleep(150L);
    
    // 한번에 구독을 해지한다
    compositeDisposable.dispose();
    
    // 잠시 기다린다
    Thread.sleep(150L);
  }
  
}

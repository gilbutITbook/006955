package chap03.sec02;

import io.reactivex.Flowable;
import io.reactivex.schedulers.Schedulers;

/** 예제 3-13 두 스레드에서 같은 객체의 변경 작업을 수행하는 예제 */
public class L13_CounterSample {
  
  public static void main(String[] args) throws Exception {
    // 숫자를 세는 객체
    final Counter counter = new Counter();
    
    // Counter의 increment 메서드를 10,000번 호출한다
    Flowable.range(1, 10000)
        // Flowable을 다른 스레드에서 처리하게 한다
        .subscribeOn(Schedulers.computation())
        // 다른 스레드에서 처리 작업을 하게 한다
        .observeOn(Schedulers.computation())
        // 구독한다
        .subscribe(
            // 데이터를 받을 때의 처리
            data -> counter.increment(),
            // 에러를 받을 때의 처리
            error -> System.out.println("에러=" + error),
            // 완료 통지를 받을 때의 처리
            () -> System.out.println("counter.get()=" + counter.get()));
    
    // 다른 스레드에서 동시에 실행한다
    Flowable.range(1, 10000)
        // Flowable을 다른 스레드에서 처리하게 한다
        .subscribeOn(Schedulers.computation())
        // 다른 스레드에서 처리 작업을 하게 한다
        .observeOn(Schedulers.computation())
        // 구독한다
        .subscribe(
            // 데이터를 받을 때의 처리
            data -> counter.increment(),
            // 데이터를 받을 때의 처리
            error -> System.out.println("에러=" + error),
            // 완료 통지를 받을 때의 처리
            () -> System.out.println("counter.get()=" + counter.get()));
    
    // 잠시 기다린다
    Thread.sleep(1000L);
  }
}

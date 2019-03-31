package chap03.sec02;

import io.reactivex.Flowable;
import io.reactivex.schedulers.Schedulers;

/** 예제 3-8 유효한 Scheduler 예제 */
public class L08_SubscribeOnSample {
  
  public static void main(String[] args) throws Exception {
    Flowable.just(1, 2, 3, 4, 5) // Flowable 설정
        .subscribeOn(Schedulers.computation()) // RxComputationThreadPool
        .subscribeOn(Schedulers.io()) // RxCachedThreadScheduler
        .subscribeOn(Schedulers.single()) // RxSingleScheduler
        .subscribe(data -> {
          String threadName = Thread.currentThread().getName();
          System.out.println(threadName + ": " + data);
        });
    
    // 잠시 기다린다
    Thread.sleep(500);
  }
  
}

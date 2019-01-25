package chap03.sec02;

import java.util.concurrent.TimeUnit;

import io.reactivex.Flowable;

/** 예제 3-4 데이터를 받는 측이 무거운 처리 작업을 하는 예제 */
public class L04_SyncSlowerSample {
  
  public static void main(String[] args) throws Exception {
    Flowable.interval(1000L, TimeUnit.MILLISECONDS)
        // 데이터를 통지할 때의 시스템 시각을 출력한다
        .doOnNext(data -> System.out
            .println("emit: " + System.currentTimeMillis() + "밀리초: " + data))
        // 구독한다
        .subscribe(data -> Thread.sleep(2000L)); // 무거운 처리 작업을 한다고 가정한다
    
    // 잠시 기다린다
    Thread.sleep(5000L);
  }
  
}

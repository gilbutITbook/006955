package chap03.sec02;

import java.util.concurrent.TimeUnit;

import io.reactivex.Flowable;

/** 예제 3-5 interval 메서드로 생성한 Flowable 예제 */
public class L05_SyncFasterSample {
  
  public static void main(String[] args) throws Exception {
    Flowable.interval(1000L, TimeUnit.MILLISECONDS)
        // デ데이터를 통지할 때의 시스템 시각을 출력한다
        .doOnNext(data -> System.out
            .println("emit: " + System.currentTimeMillis() + "밀리초: " + data))
        // 구독한다
        .subscribe(data -> Thread.sleep(500L)); // 무거운 처리 작업을 한다고 가정한다
    
    // 잠시 기다린다
    Thread.sleep(3000L);
  }
  
}

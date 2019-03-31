package chap04.sec07;

import java.util.concurrent.TimeUnit;

import chap04.DebugSubscriber;
import io.reactivex.Flowable;

/** 예제 4-97 repeatUntil(stop)의 예제 */
public class RepeatUntilSample {
  
  public static void main(String[] args) throws Exception {
    // 처리 시작 시간
    final long startTime = System.currentTimeMillis();
    
    Flowable<Long> flowable =
        // Flowable을 생성한다
        Flowable.interval(100L, TimeUnit.MILLISECONDS)
            // 3건까지 통지한다
            .take(3)
            // 통지를 반복한다
            .repeatUntil(() -> {
              // 호출 시점에 출력한다
              System.out.println("called");
              // 처리를 시작하고 500밀리초가 될 때까지 반복한다
              return System.currentTimeMillis() - startTime > 500L;
            });
    
    // 구독한다
    flowable.subscribe(new DebugSubscriber<>());
    
    // 잠시 기다린다
    Thread.sleep(1000L);
  }
}

package chap04.sec02;

import java.util.concurrent.TimeUnit;

import chap04.DebugSubscriber;
import io.reactivex.Flowable;

/** 예제 4-28 concatMapEager(mapper) 예제 */
public class ConcatMapEagerSample {
  
  public static void main(String[] args) throws Exception {
    Flowable<String> flowable = Flowable.range(10, 3)
        // 받은 데이터로 Flowable을 생성하고 바로 실행하지만 통지는 순서대로 한다
        .concatMapEager(
            // 데이터를 통지할 Flowable을 생성한다
            sourceData -> Flowable.interval(500L, TimeUnit.MILLISECONDS)
                // 2건까지 통지한다
                .take(2)
                // 원본 통지 데이터와 이 Flowable의 데이터를 조합해 문자열을 만든다
                .map(data -> {
                  // 통지 시 시스템 시각도 데이터에 추가한다
                  long time = System.currentTimeMillis();
                  return time + "ms: [" + sourceData + "] " + data;
                }));
    // 구독한다
    flowable.subscribe(new DebugSubscriber<>());
    // 잠시 기다린다
    Thread.sleep(4000L);
  }
  
}

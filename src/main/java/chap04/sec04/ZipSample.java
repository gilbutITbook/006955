package chap04.sec04;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

import chap04.DebugSubscriber;
import io.reactivex.Flowable;

/** 예제 4-81 zip(source1, source2, sipper) 예제 */
public class ZipSample {
  
  public static void main(String[] args) throws Exception {
    // 결합 대상
    Flowable<Long> flowable1 =
        // 300밀리초마다 데이터를 통지한다
        Flowable.interval(300L, TimeUnit.MILLISECONDS)
            // 5건까지 통지한다
            .take(5);
    
    // 결합 대상
    Flowable<Long> flowable2 =
        // 500밀리초마다 데이터를 통지한다
        Flowable.interval(500L, TimeUnit.MILLISECONDS)
            // 3건까지 통지한다
            .take(3)
            // 100을 더한다
            .map(data -> data + 100L);
    
    // 여러 개의 Flowable에서 받은 데이터로 새로운 데이터를 생성한다
    Flowable<List<Long>> result = Flowable.zip(
        // 결합하는 Flowable
        flowable1,
        // 결합하는 Flowable
        flowable2,
        // 인자에서 통지한 데이터를 리스트에 저장하고 통지한다
        (data1, data2) -> Arrays.asList(data1, data2));
    
    // 구독한다
    result.subscribe(new DebugSubscriber<>());
    
    // 잠시 기다린다
    Thread.sleep(2000L);
  }
  
}

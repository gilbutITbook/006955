package chap06.sec05;

import java.util.concurrent.TimeUnit;

import org.junit.Test;

import io.reactivex.Flowable;
import io.reactivex.schedulers.TestScheduler;
import io.reactivex.subscribers.TestSubscriber;

public class TestSchedulerTest {
  
  /** 예제 6-20 TestScheduler를 사용한 예제 */
  @Test
  public void TestScheduler실행() {
    // 테스트 시작 시간
    long start = System.currentTimeMillis();
    
    // 테스트용 Scheduler
    TestScheduler testScheduler = new TestScheduler();
    
    // 테스트 대상 Flowable
    Flowable<Long> flowable =
        Flowable.interval(500L, TimeUnit.MILLISECONDS, testScheduler);
    
    // 구독을 시작한다
    TestSubscriber<Long> result = flowable.test();
    
    // Scheduler가 진행되지 않으므로 아무 것도 출력되지 않는다
    System.out.println("data=" + result.values());
    result.assertEmpty();
    
    // 500밀리초만 진행한다
    testScheduler.advanceTimeBy(500L, TimeUnit.MILLISECONDS);
    
    // onNext이벤트에서 받은 데이터의 리스트
    System.out.println("data=" + result.values());
    result.assertValues(0L);
    
    // 추가로 500밀리초 진행한다
    testScheduler.advanceTimeBy(500L, TimeUnit.MILLISECONDS);
    
    // onNext 이벤트에서 받은 데이터의 리스트
    System.out.println("data=" + result.values());
    result.assertValues(0L, 1L);
    
    // 2000밀리초까지 진행한다
    testScheduler.advanceTimeTo(2000L, TimeUnit.MILLISECONDS);
    
    // onNext 이벤트에서 받은 데이터의 리스트
    System.out.println("data=" + result.values());
    result.assertValues(0L, 1L, 2L, 3L);
    
    // 현재 시각
    System.out.println(
        "testScheduler.now()=" + testScheduler.now(TimeUnit.MILLISECONDS));
    
    // 테스트에 걸린 시간
    long totalTime = System.currentTimeMillis() - start;
    System.out.println("테스트에 걸린 시간 =" + totalTime);
  }
}

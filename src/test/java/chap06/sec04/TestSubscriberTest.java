package chap06.sec04;

import java.util.concurrent.TimeUnit;

import org.junit.Test;

import io.reactivex.Flowable;
import io.reactivex.subscribers.TestSubscriber;

/** 예제 6-18,6-19 TestSubscriber 사용의 간단한 예제 */
public class TestSubscriberTest {
  
  /** 예제 6-18 TestSubscriber 사용의 간단한 예제 */
  @Test
  public void TestSubscriber사용의간단한예제() throws Exception {
    // 대상 Flowable
    Flowable<Long> target = Flowable.interval(100L, TimeUnit.MILLISECONDS);
    
    // 테스트 Subscriber 생성하고 Flowable의 처리를 시작한다
    TestSubscriber<Long> testSubscriber = target.test();
    
    // 아직 데이터가 통지되지 않았는지 확인한다
    testSubscriber.assertEmpty();
    
    // 지정된 시간 동안 대기시킨다
    // ※ 비동기 처리로 인해 100밀리초로 통지되지 않을 수 있다
    testSubscriber.await(150L, TimeUnit.MILLISECONDS);
    
    // 지금까지 통지된 데이터를 확인한다
    testSubscriber.assertValues(0L);
    
    // 지정한 시간 동안 대기하게 한다
    testSubscriber.await(100L, TimeUnit.MILLISECONDS);
    
    // 지금까지 통지된 데이터를 확인한다
    testSubscriber.assertValues(0L, 1L);
  }
  
  /** 예제 6-19 assert 메서드를 사용하는 예제*/
  @Test
  public void 빈Flowable테스트() {
    Flowable.empty()
        // TestSubscriber를 생성한다
        .test()
        // 데이터를 받지 않으면 성공
        .assertNoValues()
        // 에러가 없으면 성공
        .assertNoErrors()
        // 완료했으면 성공
        .assertComplete();
  }
}

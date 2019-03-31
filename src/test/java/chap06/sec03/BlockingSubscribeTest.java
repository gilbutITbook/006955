package chap06.sec03;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.fail;

import java.util.concurrent.TimeUnit;

import org.junit.Test;

import io.reactivex.Flowable;
import io.reactivex.subscribers.DisposableSubscriber;

/** 예제 6-17 blockingSubscribe(subscriber) 예제 */
public class BlockingSubscribeTest {
  
  @Test
  public void Flowable을실행하고처리결과확인() {
    Flowable<Long> flowable =
        // Flowable을 생성한다
        Flowable.interval(100L, TimeUnit.MILLISECONDS)
            // 5건까지 통지한다
            .take(5);
    
    // 계산 객체
    Counter counter = new Counter();
    
    // 부가 작용이 발생하는 처리를 실행한다
    flowable
        // 현재의 스레드로 구독한다
        .blockingSubscribe(new DisposableSubscriber<Long>() {
          
          @Override
          public void onNext(Long data) {
            counter.increment();
          }
          
          @Override
          public void onError(Throwable error) {
            fail(error.getMessage());
          }
          
          @Override
          public void onComplete() {
            // 아무것도 하지 않는다
          }
        });
    
    // Counter 값을 확인한다
    assertThat(counter.get(), is(5));
  }
  
  /** 순차로 값을 증가시키는 클래스 */
  private static class Counter {
    private volatile int count;
    
    void increment() {
      count++;
    }
    
    int get() {
      return count;
    }
  }
}

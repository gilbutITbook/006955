package chap06.sec03;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import java.util.concurrent.TimeUnit;

import org.junit.Test;

import io.reactivex.Flowable;

/** 예제 6-13 blockingFirst() 테스트 */
public class BlockingFirstTest {
  
  @Test
  public void 첫번째통지데이터얻기() {
    // 받아온 결과
    long actual =
        // Flowable을 생성한다
        Flowable.interval(2000L, TimeUnit.MILLISECONDS)
            // 처음에 통지된 데이터를 메인 스레드에서 얻는다
            .blockingFirst();
    
    // 확인한다
    assertThat(actual, is(0L));
  }
}

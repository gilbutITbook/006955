package chap06.sec03;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

import org.junit.Test;

import io.reactivex.Flowable;
import io.reactivex.exceptions.MissingBackpressureException;

/** 예제 6-15 blockingIterable() 테스트 */
public class BlockingIterableTest {
  
  @Test
  public void 통지데이터를얻는Iterable가져오기() throws Exception {
    // 받아온 결과
    Iterable<Long> result =
        // Flowable을 생성한다
        Flowable.interval(100L, TimeUnit.MILLISECONDS)
            // 5건까지 통지한다
            .take(5)
            // Iterable로 변환해 메인 스레드에서 얻는다
            .blockingIterable();
    
    // Iterator를 가져온다
    Iterator<Long> iterator = result.iterator();
    
    // 데이터 유무를 확인한다
    assertTrue(iterator.hasNext());
    
    // 데이터를 확인한다
    assertThat(iterator.next(), is(0L));
    assertThat(iterator.next(), is(1L));
    assertThat(iterator.next(), is(2L));
    
    // 잠시 기다린다 ※버퍼의 크기를 초과하지 않는 수준
    Thread.sleep(1000L);
    
    // 데이터를 확인한다
    assertThat(iterator.next(), is(3L));
    assertThat(iterator.next(), is(4L));
    
    // 데이터 유무를 확인한다
    assertFalse(iterator.hasNext());
  }
  
  @Test(expected = NoSuchElementException.class)
  public void 완료후에데이터를얻고예외를발생하게하기() throws Exception {
    // 받아온 결과
    Iterable<Long> result =
        // Flowable을 생성한다
        Flowable.interval(300L, TimeUnit.MILLISECONDS)
            // 3건까지 통지한다
            .take(3)
            // Iterable로 변환해 메인 스레드에서 얻는다
            .blockingIterable();
    
    // Iterator를 가져온다
    Iterator<Long> iterator = result.iterator();
    assertThat(iterator.next(), is(0L));
    assertThat(iterator.next(), is(1L));
    assertThat(iterator.next(), is(2L));
    
    // 완료 후에 데이터를 얻는다
    iterator.next();
  }
  
  @Test(expected = MissingBackpressureException.class)
  public void 버퍼사이즈를지정해Iterable를얻기() throws Exception {
    // 받아온 결과
    Iterable<Long> result =
        // Flowable을 생성한다
        Flowable.interval(300L, TimeUnit.MILLISECONDS)
            // Iterable로 변환해 메인 스레드에서 얻는다
            .blockingIterable(1);
    
    // Iterator를 가져온다
    Iterator<Long> iterator = result.iterator();
    
    // 데이터를 확인한다
    assertThat(iterator.next(), is(0L));
    assertThat(iterator.next(), is(1L));
    assertThat(iterator.next(), is(2L));
    
    // 잠시 기다린다
    Thread.sleep(1000L);
    
    // 다시 호출하면 예외가 발생한다
    iterator.next();
    
    // 실패
    fail();
  }
  
  @Test
  public void 데이터가없을때Iterable로변환하기() {
    // 받아온 결과
    Iterable<Long> result =
        // Flowable을 생성한다
        Flowable.<Long> empty()
            // Iterable로 변환해 메인 스레드에서 얻는다
            .blockingIterable();
    
    // Iterator를 가져온다
    Iterator<Long> iterator = result.iterator();
    
    // 확인한다
    assertFalse(iterator.hasNext());
  }
  
  @Test(expected = MissingBackpressureException.class)
  public void 버퍼크기를초과할때통지하기() throws Exception {
    // 받아온 결과
    Iterable<Long> result =
        // Flowable을 생성한다
        Flowable.interval(1L, TimeUnit.MICROSECONDS)
            // Iterable로 변환해 메인 스레드에서 얻는다
            .blockingIterable();
    
    // Iterator를 가져온다
    Iterator<Long> iterator = result.iterator();
    
    // 잠시 기다리고 데이터를 얻는다
    Thread.sleep(1000L);
    System.out.println("data=" + iterator.next());
    
    // 실패
    fail();
  }
}

package chap02.sec02;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.atomic.AtomicInteger;

/** 예제 2-12 AtomicCounter를 사용한 예제 */
public class L12_AtomicCounterSample {
  
  public static void main(String[] args) throws Exception {
    // 순차로 값을 증가시키는 AtomicCount 객체
    final AtomicCounter counter = new AtomicCounter();
    
    // 10,000번 계산하는 비동기 처리 작업
    Runnable task = () -> {
      for (int i = 0; i < 10000; i++) {
        counter.increment();
      }
    };
    
    // 비동기 처리 작업 생성을 준비한다
    ExecutorService executorService = Executors.newCachedThreadPool();
    
    // 새로운 스레드로 시작한다
    Future<Boolean> future1 = executorService.submit(task, true);
    // 새로운 스레드로 시작한다
    Future<Boolean> future2 = executorService.submit(task, true);
    
    // 결과가 반환될 때까지 기다린다
    if (future1.get() && future2.get()) {
      // 결과를 출력한다
      System.out.println(counter.get());
    } else {
      System.err.println("실패");
    }
    
    // 비동기 처리 작업을 종료한다
    executorService.shutdown();
  }
  
  /** 스레드 세이프(thread safe)하게 순차로 값을 증가시키는 클래스 */
  private static class AtomicCounter {
    
    private final AtomicInteger count = new AtomicInteger(0);
    
    void increment() {
      count.incrementAndGet();
    }
    
    int get() {
      return count.get();
    }
    
  }
  
}

package chap02.sec02;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/** 예제 2-6 2개의 스레드로 접근해 처리하는 예제 */
public class L06_CounterSample {
  
  public static void main(String[] args) throws Exception {
    // 순차으로 값을 증가시키는 Count 객체
    final Counter counter = new Counter();
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
      // 계산 결과 출력한다
      System.out.println(counter.get());
    } else {
      System.err.println("실패");
    }
    
    // 비동기 처리 작업 종료한다
    executorService.shutdown();
  }
  
  /** 스레드 세이프(thread safe)하지 않은 순차로 값을 증가시키는 클래스 */
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

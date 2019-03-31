package chap01.sec03;

import io.reactivex.Flowable;

/** 예제 1-8 값을 전달 받을 때 / 예제 1-9 함수형 인터페이스를 전달 받을 때 */
public class L08_L09_FunctionalTimingSample {
  
  public static void main(String[] args) throws Exception {
    // 값을 전달 받을 경우
    Flowable<Long> flowable1 = Flowable.just(System.currentTimeMillis());
    // 함수형 인터페이스를 전달 받을 경우
    Flowable<Long> flowable2 =
        Flowable.fromCallable(() -> System.currentTimeMillis());
    
    flowable1.subscribe(data -> System.out.println("flowable1: " + data));
    flowable2.subscribe(data -> System.out.println("flowable2: " + data));
    
    // 잠시 기다린다
    Thread.sleep(1000L);
    
    // 다시 실행한다
    flowable1.subscribe(data -> System.out.println("flowable1: " + data));
    flowable2.subscribe(data -> System.out.println("flowable2: " + data));
  }
  
}

package chap03.sec02;

import java.util.concurrent.TimeUnit;

import io.reactivex.Flowable;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subscribers.ResourceSubscriber;

/** 예제 3-9 observeOn 메서드로 bufferSize를 지정하는 예제（bufferSize=1） */
public class L09_ObserveOnSample1 {
  public static void main(String[] args) throws Exception {
    Flowable<Long> flowable =
        // 300밀리초마다 0부터 시작하는 데이터를 통지하는 Flowable을 생성한다  
        Flowable.interval(300L, TimeUnit.MILLISECONDS)
            // BackpressureMode.DROP을 설정했을 때와 마찬가지로 동작한다 
            .onBackpressureDrop();
    flowable
        // 비동기로 데이터를 받게 하고, 버퍼 크기를 1로 설정한다 
        .observeOn(Schedulers.computation(), false, 1)
        // 구독한다
        .subscribe(new ResourceSubscriber<Long>() {
          // 데이터를 받을 때의 처리
          @Override
          public void onNext(Long item) {
            // 무거운 처리 작업을 한다고 가정하고 1000밀리초를 기다린다 
            try {
              Thread.sleep(1000L);
            } catch (InterruptedException e) {
              e.printStackTrace();
              // 에러가 발생해 종료한다
              System.exit(1);
            }
            // 실행 중인 스레드 이름을 얻는다
            String threadName = Thread.currentThread().getName();
            // 받은 데이터를 출력한다
            System.out.println(threadName + ": " + item);
          }
          
          @Override
          public void onComplete() {
            System.out.println("완료");
          }
          
          @Override
          public void onError(Throwable error) {
            System.out.println("에러=" + error);
          }
        });
    
    // 잠시 기다린다
    Thread.sleep(7000L);
  }
}

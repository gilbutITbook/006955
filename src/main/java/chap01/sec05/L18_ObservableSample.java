package chap01.sec05;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/** 예제 1-18 Observable로 구현한 예제 */
public class L18_ObservableSample {
  public static void main(String[] args) throws Exception {
    // 인사말을 통지하는 Observable을 생성한다
    Observable<String> observable =
        Observable.create(new ObservableOnSubscribe<String>() {
          @Override
          public void subscribe(ObservableEmitter<String> emitter)
              throws Exception {
            
            // 통지 데이터
            String[] datas = { "Hello, World!", "안녕, RxJava!" };
            for (String data : datas) {
              // 구독이 해지되면 처리를 중단한다
              if (emitter.isDisposed()) {
                return;
              }
              // 데이터를 통지한다
              emitter.onNext(data);
            }
            // 완료를 통지한다
            emitter.onComplete();
          }
        });
    observable
        // 소비하는 측의 처리를 개별 스레드로 실행한다
        .observeOn(Schedulers.computation())
        // 구독한다
        .subscribe(new Observer<String>() {
          // subscribe 메서드 호출 시의 처리
          @Override
          public void onSubscribe(Disposable disposable) {
            // 아무것도 하지 않는다
          }
          
          // 데이터를 받은 때의 처리
          @Override
          public void onNext(String item) {
            // 실행 중인 Thread 이름을 얻는다
            String threadName = Thread.currentThread().getName();
            // 받은 데이터 출력한다
            System.out.println(threadName + ": " + item);
          }
          
          // 완료 통지 시의 처리
          @Override
          public void onComplete() {
            // 실행 중인 Thread 이름을 얻는다
            String threadName = Thread.currentThread().getName();
            System.out.println(threadName + ": 완료");
          }
          
          // 에러 통지 시의 처리
          @Override
          public void onError(Throwable error) {
            error.printStackTrace();
          }
        });
    // 잠시 기다린다
    Thread.sleep(500L);
  }
}

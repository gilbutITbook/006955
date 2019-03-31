package chap01.sec06;

import java.time.DayOfWeek;
import java.time.LocalDate;

import io.reactivex.Single;
import io.reactivex.SingleObserver;
import io.reactivex.disposables.Disposable;

/** 예제 1-30 Single 예제 */
public class L30_SingleSample {
  
  public static void main(String[] args) {
    // Single을 생성한다
    Single<DayOfWeek> single = Single.create(emitter -> {
      emitter.onSuccess(LocalDate.now().getDayOfWeek());
    });
    
    // 구독한다
    single.subscribe(new SingleObserver<DayOfWeek>() {
      
      /** 구독 준비가 되었을 때의 처리 */
      @Override
      public void onSubscribe(Disposable disposable) {
        // 아무것도 하지 않는다
      }
      
      /** 데이터 통지를 받은 때의 처리 */
      @Override
      public void onSuccess(DayOfWeek value) {
        System.out.println(value);
      }
      
      /** 에러 통지를 받은 때의 처리 */
      @Override
      public void onError(Throwable e) {
        System.out.println("에러=" + e);
      }
    });
  }
  
}

package chap01.sec06;

import java.time.DayOfWeek;
import java.time.LocalDate;

import io.reactivex.Maybe;
import io.reactivex.MaybeObserver;
import io.reactivex.disposables.Disposable;

/** 예제 1-31 Maybe 예제 */
public class L31_MaybeSample {
  
  public static void main(String[] args) {
    // Maybe를 생성한다
    Maybe<DayOfWeek> maybe = Maybe.create(emitter -> {
      emitter.onSuccess(LocalDate.now().getDayOfWeek());
    });
    
    // 구독한다
    maybe.subscribe(new MaybeObserver<DayOfWeek>() {
      
      /** 구독 준비가 됐을 때의 처리 */
      @Override
      public void onSubscribe(Disposable disposable) {
        // 아무것도 하지 않는다
      }
      
      /** 데이터 통지를 받을 때의 처리 */
      @Override
      public void onSuccess(DayOfWeek value) {
        System.out.println(value);
      }
      
      /** 완료 통지를 받을 떄의 처리 */
      @Override
      public void onComplete() {
        System.out.println("완료");
      }
      
      /** 에러 통지를 받을 때의 처리 */
      @Override
      public void onError(Throwable e) {
        System.out.println("에러=" + e);
      }
    });
  }
  
}

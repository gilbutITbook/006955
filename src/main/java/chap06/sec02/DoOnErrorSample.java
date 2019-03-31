package chap06.sec02;

import chap04.DebugSubscriber;
import io.reactivex.Flowable;

/** 예제 6-6 doOnError(onError) 예제 */
public class DoOnErrorSample {
  
  public static void main(String[] args) throws Exception {
    
    // Flowable을 생성한다
    Flowable.range(1, 5)
        // 에러 통지 시 로그를 출력한다
        .doOnError(error -> System.out.println("기존 데이터: " + error.getMessage()))
        // 데이터가 '3'일 때 에러가 발생한다
        .map(data -> {
          if (data == 3) {
            throw new Exception("예외 발생");
          }
          return data;
        })
        // 에러 통지 시 로그를 출력한다
        .doOnError(
            error -> System.out.println("--- map 적용 후: " + error.getMessage()))
        // 구독한다
        .subscribe(new DebugSubscriber<>());
  }
}

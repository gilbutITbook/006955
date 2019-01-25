package chap04.sec02;

import chap04.DebugSubscriber;
import io.reactivex.Flowable;

/** 예제 4 24 flatMap(onNextMapper, onErrorMapper, onCompleteSupplier) 예제 */
public class FlatMapSample3 {
  
  public static void main(String[] args) throws Exception {
    // 에러가 발생할 Flowable
    Flowable<Integer> original = Flowable.just(1, 2, 0, 4, 5)
        // 0이 들어오면 예외가 발생한다
        .map(data -> 10 / data);
    
    // 일반 통지 시, 에러 발생 시, 완료 시 각각 설정한 데이터로 변환한다
    Flowable<Integer> flowable = original.flatMap(
        // 일반 통지 시 데이터
        data -> Flowable.just(data),
        // 에러 발생 시 데이터
        error -> Flowable.just(-1),
        // 완료 시 데이터
        () -> Flowable.just(100));
    
    // 구독을 시작한다
    flowable.subscribe(new DebugSubscriber<>());
  }
}

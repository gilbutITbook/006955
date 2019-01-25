package chap01.sec03;

import io.reactivex.Flowable;

/** 예제 1-7 메서드 체인 예제 */
public class L07_MethodChainSample {
  
  public static void main(String[] args) {
    Flowable<Integer> flowable =
        // 인자의 데이터를 순서대로 통지하는 Flowable을 생성한다
        Flowable.just(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
            // 짝수에 해당하는 데어터만 통지한다 
            .filter(data -> data % 2 == 0)
            // 데이터를 100배로 변환한다
            .map(data -> data * 100);
    
    // 구독하고 받은 데이터를 출력한다
    flowable.subscribe(data -> System.out.println("data=" + data));
  }
  
}

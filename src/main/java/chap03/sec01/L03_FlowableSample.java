package chap03.sec01;

import java.util.Arrays;
import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.functions.Consumer;

/** 예제 3-3 Flowable을 사용하는 예제 */
public class L03_FlowableSample {
  
  public static void main(String[] args) {
    // 리스트로 Flowable을 생성한다
    List<String> list = Arrays.asList("a", "b", "c");
    Flowable<String> flowable = Flowable.fromIterable(list);
    
    // 처리를 시작한다
    flowable.subscribe(new Consumer<String>() {
      
      @Override
      public void accept(String value) throws Exception {
        // 통지된 데이터를 출력한다
        System.out.println(value);
      }
    });
  }
  
}

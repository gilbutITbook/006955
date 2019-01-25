package chap01.sec01;

import io.reactivex.Flowable;

/** 예제 1-1 "Hello"와 "World"를 출력하는 예제 */
public class L01_HelloWorldSample {
  
  public static void main(String[] args) {
    // 데이터를 통지하는 생산자를 생성한다
    Flowable<String> flowable = Flowable.just("Hello", "World");
    // 통지받은 데이터를 출력한다
    flowable.subscribe(data -> System.out.println(data));
  }
  
}

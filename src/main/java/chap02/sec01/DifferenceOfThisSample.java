package chap02.sec01;

import io.reactivex.functions.Action;

/** 예제 2-4 this 출력 예제(람다식과 익명 클래스의 this 비교) */
public class DifferenceOfThisSample {
  
  public static void main(String[] args) throws Exception {
    DifferenceOfThisSample target = new DifferenceOfThisSample();
    target.execute();
  }
  
  /** 익명 클래스와 람다식의 this 출력 */
  public void execute() throws Exception {
    // 익명 클래스
    Action anonymous = new Action() {
      
      @Override
      public void run() {
        System.out.println("익명 클래스의 this: " + this);
      }
    };
    
    // 람다식
    Action lambda = () -> System.out.println("람다식의 this: " + this);
    
    // 각각 실행한다
    anonymous.run();
    lambda.run();
  }
  
  @Override
  public String toString() {
    return this.getClass().getSimpleName();
  }
}

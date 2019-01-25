package chap02;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

/** 예제 final로 선언해도 상태 변경이 가능한 예제 */
public class L08_ReferenceTypeTest {
  
  @Test
  public void finalを付けても状態が変更可能であることの確認() {
    // final로 선언한 레퍼런스 타입 변수
    final ReferenceTypeObject instance = new ReferenceTypeObject();
    
    // 레퍼런스 대상을 변경하면 컴파일 에러가 발생한다
    // instance = new ReferenceTypeObject();
    
    // 변경 전 객체 상태를 확인
    assertThat(instance.getValue(), is("A"));
    
    // instance 상태 변경 가능
    instance.setValue("B");
    
    // 상태를 변경한 객체 확인
    assertThat(instance.getValue(), is("B"));
  }
  
  /** 레퍼런스(reference) 타입 객체 */
  private static class ReferenceTypeObject {
    private String value = "A";
    
    public void setValue(String value) {
      this.value = value;
    }
    
    public String getValue() {
      return value;
    }
  }
  
}

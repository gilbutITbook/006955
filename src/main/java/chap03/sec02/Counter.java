package chap03.sec02;

/** 예제 순차로 값을 증가시키는 클래스 */
class Counter {
  private volatile int count;
  
  void increment() {
    count++;
  }
  
  int get() {
    return count;
  }
}

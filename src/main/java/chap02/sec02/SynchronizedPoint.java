package chap02.sec02;

/** 예제 2-14 synchronized 블록을 사용하게 변경한 Point 클래스 */
class SynchronizedPoint {
  
  private final Object lock = new Object();
  
  private int x;
  
  private int y;
  
  void rightUp() {
    synchronized (lock) {
      x++;
      y++;
    }
  }
  
  int getX() {
    synchronized (lock) {
      return x;
    }
  }
  
  int getY() {
    synchronized (lock) {
      return y;
    }
  }
}

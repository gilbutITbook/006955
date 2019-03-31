package chap02.sec02;

/** 예제 2-15 synchronized 메서드로 변환한 예제 */
class SynchronizedPoint2 {
  
  private int x;
  
  private int y;
  
  synchronized void rightUp() {
    x++;
    y++;
  }
  
  synchronized int getX() {
    return x;
  }
  
  synchronized int getY() {
    return y;
  }
}

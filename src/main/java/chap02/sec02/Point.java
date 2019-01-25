package chap02.sec02;

import java.util.concurrent.atomic.AtomicInteger;

/** 예제 2-13 동기화되지 않은 경우 */
class Point {
  
  private final AtomicInteger x = new AtomicInteger(0);
  
  private final AtomicInteger y = new AtomicInteger(0);
  
  void rightUp() {
    x.incrementAndGet();
    y.incrementAndGet();
  }
  
  int getX() {
    return x.get();
  }
  
  int getY() {
    return y.get();
  }
}

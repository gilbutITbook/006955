package chap04.sec01;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.TimeUnit;

import io.reactivex.Flowable;

/** 예제 4-8 timer(time, unit) 예제 */
public class TimerSample<T> {
  
  public static void main(String[] args) throws Exception {
    // "분:초.밀리초" 문자열로 변환하는 Formatter
    final DateTimeFormatter formatter =
        DateTimeFormatter.ofPattern("mm:ss.SSS");
    
    // 처리 시작 시각을 출력한다
    System.out.println("시작 시각: " + LocalTime.now().format(formatter));
    
    // 1000밀리초 뒤에 숫자 '0'을 통지하는 Flowable을 생성한다
    Flowable<Long> flowable = Flowable.timer(1000L, TimeUnit.MILLISECONDS);
    
    // 구독을 시작한다
    flowable.subscribe(
        // 첫 번째 인자: 데이터 통지 시
        data -> {
          // 스레드 이름을 얻는다
          String threadName = Thread.currentThread().getName();
          // 현재 시각을 "분:초.밀리초" 형태로 얻는다
          String time = LocalTime.now().format(formatter);
          // 출력한다
          System.out.println(threadName + ": " + time + ": data=" + data);
        },
        // 두 번째 인자: 에러 통지 시
        error -> System.out.println("에러=" + error),
        // 세 번째 인자: 완료 통지 시
        () -> System.out.println("완료"));
    
    // 잠시 기다린다
    Thread.sleep(1500L);
  }
  
}

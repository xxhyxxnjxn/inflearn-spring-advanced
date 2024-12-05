package hello.advanced.trace.templatecallback.code;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TimeLogTemplate {

    public void execute(CallBack callBack){ // 변하지 않는 부분

        long startTime = System.currentTimeMillis();
        //비즈니스 로직 실행
        callBack.call(); //바뀌는 부분 -> 콜백 (전략 패턴 적용)
        //비즈니스 로직 종료
        long endTime = System.currentTimeMillis();
        long result = endTime - startTime;
        log.info("resultTime = {}", result);
    }
}

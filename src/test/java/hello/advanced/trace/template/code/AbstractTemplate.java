package hello.advanced.trace.template.code;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public abstract class AbstractTemplate {

    public void execute(){ // 변하지 않는 부분

        long startTime = System.currentTimeMillis();
        //비즈니스 로직 실행
        call();
        //비즈니스 로직 종료
        long endTime = System.currentTimeMillis();
        long result = endTime - startTime;
        log.info("resultTime = {}", result);

        //만약 고칠 부분이 있다고 하면 여기 한 부분만 고치면 되니까 단일 책임 원칙을 준수하는 것이 된다. ** 중요 **
        // 100개 클래스가 있으면 100개 다 고쳐야함 **
    }

    protected abstract void call(); //변하는 부분
}

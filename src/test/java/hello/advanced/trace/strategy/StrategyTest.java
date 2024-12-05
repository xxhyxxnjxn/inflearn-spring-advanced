package hello.advanced.trace.strategy;

import hello.advanced.trace.strategy.code.*;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

@Slf4j
public class StrategyTest {
    /**
     * 전략 패턴의 두가지 방법
     * 1. 조립 후 실행 : 생성자에 어떤 클래스를 쓸건지 의존성 주입 후 로직 실행
     * 2. 파라메터로 조립 : 실행할 로직 메소드의 파라메터로 어떤 클래스를 사용 할 건지 넘겨주는 방법
     */
    @Test
    public void test(){
//        logic1();
//        logic1InnerClassEx();
//        logic2();
//        logic2InnerClassEx();
        logic2LambdaEx();
    }

    private void logic1(){
        Strategy strategy1 = new StrategyLogic1();
        ContextV1 contextV1 = new ContextV1(strategy1);
        contextV1.execute();

        Strategy strategy2 = new StrategyLogic2();
        ContextV1 contextV2 = new ContextV1(strategy2);
        contextV2.execute();
    }

    private void logic1InnerClassEx(){
        Strategy strategy1 = new Strategy() {
            @Override
            public void call() {
             log.info("login1InnerClassEx test1");
            }
        };
        ContextV1 contextV1 = new ContextV1(strategy1);
        contextV1.execute();

        Strategy strategy2 = new Strategy() {
            @Override
            public void call() {
                log.info("login1InnerClassEx test2");
            }
        };
        ContextV1 contextV2 = new ContextV1(strategy2);
        contextV2.execute();
    }

    private void logic1LambdaEx(){
        ContextV1 contextV1 = new ContextV1(() -> log.info("login1InnerClassEx test1"));
        contextV1.execute();

        ContextV1 contextV2 = new ContextV1(() -> log.info("login1InnerClassEx test2"));
        contextV2.execute();
    }

    private void logic2(){
        ContextV2 contextV2 = new ContextV2();

        contextV2.execute(new StrategyLogic1());
        contextV2.execute(new StrategyLogic2());
    }

    private void logic2InnerClassEx(){
        ContextV2 contextV2 = new ContextV2();

        contextV2.execute(new Strategy() {

            @Override
            public void call() {
                log.info("login2InnerClassEx test1");
            }
        });

        contextV2.execute(new Strategy() {

            @Override
            public void call() {
                log.info("login2InnerClassEx test2");
            }
        });
    }

    private void logic2LambdaEx(){
        ContextV2 contextV2 = new ContextV2();

        contextV2.execute(() -> log.info("login2InnerClassEx test1"));
        contextV2.execute(() -> log.info("login2InnerClassEx test2"));
    }
}

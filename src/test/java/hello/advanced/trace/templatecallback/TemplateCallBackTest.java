package hello.advanced.trace.templatecallback;

import hello.advanced.trace.templatecallback.code.TimeLogTemplate;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

@Slf4j
public class TemplateCallBackTest {

    @Test
    public void test(){
        logic();
    }

    private void logic(){
        TimeLogTemplate timeLogTemplate = new TimeLogTemplate();
        timeLogTemplate.execute(() -> log.info("callback test"));
    }
}

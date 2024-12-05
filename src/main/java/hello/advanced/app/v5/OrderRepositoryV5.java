package hello.advanced.app.v5;

import hello.advanced.trace.callback.Template;
import hello.advanced.trace.callback.TemplateCallBack;
import hello.advanced.trace.logtrace.LogTrace;
import hello.advanced.trace.template.AbstractTemplate;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
public class OrderRepositoryV5 {

    private final Template template;

    public OrderRepositoryV5(LogTrace logTrace) {
        this.template = new Template(logTrace);
    }

    public void save(String itemId){
        template.execute("OrderRepositoryV1.save()", () -> {
            //저장 로직
            if(itemId.equals("ex")){
                throw new IllegalStateException("예외발생!!");
            }

            sleep(1000);
            return null;
        });
    }

    private void sleep(long millis){
        try {
            Thread.sleep(millis);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }
}

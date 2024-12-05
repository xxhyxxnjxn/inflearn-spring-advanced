package hello.advanced.app.v5;

import hello.advanced.trace.callback.Template;
import hello.advanced.trace.callback.TemplateCallBack;
import hello.advanced.trace.logtrace.LogTrace;
import hello.advanced.trace.template.AbstractTemplate;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceV5 {

    private final OrderRepositoryV5 orderRepositoryV5;
    private final Template template;

    public OrderServiceV5(OrderRepositoryV5 orderRepositoryV5, LogTrace trace) {
        this.orderRepositoryV5 = orderRepositoryV5;
        this.template = new Template(trace);
    }

    public void orderItem(String itemId){
        template.execute("OrderServiceV1.orderItem()", () -> {
            orderRepositoryV5.save(itemId);
            return null;
        });
    }
}

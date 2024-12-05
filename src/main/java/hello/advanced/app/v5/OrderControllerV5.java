package hello.advanced.app.v5;

import hello.advanced.trace.callback.Template;
import hello.advanced.trace.callback.TemplateCallBack;
import hello.advanced.trace.logtrace.LogTrace;
import hello.advanced.trace.template.AbstractTemplate;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderControllerV5 {

    private final OrderServiceV5 orderServiceV5;
    private final Template template;

    public OrderControllerV5(OrderServiceV5 orderServiceV5, LogTrace logTrace) {
        this.orderServiceV5 = orderServiceV5;
        this.template = new Template(logTrace);
    }

    @GetMapping("/v5/request")
    public String request(String itemId) {
        return template.execute("OrderControllerV1.request()", () -> {
            orderServiceV5.orderItem(itemId);
            return "ok";
        });
    }
}

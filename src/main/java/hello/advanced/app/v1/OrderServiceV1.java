package hello.advanced.app.v1;

import hello.advanced.trace.TraceStatus;
import hello.advanced.trace.hellotrace.HelloTraceV1;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderServiceV1 {

    private final OrderRepositoryV1 orderRepositoryV1;
    private final HelloTraceV1 trace;

    public void orderItem(String itemId){
        TraceStatus status=null;
        try {
            status= trace.begin("OrderServiceV1.orderItem()"); //수작업으로 해줘야함
            orderRepositoryV1.save(itemId);
            //예외가 터져도 end가 출력되어야 한다 -> try catch
            trace.end(status);
        }catch (Exception e) {
            trace.exception(status, e);
            throw e; //예외를 꼭 다시 던져주어야 한다.
        }

    }
}

package hello.advanced.app.v2;

import hello.advanced.trace.TraceId;
import hello.advanced.trace.TraceStatus;
import hello.advanced.trace.hellotrace.HelloTraceV1;
import hello.advanced.trace.hellotrace.HelloTraceV2;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderServiceV2 {

    private final OrderRepositoryV2 orderRepositoryV2;
    private final HelloTraceV2 trace;

    public void orderItem(TraceId traceId,String itemId){
        TraceStatus status=null;
        try {
            status= trace.beginSync(traceId,"OrderServiceV1.orderItem()"); //수작업으로 해줘야함
            orderRepositoryV2.save(status.getTraceId(),itemId);
            //예외가 터져도 end가 출력되어야 한다 -> try catch
            trace.end(status);
        }catch (Exception e) {
            trace.exception(status, e);
            throw e; //예외를 꼭 다시 던져주어야 한다.
        }

    }
}

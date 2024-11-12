package hello.advanced.trace.hellotrace;

import hello.advanced.trace.TraceStatus;
import org.junit.jupiter.api.Test;

class HelloTraceV2Test {

    @Test
    void begin_end(){
        HelloTraceV2 helloTrace = new HelloTraceV2();
        TraceStatus status = helloTrace.begin("hello");
        TraceStatus status2 =  helloTrace.beginSync(status.getTraceId(), "hello2");

        helloTrace.end(status2);
        helloTrace.end(status);

    }

    @Test
    void begin_exception(){
        HelloTraceV2 helloTrace = new HelloTraceV2();

        TraceStatus status = helloTrace.begin("hello");
        TraceStatus status2 = helloTrace.beginSync(status.getTraceId(), "hello2");

        helloTrace.exception(status2, new IllegalArgumentException());
        helloTrace.exception(status, new IllegalArgumentException());
    }
}
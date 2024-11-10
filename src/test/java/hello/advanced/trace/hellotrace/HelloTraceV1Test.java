package hello.advanced.trace.hellotrace;

import hello.advanced.trace.TraceStatus;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HelloTraceV1Test {

    @Test
    void begin_end(){
        HelloTraceV1 helloTrace = new HelloTraceV1();
        TraceStatus status = helloTrace.begin("hello");
        status=helloTrace.next("next");
        helloTrace.end(status);
    }

    @Test
    void begin_exception(){
        HelloTraceV1 helloTrace = new HelloTraceV1();
        TraceStatus status = helloTrace.begin("hello");
        helloTrace.exception(status, new IllegalArgumentException());
    }
}
package hello.advanced.trace.template;

import hello.advanced.trace.TraceStatus;
import hello.advanced.trace.logtrace.LogTrace;

public abstract class AbstractTemplate<T> {

    private final LogTrace logTrace;

    public AbstractTemplate(LogTrace logTrace) {
        this.logTrace = logTrace;
    }

    /**
     *
     * @param message
     * @return
     * 리턴타입이 각각 달라서 제네릭으로 선언해준다
     * 클래스에 제네릭을 선언하게 되면 리턴 타입이 String이면 String으로 반환해준다
     */
    public T execute(String message){
        TraceStatus status = null;

        try{
            status = logTrace.begin(message);

            //로직호출
            T result = call();

            logTrace.end(status);
            return result;
        }catch (Exception e){
            logTrace.exception(status, e);
            throw e;
        }
    }

    protected abstract T call();
}

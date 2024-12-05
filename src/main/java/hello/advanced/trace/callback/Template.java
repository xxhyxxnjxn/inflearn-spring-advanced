package hello.advanced.trace.callback;

import hello.advanced.trace.TraceStatus;
import hello.advanced.trace.logtrace.LogTrace;

/**
 * 똑같은 부분을 템플릿 메소드로 정의하고
 * 바뀌는 부분을 템플릿 콜백으로 정의한다.
 */
public class Template{

    private LogTrace trace;

    public Template(LogTrace trace) {
        this.trace = trace;
    }

    public <T> T execute(String message,TemplateCallBack<T> templateCallBack){
        TraceStatus status = null;

        try{
            status = trace.begin(message);

            //로직호출
            T result = templateCallBack.call();

            trace.end(status);
            return result;
        }catch (Exception e){
            trace.exception(status, e);
            throw e;
        }
    }
}

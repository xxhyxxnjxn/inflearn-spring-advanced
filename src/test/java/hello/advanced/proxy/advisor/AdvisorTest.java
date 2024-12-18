package hello.advanced.proxy.advisor;

import hello.advanced.proxy.common.advice.TimeAdvice;
import hello.advanced.proxy.common.sevice.ServiceImpl;
import hello.advanced.proxy.common.sevice.ServiceInterface;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.aop.ClassFilter;
import org.springframework.aop.MethodMatcher;
import org.springframework.aop.Pointcut;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.aop.support.NameMatchMethodPointcut;

import java.lang.reflect.Method;

@Slf4j
public class AdvisorTest {

    @Test
    void advisorTest1() {
        ServiceInterface target = new ServiceImpl();
        ProxyFactory proxyFactory = new ProxyFactory(target);
        DefaultPointcutAdvisor advisor = new DefaultPointcutAdvisor(Pointcut.TRUE, new TimeAdvice());
        proxyFactory.addAdvisor(advisor);
        ServiceInterface proxy = (ServiceInterface) proxyFactory.getProxy();

        proxy.find();
        proxy.save();
        /**
         * 1. proxy에 find 호출 신호가 오면 먼저 pointcut 필터 한테 물어봄 find 호출돼요 ?
         * 2. 된다. 하면 find 호출 가능
         * 3. 안된다. 하면 호출하지 않음
         */
        /**                           ----> PointCut
         * proxyfactory --> Advisor --
         *                            ----> Advice
         */
    }

    /**
     * 포인트 컷에 필터를 적용
     * 어 그러면 TimeAdvice에 조건 넣어도 되지 않느냐 ?
     * 그렇게 되면 재사용성이 떨어진다. 역할과 구현이 제대로 나뉘어지지 않음
     */

    // 포인트 컷은 크게 ClassFilter와 MethodFilter로 나뉘어진다. 둘다 true로 반환되어야 어드바이스를 적용할 수 있다.

    @Test
    @DisplayName("직접 만든 포인트 컷")
    void advisorTest2() {
        ServiceInterface target = new ServiceImpl();
        ProxyFactory proxyFactory = new ProxyFactory(target);
        DefaultPointcutAdvisor advisor = new DefaultPointcutAdvisor(new MyPointCut(), new TimeAdvice());
        proxyFactory.addAdvisor(advisor);
        ServiceInterface proxy = (ServiceInterface) proxyFactory.getProxy();

        proxy.save();
        proxy.find();
        //advisor(부가 기능)가 호출되지 않음
    }

    @Test
    @DisplayName("spring이 제공하는 포인트컷")
    void advisorTest3() {
        ServiceInterface target = new ServiceImpl();
        ProxyFactory proxyFactory = new ProxyFactory(target);

        NameMatchMethodPointcut pointCut = new NameMatchMethodPointcut();
        pointCut.setMappedName("save"); // advisor을 적용할 name을 넣음

        DefaultPointcutAdvisor advisor = new DefaultPointcutAdvisor(pointCut, new TimeAdvice());
        proxyFactory.addAdvisor(advisor);
        ServiceInterface proxy = (ServiceInterface) proxyFactory.getProxy();

        proxy.save();
        proxy.find();
    }

    static class MyPointCut implements Pointcut {

        @Override
        public ClassFilter getClassFilter() {
            return ClassFilter.TRUE;
        }

        @Override
        public MethodMatcher getMethodMatcher() {
            return new MyMethodMatcher();
        }
    }

    static class MyMethodMatcher implements MethodMatcher {
        private String matchName = "save";
        @Override
        public boolean matches(Method method, Class<?> targetClass) {
            // 메소드에 어드 바이스를 적용할지 말지 판단
            boolean result = method.getName().equals(matchName);
            log.info("포인트컷 호출 method={}, targetClass={}", method.getName(), targetClass);
            log.info("포인트컷 결과 result={}",result);
            return result;
        }

        @Override
        public boolean isRuntime() {
            //isRuntime이 참이면 matches가 메서드가 호출됨
            return false;
        }

        @Override
        public boolean matches(Method method, Class<?> targetClass, Object... args) {
            return false;
        }
    }
}

package me.wielgolaski.springtest.testscope;

import me.wielgolaski.springtest.testscope.TestScope;
import org.springframework.beans.factory.config.Scope;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.test.context.TestContext;
import org.springframework.test.context.support.AbstractTestExecutionListener;


public class TestScopeExecutionListener extends AbstractTestExecutionListener {

    @Override
    public final int getOrder() {
        return 0;
    }


    @Override
    public void beforeTestMethod(TestContext testContext) throws Exception {
        getTestContext(testContext).testCaseStared(testContext.getTestMethod());
    }

    private me.wielgolaski.springtest.testscope.TestContext getTestContext(TestContext testContext) {
        ApplicationContext applicationContext = testContext.getApplicationContext();
        return applicationContext.getBean(me.wielgolaski.springtest.testscope.TestContext.class);
    }

    @Override
    public void afterTestMethod(TestContext testContext) throws Exception {
        getTestContext(testContext).testCaseEnded(testContext.getTestMethod(), testContext.getTestException() != null);
        resetTestScope(testContext);
    }

    private void resetTestScope(TestContext testContext) {
        ApplicationContext applicationContext = testContext.getApplicationContext();
        Scope testScope = ((GenericApplicationContext) applicationContext).getBeanFactory().getRegisteredScope("test");
        ((TestScope) testScope).close();
    }
}

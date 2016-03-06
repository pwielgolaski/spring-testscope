package me.wielgolaski.springtest.testscope;


public interface TestContextAware<T extends TestContext> {
    void setTestContext(T testContext);
}

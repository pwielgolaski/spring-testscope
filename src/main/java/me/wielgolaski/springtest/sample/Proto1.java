package me.wielgolaski.springtest.sample;

import me.wielgolaski.springtest.testscope.TestContext;
import me.wielgolaski.springtest.testscope.TestContextAware;
import org.springframework.beans.factory.annotation.Autowired;

public class Proto1 implements TestContextAware {

    @Autowired
    public Proto2 proto2;

    public TestContext testContext;

    @Override
    public void setTestContext(TestContext testContext) {
        this.testContext = testContext;
    }
}

package me.wielgolaski.springtest;

import me.wielgolaski.springtest.sample.Proto1;
import me.wielgolaski.springtest.sample.Proto2;
import me.wielgolaski.springtest.sample.SampleTestContext;
import me.wielgolaski.springtest.sample.config.MainConfiguration;
import me.wielgolaski.springtest.testscope.TestContextAware;
import me.wielgolaski.springtest.testscope.TestScopeExecutionListener;
import org.junit.Assert;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = MainConfiguration.class)
@TestExecutionListeners(value = TestScopeExecutionListener.class, mergeMode = TestExecutionListeners.MergeMode.MERGE_WITH_DEFAULTS)
public abstract class AbstractRunner implements TestContextAware<SampleTestContext> {

    Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    Proto1 proto1;

    @Autowired
    Proto2 proto2;

    private SampleTestContext testContext;

    @Override
    public void setTestContext(SampleTestContext testContext) {
        this.testContext = testContext;
    }

    void report() {
        Assert.assertNotNull(testContext);
        Assert.assertNotNull(proto2);
        Assert.assertNotNull(proto1);

        logger.warn("instance={}, proto1={}, proto2={}, proto2in1={}, testContext={}-{}",
                this, proto1, proto2, proto1.proto2, testContext, testContext.getName());
        Assert.assertSame(proto2, proto1.proto2);
        Assert.assertSame(testContext, proto1.testContext);
    }


}

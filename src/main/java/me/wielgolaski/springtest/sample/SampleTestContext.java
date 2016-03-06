package me.wielgolaski.springtest.sample;

import me.wielgolaski.springtest.testscope.TestContext;

import java.lang.reflect.Method;

/**
 * Created by pwielgolaski on 05/03/16.
 */
public class SampleTestContext implements TestContext {

    private String name;

    public String getName() {
        return name;
    }

    public void testCaseStared(Method method) {
        name = method.getDeclaringClass().getSimpleName() + "." + method.getName();
    }

    public void testCaseEnded(Method method, boolean fail) {
    }

}

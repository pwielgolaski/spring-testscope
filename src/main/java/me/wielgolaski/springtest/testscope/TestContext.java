package me.wielgolaski.springtest.testscope;

import java.lang.reflect.Method;

public interface TestContext {
    void testCaseStared(Method method);
    void testCaseEnded(Method method, boolean fail);
}

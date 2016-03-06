package me.wielgolaski.springtest.testscope;

import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.config.Scope;
import org.springframework.core.NamedThreadLocal;

import java.util.HashMap;
import java.util.Map;

/**
 * Based on SimpleThreadScope
 */
public class TestScope implements Scope {

    public static final String TEST_SCOPE = "test";

    private final ThreadLocal<Map<String, Object>> threadScope = new NamedThreadLocal<Map<String, Object>>("TestScope") {
        @Override
        protected Map<String, Object> initialValue() {
            return new HashMap<>();
        }
    };

    private final ThreadLocal<Map<String, Runnable>> threadScopeDestruction = new NamedThreadLocal<Map<String, Runnable>>("TestScopeDestruction") {
        @Override
        protected Map<String, Runnable> initialValue() {
            return new HashMap<>();
        }
    };


    @Override
    public Object get(String name, ObjectFactory<?> objectFactory) {
        Map<String, Object> scope = this.threadScope.get();
        Object object = scope.get(name);
        if (object == null) {
            object = objectFactory.getObject();
            scope.put(name, object);
        }
        return object;
    }

    @Override
    public Object remove(String name) {
        Map<String, Object> scope = this.threadScope.get();
        return scope.remove(name);
    }

    @Override
    public void registerDestructionCallback(String name, Runnable callback) {
        Map<String, Runnable> destruction = this.threadScopeDestruction.get();
        destruction.put(name, callback);
    }

    @Override
    public Object resolveContextualObject(String key) {
        return null;
    }

    @Override
    public String getConversationId() {
        return Thread.currentThread().getName();
    }

    public void close() {
        Map<String, Runnable> destruction = this.threadScopeDestruction.get();
        destruction.values().forEach(Runnable::run);
        threadScopeDestruction.remove();
        threadScope.remove();
    }
}

package me.wielgolaski.springtest.sample.config;

import me.wielgolaski.springtest.sample.Proto1;
import me.wielgolaski.springtest.sample.Proto2;
import me.wielgolaski.springtest.sample.SampleTestContext;
import me.wielgolaski.springtest.sample.Service;
import me.wielgolaski.springtest.testscope.TestScope;
import me.wielgolaski.springtest.testscope.TestContext;
import me.wielgolaski.springtest.testscope.TestContextBeanPostProcessor;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.beans.factory.config.CustomScopeConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import java.util.HashMap;
import java.util.Map;

import static me.wielgolaski.springtest.testscope.TestScope.TEST_SCOPE;

@Configuration
public class MainConfiguration {

    @Bean
    public static CustomScopeConfigurer customScope() {
        CustomScopeConfigurer configurer = new CustomScopeConfigurer();
        Map<String, Object> testScope = new HashMap<>();
        testScope.put(TEST_SCOPE, new TestScope());
        configurer.setScopes(testScope);

        return configurer;
    }

    @Bean
    public static BeanPostProcessor testContextPostProcessor() {
        return new TestContextBeanPostProcessor();
    }

    @Bean
    public Service service() {
        return new Service();
    }

    @Bean
    @Scope(TEST_SCOPE)
    public Proto1 proto1() {
        return new Proto1();
    }

    @Bean
    @Scope(TEST_SCOPE)
    public Proto2 proto2() {
        return new Proto2();
    }

    @Bean
    @Scope(TEST_SCOPE)
    public TestContext testContext() {
        return new SampleTestContext();
    }
}

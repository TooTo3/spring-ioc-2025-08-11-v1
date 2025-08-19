package com.ll.framwork.ioc;

import com.ll.domain.testPost.testPost.repository.TestPostRepository;
import com.ll.domain.testPost.testPost.service.TestFacadePostService;
import com.ll.domain.testPost.testPost.service.TestPostService;

import java.util.HashMap;
import java.util.Map;

public class ApplicationContext {
    private final Map<String, Object> beans = new HashMap<>();
    private String basePackage;

    public ApplicationContext() {
        this.basePackage = basePackage;
    }

    public void init() {
    }

    @SuppressWarnings("unchecked")
    public <T> T genBean(String beanName) {
        if (!beans.containsKey(beanName)) {
            beans.put(beanName, createBean(beanName));
        }
        return (T) beans.get(beanName);
    }

    private Object createBean(String beanName) {
        return switch (beanName) {
            case "testFacadePostService" -> new TestFacadePostService(
                    genBean("testPostService"),
                    genBean("testPostRepository")
            );
            case "testPostService" -> new TestPostService(
                    genBean("testPostRepository")
            );
            case "testPostRepository" -> new TestPostRepository();
            default -> throw new IllegalArgumentException("Unknown bean: " + beanName);
        };
    }
}

package me.khmoon.inflearnthejavatest;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.AfterTestExecutionCallback;
import org.junit.jupiter.api.extension.BeforeTestExecutionCallback;
import org.junit.jupiter.api.extension.ExtensionContext;

import java.lang.reflect.Method;

public class FindSlowTestExtension implements BeforeTestExecutionCallback, AfterTestExecutionCallback {
  private long THRESHOLD;

  public FindSlowTestExtension(long THRESHOLD) {
    this.THRESHOLD = THRESHOLD;
  }

  @Override
  public void beforeTestExecution(ExtensionContext context) throws Exception {
    ExtensionContext.Store store = getStore(context);
    store.put("START TIME", System.currentTimeMillis());
  }

  private ExtensionContext.Store getStore(ExtensionContext context) {
    String testClassName = context.getRequiredTestClass().getName();
    String testMethodName = context.getRequiredTestMethod().getName();
    return context.getStore(ExtensionContext.Namespace.create(testClassName, testMethodName));
  }

  @Override
  public void afterTestExecution(ExtensionContext context) throws Exception {
    Method requiredTestMethod = context.getRequiredTestMethod();
    SlowTest annotation = requiredTestMethod.getAnnotation(SlowTest.class);
    String testMethodName = context.getRequiredTestMethod().getName();
    ExtensionContext.Store store = getStore(context);
    long start_time = store.remove("START TIME", long.class);
    long duration = System.currentTimeMillis() - start_time;
    if (duration > THRESHOLD && annotation == null) {
      System.out.printf("Please consider mark method [%s] with @SlowTest.\n", testMethodName);
    }
  }
}

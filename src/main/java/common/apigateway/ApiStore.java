package common.apigateway;

import org.apache.shiro.util.Assert;
import org.springframework.context.ApplicationContext;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * api 注册中心
 * @author: huangzhb
 * @Date: 2019年02月01日 17:33:57
 * @Description:
 */
public class ApiStore {

    private ApplicationContext applicationContext;

    private HashMap<String, ApiRunnable> apiMap = new HashMap<String, ApiRunnable>();


    public ApiStore(ApplicationContext applicationContext) {
        Assert.notNull(applicationContext);
        this.applicationContext = applicationContext;
    }

    /**
     * 基于spring IOC 容器里面的bean查找对应的api方法
     */
    public void loadApiFromSpringBeans() {
        // ioc 所有bean

        // spring ioc 扫描
        String[] names = applicationContext.getBeanDefinitionNames();
        Class<?> type;

        // 反射
        for (String name : names) {
            type = applicationContext.getType(name);
            for (Method m : type.getDeclaredMethods()) {
                // 通过反射拿到APIMapping注解
                APIMapping apiMapping = m.getAnnotation(APIMapping.class);
                if (apiMapping != null) {
                    // 找到目标方法
                    addApiItem(apiMapping, name, m);
                }
            }
        }
    }


    public ApiRunnable findApiRunnable(String apiName) {
        return apiMap.get(apiName);
    }

    private void addApiItem(APIMapping apiMapping, String beanName, Method method) {

        ApiRunnable apiRun = new ApiRunnable();
        apiRun.apiName = apiMapping.value();
        apiRun.targetMethod = method;
        apiRun.targetName = beanName;
        apiMap.put(apiMapping.value(), apiRun);
    }

    private ApiRunnable findApiRunnable(String apiName, String version) {
        return (ApiRunnable) apiMap.get(apiName + "_" + version);
    }

    public List<ApiRunnable> findApiRunnables(String apiName) {
        if (apiName == null) {
            throw new IllegalArgumentException("api name must not null");
        }
        List<ApiRunnable> list = new ArrayList<ApiRunnable>(20);
        for (ApiRunnable api : apiMap.values()) {
            if (api.apiName.equals(apiName)) {
                list.add(api);
            }
        }
        return list;
    }


    // 用于执行对应的API方法
    public class ApiRunnable {
        String apiName; // bit.api.user.getUser
        String targetName; // ioc bean 名称
        Object target; // UserServiceImpl实例
        Method targetMethod; // 目标方法 getUser

        // 多线程安全问题
        public Object run(Object... args) throws InvocationTargetException, IllegalAccessException {
            if (target == null) {
                //spring ioc 容器里面去服务bean 比如GoodsServiceImpl
                target = applicationContext.getBean(targetName);
            }
            return targetMethod.invoke(target, args);
        }

        public Class<?>[] getParamTypes() {return targetMethod.getParameterTypes();}

        public String getApiName() {return apiName;}

        public String getTargetName() {return targetName;}

        public Object getTarget() {return target;}
    }

}

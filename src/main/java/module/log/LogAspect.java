package module.log;

import com.alibaba.fastjson.JSONObject;
import com.technology.util.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.Objects;

/**
 * @Description: 操作日志记录处理
 *   @Aspect -- 作用是把当前类标识为一个切面供容器读取
 *   @Pointcut -- (切入点):就是带有通知的连接点，在程序中主要体现为书写切入点表达式
 *   @Before -- 标识一个前置增强方法，相当于BeforeAdvice的功能
 *   @AfterReturning -- 后置增强，相当于AfterReturningAdvice，方法退出时执行
 *   @AfterThrowing -- 异常抛出增强，相当于ThrowsAdvice
 *   @After -- final增强，不管是抛出异常或者正常退出都会执行
 *   @Around -- 环绕增强，相当于MethodInterceptor
 * @Author: huangzhb
 * @Date: 2020/4/15 10:32
 * @Version: 1.0
 */
@Aspect
@Component
@Slf4j
public class LogAspect {


    /**
     * 配置织入点
     */
    @Pointcut("@annotation(module.log.Log)")
    public void logPointCut() {
    }

    /**
     * 处理完请求后执行
     * @param joinPoint
     * @param jsonResult
     */
    @AfterReturning(pointcut = "logPointCut()", returning = "jsonResult")
    public void doAfterReturning(JoinPoint joinPoint, Object jsonResult) {
        handleLog(joinPoint, null, jsonResult);
    }

    /**
     * 拦截异常操作
     * @param joinPoint
     * @param e
     */
    @AfterThrowing(pointcut = "logPointCut()", throwing = "e")
    public void doAfterThrowing(JoinPoint joinPoint, Exception e) {
        handleLog(joinPoint, e, null);
    }

    protected void handleLog(final JoinPoint joinPoint, final Exception e, Object jsonResult) {


        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        HttpServletResponse response = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getResponse();

        try {
            // 获取注解
            Log controllerLog = getAnnotationLog(joinPoint);
            if (controllerLog == null) {
                return ;
            }

            // 获取当前的用户?
            // User currentUser = ShiroUtils.getSysUser();

            // *=======数据库日志=======*
            OperLog operLog = new OperLog();
            operLog.setStatus(BusinessStatus.SUCCESS.ordinal());
            // 请求地址
            String ip = IpUtils.getIpAddr(request);
            operLog.setOperIp(ip);
            if (!Objects.isNull(jsonResult)) {
                operLog.setJsonResult(JSONObject.toJSONString(jsonResult));
            }

            // TODO 设置用户信息,如果不为空

            operLog.setOperUrl(request.getRequestURI());

            if (e != null) {
                operLog.setStatus(BusinessStatus.FAIL.ordinal());
                operLog.setErrorMsg(StringUtils.substring(e.getMessage(), 0, 2000));
            }

            // 设置方法名称
            String className = joinPoint.getTarget().getClass().getName();
            String methodName = joinPoint.getSignature().getName();
            operLog.setMethod(className + "." + methodName + "()");
            // 设置请求方式
            operLog.setRequestMethod(request.getMethod());
            // 处理设置注解上的参数
            getControllerMethodDescription(controllerLog, operLog);
            // 保存数据库
            AsyncManager.me().execute(AsyncFactory.recordOper(operLog));
        } catch (Exception exception) {
            log.error("异常信息:{}", exception.getMessage());
            exception.printStackTrace();
        }
    }

    /**
     * 获取注解中对方法的描述信息 用于Controller层注解
     * @param log
     * @param operLog
     * @throws Exception
     */
    public void getControllerMethodDescription(Log log, OperLog operLog) throws Exception{
        // 设置action动作
        operLog.setBusinessType(log.businessType().ordinal());
        // 设置标题
        operLog.setTitle(log.title());
        // 设置操作人类别
        operLog.setOperatorType(log.operatorType().ordinal());
        // 是否需要保存request，参数和值
        if (log.isSaveRequestData()) {
            setRequestValue(operLog);
        }
    }

    /**
     * 获取请求的参数，放到log中
     * @param operLog
     */
    private void setRequestValue(OperLog operLog) {

        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        Map<String, String[]> map = request.getParameterMap();
        String params = JSONObject.toJSONString(map);
        operLog.setOperParam(StringUtils.substring(params, 0 ,2000));
    }

    /**
     * 是否存在注解，如果存在就获取
     * @param joinPoint
     * @return
     * @throws Exception
     */
    private Log getAnnotationLog(JoinPoint joinPoint) throws Exception {
        Signature signature = joinPoint.getSignature();
        MethodSignature methodSignature = (MethodSignature) signature;
        Method method = methodSignature.getMethod();

        if (method != null) {
            return method.getAnnotation(Log.class);
        }
        return null;
    }

    /*//前置通知
    @Before("Pointcut()")
    public void beforeMethod(JoinPoint joinPoint){
        log.info("调用了前置通知");

    }

    //@After: 后置通知
    @After("Pointcut()")
    public void afterMethod(JoinPoint joinPoint){
        log.info("调用了后置通知");
    }
    //@AfterRunning: 返回通知 rsult为返回内容
    @AfterReturning(value="Pointcut()",returning="result")
    public void afterReturningMethod(JoinPoint joinPoint,Object result){
        log.info("调用了返回通知");
    }
    //@AfterThrowing: 异常通知
    @AfterThrowing(value="Pointcut()",throwing="e")
    public void afterReturningMethod(JoinPoint joinPoint, Exception e){
        log.info("调用了异常通知");
    }

    //@Around：环绕通知
    @Around("Pointcut()")
    public Object Around(ProceedingJoinPoint pjp) throws Throwable {
        log.info("around执行方法之前");
        Object object = pjp.proceed();
        log.info("around执行方法之后--返回值：" +object);
        return object;
    }*/

}

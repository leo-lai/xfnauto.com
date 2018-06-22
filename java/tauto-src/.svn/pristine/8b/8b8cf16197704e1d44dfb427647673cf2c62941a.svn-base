package main.com.system.aspect;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.LocalVariableTableParameterNameDiscoverer;
import org.springframework.core.ParameterNameDiscoverer;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import main.com.exception.BusinessException;
import main.com.frame.domain.ResultCode;
import main.com.frame.search.BaseSearch;
@Aspect
@Component
@Order(1)
public class ValidateAspect {
	
	private static Logger logger = LoggerFactory.getLogger(ValidateAspect.class);
	
	ParameterNameDiscoverer parameterNameDiscoverer = new LocalVariableTableParameterNameDiscoverer();

    private ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    private Validator validator = factory.getValidator();
    
    @Pointcut("execution(* main.com.*.controller..*.*(..)) && args(baseSearch, ..)")
    public void aspectMethod(BaseSearch baseSearch){

    }
    
    //自定义处理，无需在被校验参数前加@Valid进行校验
    @Before("aspectMethod(baseSearch)")
    public void before(JoinPoint joinPoint,BaseSearch baseSearch){
        Set<ConstraintViolation<Object>> validResult = validator.validate(baseSearch);
        if (!validResult.isEmpty()) {
            StringBuilder sb = new StringBuilder();
            for(ConstraintViolation<Object> o : validResult){
                sb.append(o.getMessage() + ";");
                logger.error(o.getPropertyPath() + ":" + o.getMessage());
            }
            //错误信息抛给上层处理
            throw new BusinessException(ResultCode.CODE_STATE_4005, sb.toString());
        }
    }

}

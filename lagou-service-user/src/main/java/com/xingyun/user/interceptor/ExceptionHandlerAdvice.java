package com.xingyun.user.interceptor;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.HttpMediaTypeException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * @author 最爱吃小鱼
 */
@ControllerAdvice
public class ExceptionHandlerAdvice {

    private Logger logger = LoggerFactory.getLogger(ExceptionHandlerAdvice.class);


    // 异常的拦截处理
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public String handleException(HttpServletRequest request, HttpServletResponse response, Exception e) {
        logger.error("URL:{}统一异常处理。", request.getRequestURI(), e);

        return "fail";
    }



    private String getParamName(Path propertyPath) {
        String paramName = null;
        for (Path.Node s : propertyPath) {
            paramName = s.getName();
        }
        return paramName;
    }

    class ErrBean {
        private String param;
        private String errmsg;

        public ErrBean(String param, String errmsg) {
            this.param = param;
            this.errmsg = errmsg;
        }

        public String getParam() {
            return param;
        }

        public void setParam(String param) {
            this.param = param;
        }

        public String getErrmsg() {
            return errmsg;
        }

        public void setErrmsg(String errmsg) {
            this.errmsg = errmsg;
        }
    }

    private String getServiceName(int localPort) {

        switch (localPort) {
            case 8001:
                return "prd-oss";
            case 8002:
                return "prd-min";
            case 8003:
                return "prd-waiter";
            case 8004:
                return "prd-major";
            case 8005:
                return "prd-program";
            case 8006:
                return "prd-agent";
            case 8007:
                return "prd-boss";
            case 8008:
                return "prd-third";
            case 8009:
                return "inner-report";
            case 8010:
                return "prd-micro";
            case 8011:
                return "prd-dobot";
            case 8012:
                return "prd-wechat";
            case 8013:
                return "prd-printer";
            case 8014:
                return "prd-bd";
            case 8015:
                return "prd-common";
            case 8016:
                return "prd-join";
            case 8017:
                return "prd-join-agent";
            case 8018:
                return "prd-join-major";
            case 8019:
                return "prd-join-cron";
            default:
                return "unknown service name";
        }
    }

}

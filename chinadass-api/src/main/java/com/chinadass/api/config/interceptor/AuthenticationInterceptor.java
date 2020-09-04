package com.chinadass.api.config.interceptor;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.chinadass.api.common.enums.ResultCode;
import com.chinadass.api.common.exception.BusinessException;
import com.chinadass.api.config.annotation.AuthTokenIgnore;
import com.chinadass.api.entity.UserInfo;
import com.chinadass.api.entity.UserInfoExample;
import com.chinadass.api.utils.JwtUtil;
import com.chinadass.api.config.annotation.AuthToken;
import com.chinadass.api.mapper.UserInfoMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;
import java.util.List;

/**
 * 拦截器获取token并验证token
 */
@Slf4j
@SuppressWarnings("unused")
public class AuthenticationInterceptor implements HandlerInterceptor {
    @Resource
    private UserInfoMapper userInfoMapper;

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object object) throws Exception {
        String token = httpServletRequest.getHeader("token");// 从 http 请求头中取出 token
        // 如果不是映射到方法直接通过
        if (!(object instanceof HandlerMethod)) {
            return true;
        }
        HandlerMethod handlerMethod = (HandlerMethod) object;
        Method method = handlerMethod.getMethod();
        //检查是否有AuthTokenIgnore注释，有则跳过认证
        if (method.isAnnotationPresent(AuthTokenIgnore.class)) {
            AuthTokenIgnore authTokenIgnore = method.getAnnotation(AuthTokenIgnore.class);
            if (authTokenIgnore.required()) {
                return true;
            }
        }
        //检查有没有需要用户权限的注解
        if (method.isAnnotationPresent(AuthToken.class)) {
            AuthToken authToken = method.getAnnotation(AuthToken.class);
            if (authToken.required()) {
                // 执行认证
                if (token == null) {
                    log.info("无token，请重新登录");
                    throw new BusinessException(ResultCode.NO_TOKEN);
                }
                // 获取 token 中的 userAccount
                String userAccount;
                try {
                    userAccount = JwtUtil.getUserName(token);
                } catch (Exception j) {
                    log.error("401:token不合法",j);
                    throw new BusinessException(ResultCode.TOKEN_OUT_TIME);
                }
                UserInfoExample userInfoExample=new UserInfoExample();
                userInfoExample.createCriteria().andUserAccountEqualTo(userAccount);
                List<UserInfo> userInfos = userInfoMapper.selectByExample(userInfoExample);
                if (userInfos == null || userInfos.size() == 0) {
                    log.info("用户不存在，请重新登录");
                    throw new BusinessException(ResultCode.USER_NOT_EXIST);
                }
                // 验证 token
                JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256(JwtUtil.SECRET)).build();
                try {
                    jwtVerifier.verify(token);
                } catch (JWTVerificationException e) {
                    log.error("401:认证token失败",e);
                    throw new BusinessException(ResultCode.TOKEN_ILLEGAL);
                }
                return true;
            }
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest,
                           HttpServletResponse httpServletResponse,
                           Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest,
                                HttpServletResponse httpServletResponse,
                                Object o, Exception e) throws Exception {
    }
}

package xyz.erupt.eruptlimit.interceptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import xyz.erupt.core.constant.HttpStatus;
import xyz.erupt.core.model.EruptModel;
import xyz.erupt.core.service.CoreService;
import xyz.erupt.eruptlimit.service.LoginService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by liyuepeng on 12/5/18.
 */
@Service
public class LoginInterceptor extends HandlerInterceptorAdapter {

    @Autowired
    public LoginService loginService;

    private static final String ERUPT_HEADER_KEY = "eruptKey";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String eruptKey = request.getHeader(ERUPT_HEADER_KEY);
        EruptModel eruptModel = CoreService.ERUPTS.get(eruptKey);
        String token = request.getHeader("token");
        if (null == token || !loginService.verifyToken(token)) {
            response.setStatus(HttpStatus.NO_LOGIN.code);
            return false;
        }
        if (null == eruptModel) {
            return true;
        } else if (eruptModel.getErupt().loginUse()) {
            return true;
        } else {
            return true;
        }
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable ModelAndView modelAndView) throws Exception {
        super.postHandle(request, response, handler, modelAndView);
    }
}
package com.project.web.interceptor;

import com.project.entity.OnlyOneUser;
import com.project.entity.User;
import com.project.service.OnlyOneUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Created by qiaowentao on 2016/8/19.
 */
public class UserLoginInterceptor extends HandlerInterceptorAdapter {
    private static Logger LOG = LoggerFactory.getLogger(UserLoginInterceptor.class);


    @Resource
    private OnlyOneUserService oneUserService;
    /**
     * 在业务处理器处理请求之前被调用
     * 如果返回false
     *     从当前的拦截器往回执行所有拦截器的afterCompletion(),再退出拦截器链
     * 如果返回true
     *    执行下一个拦截器,直到所有的拦截器都执行完毕
     *    再执行被拦截的Controller
     *    然后进入拦截器链,
     *    从最后一个拦截器往回执行所有的postHandle()
     *    接着再从最后一个拦截器往回执行所有的afterCompletion()
     */
    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response, Object handler) throws Exception {
        LOG.info("## UserLoginInterceptor info ## preHandle");
        //从cookie中获取当前登录用户的信息
        try {
            //String cookieToken = cookieUtil.getCookieValues(request, "token");
            HttpSession session = request.getSession();
            User user = (User) session.getAttribute("user");

            if(user != null){
                String phone = user.getPhone();
                String utr = (String) session.getAttribute("utr");
                if(utr != null){
                    OnlyOneUser onlyOneUser = oneUserService.selectOnlyWithCondition(new OnlyOneUser(phone, utr));
                    if(onlyOneUser == null){
                        session.removeAttribute("utr");
                        session.removeAttribute("user");
                        //session.invalidate();
                        session.setAttribute("oneUser","false");
                        session.setAttribute("OnlyOneUser","您的账号已经在其他客户端登录了！");
                        response.sendRedirect(request.getContextPath()+"/user/toLogin");
                        LOG.info("## UserLoginInterceptor info ## /user/toLogin");
                        return false;
                    }
                }

            }

        } catch (Exception e) {
            e.printStackTrace();

        }
        return true;

    }

    /**
     * 在业务处理器处理请求执行完成后,生成视图之前执行的动作
     * 可在modelAndView中加入数据，比如当前时间
     */
    @Override
    public void postHandle(HttpServletRequest request,
                           HttpServletResponse response, Object handler,
                           ModelAndView model) throws Exception {

    }

    /**
     * 在DispatcherServlet完全处理完请求后被调用,可用于清理资源等
     *
     * 当有拦截器抛出异常时,会从当前拦截器往回执行所有的拦截器的afterCompletion()
     */
    @Override
    public void afterCompletion(HttpServletRequest request,
                                HttpServletResponse response, Object handler, Exception ex)
            throws Exception {

    }

}

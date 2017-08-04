package com.project.web.controller;

import com.project.common.util.MD5;
import com.project.common.util.RandomString;
import com.project.entity.OnlyOneUser;
import com.project.entity.User;
import com.project.entity.YqmInfo;
import com.project.service.OnlyOneUserService;
import com.project.service.UserService;
import com.project.service.YqmInfoService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Created by qiaowentao on 2016/8/17.
 */
@Controller
@RequestMapping(value="/user")
public class LoginControler {
    private final static Logger logger = LoggerFactory.getLogger(LoginControler.class);

    @Autowired
    private UserService userService;
    @Autowired
    private YqmInfoService yqmInfoService;
    @Autowired
    private OnlyOneUserService oneUserService;

    @RequestMapping(value="/index")
    public ModelAndView index(HttpServletRequest request, HttpServletResponse response,ModelAndView modelAndView){
        logger.info("## /index ##");
        String id = request.getSession().getId();
        modelAndView.addObject("id",id);
        modelAndView.setViewName("/index");
        return modelAndView;
    }

    /**
     * 去往注册页面
     * @param request
     * @param response
     * @param model
     * @param user
     * @return
     */
    @RequestMapping(value="/toRegister")
    public ModelAndView toRegister(HttpServletRequest request, HttpServletResponse response, ModelAndView model,
                                   User user){
        logger.info("## toRegister ##");
        model.addObject("user",user);
        model.setViewName("/register");
        return model;
    }

    /**
     * 用户注册
     * @param request
     * @param response
     * @param model
     * @return
     */
    @RequestMapping(value="/register",method = {RequestMethod.POST,RequestMethod.GET})
    public ModelAndView register(HttpServletRequest request, HttpServletResponse response, ModelAndView model,
                                 User user){
        logger.info("## register user-{}##",user);
        try{
            user.setSyzt(0);
            String utr = RandomString.generateRandomString(8);
            int i = userService.registerUser(user,utr);
            if(i==1){
                //注册成功，前往登录页面
                String phone = user.getPhone();
                model.setViewName("/login");
                model.addObject("phone",phone);
                return model;
            }else{
                //注册失败
                model.setViewName("/register");
                model.addObject("registermessage","false");
                model.addObject("phone",user.getPhone());
                model.addObject("email",user.getEmail());

            }
        }catch(Exception e){
            logger.error("系统出现异常",e);
        }
        return model;
    }

    /**
     * 去往登录页面
     * @param request
     * @param response
     * @param model
     * @return
     */
    @RequestMapping(value="/toLogin")
    public ModelAndView toLogin(HttpServletRequest request, HttpServletResponse response, ModelAndView model){
        logger.info("## toLogin ##");
        model.setViewName("/login");
        return model;
    }

    /**
     * 用户登录
     * @param request
     * @param response
     * @param model
     * @param phone
     * @param password
     * @return
     */
    @RequestMapping(value="/login",method = {RequestMethod.POST,RequestMethod.GET})
    public ModelAndView login(HttpServletRequest request, HttpServletResponse response, ModelAndView model,
                      @RequestParam("phone")String phone,
                      @RequestParam("password")String password){
        logger.info("登录："+phone+" 密码："+password);
        HttpSession session = request.getSession();
        String info;
        session.setAttribute("message","false");
        model.setViewName("/login");
        if(null != phone && null != password){
            User selectUser = new User();
            selectUser.setPhone(phone);
            //根据登录名查询用户的信息，如：盐值
            User selUser = userService.checkUserWithConditions(selectUser);
            if(selUser != null){
                String salt = selUser.getSalt();
                //对登录密码进行加密
                password = MD5.md5Password(salt,password);
                Subject currentUser = SecurityUtils.getSubject();

                UsernamePasswordToken token = new UsernamePasswordToken(phone, password);


                Object remember = request.getParameter("remember");
                if(null != remember){
                    token.setRememberMe(true);
                }
                //System.out.println("为了验证登录用户而封装的token为" + ReflectionToStringBuilder.toString(token, ToStringStyle.MULTI_LINE_STYLE));
                //获取当前的Subject
                try {
                    //在调用了login方法后,SecurityManager会收到AuthenticationToken,并将其发送给已配置的Realm执行必须的认证检查
                    //每个Realm都能在必要时对提交的AuthenticationTokens作出反应
                    //所以这一步在调用login(token)方法时,它会走到MyRealm.doGetAuthenticationInfo()方法中,具体验证方式详见此方法
                    System.out.println("对用户[" + phone + "]进行登录验证..验证开始");
                    currentUser.login(token);
                    System.out.println("对用户[" + phone + "]进行登录验证..验证通过");
                    String utr = RandomString.generateRandomString(8);
                    oneUserService.updateOnlyOneUserMessage(new OnlyOneUser(phone,utr));
                    model.setViewName("redirect:/user/index");
                    session.setAttribute("utr",utr);
                }catch(UnknownAccountException uae){
                    System.out.println("对用户[" + phone + "]进行登录验证..验证未通过,未知账户");
                    info = "×此用户不存在";
                    model.addObject("phone",phone);
                    model.addObject("info",info);
                }catch(IncorrectCredentialsException ice){
                    System.out.println("对用户[" + phone + "]进行登录验证..验证未通过,错误的凭证");
                    info = "×密码不正确";
                    model.addObject("phone",phone);
                    model.addObject("info",info);
                }catch(LockedAccountException lae){
                    System.out.println("对用户[" + phone + "]进行登录验证..验证未通过,账户已锁定");
                    info = "×账户已锁定";
                    model.addObject("phone",phone);
                    model.addObject("info",info);
                }catch(ExcessiveAttemptsException eae){
                    System.out.println("对用户[" + phone + "]进行登录验证..验证未通过,错误次数过多");
                    info = "×用户名或密码错误次数过多";
                    model.addObject("phone",phone);
                    model.addObject("info",info);
                }catch(AuthenticationException ae){
                    //通过处理Shiro的运行时AuthenticationException就可以控制用户登录失败或密码错误时的情景
                    System.out.println("对用户[" + phone + "]进行登录验证..验证未通过,堆栈轨迹如下");
                    info = "×用户名或密码不正确";
                    ae.printStackTrace();
                    model.addObject("phone",phone);
                    model.addObject("info",info);
                }

                //验证是否登录成功
                if(currentUser.isAuthenticated()){
                    logger.info("用户[" + phone + "]登录认证通过(这里可以进行一些认证通过后的一些系统参数初始化操作)");
                }else{
                    token.clear();
                }

            }else{

                //没有此用户
                model.addObject("phone",phone);
                model.addObject("message","false");
                info = "×此用户不存在!";
                model.addObject("info",info);
            }

        }else{
            model.addObject("phone",phone);
            model.addObject("message","false");
            info = "×用户名或密码为空!";
            model.addObject("info",info);
        }

        return model;
    }

    /**
     * 根据用户填写的手机号查询数据库是否能用
     * @param request
     * @param response
     * @param model
     * @param phone
     * @return
     */
    @ResponseBody
    @RequestMapping(value="/checkCondition",method = {RequestMethod.POST,RequestMethod.GET})
    public User chechPhone(HttpServletRequest request, HttpServletResponse response, ModelAndView model,
                           @RequestParam(value="phone",required = false) String phone,
                           @RequestParam(value="email",required = false) String email){
        logger.info("## chechPhone ##");
        User user = new User();
        user.setPhone(phone);
        user.setEmail(email);
        User checkUser = userService.checkUserWithConditions(user);
        return checkUser;
    }

    /**
     * 用户注册时检查邀请码是否可用
     * @param request
     * @param response
     * @param model
     * @return
     */
    @ResponseBody
    @RequestMapping(value="/checkYqm",method = {RequestMethod.POST,RequestMethod.GET})
    public YqmInfo checkYqm(HttpServletRequest request, HttpServletResponse response, ModelAndView model,
                            @RequestParam(value="yaoqingma",required = false) String yaoqingma){
        logger.info("## checkYqm ##");
        YqmInfo yqmInfo = new YqmInfo();
        yqmInfo.setYaoqingma(yaoqingma);
        YqmInfo checkYqm = yqmInfoService.selectYqmInfoByCondition(yqmInfo);
        return checkYqm;
    }

    //用户去往修改密码
    @RequestMapping(value="/toChangePassword",method = {RequestMethod.POST,RequestMethod.GET})
    public ModelAndView toChange(HttpServletRequest request, HttpServletResponse response, ModelAndView model){
        logger.info("## toChange ##");
       model.setViewName("/user/change");
        return model;
    }

    //用户修改密码
    @RequestMapping(value="/changePassword",method = {RequestMethod.POST,RequestMethod.GET})
    public ModelAndView changePassword(HttpServletRequest request, HttpServletResponse response, ModelAndView model,
                                 @RequestParam(value="phone",required = true) String phone,
                                 @RequestParam(value="password",required = true) String password){
        logger.info("## changePassword ##");
        User user = new User();
        user.setPhone(phone);

        return model;
    }

    //用户退出
    @RequestMapping(value="/logout")
    public ModelAndView logout(HttpServletRequest request, HttpServletResponse response, ModelAndView model){
        //request.getSession().invalidate();
        logger.info("## logout ##");
        Subject subject = SecurityUtils.getSubject();
        if (subject.isAuthenticated()) {
            subject.logout(); // session 会销毁，在SessionListener监听session销毁，清理权限缓存
        }

        model.setViewName("redirect:/user/index");
        //return "redirect:/user/index";
        return model;
    }

}

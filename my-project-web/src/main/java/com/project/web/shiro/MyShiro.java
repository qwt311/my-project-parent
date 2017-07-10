package com.project.web.shiro;

import com.project.entity.RoleAuthority;
import com.project.entity.User;
import com.project.entity.UserRole;
import com.project.service.RoleAuthorityService;
import com.project.service.UserRoleService;
import com.project.service.UserService;
import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by qiaowentao on 2016/11/7.
 */
public class MyShiro extends AuthorizingRealm {

    private static Logger logger = LoggerFactory.getLogger(MyShiro.class);

    @Autowired
    private UserService userService;
    @Autowired
    private UserRoleService userRoleService;
    @Autowired
    private RoleAuthorityService roleAuthorityService;

    /**
     * 为当前登录的Subject授予角色和权限
     * @see :本例中该方法的调用时机为需授权资源被访问时
     * @see :并且每次访问需授权资源时都会执行该方法中的逻辑,这表明本例中默认并未启用AuthorizationCache
     * @param principals
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        String currentUsername = (String)super.getAvailablePrincipal(principals);
        SimpleAuthorizationInfo simpleAuthorInfo = new SimpleAuthorizationInfo();
        //实际中可能会像上面注释的那样从数据库取得

        List<String> roleList = new ArrayList<String>();
        List<String> permissionList = new ArrayList<String>();
        List<UserRole> userRoles = userRoleService.selectUserRoleWithPhone(new UserRole(currentUsername, null));
        //用户具有的角色
        if(userRoles != null && userRoles.size() > 0){
            for(UserRole userRole:userRoles){
                String rolename = userRole.getRolename();
                roleList.add(rolename);
                List<RoleAuthority> roleAuthorities = userRole.getRoleAuthorities();
                //角色具有的权限
                if(roleAuthorities != null && roleAuthorities.size() > 0){
                    for(RoleAuthority roleAuthority:roleAuthorities){
                        permissionList.add(rolename+":"+roleAuthority.getAuthorityname());
                    }
                }
            }
        }

        //roleList.add("admin");
        simpleAuthorInfo.addRoles(roleList);
        //simpleAuthorInfo.addRole("admin");
        //添加权限
        //simpleAuthorInfo.addStringPermission("admin:manage");
        //permissionList.add("admin:manage");
        simpleAuthorInfo.addStringPermissions(permissionList);
        //logger.info("已为用户["+currentUsername+"]赋予了[admin]角色和[admin:manage]权限");
        return simpleAuthorInfo;

        //若该方法什么都不做直接返回null的话,就会导致任何用户访问/admin/listUser.jsp时都会自动跳转到unauthorizedUrl指定的地址
        //详见applicationContext.xml中的<bean id="shiroFilter">的配置
        //return null;
    }

    /**
     * 验证当前登录的Subject
     * @see :本例中该方法的调用时机为LoginController.login()方法中执行Subject.login()时
     * @param authcToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authcToken) throws AuthenticationException {
//获取基于用户名和密码的令牌
        //实际上这个authcToken是从LoginController里面currentUser.login(token)传过来的
        //两个token的引用都是一样的,本例中是org.apache.shiro.authc.UsernamePasswordToken@33799a1e
        UsernamePasswordToken token = (UsernamePasswordToken)authcToken;
        //System.out.println("验证当前Subject时获取到token为" + ReflectionToStringBuilder.toString(token, ToStringStyle.MULTI_LINE_STYLE));
//      User user = userService.getByUsername(token.getUsername());
//      if(null != user){
//          AuthenticationInfo authcInfo = new SimpleAuthenticationInfo(user.getUsername(), user.getPassword(), user.getNickname());
//          this.setSession("currentUser", user);
//          return authcInfo;
//      }else{
//          return null;
//      }
        //此处无需比对,比对的逻辑Shiro会做,我们只需返回一个和令牌相关的正确的验证信息
        //说白了就是第一个参数填登录用户名,第二个参数填合法的登录密码(可以是从数据库中取到的,本例中为了演示就硬编码了)
        //这样一来,在随后的登录页面上就只有这里指定的用户和密码才能通过验证
        String username = token.getUsername();
        User user = userService.checkUserWithConditions(new User(username));
        if(user != null) {

            //用户正常
            if(user.getSyzt() == 0){
                AuthenticationInfo authcInfo = new SimpleAuthenticationInfo(username, user.getPassword(), this.getName());
                this.setSession("user", user);
                return authcInfo;
            }else if(user.getSyzt() == 1){
                throw new LockedAccountException();
            }

        }else{
            throw new UnknownAccountException();
        }
        //没有返回登录用户名对应的SimpleAuthenticationInfo对象时,就会在LoginController中抛出UnknownAccountException异常
        return null;
    }

    /**
     * 将一些数据放到ShiroSession中,以便于其它地方使用
     * @see
     */
    private void setSession(Object key, Object value){
        Subject currentUser = SecurityUtils.getSubject();
        if(null != currentUser){
            Session session = currentUser.getSession();
            logger.info("Session默认超时时间为[" + session.getTimeout() + "]毫秒");
            if(null != session){
                session.setAttribute(key, value);
            }
        }
    }

}

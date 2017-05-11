package com.xwintop.xcore.safety.service.impl;

import java.util.List;
import javax.annotation.Resource;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.stereotype.Service;

/** 
 * @ClassName: ShiroRealm 
 * @Description: 角色控制
 * @author: xufeng
 * @date: 2017年5月11日 下午3:47:26  
 */
@Service
public class ShiroRealm extends AuthorizingRealm {
	private static final Log log = LogFactory.getLog(ShiroRealm.class);

//	@Resource(name = "userService")
//	private UserService userService;

	/**
     * 获取身份信息，我们可以在这个方法中，从数据库获取该用户的权限和角色信息
     */
	public AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		if (principals == null) {
			throw new AuthorizationException("PrincipalCollection method argument cannot be null.");
		}
		String userName = (String) getAvailablePrincipal(principals);
//		User user = null;
//		List roles = null;
//		List permissions = null;
//		try {
//			user = this.userService.getUserById(userName);
//			if (user == null) {
//				throw new AuthorizationException("用户信息不存在，请重新登录.");
//			}
//			String userId = user.getId();
//			roles = this.userService.getRolesByUserId(userId);
//			permissions = this.userService.getPermissByUserId(userId);
//		} catch (KdOmsException e) {
//			log.error("根据用户ID查询用户信息抛出异常,位置[ShiroRealm -> doGetAuthorizationInfo]", e);
//		}
//		if (user.getUserName().equals(userName))
//			log.info("尊敬的: " + userName + "用户你好");
//		else {
//			log.error("尊敬的: " + userName + "用户你好，您当前登录的用户与操作的用户信息不一致，您无权限操作本系统.");
//		}
		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
//		info.addRoles(roles);
//		info.addStringPermissions(permissions);
		return info;
	}

	/**
     * 在这个方法中，进行身份验证
     */
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authcToken)
			throws AuthenticationException {
		String username = (String) authcToken.getPrincipal();
		String password = new String((char[]) (char[]) authcToken.getCredentials());
		if ((null != username) && (null != password)) {
			 //身份验证通过,返回一个身份信息
			return new SimpleAuthenticationInfo(username, password, getName());
		}
		return null;
	}
}

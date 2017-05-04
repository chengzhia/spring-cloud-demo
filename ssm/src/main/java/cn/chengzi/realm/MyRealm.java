package cn.chengzi.realm;

import javax.annotation.Resource;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import cn.chengzi.entity.User;
import cn.chengzi.service.UserService;

public class MyRealm extends AuthorizingRealm {
	@Resource 
	private UserService userService;
	
	/**
	 * 为当前用户进行授权
	 * <P>Author : ruanchengzhi </P>      
	 * <P>Date : 2016年7月15日 </P>
	 * @param arg0
	 * @return
	 * @see org.apache.shiro.realm.AuthorizingRealm#doGetAuthorizationInfo(org.apache.shiro.subject.PrincipalCollection)
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection collection) {
		String userName = (String) collection.getPrimaryPrincipal();
		SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
		//查询出用户对应的角色进行授权
		authorizationInfo.setRoles(userService.queryRole(userName));			
		//查询出用户对应的权限进行授权
		authorizationInfo.setStringPermissions(userService.queryFunction(userName));
		return authorizationInfo;
	}
	
	/**
	 * 验证当前登录的用户
	 * <P>Author : ruanchengzhi </P>      
	 * <P>Date : 2016年7月15日 </P>
	 * @param arg0
	 * @return
	 * @throws AuthenticationException
	 * @see org.apache.shiro.realm.AuthenticatingRealm#doGetAuthenticationInfo(org.apache.shiro.authc.AuthenticationToken)
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
		String userName = (String) authenticationToken.getPrincipal();
		User user = userService.queryUserByUserName(userName);
		if(user!=null){
			AuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(user,user.getPassword(),getName());
			return authenticationInfo;
		}
		return null;
	}


}

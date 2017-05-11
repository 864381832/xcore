package com.xwintop.xcore.safety.filter;

import com.google.common.base.Strings;
//import com.kingdee.oms.core.safety.bean.User;
import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.web.filter.OncePerRequestFilter;

public class SessionFilter extends OncePerRequestFilter {
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		boolean isFilter = true;
		if ((request.getHeader("x-requested-with") != null)
				&& (request.getHeader("x-requested-with").equalsIgnoreCase("XMLHttpRequest"))) {
			String excludeUri = "/userlogin.do|/checkLogin.do|/logout.do|/yzjcheckLogin.do";
			String loginPage = request.getContextPath();
			String uri = request.getRequestURI();
			uri = uri.replace(loginPage, "");
			if ((!Strings.isNullOrEmpty(uri)) && (excludeUri.indexOf(uri) != -1)) {
				isFilter = false;
			}
			if (isFilter) {
				Subject currentUser = SecurityUtils.getSubject();
				Session session = currentUser.getSession();
//				User user = (User) session.getAttribute("sessionUser");
//				if (user == null)
//					response.setHeader("sessionstatus", "timeout");
//				else
//					filterChain.doFilter(request, response);
			} else {
				filterChain.doFilter(request, response);
			}
		} else {
			filterChain.doFilter(request, response);
		}
	}
}
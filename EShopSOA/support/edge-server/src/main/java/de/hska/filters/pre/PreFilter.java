package de.hska.filters.pre;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.math.NumberUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMethod;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;

public class PreFilter extends ZuulFilter {

	private static Logger log = LoggerFactory.getLogger(PreFilter.class);

	@Override
	public String filterType() {
		return "pre";
	}

	@Override
	public int filterOrder() {
		return 1;
	}

	@Override
	public boolean shouldFilter() {
		HttpServletRequest request = RequestContext.getCurrentContext().getRequest();
		return true;
	}

	@Override
	public Object run() {
		RequestContext ctx = RequestContext.getCurrentContext();
		HttpServletRequest request = ctx.getRequest();

		log.info(String.format("%s request to %s", request.getMethod(), request.getRequestURL().toString()));

		if (request.getMethod().equals(RequestMethod.DELETE)) {
			String header = request.getHeader("userid");
			if (header != null && !header.isEmpty() && NumberUtils.isNumber(header)) {
				int userId = Integer.parseInt(header);
				boolean isAdmin = isAdmin(userId);
				// !TODO cancel this request if user is no admin
			}
		}

		return null;
	}

	private boolean isAdmin(int userId) {
		// !TODO call to rest
		// http://localhost:8081/user-api/users/isadmin/ + userId
		return false;
	}

}

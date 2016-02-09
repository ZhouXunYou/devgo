package devgo.framework.core.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;


public abstract class SuportController {
	protected HttpServletRequest getRequest() {
		return ((ServletRequestAttributes)RequestContextHolder.currentRequestAttributes()).getRequest();
	}
	protected HttpServletResponse getResponse() {
		return ((ServletRequestAttributes)RequestContextHolder.currentRequestAttributes()).getResponse();
	}
	
}

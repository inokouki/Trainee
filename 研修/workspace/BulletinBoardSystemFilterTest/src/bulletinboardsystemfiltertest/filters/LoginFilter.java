package bulletinboardsystemfiltertest.filters;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginFilter implements Filter{
	  @Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain){
	    try{
	      String target = ((HttpServletRequest)request).getRequestURI();

	      HttpSession session = ((HttpServletRequest)request).getSession();

	      if (session == null){
	        session = ((HttpServletRequest)request).getSession(true);
	        session.setAttribute("target", target);

	        ((HttpServletResponse)response).sendRedirect("/signin");
	      }else{
	        Object loginCheck = session.getAttribute("signin");
	        if (loginCheck == null){
	          session.setAttribute("target", target);
	          ((HttpServletResponse)response).sendRedirect("/signin");
	        }
	      }

	      chain.doFilter(request, response);
	    }catch (ServletException se){
	    }catch (IOException e){
	    }
	  }

	  @Override
	public void init(FilterConfig filterConfig) throws ServletException{
	  }

	  @Override
	public void destroy(){
	  }
	}
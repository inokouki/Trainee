package chapter4.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

//@WebFilter("/*")
public class TimeFilter implements Filter {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {

		System.out.println("TimeFilter# 時間の計測を開始します。");

		long start = System.currentTimeMillis();

		try {
			chain.doFilter(request, response);
		} finally {

			long end = System.currentTimeMillis();
			System.out.println("TimeFilter# 処理時間は、" + (end - start) + "ms でした。");
		}
	}

	@Override
	public void init(FilterConfig config) throws ServletException {
	}

	@Override
	public void destroy() {
	}

}
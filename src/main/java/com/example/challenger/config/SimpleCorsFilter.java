package com.example.challenger.config;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
public class SimpleCorsFilter implements Filter {
	
	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) res;

		String origin = request.getHeader("origin");
		addHeaderIfNotExist(response, "Access-Control-Allow-Origin", (origin == null || origin.isEmpty()) ? "*" : origin);
		response.addHeader("Access-Control-Expose-Headers", "Authorization");
		response.addHeader("Access-Control-Expose-Headers", "Content-Disposition");

		addHeaderIfNotExist(response, "Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE, PUT, PATCH");
		addHeaderIfNotExist(response, "Access-Control-Max-Age", "3600");
		addHeaderIfNotExist(response, "Access-Control-Allow-Headers", "X-Requested-With, Content-Type, Authorization, x-csrf-token, X-XSRF-TOKEN");
		addHeaderIfNotExist(response, "Access-Control-Allow-Credentials", "true");

		if ("OPTIONS".equalsIgnoreCase(request.getMethod())) {
			response.getWriter().print("OK");
			response.getWriter().flush();
			response.setStatus(HttpServletResponse.SC_OK);
		} else {
			chain.doFilter(req, res);
		}
	}

	private void addHeaderIfNotExist(HttpServletResponse response, String key, String value) {
		if (response.getHeader(key) == null) {
			response.setHeader(key, value);
		}
	}
}

package br.com.controlefinanceiro.filter;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletResponse;

@WebFilter(value = "/")
public class RootFilter implements Filter {

	private final static List<Locale> ACCEPTED_LOCALES = Arrays.asList(new Locale("pt", "BR"), new Locale("pt"), Locale.US, Locale.ENGLISH); 
	
	@Override
	public void destroy() {}

	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
		if(ACCEPTED_LOCALES.contains(servletRequest.getLocale()))
			((HttpServletResponse) servletResponse).sendRedirect(servletRequest.getServletContext().getContextPath().concat("/").concat(servletRequest.getLocale().getLanguage()));
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {}

}


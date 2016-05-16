package br.com.Meensina.Filter;


import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.Meensina.usuarioBean.UsuarioLoginBean;


@WebFilter("paginas/usuario/*")
public class LoginFilter implements Filter {

	@Override
	public void destroy() {
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		UsuarioLoginBean usuarioLogin = (UsuarioLoginBean)((HttpServletRequest) request).getSession().getAttribute("usuarioLogin");
		
		
		if(usuarioLogin == null || !usuarioLogin.isAutenticacao()){
			String contextPath = ((HttpServletRequest) request)
                    .getContextPath();
			((HttpServletResponse) response).sendRedirect
            (contextPath + "/paginas/principal/login.xhtml");
		}else{
			
			chain.doFilter(request, response);
		}
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		
	}

	
	
}

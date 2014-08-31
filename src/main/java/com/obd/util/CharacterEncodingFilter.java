package com.obd.util;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * encode filter: set encode for request
 * 
 * @author Wenlong Meng(wenlong.meng@gmail.com)
 * @version 1.0 at 2011/11/15
 * @since 1.0
 */
public class CharacterEncodingFilter implements Filter {
	
	//local variables
	/**
	 * default encode: UTF-8
	 */
	private String encoding = "UTF-8";

	//setter 
	/**
	 * encoding the encoding to set
	 * 
	 * @param encoding 
	 */
	public void setEncoding(String encoding) {
		if(encoding != null){
			this.encoding = encoding;
		}
	}

	/**
	 * dofilter
	 * 
	 * @param req see{@link ServletRequest}
	 * @param res see{@link ServletResponse}
	 * @param chain see{@link FilterChain}
	 * @throws IOException
	 * @throws ServletException
	 */
	public void doFilter(ServletRequest req, ServletResponse res,
			FilterChain chain) throws IOException, ServletException {
		
		//set encode for request
		HttpServletRequest request = (HttpServletRequest) req;
		request.setCharacterEncoding(encoding);
		//set encode for response
				HttpServletResponse response = (HttpServletResponse) res;
				response.setCharacterEncoding(encoding);
				res.setContentType("text/html;charset=UTF-8");
				

		//next filter
		chain.doFilter(request, res);
	}

	/**
	 * init filter
	 * 
	 * @param arg0 see {@link FilterConfig}
	 */
	public void init(FilterConfig arg0) throws ServletException {
		
	}
	
	/**
	 * destroy
	 */
	public void destroy() {

	}

}

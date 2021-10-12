package com.stackroute.favouriteservice.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwt;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureException;
import org.springframework.http.HttpMethod;

@Component
public class FavouriteServiceFilter extends GenericFilterBean {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest httprequest = (HttpServletRequest) request;
		HttpServletResponse httpresponse = (HttpServletResponse) response;
		String authheader = httprequest.getHeader("Authorization");
		if (httprequest.getMethod().equalsIgnoreCase(HttpMethod.OPTIONS.name())) {
			chain.doFilter(httprequest, httpresponse);
		} else {
		if ((authheader == null) || (!authheader.startsWith("Bearer")))
			throw new ServletException("JWT Token is missing");
		String mytoken = authheader.substring(7);

		try {
			JwtParser jparser = Jwts.parser().setSigningKey("ibmkey");

			Jwt jwtobj = jparser.parse(mytoken);

			Claims claim = (Claims) jwtobj.getBody();

		} catch (SignatureException sig) {
			throw new ServletException("signature mismatch / token expried");
		} catch (MalformedJwtException excep) {
			throw new ServletException("token is modified which is unauthorized");
		}
		chain.doFilter(request, response);
		}

	}

}

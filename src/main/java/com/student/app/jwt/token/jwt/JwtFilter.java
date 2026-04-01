package com.student.app.jwt.token.jwt;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.student.app.jwt.token.model.User;
import com.student.app.jwt.token.model.UserService;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtFilter extends OncePerRequestFilter {

	private static final Logger logger = LoggerFactory.getLogger(JwtFilter.class);

	private JWTUtility jwtUtility;

	private UserService userService;

	public JwtFilter(JWTUtility jwtUtility, UserService userService) {
		super();
		this.jwtUtility = jwtUtility;
		this.userService = userService;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
			FilterChain filterChain) throws ServletException, IOException {
		String authorization = httpServletRequest.getHeader("X-Custom-Token");
		String token = null;
		String userName = null;

		if (null != authorization && authorization.startsWith("Bearer ")) {
			token = authorization.substring(7);
			try {
			userName = jwtUtility.getUsernameFromToken(token);
			}
			catch(Exception e) {
				throw new UsernameNotFoundException("Invalid token ,pls check");
			}
		}
		logger.info("..........token userName......... {}", userName);
		if (null != userName && SecurityContextHolder.getContext().getAuthentication() == null) {
			User user = userService.getUserByName(userName);
			logger.info("..........token userName......... {} , {} ", user.getUserName(), user.getPassword());

			if (jwtUtility.isValidateToken(token, user)) {
				logger.info(".........isValidateToken......... {} , {} ", user.getUserName(), user.getPassword());
				UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
						user, null,null);

				usernamePasswordAuthenticationToken
						.setDetails(new WebAuthenticationDetailsSource().buildDetails(httpServletRequest));

				SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
			}

		}
		filterChain.doFilter(httpServletRequest, httpServletResponse);
	}
}

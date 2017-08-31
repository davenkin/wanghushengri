package davenkin.wanghushengri.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * Created by yteng on 8/31/17.
 */

@Component
public class AuditFilter extends GenericFilterBean {
    private Logger logger = LoggerFactory.getLogger(AuditFilter.class);

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest servletRequest = (HttpServletRequest) request;

        String ipAddress = servletRequest.getHeader("X-FORWARDED-FOR");
        if (ipAddress == null) {
            ipAddress = request.getRemoteAddr();
        }
        logger.info("From [{}:{}]--{}", servletRequest.getRemoteHost(), ipAddress, servletRequest.getServletPath());
        chain.doFilter(request, response);
    }
}

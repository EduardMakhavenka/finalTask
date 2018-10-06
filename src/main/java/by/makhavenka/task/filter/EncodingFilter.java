package by.makhavenka.task.filter;

import javax.servlet.*;
import java.io.IOException;

/**
 * Filter encodes data to UTF-8
 */
public class EncodingFilter implements Filter {

    private static final String ENCODING = "encoding";
    private String code;

    @Override
    public void init(FilterConfig filterConfig){
        code = filterConfig.getInitParameter(ENCODING);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        servletRequest.setCharacterEncoding(code);
        filterChain.doFilter(servletRequest,servletResponse);
    }

    @Override
    public void destroy() {
        code =  null;
    }
}

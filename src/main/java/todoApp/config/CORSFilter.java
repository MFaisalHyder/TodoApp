package todoApp.config;

import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class CORSFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletResponse httpServLetResponse = (HttpServletResponse) response;
        httpServLetResponse.setHeader("Access-Control-Allow-Origin", "*");
        httpServLetResponse.setHeader("Access-Control-Allow-Methods", "POST, GET, PUT, OPTIONS, DELETE");
        httpServLetResponse.setHeader("Access-Control-Max-Age", "3600");
        httpServLetResponse.setHeader("Access-Control-Allow-Headers", "x-requested-with, Content-Type");
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
    }
}
package javax.servlet.http;


import javax.servlet.GenericFilter;
import java.io.IOException;
import javax.servlet.*;

/**
 * Provides an abstract servlet Filter for filtering http servlet requests and responses.
 *
 * A subclass of <code>HttpFilter</code>must override the {@link #doFilter(HttpServletRequest, HttpServletResponse, FilterChain)} method.
 *
 * <p>Additionally, a subclass may override <code>init</code> and <code>destroy</code>,
 * to manage resources that are held for the life of the filter.</p>
 *
 */
public abstract class HttpFilter extends GenericFilter {


    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        if (!(req instanceof HttpServletRequest &&
                res instanceof HttpServletResponse)) {
            throw new ServletException("non-HTTP request or response");
        }

        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;

        doFilter(request, response, chain);
    }

    protected abstract void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException ;


}

package javax.servlet;

import java.util.Enumeration;
import java.util.ResourceBundle;

/**
 * Defines a generic, protocol-independent
 * filter. To write an HTTP servlet for use on the
 * Web, extend {@link javax.servlet.http.HttpFilter} instead.
 *
 * <p><code>GenericFilter</code> implements the <code>Filter</code>
 * and <code>FilterConfig</code> interfaces. <code>GenericFilter</code>
 * may be directly extended by a filter, although it's more common to extend
 * a protocol-specific subclass such as <code>HttpFilter</code>.
 *
 * <p><code>GenericFilter</code> makes writing filters
 * easier. It provides simple versions of the lifecycle methods
 * <code>init</code> and <code>destroy</code> and of the methods
 * in the <code>FilterConfig</code> interface. <code>GenericFilter</code>
 * also implements the <code>log</code> method, declared in the
 * <code>ServletContext</code> interface.
 *
 * <p>To write a generic filter, you need only
 * override the abstract <code>doFilter</code> method.
 */
public abstract class GenericFilter implements Filter, FilterConfig, java.io.Serializable {

    private static final String LSTRING_FILE = "javax.servlet.LocalStrings";
    private static ResourceBundle lStrings =
            ResourceBundle.getBundle(LSTRING_FILE);

    private transient FilterConfig config;

    @Override
    public void destroy() {

    }

    public String getInitParameter(String name) {
        FilterConfig fc = getFilterConfig();
        if (fc == null) {
            throw new IllegalStateException(
                    lStrings.getString("err.filter_config_not_initialized"));
        }

        return fc.getInitParameter(name);
    }



    public Enumeration<String> getInitParameterNames() {
        FilterConfig fc = getFilterConfig();
        if (fc == null) {
            throw new IllegalStateException(
                    lStrings.getString("err.filter_config_not_initialized"));
        }

        return fc.getInitParameterNames();
    }

    public FilterConfig getFilterConfig() {
        return config;
    }


    public ServletContext getServletContext() {
        FilterConfig fc = getFilterConfig();
        if (fc == null) {
            throw new IllegalStateException(
                    lStrings.getString("err.filter_config_not_initialized"));
        }

        return fc.getServletContext();
    }

    public void init(FilterConfig config) throws ServletException {
        this.config = config;
        this.init();
    }

    public void init() throws ServletException {

    }

    public void log(String msg) {
        getServletContext().log(getFilterName() + ": "+ msg);
    }

    public String getFilterName() {
        FilterConfig fc = getFilterConfig();
        if (fc == null) {
            throw new IllegalStateException(
                    lStrings.getString("err.servlet_config_not_initialized"));
        }

        return fc.getFilterName();
    }

}

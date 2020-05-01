package Filter;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.IOException;

public class CharacterFilter implements javax.servlet.Filter {
    String encoding =null;

    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, javax.servlet.FilterChain chain) throws ServletException, IOException {
        if (encoding!=null){
            req.setCharacterEncoding(encoding);
            String characterEncoding = req.getCharacterEncoding();
            System.out.println(characterEncoding+"前端格式");
            resp.setContentType("text/html;charset="+encoding);
        }
        chain.doFilter(req, resp);
    }

    public void init(javax.servlet.FilterConfig config) throws ServletException {
            encoding = config.getInitParameter("encoding");
    }

}

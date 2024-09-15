package tag;

import jakarta.servlet.jsp.tagext.TagSupport;
import jakarta.servlet.jsp.JspException;
import jakarta.servlet.jsp.JspWriter;


public class WelcomeTag extends TagSupport {

    public int doStartTag() throws JspException {
        try {
            JspWriter out = pageContext.getOut();
            out.print("<h1>Good Morning </h1>");

        } catch (Exception e) {
            e.printStackTrace();
        }

        return SKIP_BODY;
    }
    
}

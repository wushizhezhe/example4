//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.al.component.tags.resource;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.BodyTagSupport;
import java.io.IOException;

public abstract class AbstractResourceTag extends BodyTagSupport {
    private static final long serialVersionUID = 3014242663894323193L;
    protected String ketchup = "open";

    public AbstractResourceTag() {
    }

    public String getKetchup() {
        return this.ketchup;
    }

    public void setKetchup(String ketchup) {
        this.ketchup = ketchup;
    }

    public int doStartTag() throws JspException {
        HttpServletRequest req = (HttpServletRequest)this.pageContext.getRequest();
        HttpServletResponse resp = (HttpServletResponse)this.pageContext.getResponse();

        try {
            this.outBody(req, resp);
        } catch (IOException var4) {
        }

        return 2;
    }

    protected String getContextPath() {
        HttpServletRequest req = (HttpServletRequest)this.pageContext.getRequest();
        return req.getContextPath();
    }

    protected abstract void outBody(HttpServletRequest var1, HttpServletResponse var2) throws IOException;
}

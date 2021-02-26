package com.al.component.tags.resource;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ResourceBundleTag extends TagSupport {
    private static final long serialVersionUID = -6233992767793104329L;

    private final String isInstanceTag = "isInstanceTag";

    protected boolean isDevelopment = "development".equals(System.getProperty("AL.RunMode"));

    private StringBuffer metaContent = new StringBuffer();

    private List<String> styles = new ArrayList<String>();

    private List<String> javascripts = new ArrayList<String>();

    public boolean isDevelopment() {
        return this.isDevelopment;
    }

    public void addMetaContent(String meta) {
        this.metaContent.append(meta);
    }

    public void addJavascript(String... javascripts) {
        byte b;
        int i;
        String[] arrayOfString;
        for (i = (arrayOfString = javascripts).length, b = 0; b < i; ) {
            String js = arrayOfString[b];
            addJavascript(js);
            b++;
        }
    }

    public void addJavascript(String javascript) {
        if (!this.javascripts.contains(javascript))
            this.javascripts.add(javascript);
    }

    public void addStyle(String... styles) {
        byte b;
        int i;
        String[] arrayOfString;
        for (i = (arrayOfString = styles).length, b = 0; b < i; ) {
            String style = arrayOfString[b];
            addStyle(style);
            b++;
        }
    }

    public void addStyle(String style) {
        if (!this.styles.contains(style))
            this.styles.add(style);
    }

    public int doStartTag() throws JspException {
        if (getValue("isInstanceTag") != null)
            return 0;
        addStyle("themes/blueprint/screen.css");
        if (this.isDevelopment) {
            addJavascript("jquery/jquery-migrate.js");
        } else {
            addJavascript("jquery/jquery-migrate.min.js");
        }
        setValue("isInstanceTag", Boolean.valueOf(true));
        return 1;
    }

    public int doEndTag() throws JspException {
        HttpServletRequest req = (HttpServletRequest)this.pageContext.getRequest();
        HttpServletResponse resp = (HttpServletResponse)this.pageContext.getResponse();
        try {
            this.pageContext.getOut().println(this.metaContent.toString());
            outStyleBody(req, resp);
            outJavascriptBody(req, resp);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return 6;
    }

    protected String getContextPath() {
        HttpServletRequest req = (HttpServletRequest)this.pageContext.getRequest();
        return req.getContextPath();
    }

    protected void outStyleBody(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String contextPath = getContextPath();
        String prefix = "<link href=\"" + contextPath + "/resources/";
        String suffix = "\" rel=\"stylesheet\" type=\"text/css\" />\n";
        StringBuffer buff = new StringBuffer();
        for (String style : this.styles)
            buff.append(prefix).append(style).append(suffix);
        this.pageContext.getOut().println(buff.toString());
    }

    protected void outJavascriptBody(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String contextPath = getContextPath();
        String prefix = "<script type=\"text/javascript\" src=\"" + contextPath + "/resources/";
        String suffix = "\"></script>\n";
        StringBuffer buff = new StringBuffer();
        for (String javascript : this.javascripts)
            buff.append(prefix).append(javascript).append(suffix);
        this.pageContext.getOut().println(buff.toString());
    }
}

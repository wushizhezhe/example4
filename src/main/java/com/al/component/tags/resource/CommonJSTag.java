//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.al.component.tags.resource;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CommonJSTag extends AbstractResourceTag {
    private static final long serialVersionUID = -9002871027504613402L;

    public CommonJSTag() {
    }

    protected void outBody(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String contextPath = this.getContextPath();
        String prefix = "<script type=\"text/javascript\" src=\"" + contextPath + "/resources/";
        String suffix = "\"></script>\n";
        StringBuffer buff = new StringBuffer();
        boolean isDevelopment = "development".equals(System.getProperty("AL.RunMode"));
        if (isDevelopment) {
            buff.append(prefix).append("jquery/jquery-migrate.js").append(suffix);
        } else {
            buff.append(prefix).append("jquery/jquery-migrate.min.js").append(suffix);
        }

        if ("open".equals(this.ketchup)) {
            buff.append(prefix).append("jquery/plugin/ketchup/jquery.ketchup.js").append(suffix);
            buff.append(prefix).append("jquery/plugin/ketchup/jquery.ketchup.helpers.js").append(suffix);
            buff.append(prefix).append("jquery/plugin/ketchup/jquery.ketchup.validations_zh.js").append(suffix);
        }

        buff.append(prefix).append("json2.js").append(suffix);
        buff.append(prefix).append("com.al.common.CommonUtils.js").append(suffix);
        buff.append(prefix).append("com.al.common.ContextHolder.js").append(suffix);
        buff.append(prefix).append("com.al.common.CoverHolder.js").append(suffix);
        buff.append(prefix).append("com.al.common.AjaxHolder.js").append(suffix);
        buff.append(prefix).append("com.al.common.ValidatorAdapter.js").append(suffix);
        buff.append(prefix).append("com.al.common.FormHolder.js").append(suffix);
        this.pageContext.getOut().println(buff.toString());
    }
}

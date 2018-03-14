package com.tsalapova.springaop.tag;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.BodyTagSupport;
import java.io.IOException;

/**
 * @author TsalapovaMD
 * @version 1.0, 2/10/2018
 */
public class InputFieldTag extends BodyTagSupport {
    private static final String REQUIRED = "required";
    private static final String DISABLED = "disabled";

    private String type;
    private String label;
    private String labelType;
    private String name;
    private String required;
    private String disabled;

    public void setType(String type) {
        this.type = type;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public void setLabelType(String labelType) {
        this.labelType = labelType;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setRequired(String required) {
        this.required = required;
    }

    public void setDisabled(String disabled) {
        this.disabled = disabled;
    }

    protected void writeStartTag(String typePattern) throws JspFieldTagException {
        JspWriter out = pageContext.getOut();
        try {
            out.write("    <div class=\"mb-2 input-group\">\n" + "        <label for=\"" + name + "\" class=\"" +
                    labelType + "-label mb-0 pt-2\"><b>" + label + ": </b></label>\n");
            out.write("        <input class=\"form-control " + (DISABLED.equals(disabled) ? " disabled aria-disabled" : "") +
                    "\" " + typePattern + "\n               id=\"" + name + "\" name=\"" + name + "\" size=\"50\" value=\"");
        } catch (IOException e) {
            throw new JspFieldTagException("Exception occurred while evaluating field tag", e);
        }
    }

    @Override
    public int doStartTag() throws JspException {
        writeStartTag(InputType.valueOf(type.replace('-', '_').toUpperCase()).getPattern());
        return EVAL_BODY_INCLUDE;
    }

    @Override
    public int doAfterBody() throws JspFieldTagException {
        return SKIP_BODY;
    }

    @Override
    public int doEndTag() throws JspFieldTagException {
        try {
            pageContext.getOut().write("\" " + (REQUIRED.equals(required) ? "required=\"required\" " : "") +
                    (DISABLED.equals(disabled) ? "disabled " : "") + "/>\n    </div>");
        } catch (IOException e) {
            throw new JspFieldTagException("Exception occurred while evaluating field tag", e);
        }
        return EVAL_PAGE;
    }
}

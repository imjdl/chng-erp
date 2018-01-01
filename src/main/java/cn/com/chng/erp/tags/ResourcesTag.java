package cn.com.chng.erp.tags;

import org.apache.commons.lang.StringUtils;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import java.io.IOException;

/**
 * Created by liuyandong on 2017/6/6.
 */
public class ResourcesTag extends SimpleTagSupport {
    private String tagName = null;
    private String type = null;
    private String rel = null;
    private String dir = null;
    private String file = null;
    private String base = null;

    public void setTagName(String tagName) {
        this.tagName = tagName;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setRel(String rel) {
        this.rel = rel;
    }

    public void setDir(String dir) {
        this.dir = dir;
    }

    public void setFile(String file) {
        this.file = file;
    }

    public void setBase(String base) {
        this.base = base;
    }

    @Override
    public void doTag() throws JspException, IOException {
        StringBuffer link = new StringBuffer();
        if (StringUtils.isNotBlank(base)) {
            link.append(base).append("/");
        }
        link.append(dir).append("/").append(file);
        getJspContext().getOut().write(link.toString());
    }
}

package cn.com.chng.erp.tags;

import javax.servlet.jsp.tagext.SimpleTagSupport;
import java.io.IOException;

/**
 * Created by liuyandong on 2017/6/20.
 */
public class CopyrightTag extends SimpleTagSupport {
    @Override
    public void doTag() throws IOException {
        getJspContext().getOut().write("");
    }
}

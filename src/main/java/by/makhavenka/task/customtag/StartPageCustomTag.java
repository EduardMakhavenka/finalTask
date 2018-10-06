package by.makhavenka.task.customtag;

import by.makhavenka.task.service.ServiceConstants;
import by.makhavenka.task.language.ConfigLocale;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;

@SuppressWarnings("serial")

public class StartPageCustomTag extends TagSupport {

    private String role;
    private String locale;

    public void  setRole(String role){
        this.role = role;
    }

    public void  setLocale(String locale){
        if(locale.equalsIgnoreCase(ServiceConstants.RU_RU)){
            locale = ServiceConstants.RU;
        } else if(locale.equalsIgnoreCase(ServiceConstants.EN_EN)) {
            locale = ServiceConstants.EN;
        } else if(locale.equalsIgnoreCase(ServiceConstants.FR_FR)){
            locale = ServiceConstants.FR;
        }
        this.locale = locale;
    }

    /**
     * Create new custom tag
     * @return
     * @throws JspException
     */
    @Override
    public int doStartTag() throws JspException {
        String tag;

        if(role.isEmpty()){
          tag = new ConfigLocale(locale).getConfigLocale(ServiceConstants.TAG);
        } else if(Integer.parseInt(role)==1){
          tag = new ConfigLocale(locale).getConfigLocale(ServiceConstants.TAG_ONE);
        } else {
          tag = new ConfigLocale(locale).getConfigLocale(ServiceConstants.TAG_TWO);
        }

        try {
            pageContext.getOut().write(ServiceConstants.H4+ tag+ServiceConstants.H4);
        } catch (IOException e){
            throw new JspException("CustomTag error",e);
        }
        return SKIP_BODY;
    }
}

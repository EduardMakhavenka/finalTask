package by.makhavenka.task.language;

import by.makhavenka.task.service.ServiceConstants;

import java.util.Locale;
import java.util.ResourceBundle;

public class ConfigLocale {

    private String sessionLocale;
    private ResourceBundle bundle;

    public  ConfigLocale(String sessionLocale){
        this.sessionLocale=sessionLocale;
    }

    public void setResourceBundle(String sessionLocale){
        if(sessionLocale!=null){
            if(sessionLocale.equals(ServiceConstants.FR)){
                bundle=ResourceBundle.getBundle(ServiceConstants.LOCALE,new Locale(ServiceConstants.FR));
            }
            if(sessionLocale.equals(ServiceConstants.EN)){
                bundle=ResourceBundle.getBundle(ServiceConstants.LOCALE,new Locale(ServiceConstants.EN));
            }
            if(sessionLocale.equals(ServiceConstants.RU)){
                bundle=ResourceBundle.getBundle(ServiceConstants.LOCALE,new Locale(ServiceConstants.RU));
            }
        } else {
            bundle = ResourceBundle.getBundle(ServiceConstants.LOCALE,new Locale(ServiceConstants.RU));
        }
    }

    public String getConfigLocale(String key){
        setResourceBundle(sessionLocale);
        return bundle.getString(key);
    }
}

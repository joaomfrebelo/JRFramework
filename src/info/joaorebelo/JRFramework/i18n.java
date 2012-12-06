/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package info.joaorebelo.JRFramework;

import java.util.Locale;
import java.util.ResourceBundle;

/**
 *
 * @author joao
 */
public class i18n {
    
    /**
     * Resource bundle
     */
    private ResourceBundle msg;
    
    public i18n(String lang,String country){
        try{
            
            if(lang == null){
                lang = "en";
                country = "US";
            }
            Locale locale = new Locale(lang, country);
            this.msg = ResourceBundle.getBundle("info.joaorebelo.JRFramework.i18n", locale);
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
    
    /**
     * Get resorce for key s
     * @param s
     * @return 
     */
    public String getString(String s){
        try{
          return this.msg.getString(s);  
        }catch(Exception e){
           return s;
        }        
    }
    
    /**
     * Get the ResourceBundle class
     * @return 
     */
    public ResourceBundle getResourceBundle(){
        return this.msg;
    }
    
}

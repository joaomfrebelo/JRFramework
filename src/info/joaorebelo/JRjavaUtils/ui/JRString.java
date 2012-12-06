/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package info.joaorebelo.JRjavaUtils.ui;

import java.util.Collection;
import java.util.HashMap;
import javax.swing.JTextField;

/**
 *
 * @author joao
 */
public class JRString {
    
    
    /**
     * Capitaliza the first character
     * @param s the strign that is to capitalize the first character
     * @return the string s with the first character capitalized
     */
    public static String ucfirst(String s){
        if(s.length()>1){
            return s.substring(0, 1).toUpperCase() + s.substring(1);
        }else{
           return s.toUpperCase();
        }
    }
    
    /**
     * 
     * Join a colletion (PHP like)
     * 
     * @param glue
     * @param collection 
     * @return 
     */
    public static String join(String glue, Collection<?> collection){
        if(collection == null){ return null; }
        if(collection.isEmpty()){ return "";}
        String s = "";
        for(Object sObj : collection){
            if(sObj instanceof JTextField){
                JTextField sCol = (JTextField)sObj; 
                s += sCol.getText();
            }else if(sObj instanceof String){
                String sCol = (String)sObj;
                s += sCol;
            }else if(sObj instanceof Integer){
                Integer sCol = (Integer)sObj;
                s += sCol.toString();
            }else{
                s += sObj.toString();
            }
            s += glue;
        }
        if("".equals(glue)){
            return s;
        }
        s = s.substring(s.lastIndexOf(glue), s.lastIndexOf(glue) + s.length());
        return s;
    }
    
    /**
     * 
     * Join a colletion (PHP like)
     * 
     * @param glue
     * @param HashMap
     * @return 
     */
    public static String join(String glue, HashMap<?,String> collection){
       return JRString.join(glue, collection.values());
    }   
    
    
    /**
     * Netbeans whene create hibernate classes for database
     * if the column name have an underscore "_" will eliminate
     * the underscore and will capitalize the nexte char for proprety name
     * this method gives the propertive name for string column name
     * to be used in criteria queries for example
     * 
     * @param columnName
     * @return 
     */
    public static String columnName2HibernateProperty(String columnName){
        String[] parts = columnName.split("_");
        Integer i = 0;
        HashMap<Integer, String> newParts = new HashMap();
        for(String p : parts){
            if(i==0){
                newParts.put(i, p);
            }else{
                newParts.put(i, JRString.ucfirst(p));
            }
            i++;
        }
        return JRString.join("", newParts);
    }
    
    
    
}

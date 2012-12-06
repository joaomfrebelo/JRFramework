package info.joaorebelo.JRjavaUtils.table;

import info.joaorebelo.JRjavaUtils.ui.JRString;
import java.util.Collection;

/**
 *
 * @author joao
 */
public class JRtableSearch {

    /**
     * 
     * To be used wher your condition is not IN neither NOT IN
     * 
     * @param column
     * @param cond
     * @param search
     * @throws Exception 
     */
    public JRtableSearch(String column, conditionType cond, String search)
    throws Exception
    {
        if(column == null){
            throw new Exception("String column can not be null"); 
        }
        if(search == null){
           if(cond != conditionType.inu && cond != conditionType.nn){
               throw new Exception("Search only can be null whene cond is \"nn\" or \"inu\".");
           }
        }
        this.column = column;
        this.search = search;
        this.condition = cond.toString();
    }
    
    /**
     * 
     * To be used whene you condition is IN or NOT IN
     * 
     * @param column
     * @param cond
     * @param search
     * @throws Exception 
     */
    public JRtableSearch(String column, conditionType cond, Collection<String> search)
    throws Exception
    {
        if(column == null){
            throw new Exception("String column can not be null"); 
        }
        if(search != null){
           if(cond != conditionType.in && cond != conditionType.nin){
               throw new Exception("Search only can be collection whene cond is \"in\" or \"nin\".");
           }
        }
        this.column = column;
        this.search = "('" + JRString.join("', '", search) + "')";
        this.condition = cond.toString();
    }   
    
    
    /**
     * Types of conditions to search to build teh where clause in T-SQL
     */
    public enum conditionType{
       /**
        * greater than
        */ 
       gt,
       /**
        * greater than or iqual
        */
       gtiq,
       /**
        * iqual
        */
       iq,
       /**
        * less than
        */
       lt,
       /**
        * less than or iqual
        */
       lti,
       /**
        * start with
        */
       sw,
       /**
        * not start with
        */
       nsw,
       /**
        * ends with
        */
       ew, 
       /**
        * not ends with
        */
       nenw,
       /**
        * contains
        */
       co,
       /**
        * not contains
        */
       nco,
       /**
        * not null
        */
       nn,
       /**
        * is null
        */
       inu,
       /**
        * in
        */
       in,
       /**
        * not in
        */
       nin
    }

    /**
     * Column to perform the where clause
     */
    private String column;

    /**
     *  gt => greater than
     *  gtiq => greater than or iqual
     *  iq => iqual
     *  lt => less than
     *  lti => less than or iqual
     *  sw => start with
     *  nsw => not start with
     *  ew => ends with
     *  new => not ends with
     *  co => contains
     *  nco => not contains
     *  nn => not null
     *  inu => is null
     *  in => in
     *  nit => not in
     */
    private String condition;

    /**
     * The string to search
     */
    private String search;
    
    /**
     * Get column to filter
     * @return 
     */
    public String getColumn() {
        return column;
    }

    /**
     * Get condition
     * @return 
     */
    public String getCondition() {
        return condition;
    }
    
    /**
     * Get the string to search for
     * @return 
     */
    public String getSearch() {
        return search;
    }
    
    
    public String getTSQLfilter(String encloseIdentifier){
        if(encloseIdentifier == null){
            encloseIdentifier = "";
        }
        String s =  "(" + encloseIdentifier + this.column + encloseIdentifier;
        
        switch(this.condition){
              case "gt":
                  s += " > '" + this.getSearch() + "'";
                  break;
              case "gtiq":
                  s += " >= '" + this.getSearch() + "'";
                  break;
              case "iq":
                  s += " = '" + this.getSearch() + "'";
                  break;
              case "lt":
                  s += " < '" + this.getSearch() + "'";
                  break;
              case "lti":
                  s += " <= '" + this.getSearch() + "'";
                  break;
              case "sw":
                  s += " LIKE '" + this.getSearch() + "%'";
                  break;
              case "nsw":
                  s += " NOT LIKE '" + this.getSearch() + "%'";
                  break;
              case "ew":
                  s += " LIKE '%" + this.getSearch() + "'";
                  break;
              case "nenw":
                  s += " NOT LIKE '%" + this.getSearch() + "'";
                  break;
              case "co":
                  s += " LIKE '%" + this.getSearch() + "%'";
                  break;
              case "nco":
                  s += " NOT LIKE '%" + this.getSearch() + "%'";
                  break;
              case "nn":
                  s += " IS NOT NULL ";
                  break;
              case "inu":
                  s += " IS NULL ";
                  break;
              case "in":
                  s += " IN  " + this.getSearch();
                  break;
              case "nin":
                  s += " NOT IN  " + this.getSearch();
                break;
        }
        
        return s+ ")";
    }

}

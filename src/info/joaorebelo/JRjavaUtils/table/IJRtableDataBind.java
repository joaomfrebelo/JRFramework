package info.joaorebelo.JRjavaUtils.table;


import java.util.Vector;

/**
 *
 * @author joao
 */
public interface  IJRtableDataBind {
    
    /**
     * Get native columns Name
     * @return 
     */
    abstract Vector<String> getColumnsName();
    
    /**
     * Get columns name translation
     * @return 
     */
    abstract Vector<String> getColumnsNameI18n();
    
    /**
     * Get the table title
     * @return 
     */
    abstract String getTableTitle();
    
    /**
     * 
     * Get the total tables total number of rows
     * if search is not null, will return the total 
     * rows filtered
     * 
     * @param search
     * @return 
     */
    abstract Integer getTotalRecords();
    
    
    abstract JRtableDataResult bind(Integer page, Integer numRecords, String orderBy, String orderType);
    
     /**
      * Handles the add record button action
      */
     abstract void AddRecordButoanAction();
     
     /**
      * Handles the update record button action
      */
     abstract void updateRecordButoanAction();
     
     /**
      * Handles the delete buttons action
      */
     abstract void delRecordButoanAction();
     
     /**
      * Handles the search button action
      */
     abstract void searchButtonAction();
     
     /**
      * add record to database
      * @return 
      */
     abstract boolean addRecord();
     
     /**
      * update record in database
      * @return 
      */
     abstract boolean updateRecord();
     
     /**
      * delete record in database
      * @return 
      */
     abstract boolean delRecord();
     
     /**
      * Get the JRtable
      * @return 
      */
     abstract JRtable getJRtable();
     
     /**
      * To change the default cell render
      */
     abstract void setDefaultCellRender();
     
     /**
      * Create JRtable
      */
     abstract void createJRtable();
}

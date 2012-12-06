
package info.joaorebelo.JRjavaUtils.table;

import java.util.Collection;
import java.util.HashSet;
import java.util.Vector;

/**
 *
 * @author joao
 */
public class JRtableDataResult {
    
    /**
     * Variable declaration for
     * page, number of records in result, total pages and total record of table
     */
    private Integer page, totalPages, totalRecords;
    
    /**
     * Vector of result rows
     */
    private Collection<Vector<Object>> rows;

    public JRtableDataResult() {
        this.rows = new HashSet();
    }

    /**
     * get the page number of the retrived data
     * @return 
     */
    public Integer getPage() {
        return page;
    }

    /**
     * Get the number of rows retrived 
     * @return 
     */
    public Integer getResultNumRecords() {
        return this.rows.size();
    }

    /**
     * Get the total number of pages
     * @return 
     */
    public Integer getTotalPages() {
        return totalPages;
    }

    /**
     * Get the number of records in the table of filtered
     * @return 
     */
    public Integer getTotalRecords() {
        return totalRecords;
    }

    /**
     * Get rows retrived from database
     * @return 
     */
    public Collection<Vector<Object>> getRows() {
        return rows;
    }

    /**
     * Set the page number
     * @param page 
     */
    public void setPage(Integer page) {
        this.page = page;
    }

    /**
     * Set the total pages in table
     * @param totalPages 
     */
    public void setTotalPages(Integer totalPages) {
        this.totalPages = totalPages;
    }

    /**
     * Set the total record in the table of filtered
     * @param totalRecords 
     */
    public void setTotalRecords(Integer totalRecords) {
        this.totalRecords = totalRecords;
    }

    /**
     * Add a row
     * @param row 
     */
    public void addRow(Vector<Object> row) {
        this.rows.add(row);
    }
    
    
    
}

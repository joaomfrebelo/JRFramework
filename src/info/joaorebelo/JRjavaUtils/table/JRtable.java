/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package info.joaorebelo.JRjavaUtils.table;

import info.joaorebelo.JRjavaUtils.ui.JRPanelBackgrond;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Vector;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableModel;

/**
 *
 * @author joao
 */
public class JRtable extends javax.swing.JPanel 
implements MouseListener{

    /**
     * Table model
     */
    private javax.swing.table.DefaultTableModel tBody;
    /**
     * JTable that will be tha table data
     */
    private JTable table;
    /**
     * JScrollPane where the table wil be render
     */
    private JScrollPane scrollPanel;
    
    /**
     * Table title panel
     */
    private JRPanelBackgrond titlePanel;
    
    /**
     * Table title
     */
    private JLabel tableTitle;

    /**
     * The navigation panel associated with this JRTable
     */
    private JRtableNavigation nav;
    

    /**
     * The table data must implements IJRtableDataBind
     */
    private IJRtableDataBind JRtableDataBind;
    
    /**
     * 
     * @param object must implements IJRtableDataBind
     */
    public JRtable(IJRtableDataBind object) {
        try {
            this.table = new JTable();
            this.table.setAutoCreateRowSorter(true);
            this.scrollPanel = new javax.swing.JScrollPane();
            this.setLayout(new BorderLayout());
            this.JRtableDataBind = object;
            this.tBody = new DefaultTableModel(object.getColumnsNameI18n(), 0){
                @Override
                public boolean isCellEditable(int row, int column) {
                    return false;
                }
            };
            this.tableTitle = new JLabel(object.getTableTitle());
            this.titlePanel = new JRPanelBackgrond(null,new FlowLayout(FlowLayout.LEFT));
            this.titlePanel.add(tableTitle);
            // init title
            this.add(this.titlePanel, BorderLayout.NORTH);
            
            this.createTable();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }


    /**
     *
     * Get the table component
     *
     * @return JTable
     */
    public JTable getTable() {
        return this.table;
    }

    /**
     *
     * Get the table's DefaultTableModel
     *
     * @return DefaultTableModel
     */
    public DefaultTableModel getDefaultModel() {
        return this.tBody;
    }

    /**
     *
     * Get the JScrollPane where the table wil be render
     *
     * @return JScroolPane
     */
    public JScrollPane getScrollPanel() {
        return this.scrollPanel;
    }

    /**
     * Set the table title
     * @param title 
     */
    public void setTitle(JLabel title){
        this.tableTitle = title;
    }
    
    /**
     * Set table title
     * @param title 
     */
    public void setTitle(String title){
        this.tableTitle.setText(title);
    }
    
    /**
     * Get the table title
     * @return 
     */
    public JLabel getTitle(){
        return this.tableTitle;
    }
    
    /**
     * Get the title panel
     * @return 
     */
    public JRPanelBackgrond getTitlePanel(){
        return this.titlePanel;
    }
    
    /**
     * 
     * Generate the table
     */
    private void createTable() {
        try {
            this.table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
            this.table.getTableHeader().setVisible(true);
            this.scrollPanel.setViewportView(table);
            this.add(this.scrollPanel, BorderLayout.CENTER);
            this.table.setModel(this.tBody);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    /**
     * Set the navigation bar to the table
     * @param navigation
     * @return JRtable
     */
    public JRtable setNavigation(JRtableNavigation navigation){
        try{
            this.nav = navigation;
            this.add(this.nav, BorderLayout.SOUTH);
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        return this;
    }
    
    /**
     * get the navigation associated with this table
     * @return 
     */
    public JRtableNavigation getNavigation(){
        return this.nav;
    }
    
    /**
     * Add column
     *
     * @param column
     */
    public void addColumn(String column) {
        try {
            this.tBody.addColumn(column);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    /**
     * Add a group of columns
     *
     * @param column
     */
    public void addColumn(String[] column) {
        try {
            this.tBody.addColumn(column);
        } catch (Exception e) {
        }
    }

    /**
     *
     * add a group of columns
     *
     * @param list
     */
    public void addColumn(ArrayList<String> list) {
        try {
            for (String s : list) {
                this.tBody.addColumn(s);
            }
        } catch (Exception e) {
        }

    }

    
    public void createSort(){
        JTableHeader h = this.table.getTableHeader();
        h.addMouseListener(this);
        System.out.println(h.toString());
        
       
    }
    
    
    /**
     * to alter /delete bind database
     */
    public void addTableRow(Vector<String> v) {

        try {

            this.tBody.addRow(v);
            this.tBody.fireTableDataChanged();
        } catch (Exception e) {
        }


    }

   
    public void addRow(){
        try{
              this.JRtableDataBind.AddRecordButoanAction();
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        
    }
    
    public void editRow(){
        try{
            this.JRtableDataBind.updateRecordButoanAction();
        }catch(Exception e){
            System.out.println(e.getMessage());  
        }
        
    }
    
    public void delRow(){
        try{
            this.JRtableDataBind.delRecordButoanAction();
        }catch(Exception e){
              System.out.println(e.getMessage());
        }
        
    }

    
    public void bind(){
        try{
            
            this.tBody.setRowCount(0);
            
            if(this.nav.pageField.getText().equals("")){
                this.nav.pageField.setText("1");
            }
            Integer page = this.nav.getPage();
            Integer numRows = this.nav.getNumberOfRowsSelected();
            String column = this.nav.getSortColumnSelected();
            String sortType = this.nav.getSorTypeSelected();
            
            
            Double maxPageD = Math.ceil((double)this.JRtableDataBind.getTotalRecords() / (double)this.nav.getNumberOfRowsSelected());
            Integer maxPage = maxPageD.intValue();
            if(page> maxPage){
                page = maxPage;
                this.nav.pageField.setText(page.toString());
            }
            
            JRtableDataResult  result =   this.JRtableDataBind.bind(
                                      page, 
                                      numRows, 
                                      column, 
                                      sortType);
            
            Integer rn = 0;
            
            for(Vector<Object> v : result.getRows()){
                this.tBody.insertRow(rn++, v);
            }
            
       
            
            
            this.nav.setTotalPages(result.getTotalPages());
            this.nav.setTotalRecords(result.getTotalRecords());
            this.nav.setShowFromRecord(((result.getPage() - 1) * result.getResultNumRecords())+1);
            this.nav.setShowToRecord(((result.getPage() -1) * result.getResultNumRecords()) + result.getResultNumRecords()   );
            
        }catch(Exception e){
              System.out.println(e.getMessage());
        }
        
    }
    
    
    public void nextPage(){
        try{
            Integer rowsCount = this.JRtableDataBind.getTotalRecords();
            if(rowsCount <= 1){
                this.nav.pageField.setText("1");
            }else{
                Double maxPageD = Math.ceil((double)rowsCount / (double)this.nav.getNumberOfRowsSelected());
                Integer maxPage = maxPageD.intValue();
                Integer nP;
                Integer p = new Integer(this.nav.pageField.getText());
                if(p >= maxPage){
                    nP = maxPage;
                }else{
                    nP = p + 1;
                }
                this.nav.pageField.setText(nP.toString());
            }
            this.bind();
        }catch(Exception e){
              System.out.println(e.getMessage());
        }
    }
    
    public void previousPage(){
        try{
            Integer page = new Integer(this.nav.pageField.getText());
            page = page == 1 ? 1 : page -1;
            this.nav.pageField.setText(page.toString());
            this.bind();
        }catch(Exception e){
              System.out.println(e.getMessage());
        }
    }
    
    public void firstPage(){
        try{
            this.nav.pageField.setText("1");
            this.bind();
        }catch(Exception e){
              System.out.println(e.getMessage());
        }
    }
    
    public void lastPage(){
        try{
            Integer rowsCount = this.JRtableDataBind.getTotalRecords();
            if(rowsCount <= 1){
                this.nav.pageField.setText("1");
            }else{
                Double maxPageD = Math.ceil((double)rowsCount / (double)this.nav.getNumberOfRowsSelected());
                Integer maxPage = maxPageD.intValue();
                this.nav.pageField.setText(maxPage.toString());
            }
            this.bind();
        }catch(Exception e){
              System.out.println(e.getMessage());
        }
    }

    /**
     * Search data in table
     */
    public void search(){
        this.JRtableDataBind.searchButtonAction();
    }
    
    
    @Override
    public void mouseClicked(MouseEvent e) {
        
    }
    
    @Override
    public void mousePressed(MouseEvent e) {
        
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        
    }

    @Override
    public void mouseEntered(MouseEvent e) {
       
    }

    @Override
    public void mouseExited(MouseEvent e) {
        System.out.println("exited");
    }
    
}

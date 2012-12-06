/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package info.joaorebelo.JRjavaUtils.table;

import info.joaorebelo.JRFramework.i18n;
import info.joaorebelo.JRjavaUtils.ui.JRPanelBackgrond;
import info.joaorebelo.JRjavaUtils.ui.JRString;
import info.joaorebelo.JRjavaUtils.ui.JRcomboOption;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author joao
 */
public class JRtableNavigation extends JPanel 
implements ActionListener, KeyListener, FocusListener {

    /**
     * 18n resources
     */
    protected i18n i18n;
    
    /**
     * Name of the component for page load number
     */
    public static final String PAGE_LOAD_NUMEBR = "page_load_number";
    /**
     * Name of the componente that have the "page" word
     */
    public static final String PAGE_WORD_PAGINATION = "page_word_pagination";
    /**
     * Name of the component that have the "of" word
     */
    public static final String OF_WORD_PAGINATION = "of_word_pagination";
    /**
     * Name of the component that have the "of" word in tha info section
     */
    public static final String OF_WORD_INFO = "of_word_info";
    /**
     * Name of the component that have the "view record" word in the info
     * section
     */
    public static final String SHOW_RECORD_WORD_INFO = "view_record_WORD_info";
    /**
     * Name of the component that have the "showTo" word in the info section
     */
    public static final String TO_WORD_INFO = "to_word_info";
    /**
     * Name of the component that is showing the start record number
     */
    public static final String SHOW_RECORD_FROM_INFO = "show_record_from_info";
    /**
     * Name of the component that is showing the start record number
     */
    public static final String SHOW_RECORD_TO_INFO = "show_record_to_info";
    /**
     * Name of the component that inform the number of total records
     */
    public static final String TOTAL_RECORDS_INFO = "total_records_info";
    /**
     * Name of the component that have the
     */
    public static final String TOTAL_PAGES = "total_pages";
    /**
     * Name of the component that have the sort word;
     */
    public static final String SORT_WORD = "sort_word";
    /**
     * Name of the component that have the sorted column
     */
    public static final String SORTED_COLUMN = "sorted_column";
    /**
     * Name of the component that have the sorted type
     */
    public static final String SORT_TYPE = "sorted_type";
    /**
     * Name of the component where is selected the number of rows pwe page
     */
    public static final String NUM_ROWS_SELECTION = "num_rows_selection";
    
    
    /**
     * JRtable controlled by this navigation
     */
    private JRtable JRtable;
    /**
     * JPannel where buttons wil be render
     */
    public JRPanelBackgrond buttonsPanel;
    /**
     * Pagination panel
     *
     * is the center of JRtableNavigation pane
     */
    public JRPanelBackgrond paginationPanel;
    /**
     * Label info panel
     *
     * is the est of the JRtableNavigation
     *
     */
    public JRPanelBackgrond infoPanel;
    /**
     * Panle where is the sort controls
     */
    public JPanel sortPanel;
    /**
     * Path showTo the button icons
     */
    public static final String ICON_PATH = "/resources/Extras_16_16/";

    /**
     * Font for combobox
     */
    public Font comboFont = new Font("TIMES", 9, 9);
    
    /**
     * Page showTo show Field
     */
    public JTextField pageField = new JTextField();
    
    /**
     * Sort columns combobox
     */
    public JComboBox<JRcomboOption> sortColumnCombobx = new JComboBox();
    
   /**
    * Sort type combobox
    */ 
   public JComboBox<JRcomboOption> sortTypeCombo = new JComboBox();
   
   /**
    * JLabel that wil indicate the maxim page
    */
   JLabel totalPg;
   
   /**
    * Number of rows per table
    */
   public JComboBox selectNumRows = new JComboBox();
   
   /**
    * Number of records label
    */
   public JLabel totalLabel;
   
   /**
    * Show from record x info label
    */
   public JLabel showFrom;
   
   /**
    * Showing to record x info label
    */
   public JLabel showTo;
   
   /**
    * Add row button
    */
   public JRtableButton addButton;
   
   /**
    * edit row button
    */
   public JRtableButton editButton;
   
   /**
    * Del row button
    */
   public JRtableButton delButton;
   
   /**
    * Refresh button
    */
   public JRtableButton refreshButton;
   
   /**
    * Search button
    */
   public JRtableButton searchButton;
   
   /**
    * The dimension of 
    */
   public Dimension navButtonDim = new Dimension(25,25);
   
    /**
     * Class contructur
     */
   public JRtableNavigation(JRtable JRtable, i18n i18n) {
        this.JRtable = JRtable;
        this.i18n = i18n;
        this.buttonsPanel = new JRPanelBackgrond(null, new FlowLayout());
        this.paginationPanel = new JRPanelBackgrond(null, new FlowLayout());
        this.infoPanel = new JRPanelBackgrond(null, new FlowLayout());
        this.sortPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        this.sortPanel.setOpaque(false);
        this.initComponents();
    }

    /**
     * init the components
     */
    private void initComponents() {
        this.setLayout(new BorderLayout());
        this.add(this.buttonsPanel, BorderLayout.WEST);
        this.add(this.paginationPanel, BorderLayout.CENTER);
        this.add(this.infoPanel, BorderLayout.EAST);
    }

    /**
     *
     * Get the buttons panel
     *
     * @return JPanel
     */
    public JPanel getButtonPanel() {
        return this.buttonsPanel;
    }

    /**
     *
     * Get thr pagination panel
     *
     * @return JPanel
     */
    public JPanel getPaginationPanel() {
        return this.paginationPanel;
    }

    /**
     * Return the pahe number
     * @return 
     */
    public Integer getPage(){
        return new Integer(this.pageField.getText());
    }
    
    /**
     *
     * Get the infoPanel
     *
     * @return JPanel
     */
    public JPanel getLabelPanel() {
        return this.infoPanel;
    }

    /**
     *
     * Add addButton row button
     *
     * @return JRtableNavigation
     */
    public JRtableButton setAddButton() {
        addButton = new JRtableButton();
        try {
            addButton.setButtonIcon(JRtableNavigation.ICON_PATH + "Add.png");
            addButton.setToolTipText(JRString.ucfirst( this.i18n.getString("add_row") ));
            addButton.setName(JRtableButton.ADD);
            addButton.addActionListener(this);
            addButton.setPreferredSize(this.navButtonDim);
            this.buttonsPanel.add(addButton);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return addButton;
    }

    /**
     * Add editButton button
     *
     * @return
     */
    public JRtableButton setEditButton() {
        editButton = new JRtableButton();
        try {
            editButton.setButtonIcon(JRtableNavigation.ICON_PATH + "Pencil.png");
            editButton.setToolTipText(JRString.ucfirst(this.i18n.getString("edit_row")));
            editButton.setName(JRtableButton.EDIT);
            editButton.addActionListener(this);
            editButton.setPreferredSize(this.navButtonDim);
            this.buttonsPanel.add(editButton);
        } catch (Exception e) {

            System.out.println(e.getMessage());
        }
        return editButton;
    }

    /**
     * Add Delete button
     *
     * @return
     */
    public JRtableButton setDelButton() {
        delButton = new JRtableButton();
        try {
            delButton.setButtonIcon(JRtableNavigation.ICON_PATH + "Close.png");
            delButton.setName(JRtableButton.DEL);
            delButton.setToolTipText(JRString.ucfirst(this.i18n.getString("del_row")));
            delButton.addActionListener(this);
            delButton.setPreferredSize(this.navButtonDim);
            this.buttonsPanel.add(delButton);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return delButton;
    }

    /**
     * Add refreshButton button
     *
     * @return
     */
    public JRtableButton setRefreshButton() {
        refreshButton = new JRtableButton();
        try {
            refreshButton.setButtonIcon(JRtableNavigation.ICON_PATH + "Restart.png");
            refreshButton.setName(JRtableButton.REFRESH);
            refreshButton.setToolTipText(JRString.ucfirst(this.i18n.getString("refresh_table")));
            refreshButton.setPreferredSize(this.navButtonDim);
            refreshButton.addActionListener(this);
            this.buttonsPanel.add(refreshButton);
        } catch (Exception e) {

            System.out.println(e.getMessage());
        }
        return refreshButton;
    }

    /**
     * Add the searchButton button
     *
     * @return
     */
    public JRtableButton setSearchButton() {
        searchButton = new JRtableButton();
        try {
            searchButton.setButtonIcon(JRtableNavigation.ICON_PATH + "Search.png");
            searchButton.setName(JRtableButton.SEARCH);
            searchButton.setToolTipText(JRString.ucfirst(this.i18n.getString("search_table")));
            searchButton.setPreferredSize(this.navButtonDim);
            searchButton.addActionListener(this);
            this.buttonsPanel.add(searchButton);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return searchButton;
    }

    /**
     * Add all pre defined buttons searchButton, addButton, editButton, delete, refreshButton
     *
     * @return
     */
    public Map<String, JRtableButton> addAllButtons() {
        Map<String, JRtableButton> map = new HashMap();
        try {
            map.put(JRtableButton.SEARCH, this.setSearchButton());
            map.put(JRtableButton.ADD, this.setAddButton());
            map.put(JRtableButton.EDIT, this.setEditButton());
            map.put(JRtableButton.DEL, this.setDelButton());
            map.put(JRtableButton.REFRESH, this.setRefreshButton());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return map;

    }

    /**
     * Add a customized button showTo the JRtableNagigation
     *
     * @param button
     * @return
     */
    public JRtableButton addCostumeButton(JRtableButton button) {
        try {
            this.buttonsPanel.add(button);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return button;
    }

    /**
     * Handles the actionEvent of the JRtableButton
     *
     * @param e
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() instanceof JComboBox){
           this._actionPerformedJCombobox(e); 
        }else if(e.getSource() instanceof JRtableButton){
            this._actionPerformedJRtableButton(e);
        }
    }

    /**
     * To handle the events target by JRtableButton
     * @param e 
     */
    private void _actionPerformedJRtableButton(ActionEvent e){
        JRtableButton o = (JRtableButton)e.getSource();
        String n = o.getName();
        switch (n) {
            case JRtableButton.ADD:
                this.JRtable.addRow();
                break;
            case JRtableButton.DEL:
                this.JRtable.delRow();
                break;
            case JRtableButton.EDIT:
                this.JRtable.editRow();
                break;
            case JRtableButton.REFRESH:
                this.JRtable.bind();
                break;
            case JRtableButton.FIRST_PAGE:
                this.JRtable.firstPage();
                break;
            case JRtableButton.LAST_PAGE:
                this.JRtable.lastPage();
            case JRtableButton.NEXT_PAGE:
                this.JRtable.nextPage();
                break;
            case JRtableButton.PREVIOUS_PAGE:
                this.JRtable.previousPage();
                break;
            case JRtableButton.SEARCH:
                this.JRtable.search();
                break;
        }
    }
    
    /**
     * Handles the Action event for JcomboBox
     * @param e 
     */
    private void _actionPerformedJCombobox(ActionEvent e){
        JComboBox<?> o = (JComboBox<Object>)e.getSource();
        String s = o.getName();
        switch(s){
            case JRtableNavigation.NUM_ROWS_SELECTION:
                this.JRtable.bind();
                break;
            case JRtableNavigation.SORT_TYPE:
                this.JRtable.bind();
                break;
            case JRtableNavigation.SORTED_COLUMN:
                this.JRtable.bind();
                break;
        }
    }
    
    /**
     * Get a Map width the position of each component
     *
     * @return
     */
    public HashMap<String, Integer> getPaginationCompPosition() {
        HashMap<String, Integer> names = new HashMap();
        Integer x = 0;
        try {
            for (Component c : this.paginationPanel.getComponents()) {
                names.put(c.getClass().getName(), x);
                x++;
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return names;
    }

    /**
     * Add go showTo next page button showTo the table's navigation
     *
     * @param position the element position if null will be assigne showTo last
     * @return will return the JRtableButton created
     */
    public JRtableButton setNextPageButton(Integer position) {
        JRtableButton next = new JRtableButton();
        try {
            next.setButtonIcon(ICON_PATH + "Forward.png");
            next.setName(JRtableButton.NEXT_PAGE);
            next.setToolTipText(JRString.ucfirst(i18n.getString("go_next_page")));
            next.setPreferredSize(this.navButtonDim);
            next.addActionListener(this);
            this.paginationPanel.add(next, position);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return next;
    }

    /**
     * Add go showTo last page button showTo the table's navigation
     *
     * @param position the element position if null will be assigne showTo last
     * @return will return the JRtableButton created
     */
    public JRtableButton setLastPageButton(Integer position) {
        JRtableButton last = new JRtableButton();
        try {
            last.setButtonIcon(ICON_PATH + "Last.png");
            last.addActionListener(this);
            last.setToolTipText(JRString.ucfirst(i18n.getString("go_last_page")));
            last.setName(JRtableButton.LAST_PAGE);
            last.setPreferredSize(this.navButtonDim);
            this.paginationPanel.add(last, position);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return last;
    }

    /**
     * Add go showTo previous page showTo the table's navigation
     *
     * @param position the element position if null will be assigne showTo last
     * @return will return the JRtableButton created
     */
    public JRtableButton setPreviousPageButton(Integer position) {
        JRtableButton pre = new JRtableButton();
        try {
            pre.setButtonIcon(ICON_PATH + "Backward.png");
            pre.setName(JRtableButton.PREVIOUS_PAGE);
            pre.addActionListener(this);
            pre.setPreferredSize(this.navButtonDim);
            pre.setToolTipText(JRString.ucfirst(i18n.getString(i18n.getString("go_previous_page"))));
            this.paginationPanel.add(pre, position);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return pre;
    }

    /**
     * Add go showTo first page button showTo the table's navigation
     *
     * @param position the element position if null will be assigne showTo last
     * @return will return the JRtableButton created
     */
    public JRtableButton setFirstPageButton(Integer position) {
        JRtableButton first = new JRtableButton();
        try {
            first.setButtonIcon(ICON_PATH + "First.png");
            first.setName(JRtableButton.FIRST_PAGE);
            first.setToolTipText(JRString.ucfirst(i18n.getString("go_first_page")));
            first.addActionListener(this);
            first.setPreferredSize(this.navButtonDim);
            this.paginationPanel.add(first, position);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return first;
    }

    /**
     * Add the field that constains the page number loaded or showTo load
     *
     * @param page the page showTo be loaded
     * @param position the element position if null will be assigne showTo last
     * @return will return the JFormattedTextField created
     */
    public JTextField setPageLoadNumber(Integer page, Integer position){
        
        try {
            pageField.addKeyListener(this);
            pageField.addFocusListener(this);
            pageField.setName(JRtableNavigation.PAGE_LOAD_NUMEBR);
            pageField.setText(page.toString());
            pageField.setPreferredSize(new Dimension(50, 20));
            pageField.setHorizontalAlignment(JTextField.RIGHT);
            pageField.setFont(comboFont);
            pageField.setToolTipText(JRString.ucfirst(i18n.getString("show_page") + " " + pageField.getText().toString()));
            this.paginationPanel.add(pageField);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return pageField;
    }

    /**
     * Set the total pages pagination
     *
     * @param total
     * @return will return the JRtableButton created
     */
    public JLabel setTotalPagesLabel(Integer total) {
        this.totalPg = new JLabel(total.toString());
        try {
            totalPg.setName(JRtableNavigation.TOTAL_PAGES);
            this.paginationPanel.add(totalPg);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return totalPg;
    }

    /**
     * Set the total pages
     * @param total 
     */
    public void setTotalPages(Integer total){
        if(this.totalPg instanceof JLabel){
            this.totalPg.setText(total.toString());
        }
    }
    
    /**
     * Set the "page" word in pagination area
     *
     * @param pageWord
     * @return will return the JLabel created
     */
    public JLabel setPageWordPagination() {
        JLabel pgWrd = new JLabel(JRString.ucfirst(i18n.getString("page")));
        try {
            pgWrd.setName(JRtableNavigation.PAGE_WORD_PAGINATION);
            this.paginationPanel.add(pgWrd);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return pgWrd;
    }

    /**
     *
     * Set the "of" word in pagination area
     *
     * @param ofWordPagination
     * @return
     */
    public JLabel setOfWordPgination() {
        JLabel word = new JLabel(i18n.getString("of"));
        try {
            word.setName(JRtableNavigation.OF_WORD_PAGINATION);
            this.paginationPanel.add(word);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return word;
    }

    /**
     * Set the "Show" word showTo the info panel
     *
     * @param showRecordWord
     * @return will return the JLabel created
     */
    public JLabel setShowRecordWord() {
        JLabel word = new JLabel(JRString.ucfirst(i18n.getString("showing_record")));
        try {
            word.setName(JRtableNavigation.SHOW_RECORD_WORD_INFO);
            this.infoPanel.add(word);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return word;
    }

    /**
     *
     * Set the from record that the JRtable is showing
     *
     * @param from Number of the start record
     * @return will return the JLabel created
     */
    public JLabel setShowRecordFromLabel(Integer from) {
        this.showFrom = new JLabel(from.toString());
        try {
            showFrom.setName(JRtableNavigation.SHOW_RECORD_FROM_INFO);
            this.infoPanel.add(showFrom);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return showFrom;
    }

    /**
     * Set the show record from info
     * @param from 
     */
    public void setShowFromRecord(Integer from){
        if(this.showFrom instanceof JLabel){
            this.showFrom.setText(from.toString());
        }
    }
    
    
    /**
     *
     * Set the showTo word in the info panel
     *
     * @param toWord
     * @return will return the JLabel created
     */
    public JLabel setToWordInfo() {
        JLabel to = new JLabel(i18n.getString("to"));
        try {
            to.setName(JRtableNavigation.TO_WORD_INFO);
            this.infoPanel.add(to);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return to;
    }

    /**
     * Set the number of the last record showed
     *
     * @param record
     * @return will return the JLabel created
     */
    public JLabel setShowToRecordLabel(Integer record) {
        this.showTo = new JLabel(record.toString());
        try {
            showTo.setName(JRtableNavigation.SHOW_RECORD_TO_INFO);
            this.infoPanel.add(showTo);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return showTo;
    }

    /**
     * Set the to record info 
     * @param to 
     */
    public void setShowToRecord(Integer to){
        if(this.showTo instanceof JLabel){
            this.showTo.setText(to.toString());
        }
    }
    
    /**
     *
     * Set the "of" word in the info panel
     *
     * @param ofWord
     * @return will return the JLabel created
     */
    public JLabel setOfWordInfo() {
        JLabel of = new JLabel(i18n.getString("of"));
        try {
            of.setName(JRtableNavigation.OF_WORD_INFO);
            this.infoPanel.add(of);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return of;
    }

    /**
     * Set the total records info
     *
     * @param total
     * @return will return the JLabel created
     */
    public JLabel setTotalRecordsLabel(Integer total) {
        this.totalLabel = new JLabel(total.toString());
        try {
            totalLabel.setName(JRtableNavigation.TOTAL_RECORDS_INFO);
            this.infoPanel.add(totalLabel);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return totalLabel;
    }

   public void setTotalRecords(Integer total){
       if(this.totalLabel instanceof JLabel){
           this.totalLabel.setText(total.toString());
       }
   }
    
    
    /**
     * Add the control that define the number of roes showTo view
     *
     * @param numRows
     * @return
     */
    public JComboBox setNumRowsCombobox(String[] numRows) {
        try {
            if (numRows == null) {
                String[] s = {"10", "20", "50"};
                numRows = s.clone();
            }
            for (String i : numRows) {
                this.selectNumRows.addItem(i);
            }
            this.selectNumRows.setToolTipText(JRString.ucfirst("table_num_records"));
            this.selectNumRows.setFont(this.comboFont);
            this.selectNumRows.addActionListener(this);
            this.selectNumRows.setName(JRtableNavigation.NUM_ROWS_SELECTION);
            this.paginationPanel.add(this.selectNumRows);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return selectNumRows;
    }

    /**
     * Make the default pagination
     *
     * @return
     */
    public HashMap<String, Component> makeDefaultPagination() {
        HashMap<String, Component> defaultPg = new HashMap();
        try {
            defaultPg.put(JRtableButton.FIRST_PAGE, this.setFirstPageButton(null));
            defaultPg.put(JRtableButton.PREVIOUS_PAGE, this.setPreviousPageButton(null));
            defaultPg.put(JRtableNavigation.PAGE_WORD_PAGINATION, this.setPageWordPagination());
            defaultPg.put(JRtableNavigation.PAGE_LOAD_NUMEBR, this.setPageLoadNumber(1, null));
            defaultPg.put(JRtableNavigation.OF_WORD_PAGINATION, this.setOfWordPgination());
            defaultPg.put(JRtableNavigation.TOTAL_PAGES, this.setTotalPagesLabel(0));
            defaultPg.put(JRtableButton.NEXT_PAGE, this.setNextPageButton(null));
            defaultPg.put(JRtableButton.LAST_PAGE, this.setLastPageButton(null));
            defaultPg.put("rows", this.setNumRowsCombobox(null));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return defaultPg;
    }

    /**
     * Make the default info
     *
     * @return will return a HashMap with all component maped by its own name
     */
    public HashMap<String, Component> makeDefaultInfo() {
        HashMap<String, Component> defaultInfo = new HashMap();
        try {
            defaultInfo.put(JRtableNavigation.SHOW_RECORD_FROM_INFO, this.setShowRecordWord());
            defaultInfo.put(JRtableNavigation.SHOW_RECORD_FROM_INFO, this.setShowRecordFromLabel(0));
            defaultInfo.put(JRtableNavigation.TO_WORD_INFO, this.setToWordInfo());
            defaultInfo.put(JRtableNavigation.SHOW_RECORD_TO_INFO, this.setShowToRecordLabel(0));
            defaultInfo.put(JRtableNavigation.OF_WORD_INFO, this.setOfWordInfo());
            defaultInfo.put(JRtableNavigation.TOTAL_RECORDS_INFO, this.setTotalRecordsLabel(0));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return defaultInfo;
    }

    /**
     * set the sort word for pagination
     *
     * @param sortWord
     * @return
     */
    public JLabel setSortWord() {
        JLabel word = new JLabel(i18n.getString("sort_by"));
        try {
            word.setName(JRtableNavigation.SORT_WORD);
            this.sortPanel.add(word);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return word;
    }

    /**
     * Define the sortColumnCombobx box with the column names showTo sort data from database
     *
     * @param options
     * @return
     */
    public JComboBox<JRcomboOption> setSortColumnCombo(Collection<JRcomboOption> options) {
        try {
            this.sortColumnCombobx.setFont(comboFont);
            this.sortColumnCombobx.setName(JRtableNavigation.SORTED_COLUMN);
            for (JRcomboOption o : options) {
                this.sortColumnCombobx.addItem(o);
            }
            this.sortColumnCombobx.setSelectedIndex(this.sortColumnCombobx.getItemCount()>0 ? 
                                                    this.sortColumnCombobx.getItemCount()-1:0);
            this.sortColumnCombobx.addActionListener(this);
            this.sortPanel.add(sortColumnCombobx);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return this.sortColumnCombobx;
    }
    
    /**
     * Get the column list showTo sort
     * @return 
     */
    public JComboBox<JRcomboOption> getSortColumnCombo(){
        return this.sortColumnCombobx;
    }
    
    /**
     * Get the column name selected showTo sort data from database
     * @return 
     */
    public String getSortColumnSelected(){
        JRcomboOption opt = (JRcomboOption)this.getSortColumnCombo().getSelectedItem();
        return opt.getValue();
    }
    
    /**
     * Set the sort type ASC / DESC
     *
     * @param options
     * @return
     */
    public JComboBox<JRcomboOption> setSortType(ArrayList<JRcomboOption> options) {
        try {
            this.sortTypeCombo.setFont(this.comboFont);
            this.sortTypeCombo.setName(JRtableNavigation.SORT_TYPE);
            for (JRcomboOption o : options) {
                this.sortTypeCombo.addItem(o);
            }
            this.sortTypeCombo.addActionListener(this);
            this.sortPanel.add(sortTypeCombo);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return this.sortTypeCombo;
    }

    /**
     * Get the sort type combobox
     * @return 
     */
    public JComboBox<JRcomboOption> getSortTypeCombo(){
        return this.sortTypeCombo;
    }
    
    /**
     * Get the selected sort type ASC/DESC
     * @return 
     */
    public String getSorTypeSelected(){
        JRcomboOption opt = (JRcomboOption)this.sortTypeCombo.getSelectedItem();
        return opt.getValue();
    }
    
    /**
     * Get the number of rows per table selected
     * @return 
     */
    public Integer getNumberOfRowsSelected(){
        //JRcomboOption opt = (JRcomboOption)this.selectNumRows.getSelectedItem();
        return new Integer(this.selectNumRows.getSelectedItem().toString());
    }
    
    /**
     * 
     * @param columns
     * @return 
     */
    public HashMap<String, Component> makeDefaultSortControl(Collection<JRcomboOption> columns) {
        this.paginationPanel.add(this.sortPanel);
        HashMap<String, Component> comp = new HashMap();
        try {
            comp.put(JRtableNavigation.SORT_WORD, this.setSortWord());
            comp.put(JRtableNavigation.SORTED_COLUMN, this.setSortColumnCombo(columns));
            ArrayList<JRcomboOption> options = new ArrayList();
            options.add(new JRcomboOption("ASC", "ASC"));
            options.add(new JRcomboOption("DESC", "DESC"));
            comp.put(JRtableNavigation.SORT_TYPE, this.setSortType(options));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return comp;
    }

    @Override
    public void keyTyped(KeyEvent e) {
        if(this.pageFieldComsumed == true && e.getSource().equals(this.pageField)){
         e.consume();   
        }
    }

    private boolean pageFieldComsumed = false; 
    
    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getSource().equals(this.pageField)){
           if( (e.getKeyCode()>= 48 && e.getKeyCode() <= 57) 
                ||
                (e.getKeyCode()>= 96 && e.getKeyCode() <= 105)
                || e.getKeyCode() == 9
                || e.getKeyCode() == 37 || e.getKeyCode() == 39
                || e.getKeyCode() == 8 || e.getKeyCode() == 46
                ){
               this.pageFieldComsumed = false;
           }else if(e.getKeyCode() == 13 || e.getKeyCode() == 10){
               this.JRtable.bind();
           }else{
               this.pageFieldComsumed = true;
           }
           
        }
        //throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void keyReleased(KeyEvent e) {
        
    }

    private Integer oldPage = 1;
            
    @Override
    public void focusGained(FocusEvent e) {
        try{
            if(e.getSource().equals(this.pageField)){
                this.oldPage = new Integer(this.pageField.getText());
            }
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void focusLost(FocusEvent e) {
        if(e.getSource().equals(this.pageField)){
            Integer page = new Integer(this.pageField.getText());    
            if(this.oldPage != page){
                this.JRtable.bind();
            }
        }
    }
}

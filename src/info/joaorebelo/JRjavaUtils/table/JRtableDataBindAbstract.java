/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package info.joaorebelo.JRjavaUtils.table;

import info.joaorebelo.JRFramework.i18n;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import table.ui.actor.ActorForm;

/**
 *
 * @author joao
 */
public abstract class JRtableDataBindAbstract extends javax.swing.JLayeredPane
        implements IJRtableDataBind{
    
    /**
     * The form for add and update record
     */
    public ActorForm actorForm;
    /**
     * Name of the form close button
     */
    public static final String FORM_CLOSE_BUTTON = "form_close_button";
    /**
     * Name of the form submit button
     */
    public static final String FORM_SUBMIT_BUTTON = "form_submit_button";
    /**
     * Index of form layerdPane
     */
    public final Integer FORM_LAYER_INDEX = 1;
    /**
     * The JRtable to show results
     */
    public JRtable actoresJRtable;
    /**
     * i18n resources
     */
    protected i18n i18n;
    /**
     *
     */
    protected JPanel tabPanel;

    /**
     * Search class
     */
    public JRtableSearch JRtableSearch;
    
    /**
     * Search form
     */
    public JRtableSearchForm searchForm;

}

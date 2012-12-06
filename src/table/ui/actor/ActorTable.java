/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package table.ui.actor;

import info.joaorebelo.JRFramework.i18n;
import hybernateUtil.HibernateUtil;
import info.joaorebelo.JRjavaUtils.table.JRtable;
import info.joaorebelo.JRjavaUtils.table.JRtableDataBindAbstract;
import info.joaorebelo.JRjavaUtils.table.JRtableDataResult;
import info.joaorebelo.JRjavaUtils.table.JRtableNavigation;
import info.joaorebelo.JRjavaUtils.table.JRtableSearchForm;
import info.joaorebelo.JRjavaUtils.ui.JRString;
import info.joaorebelo.JRjavaUtils.ui.JRcomboOption;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Vector;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import sakila.entity.Actor;

/**
 *
 * @author joao
 */
public class ActorTable extends JRtableDataBindAbstract
        implements info.joaorebelo.JRjavaUtils.table.IJRtableDataBind, 
                   ActionListener{
    
    /**
     * Creates new form ActorTable
     */
    public ActorTable(i18n i18n, JPanel tabPane) {
        initComponents();
        this.tabPanel = tabPane;
        this.i18n = i18n;
        this.setVisible(true);
        this.setLayout(new BorderLayout());
        this.createJRtable();
        this.actoresJRtable.bind();
    }

    /**
     * Get actor table
     *
     * @return
     */
    public JRtable getActoresTable() {
        return this.actoresJRtable;
    }

    /**
     * Create de ActorTable JRtable
     */
    @Override
    public void createJRtable() {
        this.actoresJRtable = new JRtable(this);
        this.actoresJRtable.createSort();
        JRtableNavigation nav = new JRtableNavigation(this.actoresJRtable, this.i18n);
        nav.addAllButtons();
        nav.makeDefaultInfo();
        nav.makeDefaultPagination();
        Collection<JRcomboOption> opt = new HashSet();
        for (String col : this.getColumnsName()) {
            opt.add(new JRcomboOption(col, JRString.ucfirst(this.i18n.getString(col))));
        }
        nav.makeDefaultSortControl(opt);
        this.actoresJRtable.setNavigation(nav);
        JLayeredPane l = new JLayeredPane();
        this.add(this.actoresJRtable, BorderLayout.CENTER);
    }

    /**
     * get the total table records 
     * if the search form have a condition than it will be 
     * with that condition
     * @return 
     */
    @Override
    public Integer getTotalRecords() {
        try {
            Session session = HibernateUtil.getSessionFactory().getCurrentSession();
            org.hibernate.Transaction t = session.beginTransaction();
            Criteria cr = session.createCriteria(Actor.class);
            if(this.searchForm != null){
                Criterion c = this.searchForm.getCriteria();
                if(c!=null){
                    cr.add(c);
                }
            }
            cr.setProjection(Projections.rowCount());

            List l = cr.list();
            t.commit();
            return new Integer(l.get(0).toString());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return 0;
    }

    /**
     * Get data from the table and populate the table
     * @param page
     * @param numRecords
     * @param orderBy
     * @param orderType
     * @return 
     */
    @Override
    public JRtableDataResult bind(Integer page, Integer numRecords, String orderBy, String orderType) {
        JRtableDataResult result = new JRtableDataResult();
        try {

            Integer totalrecords = this.getTotalRecords();
            result.setTotalRecords(totalrecords);

            Session session = HibernateUtil.getSessionFactory().getCurrentSession();
            org.hibernate.Transaction t = session.beginTransaction();
            Integer from = ((page * numRecords) - numRecords);

            Criteria c = session.createCriteria(Actor.class);
            if(this.searchForm != null){
                Criterion cr = this.searchForm.getCriteria();
                if(cr != null){
                    c.add(cr);
                }
            }
            if (orderType.toLowerCase().equals("desc")) {
                c.addOrder(Order.desc(JRString.columnName2HibernateProperty(orderBy)));
            } else {
                c.addOrder(Order.asc(JRString.columnName2HibernateProperty(orderBy)));
            }
            c.setFirstResult(from);
            c.setMaxResults(numRecords);

            List<Actor> listResult = c.list();
            result.setPage(page);
            Double tpDouble = Math.ceil((float) totalrecords / (float) numRecords);
            result.setTotalPages(tpDouble.intValue());

            for (Actor a : listResult) {
                Vector<Object> v = new Vector();
                v.add(a.getActorId().toString());
                v.add(a.getFirstName());
                v.add(a.getLastName());
                v.add(a.getLastUpdate().toString());
                result.addRow(v);
            }
            t.commit();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return result;
    }

    /**
     * handles the Add a row to the table button
     */
    @Override
    public void AddRecordButoanAction() {
        try {
            actorForm = new ActorForm(this, this.i18n, "ADD", null);
            actorForm.setTitle(this.i18n.getString("actor_add"));
            actorForm.setClosable(false);
            actorForm.setResizable(false);
            actorForm.setVisible(true);
            actorForm.setLocation(200, 200);
            JLayeredPane lp = new JLayeredPane();
            lp.add(actorForm);
            lp.moveToFront(this);
            
            this.setLayer(lp, this.FORM_LAYER_INDEX);
            this.add(lp);

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    /**
     * Upfate a row in table
     */
    @Override
    public void updateRecordButoanAction() {
        try {

            Integer sr = this.actoresJRtable.getTable().getSelectedRowCount();
            if (sr != 1) {
                String msg = "";
                if (sr == 0) {
                    msg = this.i18n.getString("select_one_row");
                } else if (sr > 1) {
                    msg = this.i18n.getString("select_only_one_row");
                }
                this.errorDialog(msg);
                return;
            }
            Short id = new Short(this.actoresJRtable.getTable().getValueAt(this.actoresJRtable.getTable().getSelectedRow(), 0).toString());

            Session session = HibernateUtil.getSessionFactory().getCurrentSession();
            Transaction t = session.beginTransaction();
            Criteria c = session.createCriteria(Actor.class);
            c.add(Restrictions.eq("actorId", id));
            List l = c.list();
            t.commit();
            if (l.isEmpty() == true) {
                this.errorDialog(this.i18n.getString("id_not_found"));
            }
            Actor a = (Actor) l.get(0);
            actorForm = new ActorForm(this, i18n, "UPDATE", a);
            actorForm.setVisible(true);
            actorForm.setLocation(300, 300);
            JLayeredPane lp = new JLayeredPane();
            lp.add(this.actorForm);
            this.setLayer(lp, FORM_LAYER_INDEX);
            lp.moveToFront(this);
            this.add(lp);
        } catch (NumberFormatException | HibernateException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Showa lp message error
     *
     * @param msg
     */
    private void errorDialog(String msg) {
        try {
            JOptionPane pane = new JOptionPane(msg, JOptionPane.ERROR_MESSAGE);
            JDialog dialog = pane.createDialog(this, this.i18n.getString("erro"));
            ImageIcon img = new ImageIcon(getClass().getResource("/resources/Extras_16_16/JR.png"));
            dialog.setIconImage(img.getImage());
            dialog.setVisible(true);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Handle the delete a row from table button
     */
    @Override
    public void delRecordButoanAction() {
        this.delRecord();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables

    /**
     * Table columns names
     * @return 
     */
    @Override
    public Vector<String> getColumnsName() {
        Vector<String> columns = new Vector();
        columns.add("actor_id");
        columns.add("first_name");
        columns.add("last_name");
        columns.add("last_update");
        return columns;
    }

    /**
     * Get translated column name
     * @return 
     */
    @Override
    public Vector<String> getColumnsNameI18n() {
        Vector<String> columns = new Vector();
        columns.add(JRString.ucfirst(this.i18n.getString("actor_id")));
        columns.add(JRString.ucfirst(this.i18n.getString("first_name")));
        columns.add(JRString.ucfirst(this.i18n.getString("last_name")));
        columns.add(JRString.ucfirst(this.i18n.getString("last_update")));
        return columns;
    }

    /**
     * Get table title
     * @return 
     */
    @Override
    public String getTableTitle() {
        return this.i18n.getString(JRString.ucfirst("actores"));
    }

    /**
     * Handle for ActionEvent
     * @param e 
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        String Cname = e.getSource().getClass().getSimpleName();
        String name = "";
        if (Cname.equals("JButton")) {
            JButton comp = (JButton) e.getSource();
            name = comp.getName();
        }

        switch (name) {
            case FORM_CLOSE_BUTTON:
                this.actorForm.dispose();
                this.actorForm = null;
                break;
            case FORM_SUBMIT_BUTTON:
                if (actorForm.typeForm.toUpperCase().equals("ADD")) {
                    this.addRecord();
                } else if (actorForm.typeForm.toUpperCase().equals("UPDATE")) {
                    this.updateRecord();
                }

                break;
        }

    }

    /**
     * Add a record to table
     * @return 
     */
    @Override
    public boolean addRecord() {
        try {
            Boolean v = this.validateForm();
            if (v == false) {
                return false;
            }
            Actor actor = new Actor();
            actor.setFirstName(this.actorForm.jTextFieldFirstName.getText().trim());
            actor.setLastName(this.actorForm.jTextFieldLastName.getText().trim());
            actor.setLastUpdate(new Date());

            Session session = HibernateUtil.getSessionFactory().getCurrentSession();
            org.hibernate.Transaction t = session.beginTransaction();
            Criteria c = session.createCriteria(sakila.entity.Actor.class);
            List l = c.setProjection(Projections.max(JRString.columnName2HibernateProperty("actor_id"))).list();
            t.commit();
            Short id = new Short(l.get(0).toString());
            actor.setActorId(++id);
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            t = session.beginTransaction();
            session.save(actor);
            t.commit();
            this.actoresJRtable.bind();
            actorForm.dispose();
            actorForm = null;
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    /**
     * Updte a record in table
     * @return 
     */
    @Override
    public boolean updateRecord() {
        try {
            if (validateForm() == true) {
                this.actorForm.actor.setFirstName(this.actorForm.jTextFieldFirstName.getText().trim());
                this.actorForm.actor.setLastName(this.actorForm.jTextFieldLastName.getText().trim());
                this.actorForm.actor.setLastUpdate(new Date());
                Session s = HibernateUtil.getSessionFactory().getCurrentSession();
                Transaction t = s.beginTransaction();
                s.update(this.actorForm.actor);
                t.commit();
                this.actoresJRtable.bind();
                actorForm.dispose();
                actorForm = null;
                return true;
            }
            return false;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    /**
     * del a record from table
     * @return 
     */
    @Override
    public boolean delRecord() {
        try{
            
            Integer sr = this.actoresJRtable.getTable().getSelectedRowCount();
            if(sr == 0){
                this.errorDialog("select_one_row");
                return false;
            }
            JOptionPane pane = new JOptionPane(this.i18n.getString("confirm_del_rows"), JOptionPane.YES_NO_OPTION);
            pane.setOptionType(JOptionPane.YES_NO_OPTION);
            JDialog dialog = pane.createDialog(this, this.i18n.getString("del_row"));
            ImageIcon img = new ImageIcon(getClass().getResource("/resources/Extras_16_16/JR.png"));
            dialog.setIconImage(img.getImage());
            dialog.setVisible(true);
            Integer o = (Integer)pane.getValue();
            if(o == JOptionPane.NO_OPTION){
                return false;
            }
            Session s;
            Transaction t; 
            s = HibernateUtil.getSessionFactory().getCurrentSession();
            t = s.beginTransaction();
            int srs[] = this.actoresJRtable.getTable().getSelectedRows();
            for(Integer r : srs){
               Actor a;
               Short id = new Short(this.actoresJRtable.getTable().getValueAt(r, 0).toString());
               Criteria c = s.createCriteria(Actor.class);
               List<Actor> l =   c.add(Restrictions.eq("actorId", id)).list();
               a = (Actor)l.get(0);
               s.delete(a); 
            }
            t.commit();
            this.actoresJRtable.bind();
            return true;
        }catch(Exception e){
            System.out.println(e.getMessage());
            this.errorDialog(e.getMessage());
            return false;
        } 
       
    }

    /**
     * Validate form values
     * @return 
     */
    private boolean validateForm() {
        try {
            Boolean v = true;
            String fn = this.actorForm.jTextFieldFirstName.getText();
            String ln = this.actorForm.jTextFieldLastName.getText();
            if (fn.equals("") == true) {
                this.actorForm.jTextFieldFirstName.setBorder(
                        BorderFactory.createBevelBorder(BevelBorder.LOWERED, Color.RED, Color.RED));
                v = false;
            }
            if (ln.equals("") == true) {
                this.actorForm.jTextFieldLastName.setBorder(
                        BorderFactory.createBevelBorder(BevelBorder.LOWERED, Color.RED, Color.RED));
                v = false;
            }

            return v;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    /**
     * Handle the serach button action
     */
    @Override
    public void searchButtonAction() {
        try{
            if(this.searchForm == null){
                searchForm = new JRtableSearchForm(this, this.i18n, Actor.class);
                searchForm.setLocation(200, 200);
                JLayeredPane lp = new JLayeredPane();
                lp.add(searchForm);
                lp.moveToFront(this);
                searchForm.setVisible(true);
                this.setLayer(lp, this.FORM_LAYER_INDEX);
                this.add(lp);
                lp.setVisible(true);
            }
            searchForm.setVisible(true);
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }

    /**
     * Get JRtabale
     * @return 
     */
    @Override
    public JRtable getJRtable() {
        return this.actoresJRtable;
    }

    /**
     * Set the default cell render and edit
     * if you do not wont the default
     */
    @Override
    public void setDefaultCellRender() {
        
    }
}

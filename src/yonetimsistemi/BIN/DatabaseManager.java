
package yonetimsistemi.BIN;

import Pojo.DataType;
import Pojo.NewHibernateUtil;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;


public class DatabaseManager<T extends DataType> implements IDatabase<T>{

    Class s;
    
    public DatabaseManager(Class s) {
        this.s = s;
    }

    @Override
    public T get() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ObservableList<T> gets() {
        SessionFactory factory = NewHibernateUtil.getSessionFactory();
        Session session = factory.openSession();
        Transaction transaction = session.beginTransaction();
        ObservableList<T> list = FXCollections.observableArrayList(session.createCriteria(s).list());
        transaction.commit();
        session.close();
        return list;
    }

    @Override
    public void save(T t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void update(T t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(T t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void updateorsave(T t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}

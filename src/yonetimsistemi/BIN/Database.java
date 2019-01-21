
package yonetimsistemi.BIN;

import Pojo.NewHibernateUtil;
import org.hibernate.SessionFactory;


public class Database {
    
    private SessionFactory factory;
    
    public void Connection(){
        factory = NewHibernateUtil.getSessionFactory();
    }
    
    public void Disconnection(){
        factory.close();
    }
    
}

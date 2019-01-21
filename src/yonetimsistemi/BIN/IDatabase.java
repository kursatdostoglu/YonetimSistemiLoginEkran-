
package yonetimsistemi.BIN;

import javafx.collections.ObservableList;

public interface IDatabase<T> {
 
    public T get();
    public ObservableList<T> gets();
    public void save(T t);
    public void update(T t);
    public void delete(T t);
    public void updateorsave(T t);
    
}


package yonetimsistemi.BIN;

import Pojo.Adminler;
import java.io.Serializable;


public class endUser implements Serializable{
    
    private Adminler endUser;

    public Adminler getEndUser() {
        return endUser;
    }

    public void setEndUser(Adminler endUser) {
        this.endUser = endUser;
    }
    
}

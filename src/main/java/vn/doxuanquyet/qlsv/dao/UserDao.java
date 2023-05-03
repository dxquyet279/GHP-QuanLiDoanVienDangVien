package vn.doxuanquyet.qlsv.dao;

import vn.doxuanquyet.qlsv.entity.User;

public class UserDao {
    public UserDao(){
        
    }
    
    public boolean checkUser(User user) {
        if (user != null) {
            if ("admin".equals(user.getUserName()) 
                    && "admin".equals(user.getPassword())) {
                return true;
            }
        }
        return false;
    }
}

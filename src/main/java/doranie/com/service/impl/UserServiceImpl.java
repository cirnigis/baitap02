package doranie.com.service.impl;

import doranie.com.dao.UserDao;
import doranie.com.dao.impl.UserDaoImpl;
import doranie.com.models.User;
import doranie.com.service.UserService;

public class UserServiceImpl implements UserService {

    UserDao userDao = new UserDaoImpl();

    // Chức năng đăng nhập
    @Override
    public User login(String username, String password) {

        User user = this.get(username);

        if (user != null && password.equals(user.getPassWord())) {
            return user;
        }

        return null;
    }

    @Override
    public User get(String username) {
        return userDao.get(username);
    }

    // Chức năng đăng ký
    @Override
    public boolean register(String email, String password, String username,
                            String fullname, String phone) {

        if (userDao.checkExistUsername(username)) {
            return false;
        }

        long millis = System.currentTimeMillis();
        java.sql.Date date = new java.sql.Date(millis);

        User user = new User();

        user.setEmail(email);
        user.setPassWord(password);
        user.setUserName(username);
        user.setFullName(fullname);
        user.setPhone(phone);
        user.setAvatar(null);
        user.setRoleid(5);
        user.setCreatedDate(date);

        userDao.insert(user);

        return true;
    }

    @Override
    public boolean checkExistEmail(String email) {
        return userDao.checkExistEmail(email);
    }

    @Override
    public boolean checkExistUsername(String username) {
        return userDao.checkExistUsername(username);
    }

    @Override
    public boolean checkExistPhone(String phone) {
        return userDao.checkExistPhone(phone);
    }

    @Override
    public void insert(User user) {
        userDao.insert(user);
    }
    
    @Override
    public void updatePassword(String email, String password) {
        userDao.updatePassword(email, password);
    }
}

package doranie.com.dao;

import doranie.com.models.User;

public interface UserDao {

    // LOGIN

    User get(String username);

    // REGISTER

    void insert(User user);

    boolean checkExistEmail(String email);

    boolean checkExistUsername(String username);

    boolean checkExistPhone(String phone);

    // FORGOT PASSWORD

    void updatePassword(String email, String password);
}
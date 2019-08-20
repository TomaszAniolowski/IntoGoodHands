package pl.coderslab.charity.user;

import java.util.List;
import java.util.Locale;

public interface UserService {
    User findByUsername(String username);
    User findByUserEmail(String email);
    List<User> getUsers();
    List<User> getAdmins();
    void saveUser(User user);
    void setLocale(String username, Locale locale);
    String getUsersLocale(String username);
    boolean checkUser(User user);
}

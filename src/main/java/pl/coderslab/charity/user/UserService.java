package pl.coderslab.charity.user;

public interface UserService {
    User findByUsername(String username);
    User findByUserEmail(String email);
    void saveUser(User user);
    boolean checkUser(User user);
}

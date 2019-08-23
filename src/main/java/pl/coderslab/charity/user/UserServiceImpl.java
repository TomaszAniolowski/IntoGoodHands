package pl.coderslab.charity.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import pl.coderslab.charity.role.Role;
import pl.coderslab.charity.role.RoleRepository;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.*;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Optional<User> findByUserId(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public List<User> getUsers() {
        return userRepository.getUsersByRoles(roleRepository.findByName("ROLE_USER"));
    }

    @Override
    public List<User> getAdmins() {
        return userRepository.getUsersByRoles(roleRepository.findByName("ROLE_ADMIN"));
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.getUserByUsername(username);
    }

    @Override
    public User findByUserEmail(String email) {
        return userRepository.getUserByEmail(email);
    }

    @Override
    public void removeUser(User user) {
        userRepository.delete(user);
    }

    @Override
    public void saveUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setEnabled(1);
        Role userRole = roleRepository.findByName("ROLE_USER");
        user.setRoles(new HashSet<Role>(Arrays.asList(userRole)));
        userRepository.save(user);
    }

    @Override
    public void setLocale(String username, Locale locale) {
        User user = userRepository.getUserByUsername(username);
        if (user == null)
            return;

        user.setLocale(locale.toLanguageTag());
        userRepository.save(user);
    }

    @Override
    public String getUsersLocale(String username) throws NullPointerException {
        return userRepository.getUserByUsername(username).getLocale();

    }

    @Override
    public boolean checkUser(User user) {
        //TODO: send email if user with such an email address exists
        return this.findByUserEmail(user.getEmail()) == null && this.findByUsername(user.getUsername()) == null;
    }

    @Override
    public void makeRole(Long id, String wantedRole) throws EntityNotFoundException, InternalError, ClassNotFoundException {
        User user = userRepository.findById(id).orElse(null);
        if (user == null)
            throw new EntityNotFoundException("No user in database with id " + id);

        if (wantedRole.equals("adm"))
            wantedRole = "ROLE_ADMIN";
        else if (wantedRole.equals("ord"))
            wantedRole = "ROLE_USER";
        else
            throw new ClassNotFoundException("Can't find role of user.");

        if (user.getRoles().contains(roleRepository.findByName(wantedRole)))
            throw new InternalError("User already has " + wantedRole.replace("_", " ").toLowerCase() + "!");

        Set<Role> roles = new HashSet<>();
        roles.add(roleRepository.findByName(wantedRole));
        user.setRoles(roles);
        userRepository.save(user);
    }
}

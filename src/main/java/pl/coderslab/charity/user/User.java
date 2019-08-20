package pl.coderslab.charity.user;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import pl.coderslab.charity.variableEntity.VariableEntity;
import pl.coderslab.charity.role.Role;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.util.Set;

@Setter
@Getter
@Data
@Entity
public class User implements VariableEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String username;

    @Email
    @Column(nullable = false, unique = true)
    private String email;

    private String password;

    private int enabled;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "user_role",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles;

    private String locale;

    @PrePersist
    private void prePersist() {
        locale = "pl-PL";
    }

}

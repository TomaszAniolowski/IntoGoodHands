package pl.coderslab.charity.institution;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import pl.coderslab.charity.variableEntity.VariableEntity;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Setter
@Getter
@Data
@Entity
@Table(name = "institutions")
public class Institution implements VariableEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    @NotEmpty
    private String name;

    @Column(columnDefinition = "TEXT")
    @NotEmpty
    private String description;

    @Override
    public String toString() {
        return "Institution: " + name + "(Description: " + description + ")";
    }
}

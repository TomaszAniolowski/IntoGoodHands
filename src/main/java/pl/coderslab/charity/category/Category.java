package pl.coderslab.charity.category;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import pl.coderslab.charity.variableEntity.VariableEntity;

import javax.persistence.*;

@Setter
@Getter
@Data
@Entity
@Table(name = "categories")
public class Category implements VariableEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String name;

    @Override
    public String toString() {
        return "Category: " + name;
    }
}

package pl.coderslab.charity.donation;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;
import pl.coderslab.charity.category.Category;
import pl.coderslab.charity.institution.Institution;
import pl.coderslab.charity.variableEntity.VariableEntity;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@Data
@Entity
@Table(name = "donations")
public class Donation implements VariableEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Pattern(regexp="^[1-9]+[0-9]*$")
    private String quantity;

    @ManyToMany
    @NotEmpty
    private List<Category> categories = new ArrayList<>();

    @OneToOne
    @NotNull
    private Institution institution;

    @NotBlank
    private String street;

    @Size(min = 1, max = 10)
    private String streetNum;

    @NotBlank
    private String city;

    @NotBlank
    @Pattern(regexp="^\\d{2}-\\d{3}$")
    private String zipCode;

    @Size(min = 7, max = 15)
    private String phoneNumber;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate pickUpDate;

    @DateTimeFormat(pattern = "HH:mm")
    private LocalTime pickUpTime;

    private String pickUpComment;
}

package pl.coderslab.charity.institution;

import java.util.List;
import java.util.Optional;


public interface InstitutionService {

    void save (Institution institution);

    void removeInstitution(Institution institution);

    Optional<Institution> findByInstitutionId(Long id);

    List<Institution> getAll();

    Long countAll();
}

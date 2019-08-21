package pl.coderslab.charity.institution;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;


public interface InstitutionService {

    void save (Institution institution);

    void removeInstitution(Institution institution);

    Institution findByInstitutionId(Long id);

    List<Institution> getAll();

    Long countAll();
}

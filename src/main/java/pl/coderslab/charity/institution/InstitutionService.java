package pl.coderslab.charity.institution;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class InstitutionService {

    private InstitutionRepository institutionRepository;

    @Autowired
    public InstitutionService(InstitutionRepository institutionRepository) {
        this.institutionRepository = institutionRepository;
    }

    public void save (Institution institution) {
        institutionRepository.save(institution);
    }

    public Institution findByInstitutionId(Long id) {
        return institutionRepository.getOne(id);
    }

    public List<Institution> getAll(){
        return institutionRepository.findAll();
    }

    public Long countAll(){
        return institutionRepository.count();
    }
}

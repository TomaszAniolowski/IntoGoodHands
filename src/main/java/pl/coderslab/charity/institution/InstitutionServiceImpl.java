package pl.coderslab.charity.institution;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class InstitutionServiceImpl implements InstitutionService{

    private InstitutionRepository institutionRepository;

    @Autowired
    public InstitutionServiceImpl(InstitutionRepository institutionRepository) {
        this.institutionRepository = institutionRepository;
    }

    @Override
    public void removeInstitution(Institution institution) {
        institutionRepository.delete(institution);
    }

    public void save (Institution institution) {
        institutionRepository.save(institution);
    }

    public Optional<Institution> findByInstitutionId(Long id) {
        return institutionRepository.findById(id);
    }

    public List<Institution> getAll(){
        return institutionRepository.findAll();
    }

    public Long countAll(){
        return institutionRepository.count();
    }
}

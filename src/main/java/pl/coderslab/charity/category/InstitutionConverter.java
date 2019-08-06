package pl.coderslab.charity.category;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import pl.coderslab.charity.institution.Institution;
import pl.coderslab.charity.institution.InstitutionRepository;

public class InstitutionConverter implements Converter<String, Institution> {
    @Autowired
    private InstitutionRepository institutionRepository;

    @Override
    public Institution convert(String id) {
        return institutionRepository.getOne(Long.parseLong(id));
    }
}

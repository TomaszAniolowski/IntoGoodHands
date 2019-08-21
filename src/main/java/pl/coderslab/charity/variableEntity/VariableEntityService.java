package pl.coderslab.charity.variableEntity;

import javax.persistence.EntityNotFoundException;

public interface VariableEntityService {

    void saveObject(VariableEntity dataObject) throws ClassNotFoundException;

    VariableEntity getObject(String instance, Long id) throws ClassNotFoundException;

    void removeObject(String instance, Long id) throws ClassNotFoundException, EntityNotFoundException;
}

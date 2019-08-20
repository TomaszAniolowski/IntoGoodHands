package pl.coderslab.charity.variableEntity;

import javassist.NotFoundException;

public interface VariableEntityService {
    VariableEntity getObject(String instance, Long id) throws NotFoundException;
    void saveObject(VariableEntity dataObject) throws ClassNotFoundException;
}

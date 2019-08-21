package pl.coderslab.charity.variableEntity;

import javassist.NotFoundException;

public interface VariableEntityService {

    void saveObject(VariableEntity dataObject) throws ClassNotFoundException;

    VariableEntity getObject(String instance, Long id) throws NotFoundException;

    void removeObject(VariableEntity dataObject) throws ClassNotFoundException;
}

package pl.coderslab.charity.variableEntity;

import org.springframework.lang.Nullable;

import javax.persistence.EntityNotFoundException;
import java.util.List;

public interface VariableEntityService {

    void saveObject(VariableEntity dataObject) throws ClassNotFoundException;

    VariableEntity getObject(String instance, Long id) throws ClassNotFoundException;

    List<? extends VariableEntity> getAllObjects(String instance, @Nullable String mode) throws ClassNotFoundException;

    void removeObject(String instance, Long id) throws ClassNotFoundException, EntityNotFoundException;
}

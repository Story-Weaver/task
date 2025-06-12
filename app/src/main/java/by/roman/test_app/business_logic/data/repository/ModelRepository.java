package by.roman.test_app.business_logic.data.repository;

import by.roman.test_app.business_logic.data.dto.ModelDTO;
import by.roman.test_app.business_logic.data.models.Model;

public interface ModelRepository {
    Model getModel(long vunp);
    void removeModel(long vunp);
    void addModel(ModelDTO dto);
}

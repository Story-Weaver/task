package by.roman.test_app.business_logic.data.repository;

import java.util.List;

import by.roman.test_app.business_logic.data.dto.ModelDTO;
import by.roman.test_app.business_logic.data.models.Model;

public interface ModelRepository {
    boolean hasRecords();
    Model getModel(String vunp);
    void removeModel(String vunp);
    void addModel(ModelDTO dto);
    List<String> getUMPList();
}

package by.roman.test_app.business_logic.network;

import by.roman.test_app.business_logic.data.dto.ModelDTO;

public interface ModelCallback {
    void onSuccess(ModelDTO dto);
    void onFailure(Throwable t);
}

package by.roman.test_app.business_logic.network;

import by.roman.test_app.business_logic.data.dto.ModelDTO;
import hilt_aggregated_deps._dagger_hilt_android_internal_managers_ActivityComponentManager_ActivityComponentBuilderEntryPoint;

public class DataFromAPI {
    private final ModelAPI modelAPI;
    public DataFromAPI(){
        this.modelAPI = new ModelAPI();
    }
    public void getModel(String uvn, ModelCallback callback){
        modelAPI.fetchByUnv(uvn, new ModelCallback() {
            @Override
            public void onSuccess(ModelDTO dto) {
                callback.onSuccess(dto);
            }

            @Override
            public void onFailure(Throwable t) {
                callback.onFailure(t);
            }
        });
    }
}

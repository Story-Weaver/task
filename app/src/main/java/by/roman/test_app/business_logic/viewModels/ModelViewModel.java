package by.roman.test_app.business_logic.viewModels;

import androidx.lifecycle.ViewModel;

import javax.inject.Inject;

import by.roman.test_app.business_logic.data.dto.ModelDTO;
import by.roman.test_app.business_logic.data.models.Model;
import by.roman.test_app.business_logic.data.repository.ModelRepository;
import by.roman.test_app.business_logic.network.DataFromAPI;
import by.roman.test_app.business_logic.network.ModelCallback;
import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class ModelViewModel extends ViewModel {
    private final ModelRepository modelRepository;
    private final DataFromAPI dataFromAPI;
    @Inject
    public ModelViewModel(ModelRepository modelRepository, DataFromAPI dataFromAPI) {
        this.modelRepository = modelRepository;
        this.dataFromAPI = dataFromAPI;
    }
    public Model getModel(long vump){
        return modelRepository.getModel(vump);
    }
    public void removeModel(long vump){
        modelRepository.removeModel(vump);
    }
    private void getRequest(String vump){
        dataFromAPI.getModel(vump, new ModelCallback() {
            @Override
            public void onSuccess(ModelDTO dto) {
                modelRepository.addModel(dto);
            }

            @Override
            public void onFailure(Throwable t) {

            }
        });
    }
    public void addModel(String vump){
        getRequest(vump);
    }
}

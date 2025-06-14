package by.roman.test_app.business_logic.viewModels;

import android.util.Log;
import android.view.Display;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import javax.inject.Inject;

import by.roman.test_app.business_logic.UiState;
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
    private MutableLiveData<UiState<Model>> localModel = new MutableLiveData<>();
    private MutableLiveData<UiState<Model>> apiModel = new MutableLiveData<>();
    private MutableLiveData<UiState<Model>> addModel = new MutableLiveData<>();
    private MutableLiveData<UiState<Model>> currentModel = new MutableLiveData<>();
    @Inject
    public ModelViewModel(ModelRepository modelRepository, DataFromAPI dataFromAPI) {
        this.modelRepository = modelRepository;
        this.dataFromAPI = dataFromAPI;
    }
    public LiveData<UiState<Model>> getLocalModel(){
        return localModel;
    }
    public LiveData<UiState<Model>> getApiModel(){
        return apiModel;
    }
    public LiveData<UiState<Model>> getAddModel(){
        return addModel;
    }
    public LiveData<UiState<Model>> getCurrentModel(){
        return currentModel;
    }
    public List<String> getUMPList(){
        return modelRepository.getUMPList();
    }
    public void getModel(String vump){
        currentModel.postValue(UiState.loading());
        if(modelRepository.getModel(vump) != null){
            currentModel.postValue(UiState.success(modelRepository.getModel(vump)));
        } else {
            currentModel.postValue(UiState.error("Failed"));
        }
    }
    public void removeModel(String vump){
        modelRepository.removeModel(vump);
    }
    public void findModel(String vump){
        localModel.postValue(UiState.loading());
        apiModel.postValue(UiState.loading());
        if(modelRepository.getModel(vump) != null){
            Log.d("VM", "true");
            localModel.postValue(UiState.success(modelRepository.getModel(vump)));
        } else {
            Log.d("VM", "false");
            localModel.postValue(UiState.error("not found"));
        }
        dataFromAPI.getModel(vump, new ModelCallback() {
            @Override
            public void onSuccess(ModelDTO dto) {
                apiModel.postValue(UiState.success(dto.toModel()));
            }

            @Override
            public void onFailure(Throwable t) {
                apiModel.postValue(UiState.error(t.getMessage()));
            }
        });
    }
    public void addModel(String vump, String email){
        addModel.postValue(UiState.loading());
        dataFromAPI.getModel(vump, new ModelCallback() {
            @Override
            public void onSuccess(ModelDTO dto) {
                dto.mail = email;
                modelRepository.addModel(dto);
                addModel.postValue(UiState.success(dto.toModel()));
            }

            @Override
            public void onFailure(Throwable t) {
                addModel.postValue(UiState.error(t.getMessage()));
            }
        });
    }
}

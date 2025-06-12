package by.roman.test_app.business_logic.viewModels;

import androidx.lifecycle.ViewModel;

import javax.inject.Inject;

import by.roman.test_app.business_logic.data.models.Model;
import by.roman.test_app.business_logic.data.repository.ModelRepository;
import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class ModelViewModel extends ViewModel {
    private final ModelRepository modelRepository;
    @Inject
    public ModelViewModel(ModelRepository modelRepository) {
        this.modelRepository = modelRepository;
    }
    public Model getModel(long vump){
        return modelRepository.getModel(vump);
    }
    public void removeModel(long vump){
        modelRepository.removeModel(vump);
    }
}

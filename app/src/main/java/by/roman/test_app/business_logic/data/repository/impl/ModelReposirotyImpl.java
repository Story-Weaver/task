package by.roman.test_app.business_logic.data.repository.impl;

import android.util.Log;

import by.roman.test_app.business_logic.data.database.ModelDao;
import by.roman.test_app.business_logic.data.dto.ModelDTO;
import by.roman.test_app.business_logic.data.models.Model;
import by.roman.test_app.business_logic.data.repository.ModelRepository;

public class ModelReposirotyImpl implements ModelRepository {
    private final ModelDao modelDao;
    public ModelReposirotyImpl(ModelDao modelDao) {
        this.modelDao = modelDao;
    }

    @Override
    public Model getModel(long vunp) {
        try {
            return modelDao.getModel(vunp);
        } catch (Exception e) {
            Log.e("ModelRepositoryImpl", "Error get model");
            return null;
        }
    }

    @Override
    public void removeModel(long vunp) {
        try {
            modelDao.removeModel(vunp);
        } catch (Exception e) {
            Log.e("ModelRepositoryImpl", "Error remove model");
        }
    }

    @Override
    public void addModel(ModelDTO dto) {
        try {
            modelDao.addModel(dto);
        } catch (Exception e) {
            Log.e("ModelRepositoryImpl", "Error add model");
        }
    }
}

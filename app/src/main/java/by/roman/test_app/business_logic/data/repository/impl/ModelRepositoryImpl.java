package by.roman.test_app.business_logic.data.repository.impl;

import android.util.Log;

import java.util.Collections;
import java.util.List;

import by.roman.test_app.business_logic.data.database.ModelDao;
import by.roman.test_app.business_logic.data.dto.ModelDTO;
import by.roman.test_app.business_logic.data.models.Model;
import by.roman.test_app.business_logic.data.repository.ModelRepository;

public class ModelRepositoryImpl implements ModelRepository {
    private final ModelDao modelDao;
    public ModelRepositoryImpl(ModelDao modelDao) {
        this.modelDao = modelDao;
    }

    @Override
    public boolean hasRecords() {
        try {
            return modelDao.hasRecords();
        } catch (Exception e) {
            Log.e("ModelRepositoryImpl", "Error check records");
            return false;
        }
    }

    @Override
    public Model getModel(String vunp) {
        try {
            return modelDao.getModel(vunp);
        } catch (Exception e) {
            Log.e("ModelRepositoryImpl", "Error get model");
            return null;
        }
    }

    @Override
    public void removeModel(String vunp) {
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

    @Override
    public List<String> getUMPList() {
        try {
            return modelDao.getUMPList();
        } catch (Exception e) {
            Log.e("ModelRepositoryImpl", "Error get list ump");
            return Collections.emptyList();
        }
    }
}

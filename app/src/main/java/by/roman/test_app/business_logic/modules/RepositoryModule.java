package by.roman.test_app.business_logic.modules;

import javax.inject.Singleton;

import by.roman.test_app.business_logic.data.database.ModelDao;
import by.roman.test_app.business_logic.data.repository.ModelRepository;
import by.roman.test_app.business_logic.data.repository.impl.ModelRepositoryImpl;
import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.components.SingletonComponent;

@Module
@InstallIn(SingletonComponent.class)
public class RepositoryModule {
    @Provides
    @Singleton
    public ModelRepository provideRadioRepository(ModelDao modelDao) {
        return new ModelRepositoryImpl(modelDao);
    }
}

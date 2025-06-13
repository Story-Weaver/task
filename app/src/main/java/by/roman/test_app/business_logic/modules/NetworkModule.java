package by.roman.test_app.business_logic.modules;

import javax.inject.Singleton;

import by.roman.test_app.business_logic.network.DataFromAPI;
import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.components.SingletonComponent;

@Module
@InstallIn(SingletonComponent.class)
public class NetworkModule {
    @Provides
    @Singleton
    public static DataFromAPI provideDataFromAPI(){
        return new DataFromAPI();
    }
}

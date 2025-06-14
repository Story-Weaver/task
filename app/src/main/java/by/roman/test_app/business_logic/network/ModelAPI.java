package by.roman.test_app.business_logic.network;

import android.util.Log;

import androidx.annotation.NonNull;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;

import javax.inject.Singleton;

import by.roman.test_app.business_logic.data.dto.ModelDTO;
import by.roman.test_app.business_logic.data.models.Model;
import by.roman.test_app.business_logic.data.models.Organization;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

@Singleton
public class ModelAPI {
    private static final String BASE_URL = "https://grp.nalog.gov.by/api/grp-public/data?unp=";
    public void fetchByUnv(String unv, ModelCallback callback){
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(BASE_URL + unv)
                .build();
        Log.d("API:", BASE_URL + unv);
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                if(response.isSuccessful()){
                    try (response){
                        String jsonResponse = response.body().string();
                        if (jsonResponse.isEmpty()) {
                            Log.e("API: model", "Empty response body.");
                            callback.onFailure(new Exception("Empty response body"));
                            return;
                        }
                        Log.d("UserAPI: model", "Response: " + jsonResponse);

                        if (response.code() != 200) {
                            Log.e("UserAPI: model", "Response code: " + response.code());
                            return;
                        }
                        Gson gson = new GsonBuilder().create();
                        try {
                            if(jsonResponse.startsWith("{")){
                                ApiResponse apiResponse = gson.fromJson(jsonResponse, ApiResponse.class);
                                Organization org = apiResponse.getOrganization();
                                ModelDTO dto = new ModelDTO();
                                dto.vunp = org.getVunp();
                                dto.vnaimp = org.getVnaimp();
                                dto.vnaimk = org.getVnaimk();
                                dto.vpadres = org.getVpadres();
                                dto.dreg = org.getDreg();
                                dto.nmns = org.getNmns();
                                dto.vmns = org.getVmns();
                                dto.ckodsost = org.getCkodsost();
                                dto.vkods = org.getVkods();
                                dto.dlikv = org.getDlikv();
                                dto.vlikv = org.getVlikv();
                                callback.onSuccess(dto);
                            }
                        } catch (Exception e) {
                            Log.e("UserAPI: model", "JSON parsing error", e);
                            callback.onFailure(e);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                        callback.onFailure(e);
                    }
                } else {
                    Log.e("UserAPI: model", "Request failed with code: " + response.code());
                    callback.onFailure(new Exception("Request failed with code: " + response.code()));
                }
            }
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                e.printStackTrace();
                callback.onFailure(e);
            }
        });
    }
}

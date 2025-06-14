package by.roman.test_app.business_logic.network;

import com.google.gson.annotations.SerializedName;

import by.roman.test_app.business_logic.data.models.Organization;

public class ApiResponse {
    @SerializedName("row")
    private Organization organization;

    public Organization getOrganization() {
        return organization;
    }
}
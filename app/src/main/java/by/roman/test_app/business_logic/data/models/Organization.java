package by.roman.test_app.business_logic.data.models;

import com.google.gson.annotations.SerializedName;

public class Organization {
    @SerializedName("vunp")
    private String vunp;

    @SerializedName("vnaimp")
    private String vnaimp;

    @SerializedName("vnaimk")
    private String vnaimk;

    @SerializedName("vpadres")
    private String vpadres;

    @SerializedName("dreg")
    private String dreg;  // Изменено на String

    @SerializedName("nmns")
    private String nmns;

    @SerializedName("vmns")
    private String vmns;

    @SerializedName("ckodsost")
    private String ckodsost;

    @SerializedName("vkods")
    private String vkods;

    @SerializedName("dlikv")
    private String dlikv;  // Изменено на String

    @SerializedName("vlikv")
    private String vlikv;

    // Геттеры
    public String getVunp() { return vunp; }
    public String getVnaimp() { return vnaimp; }
    public String getVnaimk() { return vnaimk; }
    public String getVpadres() { return vpadres; }
    public String getDreg() { return dreg; }
    public String getNmns() { return nmns; }
    public String getVmns() { return vmns; }
    public String getCkodsost() { return ckodsost; }
    public String getVkods() { return vkods; }
    public String getDlikv() { return dlikv; }
    public String getVlikv() { return vlikv; }
}
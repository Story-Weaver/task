package by.roman.test_app.business_logic.data.models;

public class Model {
    private long vunp;
    private String vnaimp;
    private String vnaimk;
    private String vpadres;
    private String dreg;
    private int nmns;
    private String vmns;
    private int ckodsost;
    private String vkods;
    private String dlikv;
    private String vlikv;

    public Model(long vunp, String vnaimp, String vnaimk, String vpadres, String dreg, int nmns, String vmns, int ckodsost, String vkods, String dlikv, String vlikv) {
        this.vunp = vunp;
        this.vnaimp = vnaimp;
        this.vnaimk = vnaimk;
        this.vpadres = vpadres;
        this.dreg = dreg;
        this.nmns = nmns;
        this.vmns = vmns;
        this.ckodsost = ckodsost;
        this.vkods = vkods;
        this.dlikv = dlikv;
        this.vlikv = vlikv;
    }

    public long getVunp() {
        return vunp;
    }
    public String getVnaimp() {
        return vnaimp;
    }
    public String getVnaimk() {
        return vnaimk;
    }
    public String getVpadres() {
        return vpadres;
    }
    public String getDreg() {
        return dreg;
    }
    public int getNmns() {
        return nmns;
    }
    public String getVmns() {
        return vmns;
    }
    public int getCkodsost() {
        return ckodsost;
    }
    public String getVkods() {
        return vkods;
    }
    public String getDlikv() {
        return dlikv;
    }
    public String getVlikv() {
        return vlikv;
    }
}
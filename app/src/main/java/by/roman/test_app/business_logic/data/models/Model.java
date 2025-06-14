package by.roman.test_app.business_logic.data.models;

public class Model {
    private final String vunp;
    private final String mail;
    private final String vnaimp;
    private final String vnaimk;
    private final String vpadres;
    private final String dreg;
    private final String nmns;
    private final String vmns;
    private final String ckodsost;
    private final String vkods;
    private final String dlikv;
    private final String vlikv;

    public Model(String vunp, String mail, String vnaimp, String vnaimk,
                 String vpadres, String dreg, String nmns, String vmns,
                 String ckodsost, String vkods, String dlikv, String vlikv) {
        this.vunp = vunp;
        this.mail = mail;
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

    public String getVunp() {
        return vunp;
    }
    public String getMail() {
        return mail;
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
    public String getNmns() {
        return nmns;
    }
    public String getVmns() {
        return vmns;
    }
    public String getCkodsost() {
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
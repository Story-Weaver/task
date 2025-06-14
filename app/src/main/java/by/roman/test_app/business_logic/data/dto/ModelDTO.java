package by.roman.test_app.business_logic.data.dto;

import androidx.annotation.NonNull;

import by.roman.test_app.business_logic.data.models.Model;

public class ModelDTO {
    public String vunp;
    public String mail;
    public String vnaimp;
    public String vnaimk;
    public String vpadres;
    public String dreg;
    public String nmns;
    public String vmns;
    public String ckodsost;
    public String vkods;
    public String dlikv;
    public String vlikv;
    public Model toModel(){
        return new Model(vunp, mail, vnaimp, vnaimk, vpadres, dreg, nmns, vmns, ckodsost,vkods, dlikv, vlikv);
    }
    public ModelDTO fromModel(@NonNull Model model){
        ModelDTO dto = new ModelDTO();
        dto.vunp = model.getVunp();
        dto.mail = model.getMail();
        dto.vnaimp = model.getVnaimp();
        dto.vnaimk = model.getVnaimk();
        dto.vpadres = model.getVpadres();
        dto.dreg = model.getDreg();
        dto.nmns = model.getNmns();
        dto.vmns = model.getVmns();
        dto.ckodsost = model.getCkodsost();
        dto.vkods = model.getVkods();
        dto.dlikv = model.getDlikv();
        dto.vlikv = model.getVlikv();
        return dto;
    }
}


package smart_school.smartschool.Model.Individual_sessions;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class OCENKI {

    @SerializedName("OcenkaName")
    @Expose
    private String ocenkaName;
    @SerializedName("Ocenka")
    @Expose
    private String ocenka;
    @SerializedName("Ocenka_bal")
    @Expose
    private Object ocenkaBal;
    @SerializedName("Ocenka_zachet")
    @Expose
    private Integer ocenkaZachet;
    @SerializedName("NOT_ON_LESSON")
    @Expose
    private Integer nOTONLESSON;
    @SerializedName("NOT_ON_LESSON_TEXT")
    @Expose
    private String nOTONLESSONTEXT;
    @SerializedName("ZAMECHANIE")
    @Expose
    private String zAMECHANIE;

    public String getOcenkaName() {
        return ocenkaName;
    }

    public void setOcenkaName(String ocenkaName) {
        this.ocenkaName = ocenkaName;
    }

    public String getOcenka() {
        return ocenka;
    }

    public void setOcenka(String ocenka) {
        this.ocenka = ocenka;
    }

    public Object getOcenkaBal() {
        return ocenkaBal;
    }

    public void setOcenkaBal(Object ocenkaBal) {
        this.ocenkaBal = ocenkaBal;
    }

    public Integer getOcenkaZachet() {
        return ocenkaZachet;
    }

    public void setOcenkaZachet(Integer ocenkaZachet) {
        this.ocenkaZachet = ocenkaZachet;
    }

    public Integer getNOTONLESSON() {
        return nOTONLESSON;
    }

    public void setNOTONLESSON(Integer nOTONLESSON) {
        this.nOTONLESSON = nOTONLESSON;
    }

    public String getNOTONLESSONTEXT() {
        return nOTONLESSONTEXT;
    }

    public void setNOTONLESSONTEXT(String nOTONLESSONTEXT) {
        this.nOTONLESSONTEXT = nOTONLESSONTEXT;
    }

    public String getZAMECHANIE() {
        return zAMECHANIE;
    }

    public void setZAMECHANIE(String zAMECHANIE) {
        this.zAMECHANIE = zAMECHANIE;
    }

}

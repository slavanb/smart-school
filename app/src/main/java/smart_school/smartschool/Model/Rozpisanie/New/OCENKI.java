
package smart_school.smartschool.Model.Rozpisanie.New;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class OCENKI {

    @SerializedName("OcenkaName")
    @Expose
    private String ocenkaName;
    @SerializedName("Ocenka")
    @Expose
    private String ocenka;
    @SerializedName("NOT_ON_LESSON")
    @Expose
    private Integer nOTONLESSON;
    @SerializedName("NOT_ON_LESSON_TEXT")
    @Expose
    private String nOTONLESSONTEXT;
    @SerializedName("ZAMECHANIE")
    @Expose
    private String zAMECHANIE;
    @SerializedName("Ocenka_bal")
    @Expose
    private String ocenka_bal;
    @SerializedName("Ocenka_progressbar")
    @Expose
    private String Ocenka_progressbar;

    @SerializedName("Ocenka_bal_color")
    @Expose
    private String Ocenka_bal_color;

    public String getOcenka_progressbar() {
        return Ocenka_progressbar;
    }

    public void setOcenka_progressbar(String ocenka_progressbar) {
        Ocenka_progressbar = ocenka_progressbar;
    }

    public String getOcenka_bal_color() {
        return Ocenka_bal_color;
    }

    public void setOcenka_bal_color(String ocenka_bal_color) {
        Ocenka_bal_color = ocenka_bal_color;
    }

    public String getOcenka_bal() {
        return ocenka_bal;
    }

    public void setOcenka_bal(String ocenka_bal) {
        this.ocenka_bal = ocenka_bal;
    }

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

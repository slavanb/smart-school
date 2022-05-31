
package smart_school.smartschool.Model.Rozpisanie;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class Lesson extends RealmObject {

    @SerializedName("LESSON_ID")
    @Expose
    @PrimaryKey
    private Integer lESSONID;
    @SerializedName("LESSON_DATE")
    @Expose
    private String lESSONDATE;
    @SerializedName("LESSON_DATE_UA")
    @Expose
    private String lESSONDATEUA;
    @SerializedName("LESSON_DATE_L")
    @Expose
    private String lESSONDATEL;
    @SerializedName("LESSON_DATE_N")
    @Expose
    private String lESSONDATEN;
    @SerializedName("LESSON_NUM")
    @Expose
    private Integer lESSONNUM;
    @SerializedName("LESSON_NUM_STR")
    @Expose
    private String lESSONNUMSTR;
    @SerializedName("LESSON_TYPE")
    @Expose
    private Integer lESSONTYPE;
    @SerializedName("PREDMET_NAME")
    @Expose
    private String pREDMETNAME;
    @SerializedName("NAME_GRUP")
    @Expose
    private String nAMEGRUP;
    @SerializedName("NOT_ON_LESSON")
    @Expose
    private Integer nOTONLESSON;
    @SerializedName("OCENKI")
    @Expose
    private String oCENKI;
    @SerializedName("ZAMECHANIE")
    @Expose
    private String zAMECHANIE;
    @SerializedName("LESSON_THEMA")
    @Expose
    private String lESSONTHEMA;

    public Integer getLESSONID() {
        return lESSONID;
    }

    public void setLESSONID(Integer lESSONID) {
        this.lESSONID = lESSONID;
    }

    public String getLESSONDATE() {
        return lESSONDATE;
    }

    public void setLESSONDATE(String lESSONDATE) {
        this.lESSONDATE = lESSONDATE;
    }

    public String getLESSONDATEUA() {
        return lESSONDATEUA;
    }

    public void setLESSONDATEUA(String lESSONDATEUA) {
        this.lESSONDATEUA = lESSONDATEUA;
    }

    public String getLESSONDATEL() {
        return lESSONDATEL;
    }

    public void setLESSONDATEL(String lESSONDATEL) {
        this.lESSONDATEL = lESSONDATEL;
    }

    public String getLESSONDATEN() {
        return lESSONDATEN;
    }

    public void setLESSONDATEN(String lESSONDATEN) {
        this.lESSONDATEN = lESSONDATEN;
    }

    public Integer getLESSONNUM() {
        return lESSONNUM;
    }

    public void setLESSONNUM(Integer lESSONNUM) {
        this.lESSONNUM = lESSONNUM;
    }

    public String getLESSONNUMSTR() {
        return lESSONNUMSTR;
    }

    public void setLESSONNUMSTR(String lESSONNUMSTR) {
        this.lESSONNUMSTR = lESSONNUMSTR;
    }

    public Integer getLESSONTYPE() {
        return lESSONTYPE;
    }

    public void setLESSONTYPE(Integer lESSONTYPE) {
        this.lESSONTYPE = lESSONTYPE;
    }

    public String getPREDMETNAME() {
        return pREDMETNAME;
    }

    public void setPREDMETNAME(String pREDMETNAME) {
        this.pREDMETNAME = pREDMETNAME;
    }

    public String getNAMEGRUP() {
        return nAMEGRUP;
    }

    public void setNAMEGRUP(String nAMEGRUP) {
        this.nAMEGRUP = nAMEGRUP;
    }

    public Integer getNOTONLESSON() {
        return nOTONLESSON;
    }

    public void setNOTONLESSON(Integer nOTONLESSON) {
        this.nOTONLESSON = nOTONLESSON;
    }

    public String getOCENKI() {
        return oCENKI;
    }

    public void setOCENKI(String oCENKI) {
        this.oCENKI = oCENKI;
    }

    public String getZAMECHANIE() {
        return zAMECHANIE;
    }

    public void setZAMECHANIE(String zAMECHANIE) {
        this.zAMECHANIE = zAMECHANIE;
    }

    public String getLESSONTHEMA() {
        return lESSONTHEMA;
    }

    public void setLESSONTHEMA(String lESSONTHEMA) {
        this.lESSONTHEMA = lESSONTHEMA;
    }

}

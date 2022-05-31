
package smart_school.smartschool.Model.One_lesson_statistic_info;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Ocenki {

    @SerializedName("LESSON_ID")
    @Expose
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
    @SerializedName("PREDMET_HTML_TEXT")
    @Expose
    private String pREDMETHTMLTEXT;
    @SerializedName("PREDMET_NAME")
    @Expose
    private String pREDMETNAME;
    @SerializedName("teacher_FIO")
    @Expose
    private String teacherFIO;
    @SerializedName("LESSON_NUM")
    @Expose
    private Integer lESSONNUM;
    @SerializedName("LESSON_NUM_STR")
    @Expose
    private String lESSONNUMSTR;
    @SerializedName("LESSON_TYPE")
    @Expose
    private Integer lESSONTYPE;
    @SerializedName("NOT_ON_LESSON")
    @Expose
    private String nOTONLESSON;
    @SerializedName("OCENKA")
    @Expose
    private String oCENKA;
    @SerializedName("LESSON_THEMA")
    @Expose
    private String lESSONTHEMA;
    @SerializedName("ZAMECHANIE")
    @Expose
    private String zAMECHANIE;

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

    public String getPREDMETHTMLTEXT() {
        return pREDMETHTMLTEXT;
    }

    public void setPREDMETHTMLTEXT(String pREDMETHTMLTEXT) {
        this.pREDMETHTMLTEXT = pREDMETHTMLTEXT;
    }

    public String getPREDMETNAME() {
        return pREDMETNAME;
    }

    public void setPREDMETNAME(String pREDMETNAME) {
        this.pREDMETNAME = pREDMETNAME;
    }

    public String getTeacherFIO() {
        return teacherFIO;
    }

    public void setTeacherFIO(String teacherFIO) {
        this.teacherFIO = teacherFIO;
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

    public String getNOTONLESSON() {
        return nOTONLESSON;
    }

    public void setNOTONLESSON(String nOTONLESSON) {
        this.nOTONLESSON = nOTONLESSON;
    }

    public String getOCENKA() {
        return oCENKA;
    }

    public void setOCENKA(String oCENKA) {
        this.oCENKA = oCENKA;
    }

    public String getLESSONTHEMA() {
        return lESSONTHEMA;
    }

    public void setLESSONTHEMA(String lESSONTHEMA) {
        this.lESSONTHEMA = lESSONTHEMA;
    }

    public String getZAMECHANIE() {
        return zAMECHANIE;
    }

    public void setZAMECHANIE(String zAMECHANIE) {
        this.zAMECHANIE = zAMECHANIE;
    }

}

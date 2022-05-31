package smart_school.smartschool.Model.One_Lesson;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
public class One_lesson_info {
    @SerializedName("auth")
    @Expose
    private String auth;
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
    @SerializedName("KLASS_ID")
    @Expose
    private Integer kLASSID;
    @SerializedName("KLASS_GRUP_ID")
    @Expose
    private Integer kLASSGRUPID;
    @SerializedName("KLASS_NAME")
    @Expose
    private String kLASSNAME;
    @SerializedName("PREDMET_NAME")
    @Expose
    private String pREDMETNAME;
    @SerializedName("NAME_GRUP")
    @Expose
    private String nAMEGRUP;
    @SerializedName("KLASS_RELATION_GRUP")
    @Expose
    private String kLASSRELATIONGRUP;
    @SerializedName("teacherFullName")
    @Expose
    private String teacherFullName;
    @SerializedName("LESSON_THEMA")
    @Expose
    private String lESSONTHEMA;
    @SerializedName("HomeWork")
    @Expose
    private String homeWork;
    @SerializedName("KABINET_NAME")
    @Expose
    private Object kABINETNAME;

    public String getAuth() {
        return auth;
    }

    public void setAuth(String auth) {
        this.auth = auth;
    }

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

    public Integer getKLASSID() {
        return kLASSID;
    }

    public void setKLASSID(Integer kLASSID) {
        this.kLASSID = kLASSID;
    }

    public Integer getKLASSGRUPID() {
        return kLASSGRUPID;
    }

    public void setKLASSGRUPID(Integer kLASSGRUPID) {
        this.kLASSGRUPID = kLASSGRUPID;
    }

    public String getKLASSNAME() {
        return kLASSNAME;
    }

    public void setKLASSNAME(String kLASSNAME) {
        this.kLASSNAME = kLASSNAME;
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

    public String getKLASSRELATIONGRUP() {
        return kLASSRELATIONGRUP;
    }

    public void setKLASSRELATIONGRUP(String kLASSRELATIONGRUP) {
        this.kLASSRELATIONGRUP = kLASSRELATIONGRUP;
    }

    public String getTeacherFullName() {
        return teacherFullName;
    }

    public void setTeacherFullName(String teacherFullName) {
        this.teacherFullName = teacherFullName;
    }

    public String getLESSONTHEMA() {
        return lESSONTHEMA;
    }

    public void setLESSONTHEMA(String lESSONTHEMA) {
        this.lESSONTHEMA = lESSONTHEMA;
    }

    public String getHomeWork() {
        return homeWork;
    }

    public void setHomeWork(String homeWork) {
        this.homeWork = homeWork;
    }

    public Object getKABINETNAME() {
        return kABINETNAME;
    }

    public void setKABINETNAME(Object kABINETNAME) {
        this.kABINETNAME = kABINETNAME;
    }

}

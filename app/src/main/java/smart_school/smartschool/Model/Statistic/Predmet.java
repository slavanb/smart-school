
package smart_school.smartschool.Model.Statistic;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Predmet {

    @SerializedName("PREDMET_ID")
    @Expose
    private String pREDMETID;
    @SerializedName("PREDMET_NAME")
    @Expose
    private String pREDMETNAME;
    @SerializedName("KLASS_ID")
    @Expose
    private String kLASSID;
    @SerializedName("KLASS_NAME")
    @Expose
    private String kLASSNAME;
    @SerializedName("avgOcenka")
    @Expose
    private String avgOcenka;
    @SerializedName("avgWeightOcenka")
    @Expose
    private String avgWeightOcenka;
    @SerializedName("cntOcenki")
    @Expose
    private String cntOcenki;
    @SerializedName("cntZachet")
    @Expose
    private String cntZachet;
    @SerializedName("cntNoZachet")
    @Expose
    private String cntNoZachet;
    @SerializedName("cntNotOnLesson")
    @Expose
    private String cntNotOnLesson;
    @SerializedName("cntLateOnLesson")
    @Expose
    private String cntLateOnLesson;
    @SerializedName("teacher_FIO")
    @Expose
    private String teacherFIO;
    @SerializedName("TEACHER_ID")
    @Expose
    private String tEACHERID;
    @SerializedName("TEACHER_HUMAN_ID")
    @Expose
    private String tEACHERHUMANID;
    @SerializedName("Klass_Pr_Obuch_LIST_ID")
    @Expose
    private String Klass_Pr_Obuch_LIST_ID;

    public String getKlass_Pr_Obuch_LIST_ID() {
        return Klass_Pr_Obuch_LIST_ID;
    }

    public void setKlass_Pr_Obuch_LIST_ID(String klass_Pr_Obuch_LIST_ID) {
        Klass_Pr_Obuch_LIST_ID = klass_Pr_Obuch_LIST_ID;
    }

    public String getPREDMETID() {
        return pREDMETID;
    }

    public void setPREDMETID(String pREDMETID) {
        this.pREDMETID = pREDMETID;
    }

    public String getPREDMETNAME() {
        return pREDMETNAME;
    }

    public void setPREDMETNAME(String pREDMETNAME) {
        this.pREDMETNAME = pREDMETNAME;
    }

    public String getKLASSID() {
        return kLASSID;
    }

    public void setKLASSID(String kLASSID) {
        this.kLASSID = kLASSID;
    }

    public String getKLASSNAME() {
        return kLASSNAME;
    }

    public void setKLASSNAME(String kLASSNAME) {
        this.kLASSNAME = kLASSNAME;
    }

    public String getAvgOcenka() {
        return avgOcenka;
    }

    public void setAvgOcenka(String avgOcenka) {
        this.avgOcenka = avgOcenka;
    }

    public String getAvgWeightOcenka() {
        return avgWeightOcenka;
    }

    public void setAvgWeightOcenka(String avgWeightOcenka) {
        this.avgWeightOcenka = avgWeightOcenka;
    }

    public String getCntOcenki() {
        return cntOcenki;
    }

    public void setCntOcenki(String cntOcenki) {
        this.cntOcenki = cntOcenki;
    }

    public String getCntZachet() {
        return cntZachet;
    }

    public void setCntZachet(String cntZachet) {
        this.cntZachet = cntZachet;
    }

    public String getCntNoZachet() {
        return cntNoZachet;
    }

    public void setCntNoZachet(String cntNoZachet) {
        this.cntNoZachet = cntNoZachet;
    }

    public String getCntNotOnLesson() {
        return cntNotOnLesson;
    }

    public void setCntNotOnLesson(String cntNotOnLesson) {
        this.cntNotOnLesson = cntNotOnLesson;
    }

    public String getCntLateOnLesson() {
        return cntLateOnLesson;
    }

    public void setCntLateOnLesson(String cntLateOnLesson) {
        this.cntLateOnLesson = cntLateOnLesson;
    }

    public String getTeacherFIO() {
        return teacherFIO;
    }

    public void setTeacherFIO(String teacherFIO) {
        this.teacherFIO = teacherFIO;
    }

    public String getTEACHERID() {
        return tEACHERID;
    }

    public void setTEACHERID(String tEACHERID) {
        this.tEACHERID = tEACHERID;
    }

    public String getTEACHERHUMANID() {
        return tEACHERHUMANID;
    }

    public void setTEACHERHUMANID(String tEACHERHUMANID) {
        this.tEACHERHUMANID = tEACHERHUMANID;
    }

}

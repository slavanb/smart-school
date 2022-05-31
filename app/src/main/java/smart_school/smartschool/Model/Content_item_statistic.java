package smart_school.smartschool.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import smart_school.smartschool.Adapters.ListItem;

public class Content_item_statistic extends ListItem {
    @SerializedName("PREDMET_ID")
    @Expose
    private Integer pREDMETID;
    @SerializedName("PREDMET_NAME")
    @Expose
    private String pREDMETNAME;
    @SerializedName("KLASS_ID")
    @Expose
    private Integer kLASSID;
    @SerializedName("KLASS_NAME")
    @Expose
    private String kLASSNAME;
    @SerializedName("avgOcenka")
    @Expose
    private Double avgOcenka;
    @SerializedName("avgWeightOcenka")
    @Expose
    private Double avgWeightOcenka;
    @SerializedName("cntOcenki")
    @Expose
    private Integer cntOcenki;
    @SerializedName("cntZachet")
    @Expose
    private Integer cntZachet;
    @SerializedName("cntNoZachet")
    @Expose
    private Integer cntNoZachet;
    @SerializedName("cntNotOnLesson")
    @Expose
    private Integer cntNotOnLesson;
    @SerializedName("cntLateOnLesson")
    @Expose
    private Integer cntLateOnLesson;
    @SerializedName("teacher_FIO")
    @Expose
    private String teacherFIO;
    @SerializedName("TEACHER_ID")
    @Expose
    private Integer tEACHERID;
    @SerializedName("TEACHER_HUMAN_ID")
    @Expose
    private Integer tEACHERHUMANID;

    public Integer getpREDMETID() {
        return pREDMETID;
    }

    public void setpREDMETID(Integer pREDMETID) {
        this.pREDMETID = pREDMETID;
    }

    public String getpREDMETNAME() {
        return pREDMETNAME;
    }

    public void setpREDMETNAME(String pREDMETNAME) {
        this.pREDMETNAME = pREDMETNAME;
    }

    public Integer getkLASSID() {
        return kLASSID;
    }

    public void setkLASSID(Integer kLASSID) {
        this.kLASSID = kLASSID;
    }

    public String getkLASSNAME() {
        return kLASSNAME;
    }

    public void setkLASSNAME(String kLASSNAME) {
        this.kLASSNAME = kLASSNAME;
    }

    public Double getAvgOcenka() {
        return avgOcenka;
    }

    public void setAvgOcenka(Double avgOcenka) {
        this.avgOcenka = avgOcenka;
    }

    public Double getAvgWeightOcenka() {
        return avgWeightOcenka;
    }

    public void setAvgWeightOcenka(Double avgWeightOcenka) {
        this.avgWeightOcenka = avgWeightOcenka;
    }

    public Integer getCntOcenki() {
        return cntOcenki;
    }

    public void setCntOcenki(Integer cntOcenki) {
        this.cntOcenki = cntOcenki;
    }

    public Integer getCntZachet() {
        return cntZachet;
    }

    public void setCntZachet(Integer cntZachet) {
        this.cntZachet = cntZachet;
    }

    public Integer getCntNoZachet() {
        return cntNoZachet;
    }

    public void setCntNoZachet(Integer cntNoZachet) {
        this.cntNoZachet = cntNoZachet;
    }

    public Integer getCntNotOnLesson() {
        return cntNotOnLesson;
    }

    public void setCntNotOnLesson(Integer cntNotOnLesson) {
        this.cntNotOnLesson = cntNotOnLesson;
    }

    public Integer getCntLateOnLesson() {
        return cntLateOnLesson;
    }

    public void setCntLateOnLesson(Integer cntLateOnLesson) {
        this.cntLateOnLesson = cntLateOnLesson;
    }

    public String getTeacherFIO() {
        return teacherFIO;
    }

    public void setTeacherFIO(String teacherFIO) {
        this.teacherFIO = teacherFIO;
    }

    public Integer gettEACHERID() {
        return tEACHERID;
    }

    public void settEACHERID(Integer tEACHERID) {
        this.tEACHERID = tEACHERID;
    }

    public Integer gettEACHERHUMANID() {
        return tEACHERHUMANID;
    }

    public void settEACHERHUMANID(Integer tEACHERHUMANID) {
        this.tEACHERHUMANID = tEACHERHUMANID;
    }
}


package smart_school.smartschool.Model.Statistic;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class StatisticHead {

    @SerializedName("auth")
    @Expose
    private String auth;
    @SerializedName("pupil")
    @Expose
    private String pupil;
    @SerializedName("date")
    @Expose
    private Date date;
    @SerializedName("AVG_OcenkaAll")
    @Expose
    private String aVGOcenkaAll;
    @SerializedName("AVG_WeightOcenkaAll")
    @Expose
    private String aVGWeightOcenkaAll;
    @SerializedName("SUM_cntOcenki")
    @Expose
    private String sUMCntOcenki;
    @SerializedName("SUM_cntZachet")
    @Expose
    private String sUMCntZachet;
    @SerializedName("SUM_cntNoZachet")
    @Expose
    private String sUMCntNoZachet;
    @SerializedName("SUM_cntNotOnLesson")
    @Expose
    private String sUMCntNotOnLesson;
    @SerializedName("SUM_cntLateOnLesson")
    @Expose
    private String sUMCntLateOnLesson;
    @SerializedName("predmets")
    @Expose
    private List<Predmet> predmets = null;
    private String mark_system;

    public String getMark_system() {
        return mark_system;
    }

    public void setMark_system(String mark_system) {
        this.mark_system = mark_system;
    }

    public String getAuth() {
        return auth;
    }

    public void setAuth(String auth) {
        this.auth = auth;
    }

    public String getPupil() {
        return pupil;
    }

    public void setPupil(String pupil) {
        this.pupil = pupil;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getAVGOcenkaAll() {
        return aVGOcenkaAll;
    }

    public void setAVGOcenkaAll(String aVGOcenkaAll) {
        this.aVGOcenkaAll = aVGOcenkaAll;
    }

    public String getAVGWeightOcenkaAll() {
        return aVGWeightOcenkaAll;
    }

    public void setAVGWeightOcenkaAll(String aVGWeightOcenkaAll) {
        this.aVGWeightOcenkaAll = aVGWeightOcenkaAll;
    }

    public String getSUMCntOcenki() {
        return sUMCntOcenki;
    }

    public void setSUMCntOcenki(String sUMCntOcenki) {
        this.sUMCntOcenki = sUMCntOcenki;
    }

    public String getSUMCntZachet() {
        return sUMCntZachet;
    }

    public void setSUMCntZachet(String sUMCntZachet) {
        this.sUMCntZachet = sUMCntZachet;
    }

    public String getSUMCntNoZachet() {
        return sUMCntNoZachet;
    }

    public void setSUMCntNoZachet(String sUMCntNoZachet) {
        this.sUMCntNoZachet = sUMCntNoZachet;
    }

    public String getSUMCntNotOnLesson() {
        return sUMCntNotOnLesson;
    }

    public void setSUMCntNotOnLesson(String sUMCntNotOnLesson) {
        this.sUMCntNotOnLesson = sUMCntNotOnLesson;
    }

    public String getSUMCntLateOnLesson() {
        return sUMCntLateOnLesson;
    }

    public void setSUMCntLateOnLesson(String sUMCntLateOnLesson) {
        this.sUMCntLateOnLesson = sUMCntLateOnLesson;
    }

    public List<Predmet> getPredmets() {
        return predmets;
    }

    public void setPredmets(List<Predmet> predmets) {
        this.predmets = predmets;
    }

}

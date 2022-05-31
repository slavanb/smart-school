package smart_school.smartschool.Model.Pupil_classes;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class Pupil extends RealmObject {

    @PrimaryKey
    @SerializedName("pupil_id")
    @Expose
    private Integer pupilId;
    @SerializedName("human_id")
    @Expose
    private Integer humanId;
    @SerializedName("familia")
    @Expose
    private String familia;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("otchestvo")
    @Expose
    private String otchestvo;
    @SerializedName("human_avatar")
    @Expose
    private String humanAvatar;
    @SerializedName("klass")
    @Expose
    private Klass_Info klass = null;

    public Klass_Info getKlass() {
        return klass;
    }

    public void setKlass(Klass_Info klass) {
        this.klass = klass;
    }

    public Integer getPupilId() {
        return pupilId;
    }

    public void setPupilId(Integer pupilId) {
        this.pupilId = pupilId;
    }

    public Integer getHumanId() {
        return humanId;
    }

    public void setHumanId(Integer humanId) {
        this.humanId = humanId;
    }

    public String getFamilia() {
        return familia;
    }

    public void setFamilia(String familia) {
        this.familia = familia;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOtchestvo() {
        return otchestvo;
    }

    public void setOtchestvo(String otchestvo) {
        this.otchestvo = otchestvo;
    }

    public String getHumanAvatar() {
        return humanAvatar;
    }

    public void setHumanAvatar(String humanAvatar) {
        this.humanAvatar = humanAvatar;
    }

}
package smart_school.smartschool.Model.All_messages;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Teacher implements Serializable {

    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("human_id")
    @Expose
    private Integer humanId;
    @SerializedName("avatar")
    @Expose
    private String avatar;
    @SerializedName("predmet_name")
    @Expose
    private String predmet_name;
    @SerializedName("klass_name")
    @Expose
    private String klass_name;

    public String getPredmet_name() {
        return predmet_name;
    }

    public void setPredmet_name(String predmet_name) {
        this.predmet_name = predmet_name;
    }

    public String getKlass_name() {
        return klass_name;
    }

    public void setKlass_name(String klass_name) {
        this.klass_name = klass_name;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getHumanId() {
        return humanId;
    }

    public void setHumanId(Integer humanId) {
        this.humanId = humanId;
    }

}
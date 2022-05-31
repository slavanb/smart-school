
package smart_school.smartschool.Model.One_Studet_info;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class PupilInfo extends RealmObject {

    @SerializedName("familia")
    @Expose
    private String familia;
    @SerializedName("name")
    @Expose
    @PrimaryKey
    private String name;
    @SerializedName("otchestvo")
    @Expose
    private String otchestvo;
    @SerializedName("pupil_id")
    @Expose
    private Integer pupilId;
    @SerializedName("human_id")
    @Expose
    private Integer humanId;
    @SerializedName("human_avatar")
    @Expose
    private String humanAvatar;
    @SerializedName("klass")
    @Expose
    private Klass klass;

    @SerializedName("allow_view")
    @Expose
    private boolean allow_view;
    @SerializedName("allow_view_date")
    @Expose
    private String allow_view_date;
    @SerializedName("pay_text")
    @Expose
    private String pay_text;
    @SerializedName("pay_link")
    @Expose
    private String pay_link;

    public boolean getAllow_view() {
        return allow_view;
    }

    public void setAllow_view(boolean allow_view) {
        this.allow_view = allow_view;
    }

    public String getAllow_view_date() {
        return allow_view_date;
    }

    public void setAllow_view_date(String allow_view_date) {
        this.allow_view_date = allow_view_date;
    }

    public String getPay_text() {
        return pay_text;
    }

    public void setPay_text(String pay_text) {
        this.pay_text = pay_text;
    }

    public String getPay_link() {
        return pay_link;
    }

    public void setPay_link(String pay_link) {
        this.pay_link = pay_link;
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

    public String getHumanAvatar() {
        return humanAvatar;
    }

    public void setHumanAvatar(String humanAvatar) {
        this.humanAvatar = humanAvatar;
    }

    public Klass getKlass() {
        return klass;
    }

    public void setKlass(Klass klass) {
        this.klass = klass;
    }

}

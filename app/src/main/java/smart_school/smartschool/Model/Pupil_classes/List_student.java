package smart_school.smartschool.Model.Pupil_classes;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class List_student extends RealmObject {
    @SerializedName("auth")
    @Expose
    @PrimaryKey
    private String auth;
    @SerializedName("pupils")
    @Expose
    private RealmList<Pupil> pupils = null;

    public String getAuth() {
        return auth;
    }

    public void setAuth(String auth) {
        this.auth = auth;
    }

    public RealmList<Pupil> getPupils() {
        return pupils;
    }

    public void setPupils(RealmList<Pupil> pupils) {
        this.pupils = pupils;
    }

}

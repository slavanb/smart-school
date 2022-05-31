
package smart_school.smartschool.Model.One_Studet_info;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class Example extends RealmObject {

    @SerializedName("auth")
    @Expose
    @PrimaryKey
    private String auth;
    @SerializedName("pupil_info")
    @Expose
    private PupilInfo pupilInfo;

    public String getAuth() {
        return auth;
    }

    public void setAuth(String auth) {
        this.auth = auth;
    }

    public PupilInfo getPupilInfo() {
        return pupilInfo;
    }

    public void setPupilInfo(PupilInfo pupilInfo) {
        this.pupilInfo = pupilInfo;
    }

}

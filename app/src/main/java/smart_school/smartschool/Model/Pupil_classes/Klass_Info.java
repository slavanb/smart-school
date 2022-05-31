package smart_school.smartschool.Model.Pupil_classes;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;

public class Klass_Info extends RealmObject {

    @SerializedName("FullKlassName")
    @Expose
    private String FullKlassName;


    public String getFullKlassName() {
        return FullKlassName;
    }

    public void setFullKlassName(String fullKlassName) {
        FullKlassName = fullKlassName;
    }
}

package smart_school.smartschool.Model.All_dialogs;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class OneDialog {

@SerializedName("auth")
@Expose
private String auth;
@SerializedName("dialogs")
@Expose
private List<Dialog> dialogs = null;

public String getAuth() {
return auth;
}

public void setAuth(String auth) {
this.auth = auth;
}

public List<Dialog> getDialogs() {
return dialogs;
}

public void setDialogs(List<Dialog> dialogs) {
this.dialogs = dialogs;
}

}
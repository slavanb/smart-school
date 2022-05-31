package smart_school.smartschool.Model.All_messages;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MessagesHead {

@SerializedName("auth")
@Expose
private String auth;
@SerializedName("interlocutors")
@Expose
private Interlocutors interlocutors;

public String getAuth() {
return auth;
}

public void setAuth(String auth) {
this.auth = auth;
}

public Interlocutors getInterlocutors() {
return interlocutors;
}

public void setInterlocutors(Interlocutors interlocutors) {
this.interlocutors = interlocutors;
}

}
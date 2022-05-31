package smart_school.smartschool.Model.One_dialog_messages;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class OneDialog {

@SerializedName("auth")
@Expose
private String auth;
@SerializedName("interlocutor_id")
@Expose
private Integer interlocutorId;
@SerializedName("interlocutor_name")
@Expose
private String interlocutorName;
@SerializedName("messages")
@Expose
private List<Message> messages = null;

public String getAuth() {
return auth;
}

public void setAuth(String auth) {
this.auth = auth;
}

public Integer getInterlocutorId() {
return interlocutorId;
}

public void setInterlocutorId(Integer interlocutorId) {
this.interlocutorId = interlocutorId;
}

public String getInterlocutorName() {
return interlocutorName;
}

public void setInterlocutorName(String interlocutorName) {
this.interlocutorName = interlocutorName;
}

public List<Message> getMessages() {
return messages;
}

public void setMessages(List<Message> messages) {
this.messages = messages;
}

}

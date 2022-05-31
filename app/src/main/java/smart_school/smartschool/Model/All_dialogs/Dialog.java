package smart_school.smartschool.Model.All_dialogs;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Dialog {

@SerializedName("not_read")
@Expose
private String notRead;
@SerializedName("interlocutor_name")
@Expose
private String interlocutorName;
@SerializedName("interlocutor_id")
@Expose
private Integer interlocutorId;
@SerializedName("interlocutor_avatar")
@Expose
private String interlocutorAvatar;
@SerializedName("preview_text")
@Expose
private String previewText;
@SerializedName("date_time")
@Expose
private String dateTime;
@SerializedName("role")
@Expose
private String role;

public String getNotRead() {
return notRead;
}

public void setNotRead(String notRead) {
this.notRead = notRead;
}

public String getInterlocutorName() {
return interlocutorName;
}

public void setInterlocutorName(String interlocutorName) {
this.interlocutorName = interlocutorName;
}

public Integer getInterlocutorId() {
return interlocutorId;
}

public void setInterlocutorId(Integer interlocutorId) {
this.interlocutorId = interlocutorId;
}

public String getInterlocutorAvatar() {
return interlocutorAvatar;
}

public void setInterlocutorAvatar(String interlocutorAvatar) {
this.interlocutorAvatar = interlocutorAvatar;
}

public String getPreviewText() {
return previewText;
}

public void setPreviewText(String previewText) {
this.previewText = previewText;
}

public String getDateTime() {
return dateTime;
}

public void setDateTime(String dateTime) {
this.dateTime = dateTime;
}

public String getRole() {
return role;
}

public void setRole(String role) {
this.role = role;
}

}
package smart_school.smartschool.Model.Token;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class Token_class_user extends RealmObject {

    @PrimaryKey
@SerializedName("id")
@Expose
private Integer id;
@SerializedName("login")
@Expose
private String login;
@SerializedName("email")
@Expose
private String email;
@SerializedName("familia")
@Expose
private String familia;
@SerializedName("name")
@Expose
private String name;
@SerializedName("otchestvo")
@Expose
private String otchestvo;
@SerializedName("parent_or_pupil")
@Expose
private String parentOrPupil;
@SerializedName("pupil_id")
@Expose
private String pupilId;
@SerializedName("parent_id")
@Expose
private Integer parentId;
@SerializedName("human_id")
@Expose
private Integer humanId;
@SerializedName("human_avatar")
@Expose
private String humanAvatar;

public Integer getId() {
return id;
}

public void setId(Integer id) {
this.id = id;
}

public String getLogin() {
return login;
}

public void setLogin(String login) {
this.login = login;
}

public String getEmail() {
return email;
}

public void setEmail(String email) {
this.email = email;
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

public String getParentOrPupil() {
return parentOrPupil;
}

public void setParentOrPupil(String parentOrPupil) {
this.parentOrPupil = parentOrPupil;
}

public String getPupilId() {
return pupilId;
}

public void setPupilId(String pupilId) {
this.pupilId = pupilId;
}

public Integer getParentId() {
return parentId;
}

public void setParentId(Integer parentId) {
this.parentId = parentId;
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

}

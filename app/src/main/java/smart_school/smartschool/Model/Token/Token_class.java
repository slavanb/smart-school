package smart_school.smartschool.Model.Token;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class Token_class extends RealmObject{

@SerializedName("auth")
@Expose
private String auth;
@PrimaryKey
@SerializedName("token")
@Expose
private String token;
@SerializedName("user")
@Expose
private Token_class_user user;

public String getAuth() {
return auth;
}

public void setAuth(String auth) {
this.auth = auth;
}

public String getToken() {
return token;
}

public void setToken(String token) {
this.token = token;
}

public Token_class_user getUser() {
return user;
}

public void setUser(Token_class_user user) {
this.user = user;
}

}
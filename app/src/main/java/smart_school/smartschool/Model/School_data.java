package smart_school.smartschool.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class School_data extends RealmObject {

@SerializedName("id")
@Expose
@PrimaryKey
private Integer id;
@SerializedName("CityId")
@Expose
private Integer cityId;
@SerializedName("ServerName")
@Expose
private String serverName;
@SerializedName("ServerLink")
@Expose
private String serverLink;

    public School_data(Integer id, Integer cityId, String serverName, String serverLink) {
        this.id = id;
        this.cityId = cityId;
        this.serverName = serverName;
        this.serverLink = serverLink;
    }

    public School_data(){

    }

    public Integer getId() {
return id;
}

public void setId(Integer id) {
this.id = id;
}

public Integer getCityId() {
return cityId;
}

public void setCityId(Integer cityId) {
this.cityId = cityId;
}

public String getServerName() {
return serverName;
}

public void setServerName(String serverName) {
this.serverName = serverName;
}

public String getServerLink() {
return serverLink;
}

public void setServerLink(String serverLink) {
this.serverLink = serverLink;
}

}
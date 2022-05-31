package smart_school.smartschool.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class City_name extends RealmObject {

@SerializedName("id")
@Expose
@PrimaryKey
private Integer id;
@SerializedName("CityName")
@Expose
private String cityName;

    public City_name(Integer id, String cityName) {
        this.id = id;
        this.cityName = cityName;
    }
    public City_name() {

    }



    public Integer getId() {
return id;
}

public void setId(Integer id) {
this.id = id;
}

public String getCityName() {
return cityName;
}

public void setCityName(String cityName) {
this.cityName = cityName;
}

}
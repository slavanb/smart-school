package smart_school.smartschool.Model.All_messages;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Pupil {

@SerializedName("name")
@Expose
private String name;
@SerializedName("human_id")
@Expose
private Integer humanId;

public String getName() {
return name;
}

public void setName(String name) {
this.name = name;
}

public Integer getHumanId() {
return humanId;
}

public void setHumanId(Integer humanId) {
this.humanId = humanId;
}

}
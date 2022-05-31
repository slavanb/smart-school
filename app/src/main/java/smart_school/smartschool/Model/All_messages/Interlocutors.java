package smart_school.smartschool.Model.All_messages;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Interlocutors {

@SerializedName("teachers")
@Expose
private List<Teacher> teachers = null;
@SerializedName("pupils")
@Expose
private List<Pupil> pupils = null;

public List<Teacher> getTeachers() {
return teachers;
}

public void setTeachers(List<Teacher> teachers) {
this.teachers = teachers;
}

public List<Pupil> getPupils() {
return pupils;
}

public void setPupils(List<Pupil> pupils) {
this.pupils = pupils;
}

}
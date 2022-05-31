package smart_school.smartschool.Model.Home_work_group;

import java.util.ArrayList;
import java.util.List;

import smart_school.smartschool.Model.Rozpisanie.New.LESSON;

public class Child_home_work {

    private String Name;
    private int vid;
    private List<LESSON> listLesson=new ArrayList<>();

    public List<LESSON> getListLesson() {
        return listLesson;
    }

    public void setListLesson(List<LESSON> listLesson) {
        this.listLesson = listLesson;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public int getVid() {
        return vid;
    }

    public void setVid(int vid) {
        this.vid = vid;
    }

}

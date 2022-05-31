package smart_school.smartschool.Model;

import java.util.ArrayList;
import java.util.List;

import smart_school.smartschool.Model.Rozpisanie.New.LESSON;
import smart_school.smartschool.Model.Rozpisanie.New.OCENKI;

public class Child {

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
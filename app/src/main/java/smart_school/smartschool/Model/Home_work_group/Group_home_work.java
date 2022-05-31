package smart_school.smartschool.Model.Home_work_group;

import java.util.ArrayList;

import smart_school.smartschool.Model.Child;

public class Group_home_work {

    private String Name;
    private String Date;
    private String countLesonGrades;
    private ArrayList<Child_home_work> Items;

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public String getCountLesonGrades() {
        return countLesonGrades;
    }

    public void setCountLesonGrades(String countLesonGrades) {
        this.countLesonGrades = countLesonGrades;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        this.Name = name;
    }

    public ArrayList<Child_home_work> getItems() {
        return Items;
    }

    public void setItems(ArrayList<Child_home_work> Items) {
        this.Items = Items;
    }
}

package smart_school.smartschool.Model;

import java.util.ArrayList;

public class Group_individual {
    private String Name;
    private String Date;
    private String countLesonGrades;
    private ArrayList<Child_individual> Items;

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

    public ArrayList<Child_individual> getItems() {
        return Items;
    }

    public void setItems(ArrayList<Child_individual> Items) {
        this.Items = Items;
    }

}
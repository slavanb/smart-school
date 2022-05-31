package smart_school.smartschool.Model;

/**
 * Created by Dell on 16.03.2018.
 */

public class Class_check_item {

    private int groupPos;
    private int childPos;
    private int status;

    public Class_check_item(int groupPos, int childPos, int status) {
        this.groupPos = groupPos;
        this.childPos = childPos;
        this.status = status;
    }

    public int getGroupPos() {
        return groupPos;
    }

    public void setGroupPos(int groupPos) {
        this.groupPos = groupPos;
    }

    public int getChildPos() {
        return childPos;
    }

    public void setChildPos(int childPos) {
        this.childPos = childPos;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}

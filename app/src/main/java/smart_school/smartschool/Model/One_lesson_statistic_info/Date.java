
package smart_school.smartschool.Model.One_lesson_statistic_info;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Date {

    @SerializedName("from")
    @Expose
    private String from;
    @SerializedName("to")
    @Expose
    private String to;
    @SerializedName("from_DMY")
    @Expose
    private String fromDMY;
    @SerializedName("to_DMY")
    @Expose
    private String toDMY;

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getFromDMY() {
        return fromDMY;
    }

    public void setFromDMY(String fromDMY) {
        this.fromDMY = fromDMY;
    }

    public String getToDMY() {
        return toDMY;
    }

    public void setToDMY(String toDMY) {
        this.toDMY = toDMY;
    }

}

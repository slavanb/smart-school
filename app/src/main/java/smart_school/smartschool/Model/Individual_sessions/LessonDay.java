
package smart_school.smartschool.Model.Individual_sessions;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LessonDay {

    @SerializedName("DAY_DATE")
    @Expose
    private String dAYDATE;
    @SerializedName("DAY_DATE_L")
    @Expose
    private String dAYDATEL;
    @SerializedName("DAY_DATE_N")
    @Expose
    private String dAYDATEN;
    @SerializedName("LESSONS")
    @Expose
    private List<LESSON> lESSONS = null;

    public String getDAYDATE() {
        return dAYDATE;
    }

    public void setDAYDATE(String dAYDATE) {
        this.dAYDATE = dAYDATE;
    }

    public String getDAYDATEL() {
        return dAYDATEL;
    }

    public void setDAYDATEL(String dAYDATEL) {
        this.dAYDATEL = dAYDATEL;
    }

    public String getDAYDATEN() {
        return dAYDATEN;
    }

    public void setDAYDATEN(String dAYDATEN) {
        this.dAYDATEN = dAYDATEN;
    }

    public List<LESSON> getLESSONS() {
        return lESSONS;
    }

    public void setLESSONS(List<LESSON> lESSONS) {
        this.lESSONS = lESSONS;
    }

}

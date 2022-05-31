package smart_school.smartschool.Adapters;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

import smart_school.smartschool.Model.Rozpisanie.New.LESSON;
import smart_school.smartschool.Model.Rozpisanie.New.OCENKI;

public class ContentItem extends ListItem {


    @SerializedName("LESSONS")
    @Expose
    private List<LESSON> lESSONS = null;

    public List<LESSON> getLESSONS() {
        return lESSONS;
    }

    public void setLESSONS(List<LESSON> lESSONS) {
        this.lESSONS = lESSONS;
    }


}
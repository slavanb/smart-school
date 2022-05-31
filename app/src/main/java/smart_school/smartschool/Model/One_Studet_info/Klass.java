
package smart_school.smartschool.Model.One_Studet_info;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class Klass extends RealmObject {

    @SerializedName("klass_id")
    @Expose
    @PrimaryKey
    private Integer klassId;
    @SerializedName("FullKlassName")
    @Expose
    private String fullKlassName;
    @SerializedName("YEAR_NUM")
    @Expose
    private Integer yEARNUM;
    @SerializedName("LETTER")
    @Expose
    private String lETTER;
    @SerializedName("YEAR_BEGIN")
    @Expose
    private Integer yEARBEGIN;
    @SerializedName("MAIN_TEACHER")
    @Expose
    private MAINTEACHER mAINTEACHER;

    public Integer getKlassId() {
        return klassId;
    }

    public void setKlassId(Integer klassId) {
        this.klassId = klassId;
    }

    public String getFullKlassName() {
        return fullKlassName;
    }

    public void setFullKlassName(String fullKlassName) {
        this.fullKlassName = fullKlassName;
    }

    public Integer getYEARNUM() {
        return yEARNUM;
    }

    public void setYEARNUM(Integer yEARNUM) {
        this.yEARNUM = yEARNUM;
    }

    public String getLETTER() {
        return lETTER;
    }

    public void setLETTER(String lETTER) {
        this.lETTER = lETTER;
    }

    public Integer getYEARBEGIN() {
        return yEARBEGIN;
    }

    public void setYEARBEGIN(Integer yEARBEGIN) {
        this.yEARBEGIN = yEARBEGIN;
    }

    public MAINTEACHER getMAINTEACHER() {
        return mAINTEACHER;
    }

    public void setMAINTEACHER(MAINTEACHER mAINTEACHER) {
        this.mAINTEACHER = mAINTEACHER;
    }

}


package smart_school.smartschool.Model.One_Studet_info;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class MAINTEACHER extends RealmObject{

    @SerializedName("MAIN_TEACHER_ID")
    @Expose
    @PrimaryKey
    private Integer mAINTEACHERID;
    @SerializedName("MAIN_TEACHER_HUMAN_ID")
    @Expose
    private Integer mAINTEACHERHUMANID;
    @SerializedName("MAIN_TEACHER_FAMILIA")
    @Expose
    private String mAINTEACHERFAMILIA;
    @SerializedName("MAIN_TEACHER_NAME")
    @Expose
    private String mAINTEACHERNAME;
    @SerializedName("MAIN_TEACHER_OTCHESTVO")
    @Expose
    private String mAINTEACHEROTCHESTVO;

    @SerializedName("MAIN_TEACHER_AVATAR")
    @Expose
    private String MAIN_TEACHER_AVATAR;

    public String getMAIN_TEACHER_AVATAR() {
        return MAIN_TEACHER_AVATAR;
    }

    public void setMAIN_TEACHER_AVATAR(String MAIN_TEACHER_AVATAR) {
        this.MAIN_TEACHER_AVATAR = MAIN_TEACHER_AVATAR;
    }

    public Integer getMAINTEACHERID() {
        return mAINTEACHERID;
    }

    public void setMAINTEACHERID(Integer mAINTEACHERID) {
        this.mAINTEACHERID = mAINTEACHERID;
    }

    public Integer getMAINTEACHERHUMANID() {
        return mAINTEACHERHUMANID;
    }

    public void setMAINTEACHERHUMANID(Integer mAINTEACHERHUMANID) {
        this.mAINTEACHERHUMANID = mAINTEACHERHUMANID;
    }

    public String getMAINTEACHERFAMILIA() {
        return mAINTEACHERFAMILIA;
    }

    public void setMAINTEACHERFAMILIA(String mAINTEACHERFAMILIA) {
        this.mAINTEACHERFAMILIA = mAINTEACHERFAMILIA;
    }

    public String getMAINTEACHERNAME() {
        return mAINTEACHERNAME;
    }

    public void setMAINTEACHERNAME(String mAINTEACHERNAME) {
        this.mAINTEACHERNAME = mAINTEACHERNAME;
    }

    public String getMAINTEACHEROTCHESTVO() {
        return mAINTEACHEROTCHESTVO;
    }

    public void setMAINTEACHEROTCHESTVO(String mAINTEACHEROTCHESTVO) {
        this.mAINTEACHEROTCHESTVO = mAINTEACHEROTCHESTVO;
    }

}

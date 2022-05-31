package smart_school.smartschool.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class List_news extends RealmObject{
    @SerializedName("ID")
    @Expose
    @PrimaryKey
    private Integer iD;
    @SerializedName("POST_DATE")
    @Expose
    private String pOSTDATE;
    @SerializedName("POST_TITLE")
    @Expose
    private String pOSTTITLE;
    @SerializedName("IMAGE")
    @Expose
    private String iMAGE;
    @SerializedName("POST_CONTENT")
    @Expose
    private String pOSTCONTENT;

    public Integer getID() {
        return iD;
    }

    public void setID(Integer iD) {
        this.iD = iD;
    }

    public String getPOSTDATE() {
        return pOSTDATE;
    }

    public void setPOSTDATE(String pOSTDATE) {
        this.pOSTDATE = pOSTDATE;
    }

    public String getPOSTTITLE() {
        return pOSTTITLE;
    }

    public void setPOSTTITLE(String pOSTTITLE) {
        this.pOSTTITLE = pOSTTITLE;
    }

    public String getIMAGE() {
        return iMAGE;
    }

    public void setIMAGE(String iMAGE) {
        this.iMAGE = iMAGE;
    }

    public String getPOSTCONTENT() {
        return pOSTCONTENT;
    }

    public void setPOSTCONTENT(String pOSTCONTENT) {
        this.pOSTCONTENT = pOSTCONTENT;
    }


}

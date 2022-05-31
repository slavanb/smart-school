
package smart_school.smartschool.Model.One_lesson_statistic_info;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class StatisticOneLessonHead {

    @SerializedName("auth")
    @Expose
    private String auth;
    @SerializedName("pupil")
    @Expose
    private String pupil;
    @SerializedName("date")
    @Expose
    private Date date;
    @SerializedName("PageNumber")
    @Expose
    private Integer pageNumber;
    @SerializedName("PageCountTotal")
    @Expose
    private Integer pageCountTotal;
    @SerializedName("PageSize")
    @Expose
    private Integer pageSize;
    @SerializedName("TotalCount")
    @Expose
    private Integer totalCount;
    @SerializedName("Ocenki")
    @Expose
    private List<Ocenki> ocenki = null;

    public String getAuth() {
        return auth;
    }

    public void setAuth(String auth) {
        this.auth = auth;
    }

    public String getPupil() {
        return pupil;
    }

    public void setPupil(String pupil) {
        this.pupil = pupil;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Integer getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(Integer pageNumber) {
        this.pageNumber = pageNumber;
    }

    public Integer getPageCountTotal() {
        return pageCountTotal;
    }

    public void setPageCountTotal(Integer pageCountTotal) {
        this.pageCountTotal = pageCountTotal;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Integer totalCount) {
        this.totalCount = totalCount;
    }

    public List<Ocenki> getOcenki() {
        return ocenki;
    }

    public void setOcenki(List<Ocenki> ocenki) {
        this.ocenki = ocenki;
    }

}

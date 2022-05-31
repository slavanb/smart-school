package smart_school.smartschool.Model.Rozpisanie.New;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Rozpisanie_model_new {
    @SerializedName("auth")
        @Expose
        private String auth;
        @SerializedName("pupil")
        @Expose
        private String pupil;
        @SerializedName("Klass")
        @Expose
        private String klass;
        @SerializedName("date")
        @Expose
        private Date date;
        @SerializedName("lesson_days")
        @Expose
        private List<LessonDay> lessonDays = null;

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

        public String getKlass() {
            return klass;
        }

        public void setKlass(String klass) {
            this.klass = klass;
        }

        public Date getDate() {
            return date;
        }

        public void setDate(Date date) {
            this.date = date;
        }

        public List<LessonDay> getLessonDays() {
            return lessonDays;
        }

        public void setLessonDays(List<LessonDay> lessonDays) {
            this.lessonDays = lessonDays;
        }

    }






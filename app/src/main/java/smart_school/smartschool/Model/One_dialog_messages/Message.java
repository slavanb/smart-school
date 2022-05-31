package smart_school.smartschool.Model.One_dialog_messages;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Message {

    @SerializedName("date_time")
    @Expose
    private String dateTime;
    @SerializedName("text")
    @Expose
    private String text;
    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("message_id")
    @Expose
    private Integer messageId;
    @SerializedName("author_id")
    @Expose
    private Integer authorId;
    @SerializedName("is_my_message")
    @Expose
    private String is_my_message;






    public String getIs_my_message() {
        return is_my_message;
    }

    public void setIs_my_message(String is_my_message) {
        this.is_my_message = is_my_message;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getMessageId() {
        return messageId;
    }

    public void setMessageId(Integer messageId) {
        this.messageId = messageId;
    }

    public Integer getAuthorId() {
        return authorId;
    }

    public void setAuthorId(Integer authorId) {
        this.authorId = authorId;
    }

}
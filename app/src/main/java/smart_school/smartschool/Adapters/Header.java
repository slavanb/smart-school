package smart_school.smartschool.Adapters;

public class Header extends ListItem {
private String header;
String fio;
public Header(){}

    public String getFio() {
        return fio;
    }

    public void setFio(String fio) {
        this.fio = fio;
    }

    public String getHeader() {
    return header;
}
public void setHeader(String header) {
    this.header = header;
}
}
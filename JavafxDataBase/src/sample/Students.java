package sample;

public class Students {
    private String no;
    private String nameSurname;
    private String department;
    private String year;
    private String mail;


    public Students(String no, String nameSurname, String department, String year, String mail) {
        this.no = no;
        this.nameSurname = nameSurname;
        this.department = department;
        this.year = year;
        this.mail = mail;
    }

    public Students(){

    }




    public String getNo() {
        return no;
    }

    public String getNameSurname() {
        return nameSurname;
    }

    public String getDepartment() {
        return department;
    }

    public void setNo(String no) {
        this.no = no;
    }

    public void setNameSurname(String nameSurname) {
        this.nameSurname = nameSurname;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }
}

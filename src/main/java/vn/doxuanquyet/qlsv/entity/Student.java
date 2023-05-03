package vn.doxuanquyet.qlsv.entity;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "student")
@XmlAccessorType(XmlAccessType.FIELD)
public class Student implements Serializable {
    private static final long serialVersionUID = 1L;
    private int id;
    private String name, grade, address, reward, punishment, activity;
    private String dateJoinDoan, dateJoinDang, placeJoinDoan, placeJoinDang;
    private boolean isDoanPhi, isDangPhi;
    private byte age;
    /* điểm trung bình của sinh viên */
    private float gpa; 

    public Student() {
    }

    public Student(int id, String name, byte age, String address, float gpa,
            String grade, String dateJoinDoan, String dateJoinDang, String placeJoinDoan, String placeJoinDang,
            String activity,String reward, String punishment, boolean isDoanPhi, boolean isDangPhi) {
        super();
        this.id = id;
        this.name = name;
        this.age = age;
        this.address = address;
        this.gpa = gpa;
        this.grade = grade;
        this.dateJoinDoan = dateJoinDoan;
        this.dateJoinDang = dateJoinDang;
        this.placeJoinDoan = placeJoinDoan;
        this.placeJoinDang = placeJoinDang;
        this.activity = activity;
        this.reward = reward;
        this.punishment = punishment;
        this.isDoanPhi = isDoanPhi;
        this.isDangPhi = isDangPhi;
    }

    public int getId() {
        return id;
    }
    
    public String getName() {
        return name;
    }
    
    public byte getAge() {
        return age;
    }

    public String getAddress() {
        return address;
    }
    
    public float getGpa() {
        return gpa;
    }

    public String getGrade() {
        return grade;
    }

    public String getDateJoinDang() {
        return dateJoinDang;
    }

    public String getDateJoinDoan() {
        return dateJoinDoan;
    }

    public String getPlaceJoinDang() {
        return placeJoinDang;
    }

    public String getPlaceJoinDoan() {
        return placeJoinDoan;
    }

    public String getActivity() {
        return activity;
    }

    public String getPunishment() {
        return punishment;
    }

    public String getReward() {
        return reward;
    }

    public boolean isDangPhi() {
        return isDangPhi;
    }

    public boolean isDoanPhi() {
        return isDoanPhi;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(byte age) {
        this.age = age;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setGpa(float gpa) {
        this.gpa = gpa;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public void setDateJoinDang(String dateJoinDang) {
        this.dateJoinDang = dateJoinDang;
    }

    public void setDateJoinDoan(String dateJoinDoan) {
        this.dateJoinDoan = dateJoinDoan;
    }

    public void setPlaceJoinDoan(String placeJoinDoan) {
        this.placeJoinDoan = placeJoinDoan;
    }

    public void setPlaceJoinDang(String placeJoinDang) {
        this.placeJoinDang = placeJoinDang;
    }

    public void setIsDangPhi(boolean isDangPhi) {
        this.isDangPhi = isDangPhi;
    }

    public void setIsDoanPhi(boolean isDoanPhi) {
        this.isDoanPhi = isDoanPhi;
    }

    public void setActivity(String activity) {
        this.activity = activity;
    }

    public void setPunishment(String punishment) {
        this.punishment = punishment;
    }

    public void setReward(String reward) {
        this.reward = reward;
    }
    
    public boolean isJoinedDang(){
        if (isDangPhi == true) return true;
        else if (dateJoinDang == null || dateJoinDang.equals("") || dateJoinDang.equals("Chưa vào Đảng") ||
                placeJoinDang == null || placeJoinDang.equals("") || placeJoinDang.equals("Chưa vào Đảng")) {
            return false;
        } else return true;
    }
}

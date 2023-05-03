package vn.doxuanquyet.qlsv.dao;
import vn.doxuanquyet.qlsv.entity.Student;
import vn.doxuanquyet.qlsv.entity.StudentXML;
import vn.doxuanquyet.qlsv.utils.FileUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;


/**
 * StudentDao class
 * 
 * @author viettuts.vn
 */
public class StudentDao {
    private static final String STUDENT_FILE_NAME = "student.xml";
    private List<Student> listStudents;

    public StudentDao() {
        this.listStudents = readListStudents();
        if (listStudents == null) {
            listStudents = new ArrayList<Student>();
        }
    }

    /**
     * Lưu các đối tượng student vào file student.xml
     * 
     * @param students
     */
    public void writeListStudents(List<Student> students) {
        StudentXML studentXML = new StudentXML();
        studentXML.setStudent(students);
        FileUtils.writeXMLtoFile(STUDENT_FILE_NAME, studentXML);
    }

    /**
     * Đọc các đối tượng student từ file student.xml
     * 
     * @return list student
     */
    public List<Student> readListStudents() {
        List<Student> list = new ArrayList<Student>();
        StudentXML studentXML = (StudentXML) FileUtils.readXMLFile(
                STUDENT_FILE_NAME, StudentXML.class);
        if (studentXML != null) {
            list = studentXML.getStudent();
        }
        return list;
    }  

    /**
     * thêm student vào listStudents và lưu listStudents vào file
     * 
     * @param student
     */
    public void add(Student student) {
        int id = 1;
        if (listStudents != null && listStudents.size() > 0) {
            id = listStudents.size() + 1;
        }
        student.setId(id);
        listStudents.add(student);
        writeListStudents(listStudents);
    }

    /**
     * cập nhật student vào listStudents và lưu listStudents vào file
     * 
     * @param student
     */
    public void edit(Student student) {
        int size = listStudents.size();
        for (int i = 0; i < size; i++) {
            if (listStudents.get(i).getId() == student.getId()) {                
                listStudents.get(i).setName(student.getName());
                listStudents.get(i).setAge(student.getAge());
                listStudents.get(i).setAddress(student.getAddress());
                listStudents.get(i).setGpa(student.getGpa());
                listStudents.get(i).setGrade(student.getGrade());
                listStudents.get(i).setDateJoinDoan(student.getDateJoinDoan());
                listStudents.get(i).setPlaceJoinDoan(student.getPlaceJoinDoan());
                listStudents.get(i).setDateJoinDang(student.getDateJoinDang());
                listStudents.get(i).setPlaceJoinDang(student.getPlaceJoinDang());
                listStudents.get(i).setIsDangPhi(student.isDangPhi());
                listStudents.get(i).setIsDoanPhi(student.isDoanPhi());
                listStudents.get(i).setActivity(student.getActivity());
                listStudents.get(i).setReward(student.getReward());
                listStudents.get(i).setPunishment(student.getPunishment());               
                writeListStudents(listStudents);
                break;
            }
        }
    }

    /**
     * xóa student từ listStudents và lưu listStudents vào file
     * 
     * @param student
     */
    public void delete(Student student) {
        boolean isFound = false;
        int size = listStudents.size();
        for (int i = 0; i < size; i++) {
            if (listStudents.get(i).getId() == student.getId()) {
                student = listStudents.get(i);
                isFound = true;
                break;
            }
        }
        if (isFound) {      
            for (int k=student.getId(); k<size; k++){
                listStudents.get(k).setId(k);
            }
            listStudents.remove(student);
            writeListStudents(listStudents);
        }
    }
    
    /**
     * sắp xếp danh sách student theo ID theo tứ tự tăng dần
     */
    public void sortStudentByID() {
        Collections.sort(listStudents, new Comparator<Student>() {
            @Override
            public int compare(Student student1, Student student2) {
                if (student1.getId() > student2.getId()) {
                    return 1;
                }
                return -1;
            }
        });
    }

    public List<Student> getListStudents() {
        return listStudents;
    }

    public void setListStudents(List<Student> listStudents) {
        this.listStudents = listStudents;
    }
}
package vn.doxuanquyet.qlsv.controller;
import vn.doxuanquyet.qlsv.dao.StudentDao;
import vn.doxuanquyet.qlsv.entity.Student;
import vn.doxuanquyet.qlsv.view.StudentView;
import vn.doxuanquyet.qlsv.view.StudentInforView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.JTable;
import javax.swing.JOptionPane;
import javax.swing.WindowConstants;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class StudentController {
    private StudentDao studentDao;
    private StudentView studentView;
    private StudentInforView stuInfoView;

    public StudentController(StudentView view) {
        this.studentView = view;
        studentDao = new StudentDao();

        view.addAddStudentListener(new AddStudentListener());
        view.addDeleteStudentListener(new DeleteStudentListener());
        view.addStudentInformationListener(new StudentInformationListener());
        view.addSortStudentIDListener(new SortStudentIDListener());
        view.addSortStudentGPAListener(new SortStudentGPAListener());
        view.addSortStudentNameListener(new SortStudentNameListener());
        view.addShowAllStudentListener(new ShowAllStudentsListener());
        view.addSearchStudentIDListener(new SearchStudentIDListener());
        view.addSearchStudentNameListener(new SearchStudentNameListener());
        view.addSearchStudentGradeListener(new SearchStudentGradeListener());
        view.addSearchStudentJoinedDang(new SearchStudentJoinedDangListener());
        view.addSearchStudentPaidDoanPhi(new SearchStudentPaidDoanPhiListener());
        view.addSearchStudentPaidDangPhi(new SearchStudentPaidDangPhiListener());
        view.addListStudentSelectionListener(new ListStudentSelectionListener());
    }

    public void showStudentView() {
        studentDao.sortStudentByID();
        List<Student> studentList = studentDao.getListStudents();
        studentView.setVisible(true);
        studentView.showListStudents(studentList);
    }
    
    public List<Student> getStudentListSort(){
        List<Student> studentList = new ArrayList<>();
        List<Integer> listStudentID = studentView.getListStudentIDTable();
        for (int id : listStudentID){
            studentList.add(searchStudentByID(id));
        }
        return studentList;
    }
    
    public Student searchStudentByID(int id){
        List<Student> studentList = studentDao.getListStudents();
        try {
            for (Student student : studentList){
                if (id == student.getId()){
                    return student;
                }
            }
            throw new IllegalArgumentException("Khong tim thay id");
        } catch (IllegalArgumentException e){
            studentView.showMessage("Không tìm thấy học sinh có ID: " + id);
        }
        return null;
    }  
    
    public List<Student> searchStudentByName(String name){
        List<Student> studentList = studentDao.getListStudents();
        List<Student> studentNameList = new ArrayList<>();
        try {
            int count = 0;
            for (Student student : studentList){
                if (name.equals(student.getName())){
                    studentNameList.add(student);
                    count++;
                }
            }
            if (count > 0) return studentNameList;
            else throw new IllegalArgumentException("Khong tim thay student co ten la" + name);
        } catch (IllegalArgumentException e){
            studentView.showMessage("Không tìm thấy học sinh có tên: " + name);
        }
        return null;
    }
    
    public List<Student> searchStudentByGrade(String grade){
        List<Student> studentList = studentDao.getListStudents();
        List<Student> studentGradeList = new ArrayList<>();
        try {
            int count = 0;
            for (Student student : studentList){
                if (grade.equals(student.getGrade())){
                    studentGradeList.add(student);
                    count++;
                }
            }
            if (count > 0) return studentGradeList;
            else throw new IllegalArgumentException("Khong tim thay student co lop la" + grade);
        } catch (IllegalArgumentException e){
            studentView.showMessage("Không tìm thấy học sinh học lớp: " + grade);
        }
        return null;
    }

    public List<Student> searchStudentJoinedDang(){
        List<Student> studentList = studentDao.getListStudents();
        List<Student> joinedDangList = new ArrayList<>();
        try {
            int count = 0;
            for (Student student : studentList){
                if (student.isJoinedDang()){
                    joinedDangList.add(student);
                    count++;
                }
            }
            if (count > 0) return joinedDangList;
            else throw new IllegalArgumentException("Khong tim thay student da vao Dang.");
        } catch (IllegalArgumentException e){
            studentView.showMessage("Không tìm thấy học sinh nào đã vào Đảng");
        }
        return null;       
    }
    
    public List<Student> searchStudentPaidDoanPhi(){
        List<Student> studentList = studentDao.getListStudents();
        List<Student> paidDoanPhiList = new ArrayList<>();
        try {
            int count = 0;
            for (Student student : studentList){
                if (student.isDoanPhi()){
                    paidDoanPhiList.add(student);
                    count++;
                }
            }
            if (count > 0) return paidDoanPhiList;
            else throw new IllegalArgumentException("Khong tim thay student da dong Doan phi.");
        } catch (IllegalArgumentException e){
            studentView.showMessage("Chưa có học sinh nào đã đóng Đoàn phí.");
        }
        return null;         
    }
    
    public List<Student> searchStudentPaidDangPhi(){
        List<Student> studentList = studentDao.getListStudents();
        List<Student> paidDangPhiList = new ArrayList<>();
        try {
            int count = 0;
            for (Student student : studentList){
                if (student.isDangPhi()){
                    paidDangPhiList.add(student);
                    count++;
                }
            }
            if (count > 0) return paidDangPhiList;
            else throw new IllegalArgumentException("Khong tim thay student da dong Dang phi.");
        } catch (IllegalArgumentException e){
            studentView.showMessage("Chưa có học sinh nào đã đóng Đảng phí.");
        }
        return null;         
    }

    /**
     * Lớp AddStudentListener 
     * chứa cài đặt cho sự kiện click button "Add"
     */
    class AddStudentListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            stuInfoView = new StudentInforView(studentView, true);
            stuInfoView.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
            stuInfoView.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosing(WindowEvent e) {
                    stuInfoView.setSaveBtnAction(false);
                }
            });
            
            stuInfoView.enableSaveBtn(true);
            stuInfoView.enableDeleteBtn(false);
            stuInfoView.enableEditBtn(false);

            stuInfoView.setVisible(true);
            
            if (stuInfoView.getSaveBtnAction()){
                Student student = stuInfoView.getStudentInfor();
                if (student != null) {
                    studentDao.add(student);
                    stuInfoView.setSaveBtnAction(false);           
                    studentView.showListStudents(studentDao.getListStudents());
                    studentView.showMessage("Thêm thành công!");                  
                }
            }
        }
    }

    /**
     * Lớp DeleteStudentListener 
     * chứa cài đặt cho sự kiện click button "Delete" 
     */
    class DeleteStudentListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            JTable studentTable = studentView.getStudentTable();
            int row = studentView.getStuTabSelectedRow();
            Student student = searchStudentByID(Integer.parseInt(studentTable.getModel().getValueAt(row, 0).toString()));
            if (student != null) {
                studentDao.delete(student);               
                studentView.showListStudents(studentDao.getListStudents());
                studentView.showMessage("Xóa thành công!");
            }
            studentView.setEnableDeleteBtn(false);
            studentView.enableStuInforBtn(false);
        }
    }
        
    /**
     * Lớp SortStudentIDListener 
     * chứa cài đặt cho sự kiện click button "Sort By ID"
     */
    class SortStudentIDListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            List<Student> studentList = getStudentListSort(); 
            Collections.sort(studentList, new Comparator<Student>() {
                @Override
                public int compare(Student student1, Student student2) {
                    if (student1.getId() > student2.getId()) {
                        return 1;
                    }
                    return -1;
                }
            });
            studentView.showListStudents(studentList);
            studentView.enableStuInforBtn(false);
            studentView.setEnableDeleteBtn(false);
        }
    }

    /**
     * Lớp SortStudentGPAListener 
     * chứa cài đặt cho sự kiện click button "Sort By GPA"
     */
    class SortStudentGPAListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            List<Student> studentList = getStudentListSort(); 
            Collections.sort(studentList, new Comparator<Student>() {
                @Override
                public int compare(Student student1, Student student2) {
                    if (student1.getGpa()> student2.getGpa()) {
                        return 1;
                    }
                    return -1;
                }
            });
            studentView.showListStudents(studentList);
            studentView.enableStuInforBtn(false);
            studentView.setEnableDeleteBtn(false);
        }
    }

    /**
     * Lớp SortStudentGPAListener 
     * chứa cài đặt cho sự kiện click button "Sort By Name"
     */
    class SortStudentNameListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            List<Student> studentList = getStudentListSort(); 
            Collections.sort(studentList, new Comparator<Student>() {
                @Override
                public int compare(Student student1, Student student2) {
                    String[] nameStudent1 = student1.getName().split(" ");
                    String firstnameStu1 = nameStudent1[nameStudent1.length - 1];
                    String[] nameStudent2 = student2.getName().split(" ");
                    String firstnameStu2 = nameStudent2[nameStudent2.length - 1];
                    return firstnameStu1.compareTo(firstnameStu2);                
                }
            });
            studentView.showListStudents(studentList);
            studentView.enableStuInforBtn(false);
            studentView.setEnableDeleteBtn(false);
        }
    }
    
    /**
     * Lớp ShowAllStudentsListener
     * chứa cài đặt cho sự kiện click button "Show All Students"
     */
    class ShowAllStudentsListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            studentDao.sortStudentByID();
            studentView.showListStudents(studentDao.getListStudents());
            studentView.enableStuInforBtn(false);
            studentView.setEnableDeleteBtn(false);
        }
    }   
    
    /**
     * Lớp SearchStudentIDListener
     * chứa cài đặt cho sự kiện click button "Search By ID"
     */
    class SearchStudentIDListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String input = JOptionPane.showInputDialog(null, "Nhập vào ID học sinh", "ID Input", 1);
            if (input == null || input.equals("")){
                // code nhu nay de khong bi bao loi input = null
            }else {
                int id = Integer.parseInt(input);
                List<Student> listIDStudent = new ArrayList<>();
                if (searchStudentByID(id) != null) {
                    listIDStudent.add(searchStudentByID(id));
                    studentView.showListStudents(listIDStudent);
                    studentView.enableStuInforBtn(false);
                    studentView.setEnableDeleteBtn(false);
                }
            }          
        }
    }
    
    /**
     * Lớp SearchStudentNameListener
     * chứa cài đặt cho sự kiện click button "Search By Name"
     */
    class SearchStudentNameListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String input = JOptionPane.showInputDialog(null, "Nhập vào Họ tên học sinh", "Name Input", 1);
            if (input == null || input.equals("")){
                // code nhu nay de khong bi bao loi input = null
            }else {
                if (searchStudentByName(input) != null) {
                    studentView.showListStudents(searchStudentByName(input));
                    studentView.enableStuInforBtn(false);
                    studentView.setEnableDeleteBtn(false);
                }
            }
        }
    }
    
    /**
     * Lớp SearchStudentGradeListener
     * chứa cài đặt cho sự kiện click button "Search By Grade"
     */
    class SearchStudentGradeListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String input = JOptionPane.showInputDialog(null, "Nhập vào Lớp học của học sinh", "Grade Input", 1);
            if (input == null || input.equals("")){
                // code nhu nay de khong bi bao loi input = null
            }else {
                if (searchStudentByGrade(input) != null) {
                    studentView.showListStudents(searchStudentByGrade(input));
                    studentView.enableStuInforBtn(false);
                    studentView.setEnableDeleteBtn(false);
                }
            }
        }
    }
    
    /**
     * Lớp SearchStudentJoinedDangListener
     * chứa cài đặt cho sự kiện click button "Đã vào Đảng"
     */
    class SearchStudentJoinedDangListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (searchStudentJoinedDang() != null) {
                studentView.showListStudents(searchStudentJoinedDang());   
                studentView.enableStuInforBtn(false);
                studentView.setEnableDeleteBtn(false);
            }
        }
    }
    
    /*
     * Lớp SearchStudentPaidDoanPhiListener
     * chứa cài đặt cho sự kiện click button "Đã đóng Đoàn phí"
     */
    class SearchStudentPaidDoanPhiListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (searchStudentPaidDoanPhi()!= null) {
                studentView.showListStudents(searchStudentPaidDoanPhi());
                studentView.enableStuInforBtn(false);
                studentView.setEnableDeleteBtn(false);
            }          
        }
    }
    
    /**
     * Lớp SearchStudentPaidDangPhiListener
     * chứa cài đặt cho sự kiện click button "Đã đóng Đảng phí"
     */
    class SearchStudentPaidDangPhiListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (searchStudentPaidDangPhi()!= null) {
                studentView.showListStudents(searchStudentPaidDangPhi());
                studentView.enableStuInforBtn(false);
                studentView.setEnableDeleteBtn(false);
            }          
        }
    }

    /**
     * Lớp ListStudentSelectionListener 
     * chứa cài đặt cho sự kiện chọn student trong bảng student
     */
    class ListStudentSelectionListener implements ListSelectionListener {
        @Override
        public void valueChanged(ListSelectionEvent e) {
            studentView.setEnableDeleteBtn(true);
            studentView.enableStuInforBtn(true);
        }
    }

    /**
     * Lớp StudentInformation
     * chứa cài đặt cho sự kiện mở dialog thông tin sinh viên
     */
    class StudentInformationListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            stuInfoView = new StudentInforView(studentView, true);
            stuInfoView.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
            stuInfoView.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosing(WindowEvent e) {
                    stuInfoView.setEditBtnAction(false);
                }
            });

            stuInfoView.enableSaveBtn(false);
            
            JTable studentTable = studentView.getStudentTable();
            int row = studentView.getStuTabSelectedRow();
            if (row >= 0) {
                stuInfoView.setIDField(studentTable.getModel().getValueAt(row, 0).toString());
                stuInfoView.setNameField(studentTable.getModel().getValueAt(row, 1).toString());
                stuInfoView.setAgeField(studentTable.getModel().getValueAt(row, 2).toString());
                stuInfoView.setGradeField(studentTable.getModel().getValueAt(row, 3).toString());
                stuInfoView.setAddressArea(studentTable.getModel().getValueAt(row, 4).toString());
                stuInfoView.setGPAField(studentTable.getModel().getValueAt(row, 5).toString());
                
                if (studentTable.getModel().getValueAt(row, 6) != null)
                    stuInfoView.setDateDoanField(studentTable.getModel().getValueAt(row, 6).toString());
                if (studentTable.getModel().getValueAt(row, 7) != null)
                    stuInfoView.setPlaceDoanField(studentTable.getModel().getValueAt(row, 7).toString());
                if (studentTable.getModel().getValueAt(row, 8) != null)
                    stuInfoView.setDateDangField(studentTable.getModel().getValueAt(row, 8).toString());
                if (studentTable.getModel().getValueAt(row, 9) != null)
                    stuInfoView.setPlaceDangField(studentTable.getModel().getValueAt(row, 9).toString());
                if (studentTable.getModel().getValueAt(row, 12) != null)
                    stuInfoView.setActivityArea(studentTable.getModel().getValueAt(row, 12).toString());
                if (studentTable.getModel().getValueAt(row, 13) != null)
                    stuInfoView.setRewardArea(studentTable.getModel().getValueAt(row, 13).toString());               
                if (studentTable.getModel().getValueAt(row, 14) != null)
                    stuInfoView.setPunishmentArea(studentTable.getModel().getValueAt(row, 14).toString());               

                stuInfoView.setDoanPhiCheck(studentDao.getListStudents().get(row).isDoanPhi());
                stuInfoView.setDangPhiCheck(studentDao.getListStudents().get(row).isDangPhi());

            }
            
            stuInfoView.setVisible(true);
            
            if (stuInfoView.getDeleteBtnAction()){
                Student student = searchStudentByID(Integer.parseInt(stuInfoView.getIDField()));
                if (student != null) {
                    studentDao.delete(student);                   
                    studentView.showListStudents(studentDao.getListStudents());
                    studentView.showMessage("Xóa thành công!");
                }
                stuInfoView.setDeleteBtnAction(false);
                stuInfoView.setEditBtnAction(false);
                studentView.setEnableDeleteBtn(false);
                studentView.enableStuInforBtn(false);
            }
            
            if (stuInfoView.getEditBtnAction()){
                Student student = stuInfoView.getStudentInfor();
                if (student != null) {
                    studentDao.edit(student);
                    studentView.showListStudents(studentDao.getListStudents());
                    studentView.showMessage("Update thông tin thành công!");
                }
                stuInfoView.setEditBtnAction(false);
                studentView.enableStuInforBtn(false);
                studentView.setEnableDeleteBtn(false);
            }
        }
    }
}

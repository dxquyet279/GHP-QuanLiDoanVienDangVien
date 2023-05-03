package vn.doxuanquyet.qlsv.view;
import vn.doxuanquyet.qlsv.view.edittable.TableCustom;
import vn.doxuanquyet.qlsv.entity.Student;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SpringLayout;
import javax.swing.WindowConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;
import java.util.List;

public class StudentView extends JFrame implements ActionListener, ListSelectionListener {
    private static final long serialVersionUID = 1L;  
    private final int margin = 15;
    private JLabel mainTitle;
    private JButton addStudentBtn;
    private JButton deleteStudentBtn;
    private JButton studentInforBtn;
    private JButton sortStudentIDBtn;
    private JButton sortStudentNameBtn;
    private JButton sortStudentGPABtn;
    private JButton showAllStudentBtn;
    private JButton searchStudentIDBtn;
    private JButton searchStudentNameBtn;
    private JButton searchStudentGradeBtn;
    private JButton isJoinedDangBtn;
    private JButton isPaidDoanPhiBtn;
    private JButton isPaidDangPhiBtn;
    private JScrollPane jScrollPaneStudentTable;
    private JTable studentTable;
    
       
    // định nghĩa các cột của bảng student
    private String [] columnNames = new String [] {
            "ID", "Họ và tên", "Tuổi", "Lớp", "Địa chỉ", "GPA", "Ngày vào Đoàn", "Nơi vào Đoàn", "Ngày vào Đảng", "Nơi vào Đảng", "Đoàn phí", "Đảng phí", "Phong trào", "Giải thưởng", "Kỷ luật"};
    // định nghĩa dữ liệu mặc định của bẳng student là rỗng
    private Object data = new Object [][] {};
    
    public StudentView() {
        initComponents();
    }

    private void initComponents() {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        // khởi tạo các phím chức năng
        addStudentBtn = new JButton("Add");
        deleteStudentBtn = new JButton("Delete");
        studentInforBtn = new JButton("Student Information");
        sortStudentIDBtn = new JButton("Sort By ID");
        sortStudentNameBtn = new JButton("Sort By Name");
        sortStudentGPABtn = new JButton("Sort By GPA");
        showAllStudentBtn = new JButton("Show All Students");
        searchStudentIDBtn = new JButton("Search By ID");
        searchStudentNameBtn = new JButton("Search By Name");
        searchStudentGradeBtn = new JButton("Search By Grade");
        isJoinedDangBtn = new JButton("Đã vào Đảng");
        isPaidDoanPhiBtn = new JButton("Đã đóng Đoàn phí");
        isPaidDangPhiBtn = new JButton("Đã đóng Đảng phí");
        
        Cursor handCursor = new Cursor(Cursor.HAND_CURSOR); 
        addStudentBtn.setCursor(handCursor);
        deleteStudentBtn.setCursor(handCursor);
        studentInforBtn.setCursor(handCursor);
        sortStudentIDBtn.setCursor(handCursor);
        sortStudentNameBtn.setCursor(handCursor);
        sortStudentGPABtn.setCursor(handCursor);
        showAllStudentBtn.setCursor(handCursor);
        searchStudentIDBtn.setCursor(handCursor);
        searchStudentNameBtn.setCursor(handCursor);
        searchStudentGradeBtn.setCursor(handCursor);
        isJoinedDangBtn.setCursor(handCursor);
        isPaidDoanPhiBtn.setCursor(handCursor);
        isPaidDangPhiBtn.setCursor(handCursor);

        // khởi tạo các label
        mainTitle = new JLabel("QUẢN LÍ THÔNG TIN ĐOÀN VIÊN, ĐẢNG VIÊN");
        Font font = mainTitle.getFont();
        mainTitle.setFont(new Font(font.getName(), Font.BOLD, 18));

        // khởi tạo bảng student
        jScrollPaneStudentTable = new JScrollPane();
        studentTable = new JTable();
        
        // cài đặt các cột và data cho bảng student
        studentTable.setModel(new DefaultTableModel((Object[][]) data, columnNames));
        jScrollPaneStudentTable.setViewportView(studentTable);
        jScrollPaneStudentTable.setPreferredSize(new Dimension (1210, 460));
        
         // tạo spring layout
        SpringLayout layout = new SpringLayout();
        // tạo đối tượng panel để chứa các thành phần của màn hình quản lý Student
        JPanel panel = new JPanel();
        panel.setSize(1250, 650);
        panel.setLayout(layout);
        panel.add(jScrollPaneStudentTable);
        panel.add(mainTitle);
        panel.add(addStudentBtn);
        panel.add(deleteStudentBtn);
        panel.add(studentInforBtn);
        panel.add(sortStudentIDBtn);
        panel.add(sortStudentGPABtn);
        panel.add(sortStudentNameBtn);
        panel.add(showAllStudentBtn);
        panel.add(searchStudentIDBtn);
        panel.add(searchStudentNameBtn);
        panel.add(searchStudentGradeBtn);
        panel.add(isJoinedDangBtn);
        panel.add(isPaidDoanPhiBtn);
        panel.add(isPaidDangPhiBtn);
        
        // cài đặt vị trí các thành phần trên màn hình login
        int spaceBtnRow = (int)(panel.getSize().getWidth() - addStudentBtn.getPreferredSize().getWidth()- deleteStudentBtn.getPreferredSize().getWidth() - studentInforBtn.getPreferredSize().getWidth() - sortStudentIDBtn.getPreferredSize().getWidth() - sortStudentNameBtn.getPreferredSize().getWidth() - sortStudentGPABtn.getPreferredSize().getWidth() - showAllStudentBtn.getPreferredSize().getWidth() - margin * 3) / 6;
        int marginBtnRow2 = (int)(panel.getSize().getWidth() - searchStudentIDBtn.getPreferredSize().getWidth() - searchStudentNameBtn.getPreferredSize().getWidth() - searchStudentGradeBtn.getPreferredSize().getWidth() - isJoinedDangBtn.getPreferredSize().getWidth() - isPaidDoanPhiBtn.getPreferredSize().getWidth() - isPaidDangPhiBtn.getPreferredSize().getWidth() - spaceBtnRow * 5) / 2;
        
        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, mainTitle, 0, SpringLayout.HORIZONTAL_CENTER, panel);        
        layout.putConstraint(SpringLayout.NORTH, mainTitle, 15, SpringLayout.NORTH, panel);
        
        layout.putConstraint(SpringLayout.WEST, jScrollPaneStudentTable, margin, SpringLayout.WEST, panel);       
        layout.putConstraint(SpringLayout.NORTH, jScrollPaneStudentTable, 50, SpringLayout.NORTH, panel);
        
        layout.putConstraint(SpringLayout.WEST, addStudentBtn, margin, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, addStudentBtn, 530, SpringLayout.NORTH, panel);
        
        layout.putConstraint(SpringLayout.WEST, deleteStudentBtn, (int)addStudentBtn.getPreferredSize().getWidth() + spaceBtnRow, SpringLayout.WEST, addStudentBtn);
        layout.putConstraint(SpringLayout.NORTH, deleteStudentBtn, 530, SpringLayout.NORTH, panel);
        
        layout.putConstraint(SpringLayout.WEST, studentInforBtn, (int)deleteStudentBtn.getPreferredSize().getWidth() + spaceBtnRow, SpringLayout.WEST, deleteStudentBtn); 
        layout.putConstraint(SpringLayout.NORTH, studentInforBtn, 530, SpringLayout.NORTH, panel);
        
        layout.putConstraint(SpringLayout.WEST, sortStudentIDBtn, (int)studentInforBtn.getPreferredSize().getWidth() + spaceBtnRow, SpringLayout.WEST, studentInforBtn);
        layout.putConstraint(SpringLayout.NORTH, sortStudentIDBtn, 530, SpringLayout.NORTH, panel);
   
        layout.putConstraint(SpringLayout.WEST, sortStudentNameBtn, (int)sortStudentIDBtn.getPreferredSize().getWidth() + spaceBtnRow, SpringLayout.WEST, sortStudentIDBtn);
        layout.putConstraint(SpringLayout.NORTH, sortStudentNameBtn, 530, SpringLayout.NORTH, panel);

        layout.putConstraint(SpringLayout.WEST, sortStudentGPABtn, (int)sortStudentNameBtn.getPreferredSize().getWidth() + spaceBtnRow, SpringLayout.WEST, sortStudentNameBtn);
        layout.putConstraint(SpringLayout.NORTH, sortStudentGPABtn, 530, SpringLayout.NORTH, panel);
        
        layout.putConstraint(SpringLayout.WEST, showAllStudentBtn, (int)sortStudentGPABtn.getPreferredSize().getWidth() + spaceBtnRow, SpringLayout.WEST, sortStudentGPABtn);
        layout.putConstraint(SpringLayout.NORTH, showAllStudentBtn, 530, SpringLayout.NORTH, panel);
        
        layout.putConstraint(SpringLayout.WEST, searchStudentIDBtn, marginBtnRow2, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, searchStudentIDBtn, 570, SpringLayout.NORTH, panel);
 
        layout.putConstraint(SpringLayout.WEST, searchStudentNameBtn, (int)searchStudentIDBtn.getPreferredSize().getWidth() + spaceBtnRow, SpringLayout.WEST, searchStudentIDBtn);
        layout.putConstraint(SpringLayout.NORTH, searchStudentNameBtn, 570, SpringLayout.NORTH, panel);
     
        layout.putConstraint(SpringLayout.WEST, searchStudentGradeBtn, (int)searchStudentNameBtn.getPreferredSize().getWidth() + spaceBtnRow, SpringLayout.WEST, searchStudentNameBtn);
        layout.putConstraint(SpringLayout.NORTH, searchStudentGradeBtn, 570, SpringLayout.NORTH, panel);
   
        layout.putConstraint(SpringLayout.WEST, isJoinedDangBtn, (int)searchStudentGradeBtn.getPreferredSize().getWidth() + spaceBtnRow, SpringLayout.WEST, searchStudentGradeBtn);
        layout.putConstraint(SpringLayout.NORTH, isJoinedDangBtn, 570, SpringLayout.NORTH, panel);
     
        layout.putConstraint(SpringLayout.WEST, isPaidDoanPhiBtn, (int)isJoinedDangBtn.getPreferredSize().getWidth() + spaceBtnRow, SpringLayout.WEST, isJoinedDangBtn);
        layout.putConstraint(SpringLayout.NORTH, isPaidDoanPhiBtn, 570, SpringLayout.NORTH, panel);
        
        layout.putConstraint(SpringLayout.WEST, isPaidDangPhiBtn, (int)isPaidDoanPhiBtn.getPreferredSize().getWidth() + spaceBtnRow, SpringLayout.WEST, isPaidDoanPhiBtn);
        layout.putConstraint(SpringLayout.NORTH, isPaidDangPhiBtn, 570, SpringLayout.NORTH, panel);
        
        this.add(panel);
        this.pack();
        this.setTitle("Student Information Manager");
        this.setSize(1250, 650);
        deleteStudentBtn.setEnabled(false);
        studentInforBtn.setEnabled(false);
        addStudentBtn.setEnabled(true);
    }
    
    public void showMessage(String message) {
        JOptionPane.showMessageDialog(this, message);
    }
    
    /**
     * hiển thị list student vào bảng studentTable
     * 
     * @param list
     */
    public void showListStudents(List<Student> list) {
        int size = list.size();
        // với bảng studentTable có 15 cột, 
        // khởi tạo mảng 2 chiều students, trong đó:
        // số hàng: là kích thước của list student 
        // số cột: là 15
        Object [][] students = new Object[size][15];
        for (int i = 0; i < size; i++) {
            students[i][0] = list.get(i).getId();
            students[i][1] = list.get(i).getName();
            students[i][2] = list.get(i).getAge();
            students[i][3] = list.get(i).getGrade();
            students[i][4] = list.get(i).getAddress();
            students[i][5] = list.get(i).getGpa();
            students[i][6] = list.get(i).getDateJoinDoan();
            students[i][7] = list.get(i).getPlaceJoinDoan();

            if (list.get(i).getDateJoinDang() == null || list.get(i).getDateJoinDang().equals("")){
                students[i][8] = "Chưa vào Đảng";
            }else students[i][8] = list.get(i).getDateJoinDang();

            if (list.get(i).getPlaceJoinDang() == null || list.get(i).getPlaceJoinDang().equals("")){
                students[i][9] = "Chưa vào Đảng";
            }else students[i][9] = list.get(i).getPlaceJoinDang();
            
            if (list.get(i).getActivity() == null || list.get(i).getActivity().equals("")){
                students[i][12] = "Chưa tham gia";
            }else students[i][12] = list.get(i).getActivity();

            if (list.get(i).getReward() == null || list.get(i).getReward().equals("")){
                students[i][13] = "Không có";
            }else students[i][13] = list.get(i).getReward();

            if (list.get(i).getPunishment() == null || list.get(i).getPunishment().equals("")){
                students[i][14] = "Không có";
            }else students[i][14] = list.get(i).getPunishment();
            
            if (list.get(i).isDoanPhi()) students[i][10] = "Đã đóng";
            else students[i][10] = "Chưa đóng";
            if (list.get(i).isDangPhi()) students[i][11] = "Đã đóng";
            else students[i][11] = "Chưa đóng";
        }
        // Edit table
        studentTable.setModel(new DefaultTableModel(students, columnNames));
        TableColumnModel colStudentModel = studentTable.getColumnModel();
        colStudentModel.getColumn(0).setPreferredWidth(23);   
        colStudentModel.getColumn(1).setPreferredWidth(75);   
        colStudentModel.getColumn(2).setPreferredWidth(38);
        colStudentModel.getColumn(3).setPreferredWidth(36);
        colStudentModel.getColumn(4).setPreferredWidth(55);
        colStudentModel.getColumn(5).setPreferredWidth(40);
        colStudentModel.getColumn(6).setPreferredWidth(108);   
        colStudentModel.getColumn(7).setPreferredWidth(100);   
        colStudentModel.getColumn(8).setPreferredWidth(108);   
        colStudentModel.getColumn(9).setPreferredWidth(100);   
        colStudentModel.getColumn(10).setPreferredWidth(65);
        colStudentModel.getColumn(11).setPreferredWidth(65);
        colStudentModel.getColumn(12).setPreferredWidth(80);   
        colStudentModel.getColumn(13).setPreferredWidth(90);
        colStudentModel.getColumn(14).setPreferredWidth(70);

        TableCustom tableCustom = new TableCustom();
        tableCustom.apply(jScrollPaneStudentTable, TableCustom.TableType.MULTI_LINE);
    }
        
        
    /**
     * Lấy danh sách thông tin student từ Table
     * 
     * @return
     */
    public List<Integer> getListStudentIDTable(){
        List<Integer> listStudentID = new ArrayList<>();
        TableModel tableModel = studentTable.getModel();
        int rowCount = tableModel.getRowCount();
        
        for (int i=0; i< rowCount; i++){
            int id = Integer.parseInt(tableModel.getValueAt(i, 0).toString());
            listStudentID.add(id);
        }
        return listStudentID;
    }
    
    public JTable getStudentTable(){
        return this.studentTable;
    }
    
    public int getStuTabSelectedRow(){
        return studentTable.getSelectedRow();
    }
    
    public void enableStuInforBtn(boolean isEnable){
        this.studentInforBtn.setEnabled(isEnable);
    }
    
    public void setEnableDeleteBtn(boolean isEnable){
        deleteStudentBtn.setEnabled(isEnable);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
    }
    
    /**
     *
     * @param e
     */
    @Override
    public void valueChanged(ListSelectionEvent e) {
    }
    
    public void addAddStudentListener(ActionListener listener) {
        addStudentBtn.addActionListener(listener);
    }
    
    public void addDeleteStudentListener(ActionListener listener) {
        deleteStudentBtn.addActionListener(listener);
    }
    
    public void addSortStudentIDListener(ActionListener listener) {
        sortStudentIDBtn.addActionListener(listener);
    }
    
    public void addSortStudentGPAListener(ActionListener listener) {
        sortStudentGPABtn.addActionListener(listener);
    }
    
    public void addSortStudentNameListener(ActionListener listener) {
        sortStudentNameBtn.addActionListener(listener);
    }
    
    public void addStudentInformationListener(ActionListener listener){
        studentInforBtn.addActionListener(listener);
    }
    
    public void addShowAllStudentListener(ActionListener listener){
        showAllStudentBtn.addActionListener(listener);
    }
    
    public void addSearchStudentIDListener(ActionListener listener){
        searchStudentIDBtn.addActionListener(listener);
    }
    
    public void addSearchStudentNameListener(ActionListener listener){
        searchStudentNameBtn.addActionListener(listener);
    }
    
    public void addSearchStudentGradeListener(ActionListener listener){
        searchStudentGradeBtn.addActionListener(listener);
    }
    
    public void addSearchStudentJoinedDang(ActionListener listener){
        isJoinedDangBtn.addActionListener(listener);
    }
    
    public void addSearchStudentPaidDoanPhi(ActionListener listener){
        isPaidDoanPhiBtn.addActionListener(listener);
    }
    
    public void addSearchStudentPaidDangPhi(ActionListener listener){
        isPaidDangPhiBtn.addActionListener(listener);
    }
    
    public void addListStudentSelectionListener(ListSelectionListener listener) {
        studentTable.getSelectionModel().addListSelectionListener(listener);
    }
        
}

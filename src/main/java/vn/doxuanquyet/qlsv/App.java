package vn.doxuanquyet.qlsv;
import vn.doxuanquyet.qlsv.controller.LoginController;
import vn.doxuanquyet.qlsv.view.LoginView;
import java.awt.EventQueue;

public class App {
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                LoginView view = new LoginView();
                LoginController controller = new LoginController(view);
                // hiển thị màn hình login
                controller.showLoginView();
            }
        });
    }
}
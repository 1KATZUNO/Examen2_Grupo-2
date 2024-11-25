import java.awt.Component;
import javax.swing.SwingUtilities;

public class Main {
   public Main() {
   }
   public static void main(String[] args) {
      SwingUtilities.invokeLater(() -> {
         Login login = new Login();
         login.setLocationRelativeTo((Component)null);
         login.setVisible(true);
      });
   }
}
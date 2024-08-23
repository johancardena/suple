
import javax.swing.*;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoCollection;
import org.bson.Document;

public class login {
    private JTextField usuarioCampo;
    private JPasswordField contrasenaCampo;
    private JButton botonLogin;
    private JPanel panelLogin;
    private JFrame frame;

    public login() {
        frame = new JFrame("Login");
        frame.setContentPane(panelLogin);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);

        botonLogin.addActionListener(e -> {
            String usuario = usuarioCampo.getText();
            String contrasena = new String(contrasenaCampo.getPassword());

            MongoDatabase db = conexion.connect();
            MongoCollection<Document> usuarios = db.getCollection("usuarios");

            Document user = usuarios.find(new Document("usuario", usuario).append("contrasena", contrasena)).first();

            if (user != null) {
                String rol = user.getString("rol");
                if ("admin".equalsIgnoreCase(rol)) {
                    new admin();
                } else if ("estudiante".equalsIgnoreCase(rol)) {
                    new estudiante(usuario);
                }
                frame.dispose();
            } else {
                JOptionPane.showMessageDialog(frame, "Credenciales incorrectas", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
    }

    public static void main(String[] args) {
        new login();
    }
}

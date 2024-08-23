
import javax.swing.*;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoCollection;
import org.bson.Document;

public class admin {
    private JPanel panelAdmin;
    private JButton botonCrearMateria;

    public admin() {
        JFrame frame = new JFrame("Admin Dashboard");
        frame.setContentPane(panelAdmin);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);

        botonCrearMateria.addActionListener(e -> {
            MongoDatabase db = conexion.connect();
            MongoCollection<Document> materias = db.getCollection("materias");

            String nombreMateria = JOptionPane.showInputDialog("Nombre de la materia:");
            Document materia = new Document("nombre", nombreMateria);
            materias.insertOne(materia);

            JOptionPane.showMessageDialog(null, "Materia creada con éxito!");
        });
    }
}

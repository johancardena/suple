
import javax.swing.*;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoCollection;
import org.bson.Document;

public class estudiante {
    private JPanel panelEstudiante;
    private JTextArea areaMaterias;

    public estudiante(String usuario) {
        JFrame frame = new JFrame("Estudiante");
        frame.setContentPane(panelEstudiante);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);

        cargarMaterias(usuario);
    }

    private void cargarMaterias(String usuario) {
        MongoDatabase db = conexion.connect();
        MongoCollection<Document> estudiantes = db.getCollection("estudiantes");

        Document estudiante = estudiantes.find(new Document("usuario", usuario)).first();
        if (estudiante != null) {
            String materias = estudiante.getString("materias");
            areaMaterias.setText(materias);
        }
    }
}

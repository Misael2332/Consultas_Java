import java.awt.Color;
import javax.swing.*;

public class CrearUsuario extends JFrame{
    public CrearUsuario(){
        super ("Crear Usuario");
        setSize(500, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JTabbedPane contenedor = new JTabbedPane();
        JPanel crearUser = new JPanel();
        crearUser.setSize(500, 500 );
        crearUser.setBackground(new Color(0x42A5F5));
        crearUser.setLayout(null);


        add(contenedor);
    }
}
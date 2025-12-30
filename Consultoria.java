import java.awt.Color;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;

//metodo principal
public class Consultoria{
    public static void main(String[] args) {
        //arraylist de doctores
        //<nombre de la clase>nombre del arreglo = new 
        ArrayList<Doctores>doctor = new ArrayList<>();
        //<nombre de la clase>nombre del arreglo
        
        //un arreglo para ejemplo
        doctor.add(new Doctores("Star", "1234", "especialista", "Estrella", "Brenes", "2221277160" , "brenes_estrella@gmail.com" , "3 a 5" , 5 , 3 , 21 , 2225));

        //creacion de la ventna principal 
        //Configuracion de la ventana aqui se va agregar el panel 
        JFrame ventana = new JFrame("Curandero");               //nombre y creacion de la ventana
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);       //funcion para que cuando se cierra la ventana tambien se cierra el programa
        ventana.setSize(400, 400);                       //tamaño de la pantalla
        ventana.setLocationRelativeTo(null);                        //coloca la pantalla en el centro de la pantalla
        //--------------------
        

        //configuracion del panel
        JPanel inicioSecion = new JPanel();                            //se crea el panel y se asigna el nombre
        inicioSecion.setSize(400,400);                    //tamaño de la pantalla
        inicioSecion.setBackground(Color.gray.darker());               //se le asigna el color a el inicio se secion, de color gris oscuro
        inicioSecion.setLayout(null);                              //se coloca el panel en el centro de la pantalla
        //--------------------

        //etiquetas
        JLabel usuario = new JLabel("Usuario:");                 //etiqueta del usuario
        JLabel contrasena = new JLabel("Contraseña:");           //etiqueta de la contraseña
        JLabel mensajeInvalido = new JLabel("  ");               //etiqueta del mensaje invalido pero no se escribe
        JLabel nuevoUser = new JLabel("Nuevo usuario");          //etiqueta del nuevo usuario para la siguiente funcion
        //--------------------

        //espacio para escribir
        JTextField User = new JTextField();                             //texto para registrar el usuario
        JPasswordField Password = new JPasswordField();                 //texto para registrar la contraseña
        //---------------------

        //botones
        JButton ingresar = new JButton("Ingresar");                //boton para ingresar el usuario y contraseña
        JButton crearUser = new JButton("Crear");                  //boton para crear un nuevo usuario
        //--------------------

        //posiciones
        usuario.setBounds(180, 25,100, 80);             //etiqueta "Usuario"
        User.setBounds(150, 80,100, 30);                //texto para el usuario
        contrasena.setBounds(168, 110, 100, 30);        //etiqueta "contraseña"
        Password.setBounds(150, 140, 100, 30);          //texto para la contraseña
        mensajeInvalido.setBounds(100,170, 200, 30);    //etiqueta "usuario y contraseña"
        ingresar.setBounds(150, 200, 100, 30);          //boton de ingresar
        nuevoUser.setBounds(50, 300, 100, 30);          //mensaje de nuevo usuario
        crearUser.setBounds(150, 300, 100, 30);         //Boton de crear usuario
        //------------------
        
        //evento al hacer click el boton 
        //evento del boton de ingresar
        ingresar.addActionListener(new ActionListener (){
            @Override
            public void actionPerformed(ActionEvent e){
                String buscandousuario = User.getText().trim();                     //Obtiene lo que el usuario escribio en el campo de texto del usuario, devuelve una cadena y espacios extra 
                char[] contrasenaChars = Password.getPassword();                    //de la misma manera tambien se obtiene la contraseña, y se guarda como caracter en esa variable devuelve un arreglo de caracteres (char)
                String buscandocontrasena = new String(contrasenaChars).trim();     //convierte ese arreglo de caracteres a un texto simple, para poder compararlo más facilmente con otras cadenas
                boolean encontrado = false;                                         //es una bandera para saber si es que se ha encontrado un doctor valido
                
                //recorre toda la lista de doctores del arraylist y cuando si lo encuentra el boolean se hace verdadero
                for(Doctores d : doctor){
                    if(d.getUsuario().equals(buscandousuario) && d.getContrasena().equals(buscandocontrasena)){
                        encontrado = true;
                        break;
                    }
                }
                //condicional para saber si es que uno de los dos está bien para entrar a la ventana 
                if(encontrado){
                    new ConsultorioConPestanas().setVisible(true);                              //manda a llamar el codigo de Consultorio
                }else{
                    mensajeInvalido.setText("Usuario o contraseña invalidos");
                    mensajeInvalido.setForeground(Color.RED);
                }
            }
        });
        //---------------------------------

        //segundo boton creado para crear un nuevo usuario
        crearUser.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                new CrearUsuario().setVisible(true);
            }
        });
        //----------------------------------

        //insercion de las cosas al panel
        inicioSecion.add(usuario);
        inicioSecion.add(contrasena);
        inicioSecion.add(mensajeInvalido);
        inicioSecion.add(nuevoUser);
        inicioSecion.add(User);
        inicioSecion.add(Password);
        inicioSecion.add(ingresar);
        inicioSecion.add(crearUser);
        inicioSecion.setVisible(true);
        //--------------------------------

        //incersion de las cosas a la ventana 
        ventana.add(inicioSecion);
        ventana.setVisible(true);
        //--------------------------------
    }
}
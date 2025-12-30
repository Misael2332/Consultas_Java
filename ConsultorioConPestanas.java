import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;
import java.io.*;
import java.awt.Dimension;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.time.LocalDate;

import javax.swing.JOptionPane;

//la clase ya es una ventana
public class ConsultorioConPestanas extends JFrame{
    

    //este metodo vacia todo automaticamente para 
    private void limpiarFormulario(JTextField nom, JTextField apelli, JTextField ed, JTextField pes,
                                JTextField presi, JTextField temper, JTextArea diag,
                                JComboBox<String> diaN, JComboBox<String> mesN, JComboBox<String> anioN,
                                JComboBox<String> diaC, JComboBox<String> mesC, JComboBox<String> anioC, 
                                JRadioButton M, JRadioButton F, ButtonGroup grupoSexo){
        nom.setText("");
        apelli.setText("");
        ed.setText("");
        pes.setText("");
        presi.setText("");
        temper.setText("");
        diag.setText("");

        grupoSexo.clearSelection();
        
        //reiniciar fecha de nacimiento
        diaN.setSelectedIndex(0);
        mesN.setSelectedIndex(0);
        anioN.setSelectedIndex(0);

        //reiniciar fecha de nacimiento
        diaC.setSelectedIndex(0);
        mesC.setSelectedIndex(0);
        anioC.setSelectedIndex(0);
    }
    //-----------------------------

    //validacion de formulario
    private String validarFormulario(JTextField nom, JTextField apelli, JTextField ed, JTextField pes,
                                JTextField presi, JTextField temper, JTextArea diag,
                                JComboBox<String> diaN, JComboBox<String> mesN, JComboBox<String> anioN,
                                JComboBox<String> diaC, JComboBox<String> mesC, JComboBox<String> anioC, 
                                JRadioButton M, JRadioButton F, ButtonGroup grupoSexo){
        if(nom.getText().trim().isEmpty() ||
           apelli.getText().trim().isEmpty() ||
           ed.getText().trim().isEmpty() ||
           pes.getText().trim().isEmpty() ||
           presi.getText().trim().isEmpty() ||
           temper.getText().trim().isEmpty() ||
           diag.getText().trim().isEmpty()){
            return "Hay campos incompletos.Asegurate de llenar todos los campos obligatorios.";
        }

        //formato y rangos numericos
        int edad;
        double peso;
        double temperatura;

        try{
            edad = Integer.parseInt(ed.getText().trim());
            peso = Double.parseDouble(pes.getText().trim());
            temperatura = Double.parseDouble(temper.getText().trim());
        }catch (NumberFormatException nfe){
            return "Edad, peso o temperatura tienen formato invalido(debenser numeros).";
        }

        if(edad < 0 || edad > 120) return "La edad debe estar entre 0 y 120.";
        if(peso <= 0 || peso > 500) return "El peso debe ser un numero positivo razonable.";
        if(temperatura < 30.0 || temperatura > 45.0) return "La temperatura está fuera de un rango plausible.";

        //validar formato de presion aceptar "120/80" o solo numero
        String pres = presi.getText().trim();
        if(!(pres.matches("\\d{2,3}/\\d{2,3}") || pres.matches ("\\d{2,3}"))){
            return "Formato de presion invalido. Ejemplos validos: 120/80 o 120";
        }

        //consistencia edad --fecha de nacimiento
        //obtenemos año de nacimiento seleccionado
        int anioNacimiento;
        int anioActual;

        try{
            anioNacimiento = Integer.parseInt((String) anioN.getSelectedItem());
        }catch (Exception ex){
            return "Año de nacimiento invalido.";
        }

        LocalDate hoy = LocalDate.now();
        anioActual = hoy.getYear();

        //si la fecha de nacimiento está en el futuro:
        if(anioNacimiento > anioActual){
            return "La fecha de nacimiento está incorrecta";
        }

        int edadCalculada = anioActual - anioNacimiento;

        //Permitimos una diferencia de 0 a 1 (meses), pero si hay más marcamos una incosistencia
        if(Math.abs(edadCalculada - edad) > 1){
            return "La fecha de nacimiento no concuerda con la edad ingresada.";
        }

        return null;
    }

    //aqui se declara el arreglo porque no la lee en donde se situava en la clase principal
        // se pone aqui para que pueda ser leida
    private ArrayList<Paciente> paciente = new ArrayList<>();


    //inicio de la clase de la pestaña
    public ConsultorioConPestanas(){
        super("Mi consultorio");                    //llama al constructor de la clase padre
        setSize(800,600);                   //tamaño de la ventana

        setLocationRelativeTo(null);
    
        //Contenedor de pestañas
        JTabbedPane contenedor = new JTabbedPane();

        //panel consultorio
        JPanel consultorio = new JPanel(null);

        //panel pacientes
        JPanel pacientes = new JPanel(null);
        pacientes.setPreferredSize(new Dimension(760, 520));

        //area de texto de pacientes
        JTextArea areaPacientes = new JTextArea();
        areaPacientes.setEditable(false);

        JScrollPane scrollPacientes = new JScrollPane(areaPacientes);
        scrollPacientes.setBounds(20, 20, 740, 480);
        pacientes.add(scrollPacientes);
        //=====================

        // Cargar pacientes desde el archivo si existe
        File archivo = new File("pacientes.txt");
        if (archivo.exists()) {
            try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
                String linea;
                StringBuilder contenido = new StringBuilder();
                while ((linea = br.readLine()) != null) {
                    contenido.append(linea).append("\n");
                }
                areaPacientes.setText(contenido.toString());
            } catch (IOException ex) {
                areaPacientes.setText("Error al leer archivo de pacientes.");
            }
        }

        //Nombre.
        JLabel nombre = new JLabel("Nombre:");
        nombre.setBounds(20,20,100,30);
        consultorio.add(nombre);
        
        JTextField nom = new JTextField();
        nom.setBounds(150,20,100,30);
        consultorio.add(nom);
        //----------------------
        
        //Apellido.
        JLabel apellido = new JLabel("Apellidos:");
        apellido.setBounds(20,60,100,30);
        consultorio.add(apellido);
        
        JTextField apelli = new JTextField();
        apelli.setBounds(150,60,100,30);
        consultorio.add(apelli);
        //----------------------

        //Edad.
        JLabel edad = new JLabel("Edad:");
        edad.setBounds(20,100,100,30);
        consultorio.add(edad);
        
        JTextField ed = new JTextField();
        ed.setBounds(150,100,100,30);
        consultorio.add(ed);
        //----------------------
        
        //Peso.
        JLabel peso =new JLabel("Peso:");
        peso.setBounds(20,140,100,30);
        consultorio.add(peso);
        
        JTextField pes = new JTextField();
        pes.setBounds(150,140,100,30);
        consultorio.add(pes);
        //----------------------
        
        //Sexo.
        JLabel sexo = new JLabel("Sexo:");
        sexo.setBounds(20,180,100,30);
        consultorio.add(sexo);
        
        JRadioButton M = new JRadioButton("Masculino");
        M.setBounds(150,180,100,30);
        consultorio.add(M);
        
        JRadioButton F = new JRadioButton("Femenino");
        F.setBounds(250,180,100,30);
        consultorio.add(F);
        
        ButtonGroup grupoSexo = new ButtonGroup();
        grupoSexo.add(M);
        grupoSexo.add(F);
        //----------------------
        
        //Pesion arterial.
        JLabel presion = new JLabel("Presion arterial:");
        presion.setBounds(20,220,100,30);
        consultorio.add(presion);
        
        JTextField presi = new JTextField();
        presi.setBounds(150,220,100,30);
        consultorio.add(presi);
        //----------------------
        
        //Temperatura.
        JLabel temperatura = new JLabel("Temperatura:");
        temperatura.setBounds(20,260,100,30);
        consultorio.add(temperatura);
        
        JTextField temper = new JTextField();
        temper.setBounds(150,260,100,30);
        consultorio.add(temper);
        //----------------------
                
        //Fecha de nacimiento.
        JLabel f_Nacimiento = new JLabel("Fecha de nacimiento:");
        f_Nacimiento.setBounds(20,300,150,30);
        consultorio.add(f_Nacimiento);
        
        String[] meses = {
            "Enero","Febrero","Marzo","Abril","Mayo","Junio",
            "Julio","Agosto","Septiembre","Octubre","Noviembre","Diciembre"
        };

        JComboBox<String> diaN = new JComboBox<>();
        JComboBox<String> mesN = new JComboBox<>(meses);
        JComboBox<String> anioN = new JComboBox<>();

        // Llenar años de forma automática 1980–2026
        for (int i = 1980; i <= 2026; i++) {
            anioN.addItem(String.valueOf(i));
        }

        Runnable actualizarDiasN = () -> {
        int m = mesN.getSelectedIndex() + 1; // meses 1-12
            int y = Integer.parseInt((String) anioN.getSelectedItem());

            int diasMes = java.time.YearMonth.of(y, m).lengthOfMonth();

            diaN.removeAllItems();
            for (int i = 1; i <= diasMes; i++) {
                diaN.addItem(String.valueOf(i));
            }
        };

        // Cada vez que el usuario cambie mes o año → recalcular días
        mesN.addActionListener(e -> actualizarDiasN.run());
        anioN.addActionListener(e -> actualizarDiasN.run());

        // Llenar por primera vez
        actualizarDiasN.run();

        // Posición de los combos
        diaN.setBounds(150,300,40,30);
        mesN.setBounds(190,300,90,30);
        anioN.setBounds(280,300,60,30);

        consultorio.add(diaN);
        consultorio.add(mesN);
        consultorio.add(anioN);
        //--------------------------------

        //Diagnostico.
        JLabel diagnostico = new JLabel("Diagnostico:");
        diagnostico.setBounds(20,340,100,30);
        consultorio.add(diagnostico);
        
        JTextArea diag = new JTextArea();
        diag.setLineWrap(true);                           
        diag.setWrapStyleWord(true);                      
        
        JScrollPane scrollDiag = new JScrollPane(diag);
        scrollDiag.setBounds(150,340,350,100);
        consultorio.add(scrollDiag);
        //---------------------------
        
        //Fecha de consulta.
        JLabel f_Consulta = new JLabel("Fecha de consulta:");
        f_Consulta.setBounds(20,460,150,30);
        consultorio.add(f_Consulta);

        JComboBox<String> diaC = new JComboBox<>();
        JComboBox<String> mesC = new JComboBox<>(meses);
        JComboBox<String> anioC = new JComboBox<>();

        // Llenar años de forma automática 1980–2026
        for (int i = 2025; i <= 2026; i++) {
            anioC.addItem(String.valueOf(i));
        }

        //funcion para que los días cambien de acuerdo al mes
        Runnable actualizarDiasC = () -> {
        int m = mesC.getSelectedIndex() + 1; // meses 1-12
            int y = Integer.parseInt((String) anioC.getSelectedItem());

            int diasMes = java.time.YearMonth.of(y, m).lengthOfMonth();

            diaC.removeAllItems();
            for (int i = 1; i <= diasMes; i++) {
                diaC.addItem(String.valueOf(i));
            }
        };

        // Cada vez que el usuario cambie mes o año → recalcular días
        mesC.addActionListener(e -> actualizarDiasC.run());
        anioC.addActionListener(e -> actualizarDiasC.run());

        // Llenar por primera vez
        actualizarDiasC.run();

        // Posición de los combos
        diaC.setBounds(150,460,40,30);
        mesC.setBounds(190,460,90,30);
        anioC.setBounds(280,460,60,30);

        consultorio.add(diaC);
        consultorio.add(mesC);
        consultorio.add(anioC);
        //--------------------------------

        //Componentes agregados a panel paciente.
        contenedor.addTab("Consultorio", consultorio);
        contenedor.addTab("Pacientes", pacientes);

        //Boton para guardar el paciente
        JButton guardar = new JButton("Guardar paciente");
        guardar.setBounds(500, 460, 180, 30);
        consultorio.add(guardar);

        guardar.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                //validar primero
                String error = validarFormulario(nom, apelli, ed, pes, presi, temper, diag,
                                            diaN, mesN, anioN, diaC, mesC, anioC, M, F, grupoSexo);

                if(error != null){
                JOptionPane.showMessageDialog(ConsultorioConPestanas.this,
                                            "Error: " + error, 
                                            "Error de validacion", 
                                            JOptionPane.ERROR_MESSAGE);  
                    return;                                              
                }

                try{
                    //aqui se agarra los datos que se ha escrito los pacientes
                    String nombre = nom.getText();
                    String apellidos = apelli.getText();
                    int edad = Integer.parseInt(ed.getText());
                    double peso = Double.parseDouble(pes.getText());
                    String sexo = M.isSelected() ? "Masculino" : (F.isSelected() ? "Femenino" : "No especificado");
                    String presion = presi.getText();
                    double temperatura = Double.parseDouble(temper.getText());
                    String fechaNac = diaN.getSelectedItem() + " de " + mesN.getSelectedItem() + " de " + anioN.getSelectedItem();
                    String diagnostico = diag.getText();
                    String fechaCons = diaC.getSelectedItem() + " de " + mesC.getSelectedItem() + " de " + anioC.getSelectedItem();
                    //-------------------------------------------------------

                    Paciente p = new Paciente(nombre, apellidos, edad, sexo, peso, presion, temperatura, fechaNac, diagnostico, fechaCons);
                    paciente.add(p);

                    ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("Paciente.dat"));
                    oos.writeObject(p);
                    oos.close();

                    areaPacientes.append(p.toString() + "\n\n");
                    JOptionPane.showMessageDialog(null, "Paciente guardado correctamente.");
                }catch (IOException ex){
                    JOptionPane.showMessageDialog(null, "Error al guardar el paciente." + ex.getMessage());
                }
                //parte donde se tiene que reiniciar la aprte y poder volver a reeescribir el paciente
                //se manda a llamar la funcion de hasta arriba para borrar todo el formulario y volver a reescribir de nuevo
                limpiarFormulario(nom, apelli, ed, pes, presi, temper, diag, diaN, mesN, anioN, diaC, mesC, anioC, M, F, grupoSexo);
            }
        });

        contenedor.addTab("Consultorio", consultorio);
        contenedor.addTab("Pacientes", pacientes);
        add(contenedor);
    }
}
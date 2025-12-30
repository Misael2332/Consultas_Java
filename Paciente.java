import java.io.Serializable;

//en este archivo se encuentra las clases de el paciente y el doctor, serializables para archivos binarios

public class Paciente implements Serializable{
    private String nombre;
    private String apellidos;
    private String sexo;
    private String presion;
    private String fechaNacimiento;
    private String diagnostico;
    private String fechaConsulta;
    private double peso;
    private double temperatura;
    private int edad;

    public Paciente(String nombre, String apellidos, int edad, String sexo, double peso, 
                    String presion, double temperatura, String fechaNacimiento, 
                    String diagnostico, String fechaConsulta){
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.edad = edad;
        this.sexo = sexo;
        this.peso = peso;
        this.presion = presion;
        this.temperatura = temperatura;
        this.fechaNacimiento = fechaNacimiento;
        this.diagnostico = diagnostico;
        this.fechaConsulta = fechaConsulta;
    }

    @Override
    public String toString(){
        return "Nombre: " + nombre + " " + apellidos + 
               "\nEdad: " + edad +
               "\nSexo: " + sexo +
               "\nPeso: " + peso + 
               "\nPresión arterial: " + presion +
               "\nTemperatura: " + temperatura +
               "\nFecha de nacimiento: " + fechaNacimiento +
               "\nDiagnóstico: " + diagnostico +
               "\nFecha de consulta: " + fechaConsulta +
               "\n----------------------------------------\n";
    }
}


class Doctores implements Serializable{
    private String usuario;
    private String especializacion;
    private String contrasena;
    private String nombre;
    private String apellido;
    private String telefono;
    private String email;
    private String horarioAtencion;
    private int consultorio;
    private int aniosExperiencia;
    private int edad;
    private int idDoctor;

    public Doctores(String usuario, String contrasena, String especializacion,
                    String nombre, String apellido, String telefono, String email,
                    String horarioAtencion, int consultorio, int aniosExperiencia, 
                    int edad, int idDoctor){
        this.usuario = usuario;
        this.contrasena = contrasena;
        this.especializacion = especializacion;
        this.nombre = nombre;
        this.apellido = apellido;
        this.telefono = telefono;
        this.email = email;
        this.horarioAtencion = horarioAtencion;
        this.consultorio = consultorio;
        this.aniosExperiencia = aniosExperiencia;
        this.edad = edad;
        this.idDoctor = idDoctor;
    }

    public String getUsuario(){
        return usuario;
    }

    public String getContrasena(){
        return contrasena;
    }

    @Override
    public String toString(){
        return "Usuario: " + usuario + "\n" + 
                "Contraseña: " + contrasena + "\n" + 
                "Especializacion: " + especializacion + "\n" +
                "Nombre: " + nombre + " " + apellido + "\n" + 
                "Telefono: " + telefono + "\n" +
                "Email: " + email + "\n" +
                "Horario: " + horarioAtencion + "\n" +
                "Consultorio: " + consultorio + "\n" +
                "Experiencia: " + aniosExperiencia + "\n" +
                "Edad: " + edad + "\n" +
                "ID Doctor: " + idDoctor;
    }
}
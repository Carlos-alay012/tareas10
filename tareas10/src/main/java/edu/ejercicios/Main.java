package edu.ejercicios;

import java.sql.*;
import java.util.Scanner;
import edu.ejercicios.clsconexion;
public class Main {
    private static Connection conexion = null;

    private static void conexion(){
        try{
            conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/tareacrud", "root", "Alay2003@");
        }catch (SQLException e){
            System.out.println("Hubo un error" + e.getMessage());
        }
    }
    public static void main(String[] args) throws SQLException {
        conexion();
        clsconexion conec = new clsconexion();

        Scanner scan = new Scanner(System.in);
        while (true){
         System.out.println("Seleccione el número de la opción que desea");
         System.out.println("1. Agregar personas");
         System.out.println("2. Consultar personas");
         System.out.println("3. Actualizar información");
         System.out.println("4. Eliminar");
         System.out.println("5. Salir del programa");

         String opcion = scan.nextLine();
         if (opcion.equals("1")){
             System.out.println("A CONTINUACIÓN INGRESE LA INFORMACIÓN QUE SE LE PIDE");
             System.out.println("Nombre: ");
             String Nombre = scan.nextLine();
             System.out.println("Apellido: ");
             String apellido = scan.nextLine();
             System.out.println("Fecha de registro (YYYY-MM-DD): ");
             String fecha_Registro = scan.nextLine();
             System.out.println("Sueldo base: ");
             double sueldo_Base = Double.parseDouble(scan.nextLine());
             System.out.println("Sexo (Masculino/Femenino/Otro): ");
             String sexo = scan.nextLine();
             System.out.println("Edad: ");
             int edad = Integer.parseInt(scan.nextLine());
             System.out.println("Longitud de casa: ");
             double longitud = Double.parseDouble(scan.nextLine());
             System.out.println("Latitud de casa: ");
             double latitud = Double.parseDouble(scan.nextLine());
             System.out.println("Comentarios: ");
             String comentarios = scan.nextLine();
             agregarp( Nombre, apellido, fecha_Registro, sueldo_Base, sexo, edad, longitud, latitud, comentarios);
         }
        else if (opcion.equals("2")){
             consultarp();
         }
        else if (opcion.equals("3")){
             System.out.println("Ingrese el código de la persona que desee actualizar");
             int codigo = Integer.parseInt(scan.nextLine());
             System.out.println("Nuevo sueldo base: ");
             double nuevoSueldoBase = Double.parseDouble(scan.nextLine());
             System.out.println("Nuevos comentarios: ");
             String nuevosComentarios = scan.nextLine();

             actualizarp(codigo, nuevoSueldoBase, nuevosComentarios);
         }
        else if (opcion.equals("4")){
             System.out.println("Código de la persona a eliminar: ");
             int codigo = Integer.parseInt(scan.nextLine());
             eliminarp(codigo);
         }
        else if(opcion.equals("5")){
            break;
         }
        }
        if (conexion != null) {
            conexion.close();
        }
    }

    private static void agregarp( String Nombre, String apellido, String fecha_Registro, double sueldo_Base, String sexo, int edad, double longitud, double latitud, String comentarios) throws SQLException {
            if (conexion == null){
                conexion();
            }
            String query = "INSERT INTO tareas10 (nombre, apellido, fecha_registro, sueldo_base, sexo, edad, longitud, latitud, comentarios) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement ps = conexion.prepareStatement(query);
            ps.setString(1, Nombre);
            ps.setString(2, apellido);
            ps.setString(3, fecha_Registro);
            ps.setDouble(4, sueldo_Base);
            ps.setString(5, sexo);
            ps.setInt(6, edad);
            ps.setDouble(7, longitud);
            ps.setDouble(8, latitud);
            ps.setString(9, comentarios);

            ps.executeUpdate();
            System.out.println("Se ha registrado correctamente");
    }
    private static void consultarp() throws SQLException {
        if(conexion == null) {
            conexion();
        }
        String query = "SELECT * FROM tareas10";
        PreparedStatement ps = conexion.prepareStatement(query);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            System.out.println("Nombre: " + rs.getString("nombre") + " Apellido: " + rs.getString("apellido"));
        }
    }

    private static void actualizarp(int codigo, double sueldoBase, String comentarios) throws SQLException {
        if (conexion == null) {
            conexion();
        }
        String query = "UPDATE tareas10 SET sueldo_base = ?, comentarios = ? WHERE codigo = ?";
        PreparedStatement ps = conexion.prepareStatement(query);
        ps.setDouble(1, sueldoBase);
        ps.setString(2, comentarios);
        ps.setInt(3, codigo);

        ps.executeUpdate();
        System.out.println("Registro actualizado con éxito");
    }

    private static void eliminarp(int codigo) throws SQLException {
        if (conexion == null) {
            conexion();
        }
        String query = "DELETE FROM tareas10 WHERE codigo = ?";
        PreparedStatement ps = conexion.prepareStatement(query);
        ps.setInt(1, codigo);

        ps.executeUpdate();
        System.out.println("Se ha eliminado correctamente");
    }
}

package edu.ejercicios;
import java.sql.*;
import java.util.Scanner;

public class clsconexion{
    private Connection conexion = null;

    private void conexion(){
        try{
            conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/tareacrud", "root", "Alay2003@");
        }catch (SQLException e){
            System.out.println("Hubo un error" + e.getMessage());
        }
    }
    public void read() throws  SQLException{
        conexion();
        String query = "SELECT * FROM tareas10";
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            ps = conexion.prepareCall(query);
            rs = ps.executeQuery();
            while (rs.next()){
                System.out.println("Nombre" + rs.getString("nombre")+ "Apellido: "+ rs.getString("Apellido"));

            }
        }finally {
            if (rs != null){
                rs.close();
            }
            if (ps != null) {

                ps.close();
            }
            conexion.close();
        }
    }
    public void insertedatos() throws  SQLException{
        conexion();
        Scanner scan = new Scanner(System.in);
        System.out.println("Ingrese el nombre");
        String nombre = scan.nextLine();
        System.out.println("Ingrese el apellido");
        String apellido = scan.nextLine();

        String query = "INSERT INTO tareas10 (nombre, apellido) VALUES (?,?)";
        PreparedStatement ps = null;
        try{
            ps = conexion.prepareStatement(query);
            ps.setString(1, nombre);
            ps.setString(2,apellido);
            ps.executeUpdate();
            System.out.println("Los datos fueron insertados correctamente");
        }finally {
            if (ps != null){
                ps.close();
            }
            conexion.close();
        }
    }
}
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package conexionmysql;

import java.sql.*;
import java.util.*;
import javax.swing.*;

/**
 *
 * @author Jose Manuel Mendez Ruiz
 */
public class ConexionMySQL {

    static String bd = "registros";
    static String login = "root";
    static String password = "";
    static String url = "jdbc:mysql://127.0.0.1:3306/" + bd;

    public static void main(String[] args) throws Exception {

        Connection conexion = null;
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            conexion = DriverManager.getConnection(url, login, password);
            if (conexion != null) {
                System.out.println("Base de Datos abierta/Conexion Exitosa" + url);

                Statement st = conexion.createStatement();//Permite comandos SQL   

                int seleccion;
                System.out.print("Introduzca un número entero del 1 al 3"
                        + ": ");
                                System.out.println("Introduzca un número entero del 1 al 3\n"
                        + "1.-Crear Tabla\n"
                        + "2.-Llenar Tabla\n"
                        + "3.-Consulta\n");
//                        + "4.-Update\n"
//                        + "5.-Borrar Registro\n"
//                        + "6.-Borrar Tabla");
                Scanner sc = new Scanner(System.in);

                seleccion = sc.nextInt();

                switch (seleccion) {
                    case 1:
                        MetodosSQL.creartabla(st);
                        break;
                    case 2:
                        MetodosSQL.llenartabla(st);
                        break;
                    case 3:
                        String ConsultaUser = JOptionPane.showInputDialog("Script SQL");
                        MetodosSQL.consulta(st, ConsultaUser);

                        break;
                        
//                        Metodos que no se utilizan
//                    case 4:
//                        MetodosSQL.update(st);
//
//                        break;
//                    case 5:
//                        MetodosSQL.borrarregisto(st);
//                        break;
//
//                    case 6:
//                        MetodosSQL.borrartabla(st);
//
//                        break;
                }

                //Cerrar la conexion con la Base de datos
                System.out.println("Cerrando Base de Datos " + url + "...");
                conexion.close();// Cerrar base de datos
                System.out.println("Base de Datos " + url + " Cerrada");

            } else {
                System.out.println("Conexion fallida");
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }
}

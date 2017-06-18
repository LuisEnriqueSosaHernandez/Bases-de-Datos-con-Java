/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package basedatos;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import static javax.swing.WindowConstants.HIDE_ON_CLOSE;
import javax.swing.table.DefaultTableModel;
/**
 *
 * @author alex_
 */
public class SQL {
    private static Connection Conexion;
    public void SQLConnection(String user, String pass, String db_name) throws Exception {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/" + db_name, user, pass);
            JOptionPane.showMessageDialog(null, "Se ha iniciado la conexión con el servidor de forma exitosa");
        } catch (ClassNotFoundException ex) {
            JOptionPane.showMessageDialog(null, "Error en la conexión");
            Logger.getLogger(SQL.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
             JOptionPane.showMessageDialog(null, "Error en la conexión");
            Logger.getLogger(SQL.class.getName()).log(Level.SEVERE, null, ex);
        }
    }   
 public void closeConnection() {
        try {
            Conexion.close();
            JOptionPane.showMessageDialog(null, "Se ha finalizado la conexión con el servidor");
        } catch (SQLException ex) {
            Logger.getLogger(SQL.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
 public void createDB(String name) throws Exception {
        try {
            String Query = "CREATE DATABASE " + name;
            Statement st = Conexion.createStatement();
            st.executeUpdate(Query);
            JOptionPane.showMessageDialog(null, "Se ha creado la base de datos " + name + " de forma exitosa");
            closeConnection();
            SQLConnection("root", "", name);

        } catch (SQLException ex) {
            Logger.getLogger(SQL.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
 public void createTable(String name) {
        try {
            String Query = "CREATE TABLE " + name + ""
                    + "(ID VARCHAR(25),Nombre VARCHAR(100), Telefono VARCHAR(12),"
                    + " Ciudad VARCHAR(20), Sexo VARCHAR(1))"; 
            Statement st = Conexion.createStatement();
            st.executeUpdate(Query);
JOptionPane.showMessageDialog(null, "Se ha creado la tabla " + name + " de forma exitosa");
        } catch (SQLException ex) {
            Logger.getLogger(SQL.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
 public void insertData(String table_name, String ID, String name, String telefono, String ciudad, String sexo) {
        try {
            String Query = "INSERT INTO " + table_name + " VALUES("
                    + "\"" + ID + "\", "
                    + "\"" + name + "\", "
                    + "\"" + telefono + "\", "
                    + "\"" + ciudad + "\", "
                    + "\"" + sexo + "\")";
            Statement st = Conexion.createStatement();
            st.executeUpdate(Query);            
            JOptionPane.showMessageDialog(null, "Datos almacenados de forma exitosa");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error en el almacenamiento de datos");
        }
    }
public boolean getValues(String table_name) {
        try {
            String Query = "SELECT * FROM " + table_name;
            Statement st = Conexion.createStatement();
            java.sql.ResultSet resultSet;
            resultSet = st.executeQuery(Query);
            if(!resultSet.next()){
                JOptionPane.showMessageDialog(null, "No hay registros");
            }else{
                JTable tabla=new JTable();
               DefaultTableModel datos = new DefaultTableModel();               
                Object[] fila = new Object[5];
                Object[] col = new Object[5];
                resultSet.beforeFirst();
                datos.addColumn("ID");
                datos.addColumn("Nombre");
                datos.addColumn("Teléfono");
                datos.addColumn("Ciudad");
                datos.addColumn("Sexo");
                col[0]="ID";
                col[1]="Nombre";
                col[2]="Teléfono";
                col[3]="Ciudad";
                col[4]="Sexo";
                datos.addRow(col);
            while (resultSet.next()) {                
                fila[0]=resultSet.getString("ID");
                fila[1]=resultSet.getString("Nombre");
                fila[2]=resultSet.getString("Telefono");
                fila[3]=resultSet.getString("Ciudad");
                fila[4]=resultSet.getString("Sexo");
                datos.addRow(fila);
                System.out.println("ID: " + resultSet.getString("ID") + " "
                        + "Nombre: " + resultSet.getString("Nombre") + " " + resultSet.getString("Telefono") + " "
                        + "Ciudad: " + resultSet.getString("Ciudad") + " "
                        + "Sexo: " + resultSet.getString("Sexo"));
            }
             tabla.setModel(datos);
            JFrame f=new JFrame();
            f.setLocationRelativeTo(null);
            f.setSize(500, 500);
             f.add(tabla);
             f.setVisible(true);
            f.setDefaultCloseOperation(HIDE_ON_CLOSE);
            return true;
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error en la adquisición de datos");
        }
        return false;
    }
public boolean Busqueda(String table_name,String ID) {
        try {
            String Query = "SELECT * FROM " + table_name+" WHERE ID="+ID;
            Statement st = Conexion.createStatement();
            java.sql.ResultSet resultSet;
            resultSet = st.executeQuery(Query);
            if(!resultSet.next()){
                JOptionPane.showMessageDialog(null, "No hay registros");
            }else{
                JTable tabla=new JTable();
               DefaultTableModel datos = new DefaultTableModel();
                 Object[] fila = new Object[5];
                 Object[] col = new Object[5];
                resultSet.beforeFirst();
                datos.addColumn("ID");
                datos.addColumn("Nombre");
                datos.addColumn("Teléfono");
                datos.addColumn("Ciudad");
                datos.addColumn("Sexo");
                col[0]="ID";
                col[1]="Nombre";
                col[2]="Teléfono";
                col[3]="Ciudad";
                col[4]="Sexo";
                datos.addRow(col);
            while (resultSet.next()) {                
                fila[0]=resultSet.getString("ID");
                fila[1]=resultSet.getString("Nombre");
                fila[2]=resultSet.getString("Telefono");
                fila[3]=resultSet.getString("Ciudad");
                fila[4]=resultSet.getString("Sexo");
                datos.addRow(fila);               
                System.out.println("ID: " + resultSet.getString("ID") + " "
                        + "Nombre: " + resultSet.getString("Nombre") + " " + resultSet.getString("Telefono") + " "
                        + "Ciudad: " + resultSet.getString("Ciudad") + " "
                        + "Sexo: " + resultSet.getString("Sexo"));
           }
            tabla.setModel(datos);
            JFrame f=new JFrame();
            f.setLocationRelativeTo(null);
            f.setSize(500, 500);
             f.add(tabla);
             f.setVisible(true);
            f.setDefaultCloseOperation(HIDE_ON_CLOSE);
            return true;
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error en la adquisición de datos");
        }
        return false;
    }
public void update(String table_name, String ID, String nombre,String telefono,String Ciudad,String Sexo){
    try {
          Statement st = Conexion.createStatement();
        if(!nombre.equals("")){
            String Query = "UPDATE " + table_name+ " SET Nombre='"+nombre+"' WHERE ID="+ID; 
             st.executeUpdate(Query);
            
        }
        if(!telefono.equals("")){
              String Query = "UPDATE " + table_name+ " SET Telefono='"+telefono+"' WHERE ID="+ID;
               st.executeUpdate(Query);
             
        }
         if(!Ciudad.equals("")){
              String Query = "UPDATE " + table_name+ " SET Ciudad='"+Ciudad+"' WHERE ID="+ID;
               st.executeUpdate(Query);
              
        }
        if(!Sexo.equals("")){
              String Query = "UPDATE " + table_name+ " SET Sexo='"+Sexo+"' WHERE ID="+ID;
               st.executeUpdate(Query);
               
        }
           
          
           
           JOptionPane.showMessageDialog(null, "Registro actualizado");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error en la actualizacion de datos");
        }
}
public void deleteRecord(String table_name, String ID) {
        try {
            String Query = "DELETE FROM " + table_name + " WHERE ID = \"" + ID + "\"";
            Statement st = Conexion.createStatement();
            st.executeUpdate(Query);
 JOptionPane.showMessageDialog(null, "Registro eliminado");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            JOptionPane.showMessageDialog(null, "Error borrando el registro especificado");
        }
    }
}

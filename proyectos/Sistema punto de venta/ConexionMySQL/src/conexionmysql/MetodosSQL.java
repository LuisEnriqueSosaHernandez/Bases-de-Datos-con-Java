/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package conexionmysql;

import java.sql.*;

/**
 *
 * @author Jose
 */
class MetodosSQL {

    static void creartabla(Statement st) throws SQLException {
        // Crear tabla contacto
        st.executeUpdate("CREATE TABLE Usuarios ("
                + "IdUsuario INT AUTO_INCREMENT, "
                + "PRIMARY KEY(IdUsuario), "
                + "NombreUser 	CHAR(40), "
                + "ApePatUser 	CHAR(40), "
                + "ApeMatUser 	CHAR(40), "
                + "Sexo 	CHAR(1), "
                + "Pais 	CHAR(20), "
                + "Ciudad 	CHAR(20), "
                + "CP 		CHAR(15), "
                + "FechaNac 	DATE)");
        System.out.println("-Creada tabla (contacto) - Ok");

    }

    static void llenartabla(Statement st) throws SQLException {
        // Insertar datos a la tabla
        String nombres[] = {"Maria", "Julia", "Mayra", "Samanta", "Karla", "Gabriela", "Francisca", "Brenda", "Fernando", "Manuel", "Victor",
            "Jorge", "Arturo", "Luis", "Angel", "Hector", "Alberto", "Andres", "Juan", "Antonio"};

        String apellidos[] = {"Sanchez", "Jimenez", "Moreno", "Gonzalez", "Rodriguez", "Gomez", "Fernandez", "Lopez", "Diaz", "Martinez",
            "Perez", "Garcia", "Mendez", "Romero", "Sosa", "Alvarez", "Torres", "Ruiz", "Ramirez", "Flores"};

        String sexo[] = {"M", "F"};

        String paises[] = {"Japan", "Mexico", "India", "Brazil", "USA", "China", "Nigeria", "USA", "India", "Argentina",
            "South Korea", "China", "Pakistan", "India", "Bangladesh", "Philippines", "Egypt", "Japan", "Brazil", "China"};

        String ciudades[] = {"Tokyo", "Mexico City", "Mumbai", "Sáo Paulo", "New York City", "Shanghai", "Lagos", "Los Angeles", "Calcutta", "Buenos Aires",
            "Seóul", "Beijing", "Karachi", "Delhi", "Dhaka", "Manila", "Cairo", "Õsaka", "Rio de Janeiro", "Tianjin"};

        String cp[] = {"971234", "984621", "341254", "1234677", "9362738", "13748275", "37485930", "1375602", "2650394", "13756039",
            "3457485", "12748567", "1372869", "937485601", "13755869", "9384758", "1387586", "3475873", "12837485", "13847560"};

        for (int i = 0; i < 10000; i++) {
            int nombresRam = (int) (Math.random() * 20);
            int apellidosPRam = (int) (Math.random() * 20);
            int apellidosMRam = (int) (Math.random() * 20);
            int x;
            if (nombresRam >= 8) {
                x = 0;
            } else {
                x = 1;
            }
            int ciudad_paisesRam = (int) (Math.random() * 20);
            int CPRam = (int) (Math.random() * 20);
            int diaRam = 1 + (int) (Math.random() * ((30 - 1) + 1));
            int mesRam = 1 + (int) (Math.random() * ((12 - 1) + 1));
            int añoRam = 1900 + (int) (Math.random() * ((2100 - 1900) + 1));
            if (mesRam == 2) {
                if (diaRam == 29 || diaRam == 30) {
                    diaRam = 28;
                }
            }
            String fechaRam = (añoRam + "-" + mesRam + "-" + diaRam);
            st.executeUpdate("INSERT INTO Usuarios ("
                    + "NombreUser, "
                    + "ApePatUser, "
                    + "ApeMatUser, "
                    + "Sexo, "
                    + "Pais, "
                    + "Ciudad, "
                    + "CP, "
                    + "FechaNac)"
                    + "VALUES ("
                    + "'" + nombres[nombresRam]
                    + "', '" + apellidos[apellidosPRam]
                    + "', '" + apellidos[apellidosMRam]
                    + "', '" + sexo[x]
                    + "', '" + paises[ciudad_paisesRam]
                    + "', '" + ciudades[ciudad_paisesRam]
                    + "', '" + cp[CPRam]
                    + "', '" + fechaRam
                    + "' )");
        }
        System.out.println("-Añadir registros a la tabla - Ok");

    }

    static void consulta(Statement st, String ConsultaUser) throws SQLException {

        ResultSet rs = st.executeQuery(ConsultaUser);

        for (int x = 1; x <= rs.getMetaData().getColumnCount(); x++) {
            System.out.print(rs.getMetaData().getColumnName(x) + "\t");
        }

        System.out.println("\n");

        while (rs.next()) {
            for (int x = 1; x <= rs.getMetaData().getColumnCount(); x++) {
                System.out.print(rs.getString(x) + "\t");
            }
            System.out.println("");
        }

    }

    static void update(Statement st) throws SQLException {
        // Update de Datos
        ResultSet rsUpdate = st.executeQuery("SELECT nombreuser,Pais FROM usuarios WHERE NombreUser = 'Luis' ");
        rsUpdate.next();
        int idU = rsUpdate.getInt("id");
        st.executeUpdate("UPDATE usuarios SET pais='Mexico' WHERE id=" + idU);

    }

    static void borrarregisto(Statement st) throws SQLException {
        // Borrar resgitro
        ResultSet rsBorrar = st.executeQuery("SELECT id FROM Usuarios WHERE nombre='Manuel'");
        rsBorrar.next();
        int idB = rsBorrar.getInt("id");
        st.executeUpdate("DELETE FROM Usuarios WHERE id=" + idB);

    }

    static void borrartabla(Statement st) throws SQLException {
        // Borrar tabla 
        System.out.println("Borrando la tabla Usuarios.....");
        st.executeUpdate("DROP TABLE usuarios");
        System.out.println("Hecho!");
        
    }

}

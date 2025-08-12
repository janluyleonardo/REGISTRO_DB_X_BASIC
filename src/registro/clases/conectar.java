/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package registro.clases;

import java.sql.Connection;
import java.sql.DriverManager;

public class conectar {

    Connection conectar = null;

    public Connection conexion() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conectar = DriverManager.getConnection("jdbc:mysql://localhost/curricolum", "MORJAN", "Moreno.880501");
        } catch (Exception e) {
            System.out.print(e.getMessage());
        }
        return conectar;
    }

}

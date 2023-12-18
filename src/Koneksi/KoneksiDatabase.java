/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Koneksi;

/**
 *
 * @author Aulia Syamsul
 */
import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class KoneksiDatabase {
     Connection connection;
    Statement statement;
    String SQL;
    public Connection koneksiDatabase() {
        String url = "jdbc:mysql://localhost:3308/antrianktp";
        String username = "root";
        String password = "";
        try {
            // Register JDBC driver
              Class.forName("com.mysql.cj.jdbc.Driver");
            // Open a connection
            connection = DriverManager.getConnection(url, username, password);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return connection;
    }
 public Connection closeKoneksi() {

        try {
            connection.close();
        } catch (Exception e) {
        }
        return connection;
    }

    public ResultSet eksekusiQuery(String sql) {
        koneksiDatabase();
        ResultSet resultSet = null;

        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);
            System.out.println(sql);
        } catch (SQLException ex) {
        }

        return resultSet;

    }

    public String eksekusiUpdate(String sql) {

        koneksiDatabase();
        String result = "";

        try {
            statement = connection.createStatement();
            statement.executeUpdate(sql);
            System.out.println(sql);
        } catch (SQLException ex) {
            result = ex.toString();
        }

        return result;

    }

    // Fungsi untuk eksekusi query select semua kolom
    public ResultSet querySelectAll(String namaTabel) {

        SQL = "SELECT * FROM " + namaTabel;
        return this.eksekusiQuery(SQL);

    }

    // Fungsi eksekusi query insert

    public String queryInsert(String namaTabel, String[] namaKolom, String[] isiTabel) {

        int i;
        SQL = "INSERT INTO " + namaTabel + "(";
        for (i = 0; i <= namaKolom.length - 1; i++) {
            SQL += namaKolom[i];
            if (i < namaKolom.length - 1) {
                SQL += ",";
            }
        }
        SQL += ") VALUES(";
        for (i = 0; i <= isiTabel.length - 1; i++) {
            SQL += "'" + isiTabel[i] + "'";
            if (i < isiTabel.length - 1) {
                SQL += ",";
            }
        }

        SQL += ")";
        return this.eksekusiUpdate(SQL);

    }
 public ResultSet displayalldata(){
     ResultSet resultSet = querySelectAll("antrian");
        try {
            while (resultSet.next()) {
                System.out.println(resultSet.getInt(1) + " " + resultSet.getString(2) + " " + resultSet.getString(3));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultSet;
    }
    public static void main(String[] args) {
KoneksiDatabase con = new KoneksiDatabase();
con.koneksiDatabase();
con.displayalldata();

     
    }
}

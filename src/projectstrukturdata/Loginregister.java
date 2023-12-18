/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projectstrukturdata;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Aulia Syamsul
 */
import Koneksi.KoneksiDatabase;

public class Loginregister {
    KoneksiDatabase data = new KoneksiDatabase();

    public Loginregister() {
        data.koneksiDatabase();
    }

    public boolean login(String username, String password) {
        String query = "SELECT * FROM user WHERE username = '" + username + "' AND password = '" + password + "'";
        ResultSet resultSet = data.eksekusiQuery(query);
        try {
            if (resultSet.next()) {
                return true; // Autentikasi berhasil
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false; // Autentikasi gagal
    }

    public void register(String username, String password,String email) {
        String[] namaKolom = { "username", "password","email" };
        String[] isiTabel = { username, password,email };
        data.queryInsert("user", namaKolom, isiTabel);
    }
}

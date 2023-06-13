/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;

/**
 *
 * @author Asus
 */
public class ResetDAO {
    public void resetDatabase() throws Exception {
        try {
            DBContext db = new DBContext();
            try (Connection con = db.getConnection()) {
                String sql = "{call reset_database}";
                try (CallableStatement st = con.prepareCall(sql)) {
                    if (st.executeUpdate() != 1){
                        System.out.println("ERROR RESETING");
                    }
                }
            }
        } catch (SQLException e) {
            System.out.println("Error");
        }
    }
}

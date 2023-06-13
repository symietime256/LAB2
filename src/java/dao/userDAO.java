/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import model.User;
import model.News;

/**
 *
 * @author Inspiron
 */
public class userDAO {
    
    private static final String ID_ADMIN = "id_Admin";
    private static final String GENDER = "Gender";
    private static final String USER_NAME = "User_name";
    private static final String PASSWORD = "PASSWORD";
    private static final String USERNAME = "Username";

    public boolean InsertUser(User user) {
        try {
            DBContext db = new DBContext();
            Connection con = db.getConnection();
            if (con != null) {
                String sql = "{call insertUser(?,?,?,?,?,?,?)}";
                try (CallableStatement call = con.prepareCall(sql)) {
                    call.setString(2, user.getPass());
                    call.setNString(3, user.getName());
                    call.setString(4, user.getUname());
                    call.setInt(5, 0);
                    call.setString(6, user.getGender());
                    DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
                    String strDate = df.format(user.getDob());
                    call.setString(7, strDate);
                    call.registerOutParameter(1, java.sql.Types.INTEGER);
                    call.executeUpdate();
                    if (call.getInt(1) == 0) {
                        throw new Exception();
                    }
                }
                con.close();
                return true;
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    public User Login(String username, String pass) {
        User new_user = new User();
        try {
            DBContext db = new DBContext();
            Connection con = db.getConnection();
            if (con != null) {
                String sql = "Select * from UserS where "+USERNAME+" = " + "'" + username + "'" + "AND PASSWORD = " + "'" + pass + "'";
                try (Statement call = con.createStatement()) {
                    ResultSet rs = call.executeQuery(sql);
                    while (rs.next()) {             //needed even if just 1 row
                        boolean isAdmin = false;
                        if (rs.getInt(ID_ADMIN) == 1) {
                            isAdmin = true;
                        }
                        new_user = new User(rs.getInt("User_id"), rs.getString(PASSWORD), rs.getNString(USER_NAME), rs.getString(USERNAME), rs.getNString(GENDER), isAdmin, rs.getDate("dob"));
                    }
                }
                con.close();
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return new_user;
    }

    public boolean CheckDuplicate(String username) {
        try {
            DBContext db = new DBContext();
            Connection con = db.getConnection();
            if (con != null) {
                String sql = "{call checkDuplicate(?,?)}";
                try (CallableStatement call = con.prepareCall(sql)) {
                    call.setNString(1, username);
                    call.registerOutParameter(2, java.sql.Types.INTEGER);
                    call.executeUpdate();
                    if (call.getInt(2) > 0) {
                        throw new Exception();
                    }
                }
                con.close();
                return false;
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return true;
    }

    public User getUser(int id) {
        User new_user = new User();
        try {
            DBContext db = new DBContext();
            Connection con = db.getConnection();
            if (con != null) {
                String sql = "Select * from UserS where User_id =" + id;
                try (Statement call = con.createStatement()) {
                    ResultSet rs = call.executeQuery(sql);
                    while (rs.next()) {             //needed even if just 1 row
                        boolean isAdmin = false;
                        if (rs.getInt(ID_ADMIN) == 1) {
                            isAdmin = true;
                        }
                        new_user = new User(id, rs.getString(PASSWORD), rs.getNString(USER_NAME), rs.getString(USERNAME), rs.getNString("Gender"), isAdmin, rs.getDate("dob"));
                    }
                }
                con.close();
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return new_user;
    }

    public HashMap<Integer, User> getAllUser() {
        HashMap<Integer, User> list = new HashMap<>();
        try {
            DBContext db = new DBContext();
            Connection con = db.getConnection();
            if (con != null) {
                String sql = "Select * from UserS";
                try (Statement call = con.createStatement()) {
                    ResultSet rs = call.executeQuery(sql);
                    while (rs.next()) {             //needed even if just 1 row
                        boolean isAdmin = false;
                        if (rs.getInt(ID_ADMIN) == 1) {
                            isAdmin = true;
                        }
                        User new_user = new User(rs.getInt("User_id"), rs.getString(PASSWORD), rs.getNString(USER_NAME), rs.getString(USERNAME), rs.getNString(GENDER), isAdmin, rs.getDate("dob"));
                        list.put(new_user.getId(), new_user);
                    }
                }
                con.close();
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return list;
    }

    public boolean delUser(int id) {
        try {
            DBContext db = new DBContext();
            Connection con = db.getConnection();
            if (con != null) {
                String sql = "{call delUser(?)}";
                try (CallableStatement call = con.prepareCall(sql)) {
                    call.setInt(1, id);
                    if (call.executeUpdate() <= 0) {
                        throw new Exception();
                    }
                }
                con.close();
                return true;
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    //trong servlet, thg nao de la null thi set thuoc tinh doi tg gui vao nhu cu
    public boolean updateUser(User user) {//nut bam
        try {
            DBContext db = new DBContext();
            Connection con = db.getConnection();
            if (con != null) {
                String sql = "{call updateUser(?,?,?,?,?,?)}";
                try (CallableStatement call = con.prepareCall(sql)) {
                    call.setInt(1, user.getId());
                    call.setString(2, user.getPass());
                    call.setNString(3, user.getName());
                    call.setString(4, user.getUname());
                    call.setNString(5, user.getGender());
                    DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
                    String strDate = df.format(user.getDob());
                    call.setString(6, strDate);
                    if (call.executeUpdate() <= 0) {
                        throw new Exception();
                    }
                }
                con.close();
                return true;
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    public ArrayList<News> GetAllAdminNews(int admin) {
        ArrayList<News> list = new ArrayList<>();
        try {
            DBContext db = new DBContext();
            Connection con = db.getConnection();
            if (con != null) {
                String sql = "SELECT * FROM News n "
                        + " WHERE User_id =" + admin
                        + " ORDER BY News_id desc ;";
                try (Statement call = con.createStatement()) {
                    ResultSet rs = call.executeQuery(sql);
                    while (rs.next()) {             //needed even if just 1 row
                        News news = new News(rs.getInt("News_id"), admin, rs.getInt("Cat_id"), rs.getString("News_title"), rs.getString("News_subtitle"), rs.getString("News_content"), rs.getString("News_image"));
                        list.add(news);
                    }
                }
                con.close();
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return list;
    }

    public static void main(String[] args) {
        userDAO dao = new userDAO();
        ArrayList<News> list = dao.GetAllAdminNews(3);
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i).getImage());
        }
    }
}


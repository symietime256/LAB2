/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import model.News;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author Asus
 */
public class SaveDAO {
    
    private static final String ERROR_NOTIFICATION = "Error";
    
    public static ArrayList<News> getSaveNews(int user_id) throws Exception {
        ArrayList<News> listNews = new ArrayList<>();
        News news;
        try {
            DBContext db = new DBContext();
            try (Connection con = db.getConnection(); Statement st = con.createStatement()) {
                String sql = "SELECT * "
                        + " FROM News n,Category c, [Save] s "
                        + " WHERE n.Cat_id = c.Cat_id "
                        + "\tAND s.[User_id] = " + user_id
                        + "\tAND n.News_id = s.News_id "
                        + "ORDER BY s.Save_id desc;";
                ResultSet rs = st.executeQuery(sql);
                while (rs.next()) {
                    news = new News();
                    news.setUser_id(rs.getInt("User_id"));
                    news.setNews_id(rs.getInt("News_id"));
                    news.setCat_id(rs.getInt("Cat_id"));
                    news.setTitle(rs.getString("News_title"));
                    news.setSubtitle(rs.getString("News_subtitle"));
                    news.setContent(rs.getString("News_content"));
                    news.setImage(rs.getString("News_image"));
                    listNews.add(news);
                }
            }
        } catch (SQLException e) {
            
        }
        return listNews;
    }
    
    
    public static void insertSaveNews(int user_id, int news_id) throws Exception {
        try {
            DBContext db = new DBContext();
            try (Connection con = db.getConnection()) {
                String sql = "{call insert_save(?,?)}";
                //truyen tham so
                try (CallableStatement st = con.prepareCall(sql)) {
                    //truyen tham so
                    st.setInt(1, user_id);
                    st.setInt(2, news_id);
                    
                    if (st.executeUpdate() != 1) {
                        System.out.println("ERROR INSERTING SAVE NEWS");
                    }
                }
            }
        } catch (SQLException e) {
            System.out.println(ERROR_NOTIFICATION);
        }
    }
    
    

    public static void deleteSaveNews(int save_id) throws Exception {
        try {
            DBContext db = new DBContext();
            try (Connection con = db.getConnection()) {
                String sql = "{call delete_save(?)}";
                //truyen tham so
                try (CallableStatement st = con.prepareCall(sql)) {
                    //truyen tham so
                    st.setInt(1, save_id);
                    if (st.executeUpdate() != 1) {
                        System.out.println("ERROR DELETING SAVE NEWS");
                    }
                }
            }
        } catch (SQLException e) {
            System.out.println(ERROR_NOTIFICATION);
        }
    }
    
    

    public static int findSaveId(int user_id, int news_id) throws Exception {
        int save_id = 0;
        try {
            save_id = 0;
            DBContext db = new DBContext();
            try (Connection con = db.getConnection(); Statement st = con.createStatement()) {
                String sql = " SELECT * "
                        + " FROM [Save] "
                        + " WHERE [User_id] = " + user_id
                        + "\tAND News_id = " + news_id;
                ResultSet rs = st.executeQuery(sql);
                rs.next();
                save_id = rs.getInt("Save_id");
                
            }
        } catch (SQLException e) {
            System.out.println(ERROR_NOTIFICATION);
        }
        return save_id;
    }



}

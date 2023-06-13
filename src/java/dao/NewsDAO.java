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
import java.util.List;

/**
 *
 * @author Asus
 */
public class NewsDAO {
    
    private static final String USER_ID = "User_id";
    private static final String NEWS_ID = "News_id";
    private static final String CAT_ID = "Cat_id";
    private static final String NEWS_TITLE = "News_title";
    private static final String NEWS_CONTENT = "News_content";
    private static final String NEWS_SUBTITLE = "News_subtitle";
    private static final String NEWS_IMAGE = "News_image";
    private static final String SELECT_COM = "Select * ";
    private static final String ERROR_MESSAGE = "Error";
    
    
    public ArrayList<News> getAllNews() {
        ArrayList<News> list = new ArrayList<>();
        News news;
        try {
            DBContext db = new DBContext();
            Connection con = db.getConnection();
            if (con != null) {
                String sql = "Select * from News "
                        + "ORDER BY News_id desc ;";
                try (Statement call = con.createStatement()) {
                    ResultSet rs = call.executeQuery(sql);
                    while (rs.next()) {             //needed even if just 1 row
                        news = new News();
                        news.setUser_id(rs.getInt(USER_ID));
                        news.setNews_id(rs.getInt(NEWS_ID));
                        news.setCat_id(rs.getInt(CAT_ID));
                        news.setTitle(rs.getString(NEWS_TITLE));
                        news.setSubtitle(rs.getString(NEWS_SUBTITLE));
                        news.setImage(rs.getString(NEWS_IMAGE));
                        list.add(news);
                    }
                }
                con.close();
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return list;
    }


    public News getNews(int news_id) {//get all content in a news given news id
        News news = null;
        try {
            DBContext db = new DBContext();
            try (Connection con = db.getConnection(); Statement st = con.createStatement()) {
                String sql = " SELECT * "
                        + " FROM News n, Category c "
                        + " WHERE n.Cat_id = c.Cat_id "
                        + " AND n.News_id =  " + news_id + ";";
                ResultSet rs = st.executeQuery(sql);
                
                rs.next();
                news = new News();
                news.setUser_id(rs.getInt(USER_ID));
                news.setNews_id(rs.getInt(NEWS_ID));
                news.setCat_id(rs.getInt(CAT_ID));
                news.setContent(NEWS_CONTENT);
                news.setTitle(rs.getString(NEWS_TITLE));
                news.setSubtitle(rs.getString(NEWS_SUBTITLE));
                news.setImage(rs.getString(NEWS_IMAGE));
                
            }
        } catch (Exception e) {
            System.out.println(ERROR_MESSAGE);
        }
        return news;
    }

    public void insertNews(News news) {//inserting news
        try {
            DBContext db = new DBContext();
            try (Connection con = db.getConnection()) {
                String sql = "{call insert_news(?,?,?,?,?,?)}";
                //truyen tham so
                try (CallableStatement st = con.prepareCall(sql)) {
                    //truyen tham so
                    st.setInt(1, news.getUser_id());
                    st.setInt(2, news.getCat_id());
                    st.setString(3, news.getTitle());
                    st.setString(4, news.getSubtitle());
                    st.setString(5, news.getContent());
                    st.setString(6, news.getImage());
                    
                    if (st.executeUpdate() != 1) {
                        System.out.println("ERROR INSERTING NEWS");
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("amgous");
        }
    }

    public void deleteNews(int news_id) throws Exception {
        try {
            DBContext db = new DBContext();
            try (Connection con = db.getConnection()) {
                String sql = "{call delete_news(?)}";
                //truyen tham so
                try (CallableStatement st = con.prepareCall(sql)) {
                    //truyen tham so
                    st.setInt(1, news_id);
                    if (st.executeUpdate() != 1) {
                        System.out.println("ERROR DELETING NEWS");
                    }
                }
            }
        } catch (SQLException e) {
            System.out.println(ERROR_MESSAGE);
        }
    }

    public void updateNews(News news) throws Exception {
        try {
            DBContext db = new DBContext();
            try (Connection con = db.getConnection()) {
                String sql = "{call update_news(?,?,?,?,?,?)}";
                //truyen tham so
                try (CallableStatement st = con.prepareCall(sql)) {
                    //truyen tham so
                    st.setInt(1, news.getNews_id());
                    st.setInt(2, news.getCat_id());
                    st.setString(3, news.getTitle());
                    st.setString(4, news.getSubtitle());
                    st.setString(5, news.getContent());
                    st.setString(6, news.getImage());
                    
                    if (st.executeUpdate() != 1) {
                        System.out.println("ERROR UPDATING NEWS");
                    }
                }
            }
        } catch (SQLException e) {
            System.out.println(ERROR_MESSAGE);
        }
    }

    public ArrayList<News> searchTitle(String title) throws Exception {
        ArrayList<News> listNews = new ArrayList<>();
        News news = new News();
        try {
            DBContext db = new DBContext();
            try (Connection con = db.getConnection(); Statement st = con.createStatement()) {
                String sql = SELECT_COM
                        + "FROM News  "
                        + "WHERE News_title LIKE N'%" + title + "%' ;";
                ResultSet rs = st.executeQuery(sql);
                while (rs.next()) {
                    news = new News();
                    news.setUser_id(rs.getInt(USER_ID));
                    news.setNews_id(rs.getInt(NEWS_ID));
                    news.setCat_id(rs.getInt(CAT_ID));
                    news.setContent(NEWS_CONTENT);
                    news.setTitle(rs.getString(NEWS_TITLE));
                    news.setSubtitle(rs.getString(NEWS_SUBTITLE));
                    news.setImage(rs.getString(NEWS_IMAGE));
                    listNews.add(news);
                }
            }
        } catch (SQLException e) {

        }
        return listNews;
    }

    public ArrayList<News> searchCategory(int cat_id) {
        ArrayList<News> listNews = new ArrayList<>();
        News news = new News();
        try {
            DBContext db = new DBContext();
            try (Connection con = db.getConnection(); Statement st = con.createStatement()) {
                String sql = "SELECT * "
                        + "FROM News "
                        + "WHERE Cat_id = " + cat_id;
                ResultSet rs = st.executeQuery(sql);
                while (rs.next()) {
                    news = new News();
                    news.setUser_id(rs.getInt(USER_ID));
                    news.setNews_id(rs.getInt(NEWS_ID));
                    news.setCat_id(rs.getInt(CAT_ID));
                    news.setContent(NEWS_CONTENT);
                    news.setTitle(rs.getString(NEWS_TITLE));
                    news.setSubtitle(rs.getString(NEWS_SUBTITLE));
                    news.setImage(rs.getString(NEWS_IMAGE));
                    listNews.add(news);
                }
            }
        } catch (Exception e) {

        }
        return listNews;
    }

    public ArrayList<News> searchTitleCat(String title, int cat_id) throws Exception {
        ArrayList<News> listNews = new ArrayList<>();
        News news = new News();
        try {
            DBContext db = new DBContext();
            try (Connection con = db.getConnection(); Statement st = con.createStatement()) {
                String sql = "SELECT * "
                        + "FROM News c "
                        + "WHERE News_title LIKE N'%" + title + "%' AND Cat_id = " + cat_id;
                ResultSet rs = st.executeQuery(sql);
                while (rs.next()) {
                    news = new News();
                    news.setUser_id(rs.getInt(USER_ID));
                    news.setNews_id(rs.getInt(NEWS_ID));
                    news.setCat_id(rs.getInt(CAT_ID));
                    news.setContent(NEWS_CONTENT);
                    news.setTitle(rs.getString(NEWS_TITLE));
                    news.setSubtitle(rs.getString(NEWS_SUBTITLE));
                    news.setImage(rs.getString(NEWS_IMAGE));
                    listNews.add(news);
                }
            }
        } catch (SQLException e) {

        }
        return listNews;
    }

    public int getLatestId() {
        int latest_id = 0;
        try {
            DBContext db = new DBContext();
            try (Connection con = db.getConnection(); Statement st = con.createStatement()) {
                String sql = "SELECT "
                        + "  CASE "
                        + "    WHEN (SELECT "
                        + "        COUNT(1) "
                        + "      FROM News ) = 0 THEN 1 "
                        + "    ELSE IDENT_CURRENT('News') "
                        + "  END AS latest_id;";
                ResultSet rs = st.executeQuery(sql);
                rs.next();
                latest_id = rs.getInt("latest_id");
                
            }
        } catch (Exception e) {
            System.out.println("amogus");
        }
        return latest_id;
    }


}
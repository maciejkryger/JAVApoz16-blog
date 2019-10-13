package pl.sda.dao;


import pl.sda.model.User;
import pl.sda.util.GenerateToken;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDaoImpl implements UserDao {

    private User user;
    private Long id;
    private String login;
    private String password;


    public UserDaoImpl() {
    }

    @Override
    public User getUser(String username) {

        Context ctx = null;
        Connection con = null;
        ResultSet rs = null;
        PreparedStatement stmt = null;

        try {
            ctx = new InitialContext();
            DataSource ds = (DataSource) ctx.lookup("java:/comp/env/jdbc/MyBlogDB");

            con = ds.getConnection();

            stmt = con.prepareStatement("SELECT * FROM users WHERE username = ?");
            stmt.setString(1, username);

            rs = stmt.executeQuery();

            if (rs.next()) {
                Long id = rs.getLong("id");
                String userName = rs.getString("username");
                String password = rs.getString("password");

                return new User(id, userName, password);
            }

        } catch (NamingException | SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (stmt != null) {
                    stmt.close();
                }
                if (con != null) {
                    con.close();
                }
                if (ctx != null) {
                    ctx.close();
                }
            } catch (SQLException e) {
                System.out.println("Exception in closing DB resources");
            } catch (NamingException e) {
                System.out.println("Exception in closing Context");
            }
        }
        return null;
    }

    @Override
    public String addUser(String username, String password) {

        System.out.println("addUser method in DAO is trying to add item in database");
        String newToken = new GenerateToken().startGenerateToken();
        System.out.println(newToken);
        String insertSQL = "INSERT INTO users (username, password, token) VALUES(?,?,?)";

        Context ctx = null;
        Connection con = null;
        ResultSet rs = null;
        PreparedStatement stmt = null;

        try {
            ctx = new InitialContext();
            DataSource ds = (DataSource) ctx.lookup("java:/comp/env/jdbc/MyBlogDB");

            con = ds.getConnection();

            stmt = con.prepareStatement(insertSQL);
            stmt.setString(1, username);
            stmt.setString(2, password);
            stmt.setString(3, newToken);
            stmt.executeUpdate();

        } catch (NamingException | SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (stmt != null) {
                    stmt.close();
                }
                if (con != null) {
                    con.close();
                }
                if (ctx != null) {
                    ctx.close();
                }
            } catch (SQLException e) {
                System.out.println("Exception in closing DB resources");
            } catch (NamingException e) {
                System.out.println("Exception in closing Context");
            }
        }
        return null;
    }


}

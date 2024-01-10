package next.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import core.jdbc.ConnectionManager;
import next.model.User;

public class UserDao {
    Connection con = null;
    PreparedStatement pstmt = null;
    ResultSet rs = null;
    User user = null;
    public void insert(User user) throws SQLException {
        try {
            con = ConnectionManager.getConnection();
            String sql = "INSERT INTO USERS VALUES (?, ?, ?, ?)";
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, user.getUserId());
            pstmt.setString(2, user.getPassword());
            pstmt.setString(3, user.getName());
            pstmt.setString(4, user.getEmail());
            pstmt.executeUpdate();
        } finally {
            Close_All();
        }
    }


    public void update(User user) throws SQLException {
        // TODO 구현 필요함.
    }

    public List<User> findAll() throws SQLException {
        // TODO 구현 필요함.
        return new ArrayList<User>();
    }

    public User findByUserId(String userId) throws SQLException {
        try {
            con = ConnectionManager.getConnection();
            String sql = "SELECT userId, password, name, email FROM USERS WHERE userid=?";
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, userId);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                user = new User(rs.getString("userId"), rs.getString("password"), rs.getString("name"),
                        rs.getString("email"));
            }
            return user;
        } finally {
            Close_All();
        }
    }

    private void Close_All() throws SQLException {
        Close_pstmt();
        Close_con();
        Close_rs();
    }

    private void Close_con() throws SQLException {
        if (con != null) {
            con.close();
        }
    }

    private void Close_pstmt() throws SQLException {
        if (pstmt != null) {
            pstmt.close();
        }
    }
    private void Close_rs() throws SQLException {
        if (rs != null) {
            rs.close();
        }
    }
}

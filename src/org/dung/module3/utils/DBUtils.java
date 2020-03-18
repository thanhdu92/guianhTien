package org.dung.module3.utils;

import org.dung.module3.models.Product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DBUtils {
//    public static UserAccount findUser(Connection conn, //
//                                       String userName, String password) throws SQLException {
//
//        String sql = "Select a.User_Name, a.Password, a.Gender from User_Account a " //
//                + " where a.User_Name = ? and a.password= ?";
//
//        PreparedStatement pstm = conn.prepareStatement(sql);
//        pstm.setString(1, userName);
//        pstm.setString(2, password);
//        ResultSet rs = pstm.executeQuery();
//
//        if (rs.next()) {
//            String gender = rs.getString("Gender");
//            UserAccount user = new UserAccount();
//            user.setUserName(userName);
//            user.setPassword(password);
//            user.setGender(gender);
//            return user;
//        }
//        return null;
//    }
//
//    public static UserAccount findUser(Connection conn, String userName) throws SQLException {
//
//        String sql = "Select a.User_Name, a.Password, a.Gender from User_Account a "//
//                + " where a.User_Name = ? ";
//
//        PreparedStatement pstm = conn.prepareStatement(sql);
//        pstm.setString(1, userName);
//
//        ResultSet rs = pstm.executeQuery();
//
//        if (rs.next()) {
//            String password = rs.getString("Password");
//            String gender = rs.getString("Gender");
//            UserAccount user = new UserAccount();
//            user.setUserName(userName);
//            user.setPassword(password);
//            user.setGender(gender);
//            return user;
//        }
//        return null;
//    }

    public static List<Product> queryProduct(Connection conn) throws SQLException {
        String sql = "Select a.Productname, a.Price, a.Quantity, a.color, a.category from Product a ";

        PreparedStatement pstm = conn.prepareStatement(sql);

        ResultSet rs = pstm.executeQuery();
        List<Product> list = new ArrayList<Product>();
        while (rs.next()) {
            String productname = rs.getString("ProductName");
            Integer price = rs.getInt("Price");
            Integer quantity = rs.getInt("Quantity");
            String color = rs.getString("Color");
            String category = rs.getString("Category");
            Product product = new Product();
            product.setProductname(productname);
            product.setPrice(price);
            product.setQuantity(quantity);
            product.setColor(color);
            product.setCategory(category);
            list.add(product);
        }
        return list;
    }

    public static Product findProduct(Connection conn, String productname) throws SQLException {
        String sql = "Select a.Productname, a.Price, a.Quantity, a.color, a.category from Product a where a.Productname=?";

        PreparedStatement pstm = conn.prepareStatement(sql);
        pstm.setString(1, productname);

        ResultSet rs = pstm.executeQuery();

        while (rs.next()) {
            Integer price = rs.getInt("Price");
            Integer quantity = rs.getInt("Quantity");
            String color = rs.getString("Color");
            String category = rs.getString("Category");
            Product product = new Product(productname, price, quantity, color, category);
            return product;
        }
        return null;
    }

    public static void updateProduct(Connection conn, Product product) throws SQLException {
        String sql = "Update Product set Price =?, Quantity=? ,Color=? ,Category=? where Productname=? ";

        PreparedStatement pstm = conn.prepareStatement(sql);

        pstm.setString(1, product.getProductname());
        pstm.setInt(2, product.getPrice());
        pstm.setInt(3, product.getQuantity());
        pstm.setString(4, product.getColor());
        pstm.setString(5, product.getCategory());
        pstm.executeUpdate();
    }

    public static void insertProduct(Connection conn, Product product) throws SQLException {
        String sql = "Insert into Product(productname,price,quantity,color,category) values (?,?,?,?,?)";

        PreparedStatement pstm = conn.prepareStatement(sql);

        pstm.setString(1, product.getProductname());
        pstm.setInt(2, product.getPrice());
        pstm.setInt(3, product.getQuantity());
        pstm.setString(4, product.getColor());
        pstm.setString(5, product.getCategory());

        pstm.executeUpdate();
    }

    public static void deleteProduct(Connection conn, String productname) throws SQLException {
        String sql = "Delete From Product where Productname= ?";

        PreparedStatement pstm = conn.prepareStatement(sql);

        pstm.setString(1, productname);

        pstm.executeUpdate();
    }
}

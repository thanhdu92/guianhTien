package org.dung.module3.servlet;

import org.dung.module3.models.Product;
import org.dung.module3.utils.DBUtils;
import org.dung.module3.utils.MyUtils;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

@WebServlet(urlPatterns = { "/editProduct" })
public class EditProductServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public EditProductServlet() {
        super();
    }

    // Hiển thị trang sửa sản phẩm.
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Connection conn = MyUtils.getStoredConnection(request);

        String productname= (String) request.getParameter("productname");

        Product product = null;

        String errorString = null;

        try {
            product = DBUtils.findProduct(conn, productname);
        } catch (SQLException e) {
            e.printStackTrace();
            errorString = e.getMessage();
        }

        // Không có lỗi.
        // Sản phẩm không tồn tại để edit.
        // Redirect sang trang danh sách sản phẩm.
        if (errorString != null && product == null) {
            response.sendRedirect(request.getServletPath() + "/productList");
            return;
        }

        // Lưu thông tin vào request attribute trước khi forward sang views.
        request.setAttribute("errorString", errorString);
        request.setAttribute("product", product);

        RequestDispatcher dispatcher = request.getServletContext()
                .getRequestDispatcher("/WEB-INF/views/editProductView.jsp");
        dispatcher.forward(request, response);

    }

    // Sau khi người dùng sửa đổi thông tin sản phẩm, và nhấn Submit.
    // Phương thức này sẽ được thực thi.
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Connection conn = MyUtils.getStoredConnection(request);

        String productname = (String) request.getParameter("productname");
        String priceStr = (String) request.getParameter("price");
        String quantityStr = (String) request.getParameter("quantity");
        String color = (String) request.getParameter("color");
        String category = (String) request.getParameter("category");
        float price = 0;
        try {
            price = Float.parseFloat(priceStr);
        } catch (Exception e) {
        }

        float quantity = 0;
        try {
            price = Float.parseFloat(quantityStr);
        } catch (Exception e) {
        }

        Product product = new Product(productname, price, quantity, color,category);

        String errorString = null;

        try {
            DBUtils.updateProduct(conn, product);
        } catch (SQLException e) {
            e.printStackTrace();
            errorString = e.getMessage();
        }
        // Lưu thông tin vào request attribute trước khi forward sang views.
        request.setAttribute("errorString", errorString);
        request.setAttribute("product", product);

        // Nếu có lỗi forward sang trang edit.
        if (errorString != null) {
            RequestDispatcher dispatcher = request.getServletContext()
                    .getRequestDispatcher("/WEB-INF/views/editProductView.jsp");
            dispatcher.forward(request, response);
        }
        // Nếu mọi thứ tốt đẹp.
        // Redirect sang trang danh sách sản phẩm.
        else {
            response.sendRedirect(request.getContextPath() + "/productList");
        }
    }

}

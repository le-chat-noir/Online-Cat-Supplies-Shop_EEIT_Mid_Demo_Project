package _01_.account.register.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/CheckAccountAvailability")
public class CheckAccountAvailabilityServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			response.setContentType("text/html;charset=UTF-8");
			String account = request.getParameter("account");
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			String connUrl = "jdbc:sqlserver://localhost:1433;databaseName=LeonPower";
			Connection conn = DriverManager.getConnection(connUrl, "sa", "");	// SQL Password Here
			
			String qryStmt = "SELECT account FROM userProfiles WHERE account = ?";
			PreparedStatement stmt = conn.prepareStatement(qryStmt);
			stmt.setString(1, account);
			ResultSet rs = stmt.executeQuery();
			
			boolean isAccOk = true;
			if (rs.next()) {
				isAccOk = false;
			}
			response.getWriter().print(isAccOk);
			conn.close();
		} catch (Exception e) {
			System.out.println("CheckAccountAvailabilityServlet Error");
			response.getWriter().print("");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}

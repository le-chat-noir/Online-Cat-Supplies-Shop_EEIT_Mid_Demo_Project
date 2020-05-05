package _06_.product.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import _06_.product.model.ProductService;
import utility.HibernateUtility;

@WebServlet("/BackEnd/DeleteProduct")
public class DeleteProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		processAction(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		processAction(request, response);
	}

	private void processAction(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("hello");
		int pid = Integer.parseInt(request.getParameter("pid").substring(3));
		ProductService pService = new ProductService(HibernateUtility.getSessionFactory().getCurrentSession());
		pService.deleteproduct(pid);
		
	}

}

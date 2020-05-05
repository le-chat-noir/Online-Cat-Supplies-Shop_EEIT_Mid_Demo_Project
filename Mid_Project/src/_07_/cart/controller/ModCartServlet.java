package _07_.cart.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Servlet implementation class ModCartServlet
 */
@WebServlet("/ModCart")
public class ModCartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processAction(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processAction(request, response);
	}

	private void processAction(HttpServletRequest request, HttpServletResponse response) {
		int pid = Integer.parseInt(request.getParameter("pid"));
		int quantity = Integer.parseInt(request.getParameter("quantity"));
		HttpSession session = request.getSession();
		int loc = -1;
		Cookie[] cookiesFromClient = request.getCookies();
		if (session.getAttribute("name") != null) {
			String user = (String) session.getAttribute("name");
			for (int i = 0; i < cookiesFromClient.length; i++) {
				if (cookiesFromClient[i].getName().equals(user + "Cart")) {
					loc = i;
				}
			}
		} else {
			for (int i = 0; i < cookiesFromClient.length; i++) {
				if (cookiesFromClient[i].getName().equals("Cart")) {
					loc = i;
				}
			}
		}
		
		String cartStr = cookiesFromClient[loc].getValue();

		JSONArray cartArray = new JSONArray(cartStr);
		for (int i = 0; i < cartArray.length(); i++) {
			JSONObject jobj = cartArray.getJSONObject(i);
			if (jobj.getInt("pid") == pid) {
				jobj.put("quantity", quantity);
				break;
			}
		}

		cartStr = cartArray.toString();
		System.out.println(cartStr);
		if (session.getAttribute("name") != null) {
			String user = (String) session.getAttribute("name");
			Cookie cookiesToClient = new Cookie(user+"Cart", cartStr);
			cookiesToClient.setMaxAge(86400);
			response.addCookie(cookiesToClient);
		}else {
			Cookie cookiesToClient = new Cookie("Cart", cartStr);
			cookiesToClient.setMaxAge(86400);
			response.addCookie(cookiesToClient);
		}
	}

}

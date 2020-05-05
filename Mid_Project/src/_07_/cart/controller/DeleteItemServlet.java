package _07_.cart.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;


@WebServlet("/DeleteItem")
public class DeleteItemServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processAction(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processAction(request, response);
	}

	private void processAction(HttpServletRequest request, HttpServletResponse response) throws IOException {
		request.setCharacterEncoding("UTF-8");
		System.out.println("Hi");
		int pid = Integer.parseInt(request.getParameter("pid"));

		int loc = -1;
		Cookie[] cookiesFromClient = request.getCookies();
		for (int i = 0; i < cookiesFromClient.length; i++) {
			if (cookiesFromClient[i].getName().equals("Cart")) {
				loc = i;
			}
		}
		
		String cartStr = cookiesFromClient[loc].getValue();
		
		JSONArray cartArray = new JSONArray(cartStr);
		for (int i = 0; i < cartArray.length(); i++) {
			JSONObject jobj = cartArray.getJSONObject(i);
			if (jobj.getInt("pid") == pid) {
				cartArray.remove(i);
				break;
			}
		}

		cartStr = cartArray.toString();
		System.out.println(cartStr);
		Cookie cookiesToClient = new Cookie("Cart", cartStr);
		cookiesToClient.setMaxAge(86400);
		response.addCookie(cookiesToClient);
	}

}

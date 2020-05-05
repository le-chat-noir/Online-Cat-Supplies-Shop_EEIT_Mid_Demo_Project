package _07_.cart.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.hibernate.engine.transaction.jta.platform.internal.JBossAppServerJtaPlatform;
import org.json.JSONArray;
import org.json.JSONObject;

@WebServlet("/AddCart")
public class AddCartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		processAction(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		processAction(request, response);
	}

	private void processAction(HttpServletRequest request, HttpServletResponse response) throws IOException {
		request.setCharacterEncoding("UTF-8");

		int pid = Integer.parseInt(request.getParameter("pid"));
		HttpSession session = request.getSession();
		System.out.println(session.getAttribute("name"));
		
		int loc=-1;
		int userCookie = -1;
		Cookie[] cookiesFromClient = request.getCookies();
		
		if(session.getAttribute("name")!=null) {
			String user = (String) session.getAttribute("name");
			for (int i = 0; i < cookiesFromClient.length; i++) {
				if (cookiesFromClient[i].getName().equals(user+"Cart")) {
					loc = i;
					userCookie =1;
				}
			}
		}
		if(loc==-1) {
			for (int i = 0; i < cookiesFromClient.length; i++) {
				if (cookiesFromClient[i].getName().equals("Cart")) {
					loc = i;
				}
			}
		}
		
		System.out.println(loc);
		String cartStr="[]";
		if(loc!=-1) {
			cartStr = cookiesFromClient[loc].getValue();
		}
		System.out.println(cartStr);
		JSONArray cartArray = new JSONArray(cartStr);
		int added = 0;
		for (int i = 0; i < cartArray.length(); i++) {
			JSONObject jobj = cartArray.getJSONObject(i);
			if (jobj.getInt("pid") == pid) {
				int j = jobj.getInt("quantity")+1;
				jobj.put("quantity", j);
				added = 1;
				break;
			}
		}
		if(added==0) {
			JSONObject jobj = new JSONObject();
			jobj.put("pid", pid);
			jobj.put("quantity", 1);
			cartArray.put(jobj);
		}

		cartStr = cartArray.toString();
		System.out.println(cartStr);
		if(session.getAttribute("name")!=null&&userCookie==1) {
			String user = (String) session.getAttribute("name");
			Cookie cookiesToClient = new Cookie(user+"Cart", cartStr);
			cookiesToClient.setMaxAge(86400);
			response.addCookie(cookiesToClient);
		}else if (session.getAttribute("name")!=null&&userCookie==-1) {
			String user = (String) session.getAttribute("name");
			Cookie cookiesToClient = new Cookie(user+"Cart", cartStr);
			Cookie cookiesToDelete = new Cookie("Cart", "[]");
			cookiesToClient.setMaxAge(86400);
			cookiesToDelete.setMaxAge(0);
			response.addCookie(cookiesToClient);
			response.addCookie(cookiesToDelete);
		}else {
			Cookie cookiesToClient = new Cookie("Cart", cartStr);
			cookiesToClient.setMaxAge(86400);
			response.addCookie(cookiesToClient);
		}
	}
		

}

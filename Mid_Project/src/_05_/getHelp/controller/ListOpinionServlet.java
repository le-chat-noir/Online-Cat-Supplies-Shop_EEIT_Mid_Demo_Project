package _05_.getHelp.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;

import _05_.getHelp.model.OpinionBean;
import _05_.getHelp.model.OpinionService;
import utility.HibernateUtility;


@WebServlet("/ListOpinion")
public class ListOpinionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processAction(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processAction(request, response);
	}


	private void processAction(HttpServletRequest request, HttpServletResponse response) throws IOException {
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		OpinionService oService = new OpinionService(HibernateUtility.getSessionFactory().getCurrentSession());
		
		List<OpinionBean> oList = oService.getAllData();

		JSONArray jsonArray = new JSONArray();

		for (OpinionBean oBean : oList) {
			JSONObject jobj = new JSONObject();
			jobj.put("uid", oBean.getUid());
			jobj.put("userName", oBean.getUserName());
			jobj.put("userMail", oBean.getUserMail());
			jobj.put("opinionTitle", oBean.getOpinionTitle());
			jobj.put("opinionText", oBean.getOpinionText());
			jobj.put("solution", oBean.getSolution());
			jobj.put("caseStatus", oBean.getCaseStatus());
			jsonArray.put(jobj);
		}

		String jsonString = jsonArray.toString();
		System.out.println(jsonString);
		response.getWriter().write(jsonString);
		response.getWriter().flush();
	}

}

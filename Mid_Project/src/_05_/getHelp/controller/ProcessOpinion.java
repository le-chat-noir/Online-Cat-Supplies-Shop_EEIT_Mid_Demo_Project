package _05_.getHelp.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import _05_.getHelp.model.OpinionBean;
import _05_.getHelp.model.OpinionService;
import utility.HibernateUtility;


@WebServlet("/ProcessOpinion")
public class ProcessOpinion extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processAction(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processAction(request, response);
	}

	private void processAction(HttpServletRequest request, HttpServletResponse response) throws IOException   {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		HttpSession session = request.getSession();
		String name = request.getParameter("name");
		String email = request.getParameter("mail");
		String title = request.getParameter("title");
		String feedback = request.getParameter("feedback");
		
		OpinionBean oBean = new OpinionBean();
		oBean.setUserName(name);
		oBean.setOpinionText(feedback);
		if(session.getAttribute("uid")!=null) {
			oBean.setUid((int)session.getAttribute("uid"));
		}
		if(!email.equalsIgnoreCase("")) {
			oBean.setUserMail(email);
		}
		if(!title.equalsIgnoreCase("")) {
			oBean.setOpinionTitle(title);
		}
		OpinionService oService = new OpinionService(HibernateUtility.getSessionFactory().getCurrentSession());
		oService.insertData(oBean);
		
		String feedbackToSend = feedback.replaceAll("(\r\n|\n)", "<br />");
		SendGetHelpMail mailAction = new SendGetHelpMail();
		mailAction.SendFeedbackMail(name, email, title ,feedbackToSend);
	}

}

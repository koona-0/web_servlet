package notice;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class mailok extends HttpServlet {
	private static final long serialVersionUID = 1L;
	PrintWriter pw = null;
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		this.pw = response.getWriter();
		
		String to_name = request.getParameter("to_name");
		String to_mail = request.getParameter("to_mail");
		String subject = request.getParameter("subject");
		String context = request.getParameter("context");
		
		try {
			m_maildbinsert mi = new m_maildbinsert(to_name,to_mail,subject,context,request);
			String msg = mi.msg;	
			
			if(msg.equals("ok")) {
				this.pw.write("<script>"
						+ "alert('올바르게 DB에 저장되었습니다.');"
						+ "location.href = './send_mail.html';"
						+ "</script>");
			}else {
				this.pw.write("<script>"
						+ "alert('데이터베이스 오류 발생1');"
						+ "history.go(-1);"
						+ "</script>");
			} 
		}catch (Exception e) {
			this.pw.write("<script>"
					+ "alert('데이터베이스 오류 발생2');"
					+ "history.go(-1);"
					+ "</script>");
		}
	}
}
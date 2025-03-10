package web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class agreeok extends HttpServlet {
	private static final long serialVersionUID = 1L;
	PrintWriter pw = null;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");

		try {
			this.pw = response.getWriter();
			String[] checkok = request.getParameterValues("checkok");
			int count = 0;
			for (int i = 0; i < checkok.length - 1; i++) {
//			System.out.println("checkok"+i+" : \n"+checkok[i]+"\n");
				if(checkok[i].equals("on")) {
					count++;
				}
			}
//			System.out.println("카운트"+count);
//			System.out.println("체크배열" + Arrays.toString(checkok));
			if (count == checkok.length - 1) {
				request.setAttribute("count", count);
				RequestDispatcher rd = request.getRequestDispatcher("./agreeok.jsp");
				rd.forward(request, response);
			}
		} catch (Exception e) {
			this.pw.write("<script>" + "alert('올바른 접근방식이 아닙니다.');" + "history.go(-1);" + "</script>");
		} finally {
			this.pw.close();
		}

	}

}
package web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class movie_part2 extends HttpServlet {
	private static final long serialVersionUID = 1L;
	PrintWriter pw = null;
	
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		res.setContentType("text/html;charset=utf-8");
		
		try {
			this.pw = res.getWriter();
			
			String mname = req.getParameter("mname");
			String mtel = req.getParameter("mtel");
			String movie_name = req.getParameter("movie_name");	
			String mdate = req.getParameter("mdate");
			
			String redate = mdate.replace("-", "");
			
//		System.out.println("예약 날짜 : " + redate);
//		System.out.println("오늘 날짜 : " + this.today());
			
			if(Integer.parseInt(redate) < Integer.parseInt(this.today())) {
				this.pw.write("<script>" 
						+ "alert('오늘 이후의 날짜로 예약해주세요.');" 
						+ "history.go(-1);" 
						+ "</script>");
			}else {
				ArrayList<String> data = new ArrayList<String>();
				data.add(mname);
				data.add(mtel);
				data.add(movie_name);
				data.add(mdate);
				
				req.setAttribute("data", data);
				RequestDispatcher rd = req.getRequestDispatcher("./movie_part2.jsp");
				rd.forward(req, res);
				
			}
			
		}catch (Exception e) {
			this.pw.write("<script>" 
					+ "alert('올바른 접근이 아닙니다.');" 
					+ "history.go(-1);" 
					+ "</script>");
		}finally {
			this.pw.close();
		}

	}
	
	public String today() {
		Date day = new Date();
		SimpleDateFormat sf = new SimpleDateFormat("yyyyMMdd");
		return sf.format(day);
	}
}








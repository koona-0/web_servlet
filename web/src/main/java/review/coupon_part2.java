package review;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class coupon_part2 extends HttpServlet {
	private static final long serialVersionUID = 1L;
	PrintWriter pw = null;
	
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		res.setContentType("text/html;charset=utf-8");
		
		try {
			this.pw = res.getWriter();
			
			String mid = req.getParameter("mid");
			String cnum = req.getParameter("cnum");
			String agree_ad = req.getParameter("agree_ad");
			
			String ca[] = {"A1316B1004", "C4024A0096", "B1987C3136"};
			ArrayList<String> clist = new ArrayList<String>(Arrays.asList(ca));
			ArrayList<String> data = new ArrayList<String>();
			
			//System.out.println(agree_ad);
			if(agree_ad.equals("on")) {		//광고수신 동의일때 
				if(clist.contains(cnum)) {	//쿠폰이 있을때 
					//보내기 
					data.add(mid);
					data.add(cnum);
					data.add(agree_ad);
					req.setAttribute("data", data);
					RequestDispatcher rd = req.getRequestDispatcher("./coupon_part2.jsp");
					rd.forward(req, res);
				}else {	//쿠폰없을때 
					this.pw.write("<script>" 
							+ "alert('해당 쿠폰번호를 확인하세요');" 
							+ "history.go(-1);" 
							+ "</script>");
				}
			}else {//광고수신미동의 
				this.pw.write("<script>" 
						+ "alert('광고수신동의 오류');" 
						+ "history.go(-1);" 
						+ "</script>");
			}
		}catch (Exception e) {
			this.pw.write("<script>" 
					+ "alert('올바른 접근방식이 아닙니다.');" 
					+ "history.go(-1);" 
					+ "</script>");
		}finally {
			this.pw.close();
		}
	}
}

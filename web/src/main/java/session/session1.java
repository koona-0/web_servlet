package session;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/*
session : 일정 시간동안 브라우저에 해당 값을 저장 및 유지시키는 방식 (Back-end)
cookie : 브라우저에 cache 메모리에 값을 저장하는 방식 (도메인 및 IP 기준) - (Front, Back-end)
storage : 메모리에 저장 (Front, Back-end) => local, session
 => cookie, storage는 눈에 보임 BUT ! session은 암호화 되어있음 
*/

//session1 => session을 생성하는 컨트롤러 
public class session1 extends HttpServlet {
	private static final long serialVersionUID = 1L;
	PrintWriter pw = null;
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String mid = request.getParameter("mid");
		String mpass = request.getParameter("mpass");
		response.setContentType("text/html;charset=utf-8");
		this.pw = response.getWriter();
		
		if(mid.equals("hong") && mpass.equals("a1234")) {	//정상적인 로그인
			String username = "홍길동";
			String usertel = "01012341234";
			
			//HttpSession : 브라우저에 cache 메모리에 데이터를 임시 저장 
			HttpSession se = request.getSession();
			//session을 생성 
			se.setAttribute("id", mid);	//아이디 
			se.setAttribute("name", username);	//이름 
			se.setAttribute("tel", usertel);	//전화번호 
			
		}else {	//아이디 및 패스워드가 틀린 경우 
			this.pw.write("<script>" 
					+ "alert('아이디 및 패스워드를 확인하세요');" 
					+ "history.go(-1);" 
					+ "</script>");
		}
		this.pw.close();
		
	}

}

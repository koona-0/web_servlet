package session;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class session3 extends HttpServlet {
	private static final long serialVersionUID = 1L;

	//로그아웃은 jsp가 필요없음!!
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession se = request.getSession();
		
		//모든 session의 값을 초기화 (삭제)
		se.invalidate();
		
		//ex) 쇼핑몰의 플로우배너 최근본상품 중 하나를 지울때  
		//se.removeAttribute("tel");	//특정 session값만 삭제를 할 수 있음 
	}

}

package ajax;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class ajax_postok extends HttpServlet {
	private static final long serialVersionUID = 1L;
	PrintWriter pw = null;
       
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//CORS를 해결하기 위해서 사용하는 방식의 코드
		//해당 Origin, Credentials 을 사용하여 도메인이 다르게 접근을 하더라도 ajax통신이 되도록 허락을 하는 명령어 
		//실무에서는 "*"를 안쓰고 서버아이피나 특정 도메인을 걸어서 사용함 
		//ajax통신시 웬만하면 써두는것이 좋음!!! 
		//아래 두 줄은 상대방의 주소로 전송시 CORS를 방지하는 코드
		response.addHeader("Access-Control-Allow-Origin", "*");	// 모든 아이피 허용 //*대신 아이피넣으면 해당 아이피만 허용  
		response.addHeader("Access-Control-Allow-Credentials", "true");

		
		
		
		
		String userid = request.getParameter("userid");
		String usermail = request.getParameter("usermail");
		String msg = "";	//ok : 정상 아이디, no : 아이디 오류 발생, error : 백엔드 오류
		
		System.out.println(userid + usermail);
		
		this.pw = response.getWriter();
		
		if(userid.equals("hong") && usermail.equals("hong@nate.com")) {
			msg = "no";
		}else {
			msg = "ok";
		}
		this.pw.print(msg);	//Front-end에게 보내는 값 
		
	}
}
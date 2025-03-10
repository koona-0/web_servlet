package web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class deliveryok extends HttpServlet {
	private static final long serialVersionUID = 1L;
	PrintWriter pw = null; 
	//즉시실행에서 언어셋하기위함 (여기 핸들링 더해야 함)
//	HttpServletRequest request;
//	HttpServletResponse response;
	
	//action에서 해당 class를 실행하면 즉시 실행됨 (즉시실행메소드)
	//public deliveryok(){}
	/* 여기에 뭔가 코드 더 추가해야하는데 진도 빼야해서 
	 * 암튼 즉시실행도 이런식으로 쓸 수 있다 알아두기 
	 * (여기 핸들링 더해야 함)
	public deliveryok(){
		try {
			this.request.setCharacterEncoding("utf-8");
    		this.response.setContentType("text/html;charset=utf-8");
    		this.pw = this.response.getWriter();
		}catch (Exception e) {
			this.pw.write("<script>" 
					+ "alert('해당 URL 경로가 올바르지 않습니다.');" 
					+ "history.go(-1);" 
					+ "</script>");
		}
	}
	*/
	
	/* 즉시실행메소드 주의할 점
	 * 원래 만들어질 때 servlet으로 만들어짐 => class로 만들때랑 엄연히 다른놈이라 다르게 인식 
	 * 일반 class에서 사용하는 형태와는 구조가 다름 
	 * 즉시실행메소드에는 파라미터를 쓰지 않는것이 원칙 
	 * 굳이 즉시실행에서 쓰고싶다면 
	 * 그래서 아래처럼 하려면 void를 넣어서 별동대를 만들어줘야함 
	 */
	
	//일반 메소드 (void 들어가서 즉시실행메소드가 아님)
    public void deliveryok(HttpServletRequest request, HttpServletResponse response) {	    
    	//throws ServletException, IOException
    	//원래 여기 try catch써야하는데 throws Ex사용해서 안쓸수도있음!
    	try {
    		request.setCharacterEncoding("utf-8");
    		response.setContentType("text/html;charset=utf-8");  
    		this.pw = response.getWriter();
    	}catch (Exception e) {
    		System.out.println("해당 URL 경로가 올바르지 않습니다.");	//원래 sysout안쓰고 alert 사용
    		
    		this.pw.write("<script>" 
					+ "alert('해당 URL 경로가 올바르지 않습니다.');" 
					+ "history.go(-1);" 
					+ "</script>");
		}
    }
    
    //매개변수명을 변경하여 response, request를 다르게 사용 가능 
	protected void doPost(HttpServletRequest rq, HttpServletResponse rs) throws ServletException, IOException {
		//void로 만든 함수 
		this.deliveryok(rq, rs);
		
		//해당 메소드의 파라미터 변수를 바꿔서 간단하게 사용 가능 
		String mname = rq.getParameter("mname");
		String mtel = rq.getParameter("mtel");
		String memail = rq.getParameter("memail");
		String maddress = rq.getParameter("maddress");
		
		//원시배열은 jsp에서 또 따로 풀어야함 그래서 클래스 배열 사용
		ArrayList<String> data = new ArrayList<String>();
		data.add(mname);
		data.add(mtel);
		data.add(memail);
		data.add(maddress);	//이런거를 DTO를 사용하여 간단하게 추가 가능 
		
		//setAttribute 너무 많이 생성하여 jsp로 전달시 getAttribute 많아지기 때문에 클래스 배열로 전달 
		rq.setAttribute("data", data);
		RequestDispatcher rd = rq.getRequestDispatcher("./deliveryok.jsp");
		rd.forward(rq, rs);
	}

}

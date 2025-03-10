package web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class pollok extends HttpServlet {
	private static final long serialVersionUID = 1L;

	// method="get"
	// PrintWriter : Controller에서 종료(결과값을 여기서 처리)
	// RequestDispatcher : Controller => view (jsp)에서 결과를 처리
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		response.setCharacterEncoding("utf-8");
//		response.setContentType("text/html");
		// 위 두줄은 책. 아래 한줄은 실무. 같은 역할
		response.setContentType("text/html;charset=utf-8");
		request.setCharacterEncoding("utf-8");
		
		/*
		값선언은 웬만하면 try안에 선언하자
		equals를 사용하지않고 ==를 사용하려고 intern을 사용시
		intren은 무조건 값을 받아줘야해서 오류남 
		근데 try catch밖에 있을 경우 웹페이지가 망가져버리기때문에
		try catch로 예외처리될 수 있게 하기 
		*/
		// 동일한 name => radio : 여러개 오브젝트 중에 한가지만 선택
		// getParameter : get, post (name 전달됨 값 동일함)
		//request.getParameter : 무조건 Front-end에서 값이 이관되어야함 안넘어오면 예외처리됨 catch
		String subject = request.getParameter("subject");	//여기에 .intern(); 사용할때 try안에 넣기
		String etc[] = request.getParameterValues("etc");	//getParameterValues : 원시배열로 받기
//		ArrayList<String> etc = new ArrayList<String>(Arrays.asList(request.getParameterValues("etc"))) ;	//원시배열로 받기 때문에 클래스배열로 쓰고싶을때는 이렇게 쓰기
		PrintWriter pw = response.getWriter();
		try {
			//여기서는 view로 보내기때문에 pw가 안보임
//			pw.write("<script>"
//					+ "alert('test');"
//					+ "</script>");
			
			//값이 없으면 request.getParameter가 catch로 보내버리기때문에 필요없음 
//			if(subject.equals(null)) {
//				System.out.println("값없음");
//			}
			
			int w = 0;
			while(w < etc.length) {
//				request.setAttribute("etc"+w, etc[w]);
				w++;
			}
			request.setAttribute("etc", etc);
			System.out.println(Arrays.toString(etc));
			
			request.setAttribute("subject", subject);	//view에 출력 메소드 
			RequestDispatcher rd = request.getRequestDispatcher("./pollok.jsp");
			rd.forward(request, response);
		} catch (Exception e) {
			System.out.println(e);
			pw.write("<script>"
					+ "alert('올바른 접근이 아닙니다.');"
					+ "</script>");

		} finally {
			pw.close();
		}
	}
}
package web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collection;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

//여러개의 파일을 전송하는 방식 
@MultipartConfig( 
		//아래 코드에 조건문이 없으면 용량이 오버되면 서버가 사망해버림
		//front에서 검토해주는게 좋음 그래도 back에서 체크해야함 
		//그래서 maxFileSize를 넉넉하게 잡거나 제한없이 설정함
		//아래 세줄을 무조건 쓰는것은 아니고 Front가 파일크기를 확인안해주면 욕해도됨
		//Front가 검토해주면 맥스사이즈 사용해도됨 
		fileSizeThreshold = 1024 * 1024 * 2,
//		maxFileSize = 1024 * 1024 * 5,	//해당 Max 파일 용량이 5MB 이상일 경우 Server Down
		maxRequestSize = 1024 * 1024 * 100
	)
public class multi_file extends HttpServlet {
	private static final long serialVersionUID = 1L;
	PrintWriter pw = null;
       
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		
		try {
			this.pw = response.getWriter();
			String mname = request.getParameter("mname");
			System.out.println(mname);
			String url = request.getServletContext().getRealPath("/upload/");
			
			//name을 따로 설정하지 않으며, 배열 클래스를 이용하여 IO값을 핸들링함 
			//Collection : 모든 name값을 다 받을 수 있음 => 프론트가 뿅감
			Collection<Part> p = request.getParts();	//여러 파일을 받을경우 getParts를 사용함 => 원시배열이 아니라 Collection을 사용, name 사용안함  
			for(Part f : p) {	//foe each 사용 
				long size = f.getSize();
//				System.out.println("test");	//html에서 파일 하나 안넣어도 인풋파일 개수만큼 반복
				String filename = f.getSubmittedFileName();
				
				
				//파일명이 있고 2MB 이하일 경우 
				//if(!filename.equals("") && size < 2097152) {	
				//if(filename != null && size < 2097152) {
				//if(!filename.equals(null) || size < 2097152) {
				if(filename != null && size < 2097152) {
					f.write(url + filename);	//조건이 맞을경우 저장 
					//flush, close 안넣음 => stream아니어서 알아서 종료 잘 함 
				}
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
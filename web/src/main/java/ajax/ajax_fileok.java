package ajax;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

@MultipartConfig(
	fileSizeThreshold = 1024 * 1024 * 5,
	maxFileSize = 1024 * 1024 * 50,
	maxRequestSize = 1024 * 1024 * 100
)
public class ajax_fileok extends HttpServlet {
	private static final long serialVersionUID = 1L;
	PrintWriter pw = null;
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//ajax통신시 CORS 방지 
		response.addHeader("Access-Control-Allow-Origin", "*");	
		response.addHeader("Access-Control-Allow-Credentials", "true");
		
		request.setCharacterEncoding("utf-8");	//한글파일명이 올수도 있으니까
		
		this.pw = response.getWriter();
		
		//경로확인 
		//String url = request.getServletContext().getRealPath("");
		//System.out.println(url);
		/*
		/Users/nayeong/Documents/web_servlet/.metadata/.plugins/org.eclipse.wst.server.core/tmp0/wtpwebapps/web/
		*/
		
		//파일 저장
		Part mfile = request.getPart("mfile"); 	//전송된 파일 데이터를 받는 역할 
		String url = request.getServletContext().getRealPath("/upload/");	//전송될 파일 디렉토리 
		String filenm = mfile.getSubmittedFileName();	//파일명
		mfile.write(url + filenm);
		this.pw.print("ok");
	}
}
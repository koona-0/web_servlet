package review;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

@MultipartConfig(	//첨부파일 환경설정 
		fileSizeThreshold = 1024 * 1024 * 5	//한개의 파일 전송 크기 : 5MB 
		)
public class io_ok extends HttpServlet {
	private static final long serialVersionUID = 1L;
	PrintWriter pw = null;

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");

		try {
			this.pw = response.getWriter();
			
			Part mfile = request.getPart("mfile");
			
			//파일명을 확인하는 코드 
			String filename = mfile.getSubmittedFileName();
			
			long filesize = mfile.getSize();	//파일 용량 크기 
			if(filesize > 5242880) {
				this.pw.write("<script>" 
						+ "alert('파일 첨부 용량은 최대 5MB까지 입니다.');" 
						+ "history.go(-1);" 
						+ "</script>");
			}
			else {
				String url = request.getServletContext().getRealPath("/notice/");
				mfile.write(url + filename);
				this.pw.write("<script>" 
						+ "alert('정상적으로 파일 업로드되었습니다.');" 
						+ "history.go(-1);" 
						+ "</script>");
			}
		} catch (Exception e) {
			System.out.println(e);
			this.pw.write("<script>" 
						+ "alert('올바른 접근방식이 아닙니다.');" 
						+ "history.go(-1);" 
						+ "</script>");
		} finally {
			this.pw.close();
		}
	}

}

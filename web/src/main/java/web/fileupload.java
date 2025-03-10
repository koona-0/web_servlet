package web;
//cos 사용없이 자체 라이브러리를 이용하여 설정
//cos => 파일 라이브러리 2005년에 개발됨

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

//HTML에 첨부파일 기능이 있을때만 사용 
@MultipartConfig(	//첨부파일 환경설정 
	fileSizeThreshold = 1024 * 1024 * 2,	//한개의 파일 전송 크기 : 2MB 
	maxFileSize = 1024 * 1024 * 5,			//파일 최대 크기 용량 : 5MB
	maxRequestSize = 1024 * 1024 * 100		//여러개의 첨부 파일 전체의 용량 : 100MB //multiple로 받은 총 크기
)
public class fileupload extends HttpServlet {
	private static final long serialVersionUID = 1L;
	PrintWriter pw = null;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");

		try {
			this.pw = response.getWriter();
			//Front-end에서 전송된 name값에 I/O 처리 방식
			//Part(클라이언트) <=> @MultipartConfig(서버) 연결 
			Part mfile = request.getPart("mfile");
			//파일명을 확인하는 코드 
			String filename = mfile.getSubmittedFileName();
			long filesize = mfile.getSize();	//파일 용량 크기 
//			System.out.println(filesize);
			if(filesize > 2097152) {
				this.pw.write("<script>" 
						+ "alert('파일 첨부 용량은 최대 2MB까지 입니다.');" 
						+ "history.go(-1);" 
						+ "</script>");
			}
			else {
				/*
				WEB Server에서 적용된 디렉토리에 저장 (src경로 x)
				/Users/nayeong/Documents/web_servlet/.metadata/.plugins/org.eclipse.wst.server.core/tmp0/wtpwebapps/web/upload/
				
				WEB I/O : 웹 전용 이미지 디렉토리를 별도로 구성해야만 정상적으로 웹에서 이미지를 확인할 수 있음
				getServletContext(웹경로) + getRealPath(저장디렉토리);
				
				APP I/O : File => InputStream => OutputStream 
				*/
				String url = request.getServletContext().getRealPath("/upload/");
				System.out.println(url);
				mfile.write(url + filename);
				this.pw.write("<script>" 
						+ "alert('정상적으로 파일 업로드되었습니다.');" 
						+ "history.go(-1);" 
						+ "</script>");
			}
		} catch (Exception e) {
			this.pw.write("<script>" 
						+ "alert('올바른 접근방식이 아닙니다.');" 
						+ "history.go(-1);" 
						+ "</script>");
		} finally {
			this.pw.close();
		}
	}
}
package notice;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import shop.m_dbinfo;
import shop.m_md5;

//원래는 컨트롤러에 10줄도 안씀! 다 모델로 만들어야함 !!! 
//실무에서 이렇게 만들면 욕을 바가지로 먹는다 !!!

//파일첨부시 필수 ! 
@MultipartConfig(
	fileSizeThreshold = 1024 * 1024 * 5,	//파일하나 5MB
	maxFileSize = 1024 * 1024 * 50,			//파일들의 총 용량 50MB
	maxRequestSize = 1024 * 1024 * 500		//파일이 여러개 날아올때만 필요한 라인 //파일 하나만 날아올땐 필요없음 
)
public class notice_writeok extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Connection con = null;
	//ResultSet 필요없음! 쓰기 할거니까
	PreparedStatement ps = null;
	PrintWriter pw = null;
	//Model
	m_dbinfo db = new m_dbinfo();	//DB 접속 정보 
	m_md5 md5 = new m_md5();		//md5 암호화 
	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		this.pw = response.getWriter();
		
		//첨부파일이 있고, 없고 따라서 SQL 저장방식이 변경됨 
		Part nfile = request.getPart("nfile");
		long filesize = nfile.getSize();	//getSize()는 long값으로 return해줌
		
		try {
			this.con = this.db.getConnection();		//DB 연결 
			String subject = request.getParameter("subject");
			String writer = request.getParameter("writer");
			String pw = request.getParameter("pw");
			String texts = request.getParameter("texts");
			
			//패스워드를 암호화
			pw = this.md5.md5_code(pw);
			
			String sql = "";	//DB Query문 
			int result = 0;		//DB에서 저장된 결과를 받는 변수 (저장되면 1) select에는 필요없음 i,u,d에만 필요  
			
			
			if(filesize == 0) {		//첨부파일이 없을 경우
				sql = "insert into notice (nidx,subject,writer,pw,texts,ndate)"
						+ "values ('0',?,?,?,?,now())";
				this.ps = this.con.prepareStatement(sql);
				this.ps.setString(1, subject);
				this.ps.setString(2 , writer);
				this.ps.setString(3, pw);
				this.ps.setString(4, texts);
				//System.out.println(sql);
				result = this.ps.executeUpdate();	//DB에 저장 
				if(result > 0) {	//저장이 된 경우 
					this.pw.write("<script>"
							+ "alert('게시물이 올바르게 등록되었습니다.');"
							+ "location.href = './notice_list.jsp';"
							+ "</script>");
				}else {	//저장이 안된 경우 
					
				}
				
			}else {		//첨부파일이 있을 경우 
				//Model을 이용하여 첨부파일을 저장하는 방식 
				m_notice nt = new m_notice(nfile,subject,writer,pw,texts,request);
				//return 메소드가 private이므로 전역변수의 값을 직접 로드 
				String msg = nt.msg;	//모델의 필드에 올라온 변수는 이렇게 처리 가능 
				//리턴 받으려고 굳이굳이 public사용하지 않아도됨 !! 필드 사용하기
				
				if(msg.equals("ok")) {
					this.pw.write("<script>"
							+ "alert('올바르게 공지사항이 등록되었습니다.');"
							+ "location.href = './notice_list.jsp';"
							+ "</script>");
				}else {
					this.pw.write("<script>"
							+ "alert('데이터베이스 및 첨부파일 오류 발생');"
							+ "history.go(-1);"
							+ "</script>");
				}
			}
			
		}catch (Exception e) {
			this.pw.write("<script>"
					+ "alert('데이터베이스 문제로 인하여 저장되지 않았습니다.');"
					+ "history.go(-1);"
					+ "</script>");
		}finally {
			try {
				this.ps.close();
				this.con.close();
				this.pw.close();
			}catch (Exception e) {
				
			}
		}
	}
}
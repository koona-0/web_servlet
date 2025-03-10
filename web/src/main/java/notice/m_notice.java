package notice;

import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;

import shop.m_dbinfo;

//공지사항 게시판 파일을 저장하는 Model 
//원래 DTO도 써야됨 !!
public class m_notice extends HttpServlet{
	Connection con = null;
	PreparedStatement ps = null;
	m_dbinfo db = new m_dbinfo();	
	
	String sql = "";	//DB 쿼리문 
	int result = 0;		//DB 저장 결과값 
	
	String msg = "";	//Model에서 처리된 값을 Controller로 결과값을 반환 
	
	String subject,writer,pw,texts;
	//ArrayList로 만들어도됨

	//즉시실행메소드에서는 첨부파일을 저장하는 역할만 수행 => 다른메소드에서 DB로 연결  
	public m_notice(Part nfile,String subject,String writer,String pw,String texts,HttpServletRequest request) throws Exception{
		//DTO를 안만들어서 이모양으로 해야함 
		this.subject = subject;
		this.writer = writer;
		this.pw = pw;
		this.texts = texts;
		
		long filesize = nfile.getSize();	//파일 용량 
		String filenm = nfile.getSubmittedFileName();	//파일명 
//		System.out.println(request.getServletContext().getRealPath(""));	//파일 저장 경로 확인
		String url = request.getServletContext().getRealPath("/notice_file/");	//첨부파일 저장될 Web Directory 설정 
		try {//io니까 트캐 넣기 
			nfile.write(url + filenm);	//웹에 저장 
			this.fileok(filenm);	//정상적으로 저장되었을 경우 
		}catch (Exception e) {
			this.fileok("error");	//비정상적으로 해당 디렉토리에 파일이 저장되지 않을 경우 
		}
		
		//http://localhost:8080/web/notice_file/forest.jpeg 확인 
		
	}
	
	//DB로 저장 및 Controller로 결과값을 return하는 메소드 
	private String fileok(String data) throws Exception{
		if(data.equals("error")) {
			this.msg = "error";
		}else {	//파일이 정상적으로 저장되었을 경우 
			try {
				this.con = this.db.getConnection();
				this.sql = "insert into notice (nidx,subject,writer,pw,texts,filenm,nfile,ndate)"
						+ "values ('0',?,?,?,?,?,?,now())";
				this.ps = this.con.prepareStatement(sql);
				this.ps.setString(1, this.subject);
				this.ps.setString(2, this.writer);
				this.ps.setString(3, this.pw);
				this.ps.setString(4, this.texts);
				this.ps.setString(5, data);
				this.ps.setString(6, data);
				//물음표개수랑 setString개수 다르면 에러남 !
				this.result = this.ps.executeUpdate();
				
				if(result > 0) {	//저장이 된 경우 
					this.msg = "ok";
				}else {	//저장이 안된 경우 
					this.msg = "error";
				}
				
			}catch (Exception e) {
				this.msg = "error";
			}finally {
				//원래 여기에 트캐 넣어야하는데 throws사용 => 즉시실행 메소드에도 throws 사용 
				//서블릿에서는 post메소드 스스로 만들어져있어서 못썼었지만 모델에서는 가능  
				this.ps.close();
				this.con.close();
			}
		}
		
		return this.msg;	//Controller로 보내는 값 
	}
	
}

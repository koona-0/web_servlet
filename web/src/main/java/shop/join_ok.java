package shop;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//get + post 함께 있으면 servlet 단독 실행 가능 
public class join_ok extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Connection con = null;	//Database 연결시 필수 (SQL Query 실행시 필요)
	
	/*
	//service : doGet, doPost 둘 다 받음 모두 사용 가능  
	//서블릿 파일 만들 때 넥스트넥스트하면 서비스있음
	//대신 service는 보안이 제일 약함 !! => 테스트할때 테스트용으로만 사용  
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("post, get 모두 받음");
	}
	*/
	
	/*
	 * DB 기본 문법 (회원가입하는데 doGet 쓰면 작살난다)
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		m_dbinfo db = new m_dbinfo();	//실행 명령어
		try {
			this.con = db.getConnection();
			
		}catch (Exception e) {
			System.out.println("DB 연결 실패");
		}finally {
			try {
				this.con.close();				
			}catch (Exception e) {
				System.out.println("DB가 올바르게 해제되지 않았습니다.");
			}
		}
	}
	*/
	
	PrintWriter pw = null;
	PreparedStatement ps = null;
	//회원가입 진행 
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");	//여기 오타나면 다따운받아서 코드 유출됨 주의
		this.pw = response.getWriter();
		m_dbinfo db = new m_dbinfo();
		
		try {
			this.con = db.getConnection();
			String spart = request.getParameter("spart");
			String sid = request.getParameter("sid");
			String spw = request.getParameter("spw");
			String snm = request.getParameter("snm");
			String stel = request.getParameter("stel");
			String semail = request.getParameter("semail");
						
			spw = new m_md5().md5_code(spw);	//문자 패스워드를 MD5로 암호화 
			String sql = "";	//Database Query문  

			if(spart.equals("P")) {	//개인회원
				sql = "insert into shop_member values('0',?,?,?,?,?,?,null,now())";
				this.ps = this.con.prepareStatement(sql);
				this.ps.setString(1, spart);
				this.ps.setString(2, sid);
				this.ps.setString(3, spw);
				this.ps.setString(4, snm);
				this.ps.setString(5, stel);
				this.ps.setString(6, semail);
				int result = this.ps.executeUpdate();
				if(result > 0) {	//정상적으로 DB에 insert되었을 경우 
					this.pw.write("<script>"
							+ "alert('회원가입이 정상적으로 완료되었습니다.');"
							+ "location.href='./login.jsp'"
							+ "</script>");
				}
				
				
			}else {   //사업자회원 
	            String sno = request.getParameter("sno");
	            sql = "insert into shop_member values('0',?,?,?,?,?,?,?,now())";
	            this.ps = this.con.prepareStatement(sql);
	            this.ps.setString(1, spart);
	            this.ps.setString(2, sid);
	            this.ps.setString(3, spw);
	            this.ps.setString(4, snm);
	            this.ps.setString(5, stel);
	            this.ps.setString(6, semail);
	            this.ps.setString(7, sno);
	            int result = this.ps.executeUpdate();
	            if(result > 0) {   //정상적으로 DB에 insert되었을 경우 
	               this.pw.write("<script>"
	                     + "alert('사업자 회원가입이 정상적으로 완료되었습니다.');"
	                     + "location.href='./login.jsp'"
	                     + "</script>");
	            }
	         }
		}catch (java.sql.SQLIntegrityConstraintViolationException se) {
			this.pw.write("<script>"
					+ "alert('연락처 및 이메일이 중복되어서 가입이 취소되었습니다..');"
					+ "history.go(-1);"
					+ "</script>");
		}catch (Exception e) {
			System.out.println(e);
		}finally {
			try {
				this.ps.close();
				this.con.close();
				this.pw.close();				
			}catch (Exception e) {
				System.out.println(e);
			}
		}
	}
}
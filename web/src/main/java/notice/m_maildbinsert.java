package notice;

import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;

import shop.m_dbinfo;

public class m_maildbinsert extends HttpServlet {
	Connection con = null;
	PreparedStatement ps = null;
	m_dbinfo db = new m_dbinfo();

	String sql = ""; // DB 쿼리문
	int result = 0; // DB 저장 결과값
	String msg = ""; // Model에서 처리된 값을 Controller로 결과값을 반환
	String to_name, to_mail, subject, context;

	public m_maildbinsert(String to_name, String to_mail, String subject, String context, HttpServletRequest request) throws Exception {
		this.to_name = to_name;
		this.to_mail = to_mail;
		this.subject = subject;
		this.context = context;
		System.out.println("m_maildbi : "+to_name);
		this.sendok();
	}

	private String sendok() throws Exception {
		System.out.println("sendok : "+this.to_name);
		try {
			this.con = this.db.getConnection();
			this.sql = "insert into send_mail (midx,to_name,to_mail,subject,context,mdate)"
					+ "values ('0',?,?,?,?,now())";
			this.ps = this.con.prepareStatement(sql);
			this.ps.setString(1, this.to_name);
			this.ps.setString(2, this.to_mail);
			this.ps.setString(3, this.subject);
			this.ps.setString(4, this.context);
			this.result = this.ps.executeUpdate();

			if (result > 0) { // 저장이 된 경우
				this.msg = "ok";
			} else { // 저장이 안된 경우
				this.msg = "error";
			}

		} catch (Exception e) {
			this.msg = "error";
		} finally {
			this.ps.close();
			this.con.close();
		}
		return this.msg;
	}
}
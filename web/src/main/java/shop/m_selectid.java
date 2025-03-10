package shop;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class m_selectid {
	Connection con = null;
	m_dbinfo db = new m_dbinfo();
	PreparedStatement ps = null;
	ResultSet rs = null;
	String id = null;

	public String idok(String snm, String stel, String semail) {
		try {
			this.con = this.db.getConnection();

			// select sid from shop_member where snm="" and stel="" and semail="";
			String sql = "select sid from shop_member where snm=? and stel=? and semail=?";
			this.ps = this.con.prepareStatement(sql);
			this.ps.setString(1, snm);
			this.ps.setString(2, stel);
			this.ps.setString(3, semail);
			this.rs = this.ps.executeQuery();
			
			if (this.rs.next()) {  //next() 호출 후 데이터 읽기
				this.id = this.rs.getString("sid");
			} 
		} catch (Exception e) {
			System.out.println("db select error");

		} finally {
			try {
				this.rs.close();
				this.ps.close();
				this.con.close();
			} catch (Exception e) {
				System.out.println("db close error");
			}
		}
		return this.id;
	}
}
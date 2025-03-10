package shop;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class m_updatepw {
	Connection con = null;
	PreparedStatement ps = null;
	m_dbinfo db = new m_dbinfo();
	int result = 0;
	
	public int pwok(String mid, String newpw) {
		try {
			this.con = this.db.getConnection();
			
			newpw = new m_md5().md5_code(newpw);
			//update shop_member set spw="123" where sid="test";
			String sql = "update shop_member set spw=? where sid=?";
			this.ps = this.con.prepareStatement(sql);
			this.ps.setString(1, newpw);
			this.ps.setString(2, mid);
			this.result = this.ps.executeUpdate();
			System.out.println("pwok : " + this.result);
			
		}catch (Exception e) {
			this.result = 0;
			System.out.println(e);
			System.out.println("pwok error");
		}finally {
			try {
				this.ps.close();
				this.con.close();
				
			}catch (Exception e) {
				this.result = 0;
				System.out.println("pwok close error");
			}
		}		
		return this.result;
	}
}
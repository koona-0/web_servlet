package mallpage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import event.dbconfig;
import shop.m_dbinfo;

//Copyright Model + login Model
public class copyright {
   Connection con = null;
   PreparedStatement ps = null;
   ResultSet rs = null;
   String sql = null;
   ArrayList<String> cp = null;
   m_dbinfo db = new m_dbinfo();
   String result = null;
   m_member mmb = new m_member();    //DTO선언
   
   //login
   public String user_login(m_member dto) {
      try {
         this.con = this.db.getConnection();
         this.sql = "select mid,mname,memail from joins where mid=? and mpass=?";
         this.ps = this.con.prepareStatement(this.sql);
         this.ps.setString(1, dto.getMid());
         this.ps.setString(2, dto.getMpass());
         this.rs = this.ps.executeQuery();
         
         if(this.rs.next() == true) { //정상적으로 아이디 및 패스워드가 맞을 경우
            this.result = "ok";
            mmb.setMid(this.rs.getString("mid"));   //아이디
            mmb.setMname(this.rs.getString("mname"));   //가입자명
            mmb.setMemail(this.rs.getString("memail"));   //이메일
            
         }
         
      }catch (Exception e) {
         this.result = null;
      } finally {
         try {
            this.rs.close();
            this.ps.close();
            this.con.close();
         } catch (Exception e2) {
            
         }
      }
      return this.result;
   }
   
   
   
   
   
   
   
   public ArrayList<String> copyright_info() {
      try {
         con = this.db.getConnection();
         this.sql = "select * from corp_info";
         this.ps = this.con.prepareStatement(this.sql);
         this.rs = this.ps.executeQuery(); //클래스 배열에 넣기 넣에 next넣어야 함
         this.rs.next();
         this.cp = new ArrayList<String>();
         
         this.cp.add(this.rs.getString("corp_nm"));
         this.cp.add(this.rs.getString("ceo_nm")); 
         this.cp.add(this.rs.getString("corp_addr"));
         this.cp.add(this.rs.getString("corp_tel"));
         this.cp.add(this.rs.getString("corp_time"));
         this.cp.add(this.rs.getString("ceo_email"));
         this.cp.add(this.rs.getString("corp_no"));
         this.cp.add(this.rs.getString("corp_no2"));
         this.cp.add(this.rs.getString("corp_master"));
         this.cp.add(this.rs.getString("corp_domain"));
         
         //System.out.println(this.cp);
      } catch (Exception e) {

      } finally {
         try {
            this.rs.close();
            this.ps.close();
            this.con.close();
         } catch (Exception e2) {
            System.out.println("Database Error!!");
         }
      }
      return this.cp;
   }
}

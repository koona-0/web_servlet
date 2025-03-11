package notice;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
//Database 게시물 삭제 처리 Controller

import shop.m_dbinfo;
import shop.m_md5;
public class notice_delete extends HttpServlet {
   private static final long serialVersionUID = 1L;
    
   Connection con = null;
   PreparedStatement ps= null;
   String sql = null;
   int result = 0;
   m_dbinfo db = new m_dbinfo(); //DB 정보
   m_md5 md5 = new m_md5(); //MD5 암호화
   PrintWriter pw = null;
   
   protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      response.setContentType("text/html;charset=utf-8");
      String nidx = request.getParameter("nidx");
      String ori_pw = request.getParameter("ori_pw");
      String npw = request.getParameter("npw");
      this.pw = response.getWriter();
      try {
         //사용자가 입력한 패스워드와 자동 증가값이 올바르게 (post전송)전달되지 않을 경우 작동 해제시킴
         if(nidx.equals(null)||npw.equals(null)) {
            this.pw.write("<script>"
                  + "alert('올바른 접근이 아닙니다.');"
                  + "history.go(-1);"
                  + "</script>");
         }else {
            npw = md5.md5_code(npw); //사용자가 입력한 패스워드를 md5로 변환후 DB의 패스워드와 비교 
            if(npw.equals(ori_pw)) {
               this.con = this.db.getConnection(); //DB연결
               this.sql = "delete from notice where nidx=?";
               this.ps = this.con.prepareStatement(this.sql);
               this.ps.setString(1, nidx);
               this.result = this.ps.executeUpdate();
               System.out.println(this.result);
               if(this.result > 0) {
                  this.pw.write("<script>"
                        + "alert('게시물을 삭제하였습니다.');"
                        + "location.href='./notice_list.do';"
                        + "</script>");
               }
            }else {
               this.pw.write("<script>"
                     + "alert('패스워드가 틀립니다.');"
                     + "history.go(-1);"
                     + "</script>");
            }
         }
      } catch (Exception e) {
         this.pw.write("<script>"
               + "alert('Database삭제 오류 발생!!');"
               + "history.go(-1);"
               + "</script>");
      }finally {
         try {
            this.ps.close();
            this.con.close();
            this.pw.close();
            
         } catch (Exception e2) {
            System.out.println("DB 접속에 따른 해제권한 오류 발생!!");
         }
      }
      
      
   }

}

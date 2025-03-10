package web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class agreeok_2 extends HttpServlet {
   private static final long serialVersionUID = 1L;
   PrintWriter pw = null;
   protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      request.setCharacterEncoding("utf-8");
      response.setContentType("text/html;charset=utf-8");
      
      try {
         this.pw = response.getWriter();
         
         //사용자 입력값 가져오기
         String mid = request.getParameter("mid");
         String mname = request.getParameter("mname");
         String mpass = request.getParameter("mpass");
         String memail = request.getParameter("memail");
         String mtel = request.getParameter("mtel");
         
         // 모든 값이 입력된 경우 -> 회원가입 완료 페이지 이동
         /*
         만약에 회원정보입력 창에서 submit - pattern 때렸으면 조건문 필요없이 바로 
         request.setAttribute / RequestDispatcher /forward 입력하면 됨
            */
            if (mid != null && !mid.equals("") &&
                mname != null && !mname.equals("") &&
                mpass != null && !mpass.equals("") &&
                memail != null && !memail.equals("") &&
                mtel != null && !mtel.equals("")) {
            /////////////////////////////////////////////   
                request.setAttribute("mid", mid);
                request.setAttribute("mname", mname);
                request.setAttribute("memail", memail);
                request.setAttribute("mtel", mtel);

                // 회원가입 완료 페이지로 이동
                RequestDispatcher rd = request.getRequestDispatcher("./agreeok_2.jsp");
                rd.forward(request, response);

            }else {
            this.pw.write("<script>"
                  + "alert('모든 항목을 입력해야 합니다.');" // 알림창 띄우기
                  + "history.go(-1);" // 이전 페이지로 이동
                  + "</script>");
                  return;
         }
         //////////////////////////////////////////////
         
      }catch (Exception e) {
         System.out.println(e);
         this.pw.write("<script>" 
               + "alert('올바른 접근방식이 아닙니다.');" 
               + "history.go(-1);" 
               + "</script>");
      }finally {
         this.pw.close();
      }
   }
}

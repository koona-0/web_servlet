package shop;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class idcheck extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Connection con = null;
       
	//ajax 통신을 받는 메소드 (아이디 중복체크) 
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String msg = "";	//Front-end에게 결과값을 보내는 변수 
		//Back-end가 Front-end에게 결과값을 통보하는 역할 
		PrintWriter pw = response.getWriter();
		
		try{
			//Ajax로 Front-end가 보낸 데이터를 받는 역할 
			String id = request.getParameter("sid");
			
			//여기에 null일 경우를 핸들링하지않고 예외처리로 빼줘도 됨! 
			//if(id.equals("") || id.equals(null))
			if(id.equals("")) {	//여기 null만쓰면 공백 입력시 해당하지 않음!!
				msg = "error";
			}else {
				m_dbinfo db = new m_dbinfo();	//모델 로드
				//db사용시 try catch 필요 //싫으면 throws에 D어쩌고 추가 
				this.con = db.getConnection();	//Database 연결 시작
				
				/*
				sql query문 작성 방법 ⭐️ 외우기 
				1. select => ResultSet, executeQuery
				2. insert, update, delete => int, executeUpdate 
				*/
				//select sid from shop_member where sid='변수명';
				String sql = "select count(*) as ctn from shop_member where sid='"+id+"';";
				
				//Statement : Database에 쿼리문을 작성할 수 있도록 사용하는 메소드 
				//createStatement() : create, alter, drop //이거 보안 쓰레기라 사실 요즘 안씀  
				Statement st = this.con.createStatement();
				
				//ResultSet : 결과값을 받는 역할 select만 사용 
				//executeQuery : ResultSet와 세트 
				ResultSet rs = st.executeQuery(sql);	//select에 사용하는 명령어 
				
				//쿼리문 잘쓴건가 싶을때 그냥 찍어보기
				//System.out.println(sql);
				
				//조건문을 사용한 경우	(한번만 쓸 때 사용 => count나 sum (값이 하나만 나올때)사용시에만 사용) 
				if(rs.next()==true) {	//정상적으로 query문이 작동했을 경우 
					if(rs.getString("ctn").equals("0")) {	//해당 데이터가 있을 경우 //⭐연산기호 사용 불가능@! 꼭쓰고싶다면 .intern()쓰기 
						msg = "ok";
					}else {	//검색한 데이터가 있을 경우 
						msg = "no";
					}
					System.out.println(rs.getString("ctn"));
				}
				//else의 경우 (필요없음 오류발생시 exception으로 빠짐) 
				
				rs.close();
				st.close();
				/*
				//반복문을 사용한 경우 (모든 경우 사용 가능)
				while (rs.next()) {	//rs.next() : 결과값에 대해서 반복문이 실행 (true, false) 
					System.out.println(rs.getString("ctn"));
				}
				*/
			}
			pw.write(msg);
	
		}catch (NullPointerException ne) {	//Front-end가 올바른 값을 전달하지 않을 경우 
			msg = "error";
			pw.write(msg);	//ok : 사용가능 아이디, no : 사용불능 아이디, error : 데이터 오류
		}catch (Exception e) {
			//프론트가 무시할까봐 혼자 몰래 고치려고 ㅜㅜ 
			msg = "DBS error";	//실무에서 백엔드 잘못시 백엔드만 아는 비밀 메세지 ㅎ  "Mysql-CODE 844" : 선생님만의 sql쿼리 오류 비밀코드 
			System.out.println(e);
			pw.write(msg);
		}finally {
			pw.close();
			
		}		
		
	}

}

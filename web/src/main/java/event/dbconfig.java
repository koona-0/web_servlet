package event;

import java.sql.Connection;
import java.sql.DriverManager;

//database 환경설정 및 셋팅값 
public class dbconfig {
	
	//Connection 임포트는 sql 인터페이스! 
	public static Connection info() throws Exception{
		//String database = "com.mysql.jdbc.Driver";	//v5.1, v5.5
		//mysql version에 연결 설정 파트 
		String database = "com.mysql.cj.jdbc.Driver";	//v5.7 ~ 
		
		/*mysql 연결 경로 
		 *  db연결할때 쓴 경로 
		 *  포트 바꿀때 같이 바꿔줘야함
		 *  남에거에 저장할땐 localhost 대신 남의 아이피 넣기
		 */
		String dburl = "jdbc:mysql://localhost:3306/mrp";
		String user = "project";		//mysql 아이디 
		String passwd = "p402402";		//mysql 패스워드 
		
		Class.forName(database);	//어떤 라이브러리를 이용하여 DB에 접속할 것인지를 정함 
		Connection con = DriverManager.getConnection(dburl,user,passwd);	//mysql -u 아이디 -p 랑 같은 역할
		
		return con;
	}
}

package shop;

import java.sql.Connection;
import java.sql.DriverManager;

//오라클이면 com.뒤에 오라클쓰면됨 
import com.mysql.cj.jdbc.Driver;
//임포트 해서 조금씩 치다보면 자동완성에 다 뜨니 외우지 않아도됨 

//Database, 사용자 아이디, 사용자 패스워드
//db이용시 주의사항 : static으로 메모리에 올려야함!!
public class m_dbinfo {
	
	//Database의 기본 메소드명 : getConnection
	//메소드 이름 바꿔도됨 
	public static Connection getConnection() throws Exception{
		/* 실무에서 사용하는 두가지 방법 
		 * 1. StringBuilder 사용
		 * 2. Map 사용 -> 보안강화 
		 * 
		 * 책에선 String을 사용하는데 보안쓰레기에 실무에서 쓰지도않음 
		StringBuilder sb;
		sd.append();
		
		Map<K, V>
		*/
		
		//해당 라이브러리 복붙
		//해당 라이브러리를 가져오는 역할 변수 
		String db_driver = "com.mysql.cj.jdbc.Driver";
		
		//데이터베이스 프로퍼티스 가서 복붙해오기
		/*데이터베이스 경로 연결 
		jdbc:mysql:// => ip또는 도메인 
		: 포트번호 (현재 3306) //회사에선 보안때문에 도메인 바꿔서 씀 실무에서 여기 번호 따라서 바꾸기
		 */
		String db_url = "jdbc:mysql://localhost:3306/mrp";
		
		//mysql에 접속하는 사용자 
		String db_user = "project";
		
		//mysql에 접속하는 사용자의 패스워드 
		String db_passwd = "a123456";
		
		//실제 Database에 연결을 하는 명령어 
		Class.forName(db_driver);
		Connection con = DriverManager.getConnection(db_url,db_user,db_passwd);
		
		//안써도 되는데 한번 확인해보기 
		//데이터베이스 잘 연결됐는지 확인하기!
		//System.out.println(con);
		
		return con;	//데이터베이스가 정상적으로 연결의 되었는지 확인한 결과를 return
	}
}

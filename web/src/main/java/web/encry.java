package web;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//Controller
public class encry extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String passwd = "940";
		//해당 문자를 base64를 이용하여 encode(암호화) 
		encry_model em = new encry_model();	//암호화 model 클래스 로드 
		String result = em.dataencode(passwd);	//해당 메소드에 문자값을 전달 
		System.out.println(result);	//최종 암호화된 사항을 출력 
		
		//base64로 변환된 문자를 decode(복호화) 이용하여 원본 문자로 변환 
		String repasswd = "OTQw";
		String result2 = em.datadecode(repasswd);
		System.out.println(result2);
		//=> base64요즘 잘 안쓰임 get으로 보낼때나 이메일에 사용  
		
		//md5 암호화 알고리즘 
		String result3 = em.md5_encode(passwd);
		System.out.println(result3);
		//md5는 복호화가 불가능 바로 DB에 저장 
		//사실 해커가 마음먹으면 가능 그래도 base64보다 안전
		
		String result4 = em.sha_encode2(passwd);
		System.out.println(result4);
		
	}
}

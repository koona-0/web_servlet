package admin;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

//front가 enctype설정시 무조건 셋팅해야만 정상적으로 페이지가 활성화됨 
@MultipartConfig(
		fileSizeThreshold = 1024*1024*10,	//모바일은 고화질일수있어서 10메가로 낭낭하게 잡아줌 
		maxFileSize = 1024*1024*50,
		maxRequestSize = 1024*1024*100
)
public class mobileok extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		//언어셋은 외부클래스 ㄴㄴ 여기서 하기 
		req.setCharacterEncoding("utf-8");
		res.setContentType("text/html;charset=utf-8");
		
		String se = req.getParameter("security");	//html의 input hidden
		System.out.println(se);
		
		if(!se.equals("m")) {	//hidden의 값이 다를 경우 
			PrintWriter pw = res.getWriter();
			//언어셋 안해서 그냥 영어씀
			pw.write("<script>" 
					+ "alert('Error!!');" 
					+ "history.go(-1);" 
					+ "</script>");
		}else {	//hidden이 맞을 경우 
			try {
				new reviews(req,res);	//데이터를 핸들링함 				
			}catch (Exception e) {
				System.out.println(e);
			}
		}
	}
}

//외부 클래스 사용 
class reviews{	//외부 클래스 사용시 한글 깨짐 처리는 메인 doPost, doGet에서 처리함 
	PrintWriter pw = null;
	public reviews(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException{
		this.pw = res.getWriter();
		String mname = req.getParameter("mname");	//고객명 
		String pname = req.getParameter("pname");	//상품명
		String star = req.getParameter("star");		//별점
		String mtext = req.getParameter("mtext");	//리뷰 내용
		Part p = req.getPart("mfile");				//리뷰 이미지
		String filename = p.getSubmittedFileName();	//리뷰 이미지의 이름
		
		/*
		//디렉토리가 없을 경우 
		String ori = req.getServletContext().getRealPath("/review2/");
		File f = new File(ori);
		f.mkdir();
		*/	
		
		if(filename != "") {	//이미지가 첨부되었을 경우 
			String url = req.getServletContext().getRealPath("/review/");
			p.write(url + filename);
		}
		
		//historygo-1 쓰면 전에 작성한게 남아있음
		//location.href 전에 작성한거 초기화 
		pw.write("<script>" 
				+ "alert('정상적으로 리뷰가 등록되었습니다.');" 
				+ "location.href='./mobile.html'" 
				+ "</script>");
		
		System.out.println(mname);
	}
}


/*
/Users/nayeong/Documents/web_servlet/.metadata/.plugins/org.eclipse.wst.server.core/tmp0/wtpwebapps/web/
*/

















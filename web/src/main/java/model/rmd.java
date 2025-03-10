package model;

import java.util.ArrayList;
import java.util.Random;

//Model (난수를 생성하는 모델)
public class rmd {
	ArrayList<String> num = null;
	int ea = 0;
	
	public rmd(int n) {
		this.ea = n;	//랜덤 생성할 숫자 자릿수 => 자동등록방지, 게시보안코드 등등 사용 
		this.num = new ArrayList<String>();	
	}
	
	public ArrayList<String> make_num(){	//난수를 생성하는 메소드 
		//Random 클래스 사용
		Random rd = new Random();
		int w = 1;
		while(w <= this.ea) {
			this.num.add(String.valueOf(rd.nextInt(10)));	//0~9	
			w++;
		}
		return this.num;
	}
}

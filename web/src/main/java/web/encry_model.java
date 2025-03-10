package web;

import java.security.MessageDigest;
import java.util.Base64;
import java.util.Base64.Decoder;
import java.util.Base64.Encoder;

//문자를 암호화하는 모델  
public class encry_model {
	public String dataencode(String word) {	//base64 암호화 
		Encoder ec = Base64.getEncoder();
		String security = ec.encodeToString(word.getBytes());	//encodeToString(바이트) => 스트링 
		
		return security;
	}
	public String datadecode(String word) {	//base64 복호화 
		Decoder dc = Base64.getDecoder();
		/*
		byte dec[] = dc.decode(word);
		String security = new String(dec);
		 */
		byte dec[] = dc.decode(word);
		String security = new String(dc.decode(word));
		return security;
	}
	
	/*MD5 : %02x 기본 == %x, %01
	 * 		%03x 부터 변함 
	 * 		x : 소문자 X : 대문자 	
	*/		
	public String md5_encode(String word) {
		String security = "";
		try {
			//MessageDigest : 암호화 클래스 구성 형태를 가지고있는 라이브러리 
			MessageDigest md = MessageDigest.getInstance("md5");
			md.update(word.getBytes());	//해당 암호화 기준으로 byte로 문자를 변환 
			byte md5byte[] = md.digest();	//byte 원시배열에 해당 암호화된 byte들을 저장 
			
			StringBuilder sb = new StringBuilder();	//문자열 클래스로 연속저장 
			for(byte s : md5byte) {	//for each를 이용하여 연속 저장 
				sb.append(String.format("%02x", s));	
				//%02x : 2자리 문자 => 1234의 경우 01, 02, 03, 04
				//%01x : 1자리 문자 => 1234의 경우 1, 2, 3, 4
				//%05x : 5자리 문자 => 00001, 00002, 00003, 00004
			}
			security = String.valueOf(sb);
		}catch (Exception e) {
			security = "md5 Error!";
		}
		return security;
	}

	/*
	 * 블록체인 기술 사용
	 * sha-1 : 16진수 40자로 생성되는 암호화 기술 
	 *			%01x, %02x, %03x 모두 다름  
	 * sha-2 : sha-224, sha-256, sha-384, sha-512 
	 * sha-3 : sha3-224, sha3-256, sha3-384, sha3-512	//높은 jdk 버전 필요 요즘 많이 사용
	 */
	public String sha_encode2(String word) {
		String security = "";
		try {
			/* getInstance("sha-1")
			 * "sha-224"
			 * "sha-516"
			 */
			MessageDigest sha = MessageDigest.getInstance("sha-1");
			sha.update(word.getBytes());	
			byte shabyte[] = sha.digest();	 
			
			StringBuilder sb = new StringBuilder();	
			for(byte s : shabyte) {	 
				sb.append(String.format("%02x", s));	
			}
			security = String.valueOf(sb);
		}catch (Exception e) {
			security = "sha Error!";
		}
		return security;
	}
	
	//복합 암호화 기술 => base64 + md5
	//복합 암호화 기술 => md5 + sha-2 => 하나뚫는데 22시간 걸림 
	
}

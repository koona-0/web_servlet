function partcheck(part){
	var snoview = document.getElementById("snoview");
	
	if(part=="P"){
		snoview.style.display = "none";	
	}else{
		snoview.style.display = "block";	
	}
}


function loginck(){
	if(frm.sid.value==""){
		alert("아이디를 입력하세요");
		return false;
	}else if(frm.spw.value==""){
		alert("패스워드를 입력하세요");
		return false;
	}else{
		if(frm.spart[0].checked == true){	//일반회원 체크 
			//보내는 3가지 방법
			//frm.submit();
			//return true;
			return;
		}else if(frm.spart[1].checked == true){	//사업자회원 체크 
			if(frm.sno.value == ""){
				alert("사업자 번호를 입력하세요");
				return false;
			}else if(frm.sno.value.length < 10){
				alert("올바른 사업자 번호를 입력하세요");
				return false;
			}else{
				return true;
			}
		}
		
	}
	
}

function find_id(){
	
	location.href="./find_id.jsp";
	
}


/*
var  find_id = function(){
	location.href="./find_id.jsp";
}


var find_pw =function(){
	location.href="./find_pw.jsp";
}


*/









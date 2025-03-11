function ajax_file(){	//전송 버튼 클릭시 이벤트 함수 
	var mfile = document.getElementById("mfile");
	
	if(mfile.value == ""){
		alert("파일을 첨부해주세요");
	}else {
		this.ajax_post(mfile);	//ajax post 통신을 위한 함수 
	}
}

//ajax I/O로 파일 전송하는 방식 
function ajax_post(mfile){
	var http,result;
	var formdata = new FormData();	//form태그를 이용하는 방식과 동일 
	formdata.append("mfile",mfile.files[0]);	//배열 기준으로 파일을 처리 //0넣은 이유는 파일 하나만 써서 
	
	http = new XMLHttpRequest();
	http.onreadystatechange = function(){
		if(http.readyState == 4 && http.status == 200){
			console.log(this.response);		
		}
	}
	//파일 전송하니까 POST로 전송 
	http.open("post","./ajax_fileok.do", true);
	//http.setRequestHeader("content-type","multipart/form-data");	//formdata사용시 필요없음 
	http.send(formdata);	//FormData 함수의 값을 send 함수에 인자값으로 적용하여 전송 
}
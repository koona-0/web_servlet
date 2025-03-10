//게시물 등록시 체크하는 함수 
//여기에 texts 게시판 내용을 체크하면 망가짐 => 내용을 넣어도 안넣었다고 인식 ! 주의 
function writeck(){
	if(frm.subject.value==""){
		alert("제목을 입력하셔야 합니다.");
		frm.subject.focus();
	}else if(frm.writer.value==""){
		alert("글쓴이를 입력하셔야 합니다.");
		frm.writer.focus();
	}else if(frm.pw.value==""){
		alert("비밀번호를 입력하셔야 합니다.");
		frm.pw.focus();
	}else {
		//CKEDITOR.instances.HTML의 id명.getData() : ckeditor를 로드
		var txt = CKEDITOR.instances.editor.getData();
		if(txt == ""){
			alert("내용을 입력하셔야합니다.")
		}else if(txt.length < 40){	//엔터바바박 방지 
			alert("최소 40자 이상 입력되어야 합니다.");
		}else{
			frm.submit();
		}
	}
}
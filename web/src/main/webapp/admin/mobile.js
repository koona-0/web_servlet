//ECMAScript 이벤트 핸들링 함수 (js아니고 es임!)
//touchstart, touchend. touchmove, touchcancel, click, change, submit, keypress, keyup, keydown
document.getElementById("btn").addEventListener("touchstart",function(){
	//alert("test1");
	reviews();
});


var reviews = function(){
	if(frm.mname.value==""){
		alert("고객명을 입력하세요");
		frm.mname.focus();
	}else if(frm.pname.value==""){
		alert("상품명을 입력하세요");
		frm.pname.focus();
	}else if(frm.mtext.value==""){
		alert("리뷰 내용을 입력하세요");
		frm.mtext.focus();
	}else{
		frm.submit();
	}
}
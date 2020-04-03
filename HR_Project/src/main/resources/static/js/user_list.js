//모두 체크, 체크해제 시키는 메소드
function allchk(){
	var all_chk = document.getElementById('all_chk');
	var chk_box = document.getElementsByClassName('chk_box');
	if(all_chk.checked){		
		for(var i = 0; i < chk_box.length ; i++){
			chk_box[i].checked = true;
		}
	}else{
		for(var i = 0; i < chk_box.length ; i++){
			chk_box[i].checked = false;
		}
	}
}




//정렬 아이콘 설정
var icon_type;	//어느 필드의 아이콘
var icon_direction;	//아이콘의 방향

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

//필드 클릭시 정렬 속성을 정해주는 메소드
function sort(input_sortType){

	if(input_sortType === sortType){
		if(sortDirection === 'desc'){
			sortDirection = 'asc';
		}else if(sortDirection === 'asc'){
			sortDirection = 'desc';
		}
	}else{
		sortType = input_sortType;
		sortDirection = 'desc';
	}
}

//필드 클릭 시 아이콘 설정값을 주는 메소드
function select_icon(inputIconType,inputIconNum){
	icon_type = inputIconType;
	icon_direction = inputIconNum;
}

function init_icon(){
	sortType = 'id';
	sortDirection = 'desc';
	icon_type = 'init';
}


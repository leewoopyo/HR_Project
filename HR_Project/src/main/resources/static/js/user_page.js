
//검색 조건과 검색어를 저장
function saveparam(){
	select_column = document.getElementById('select_column');
	find_str = document.getElementById('find_str').value;
	
	//selected 뽑는 반복문
	for(i=0; i<select_column.options.length; i++) {
	    if(select_column.options[i].selected == true) {
	        val = select_column.options[i].value;
	        break;
	    }
	}	
}

//리스트 조회하는 메소드
function sendparam(pageNum,startPage){
	
	//값 잘 나오나 확인
	console.log("검색조건 : " + val);
	console.log("검색어 : " +  find_str);
	console.log("페이지 : " +  pageNum);
	console.log("시작페이지 : " +  startPage);
	console.log("정렬 타입 : " +  sortType);
	console.log("정렬 방향 : " +  sortDirection);
	console.log("---------------------");
	
	//전체 조회를 제외한 나머지 검색은 검색어가 공백이면 입력하라는 경고창이 나옴
	if(val !== 'all'){
		if(find_str === '')
			alert('검색어를 입력해주세요.');
	}
	
	//selected값에 따라서 출력할 list를 다르게 함
	if(val === 'all'){
		$("#list").load("./user/list/"+pageNum+"/"+startPage+"/"+sortType+"/"+sortDirection);
	}else{
		$("#list").load("./user/onelist/"+val+"/"+find_str+"/"+pageNum+"/"+startPage+"/"+sortType+"/"+sortDirection);
	}
}

//삭제 할 파라메터를 넘기는 메소드
function delete_user(){
	
	let chk_box = document.getElementsByClassName("chk_box");	//체크박스 변수(해당 페이지)
	let index_num = document.getElementsByClassName('index_num');	//체크된 데이터의 번호 
	let username_list = document.getElementsByClassName("username_list");	//체크된 데이터의 아이디 값
	let param;  
	let param_list = [];
	let param_json;
	
	//모든 데이터에서 체크된 리스트 안에 해당 페이지의 체크된 값을 넣음 
	for(let i=0; i < chk_box.length; i++){
		if(chk_box[i].checked === true){
			checked_list.push(index_num[i].innerHTML);
			checked_username_list.push(username_list[i].innerText);
		}
	}
	
	//전체 체크된 데이터를 각각 json형식으로 변환시킴
	for(let i = 0;i < checked_username_list.length; i++){
		param = {username : checked_username_list[i]};
		param_list.push(param);
	}
	
	//json 형식으로 변환된 값을 리스트를 가진 json형태의 데이터로 변환시키고, 페이지 이동
	param_json = {deleteuUser : JSON.stringify(param_list)}
	location.href="./user/delete_users/" + $.param(param_json);
}

//체크된 인덱스를 저장하기
function save_chk(){
	let chk_box = document.getElementsByClassName('chk_box');
	let index_num = document.getElementsByClassName('index_num');
	let username_list = document.getElementsByClassName("username_list");

	//리스트에 데이터 삽입 
	for(let i=0; i < chk_box.length; i++){
		if(chk_box[i].checked === true){
			checked_list.push(index_num[i].innerHTML);
			checked_username_list.push(username_list[i].innerText);
		}
	}
	console.log(checked_list);
	console.log(checked_username_list);
}
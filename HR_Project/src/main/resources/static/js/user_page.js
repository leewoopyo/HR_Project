//선택할 검색 조건
var select_column;
//검색할 검색어 
var find_str;
//selected 뽑는 변수
var val;

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
	
	if(val !== 'all'){
		if(find_str === '')
			alert('검색어를 입력해주세요.');
	}
	
	//selected값에 따라서 출력할 list를 다르게 함
	if(val === 'all'){
		$("#list").load("./user/list/"+pageNum+"/"+startPage);
	}else{
		$("#list").load("./user/onelist/"+val+"/"+find_str+"/"+pageNum+"/"+startPage);
	}
}

function delete_user(){
	
	let checkbox = document.getElementsByClassName("chk_box");
	let username_list = document.getElementsByClassName("username_list");
	let param;  
	let param_list = [];
	let param_json;
	
	console.log(checkbox);
	console.log(username_list);
	
	for(let i = 0;i < checkbox.length; i++){
		if(checkbox[i].checked === true){
			//console.log(i);
			//console.log(username_list[i].innerHTML);
			param = {username : username_list[i].innerHTML};
			param_list.push(param);
			console.log(param);
		}
	}
	console.log(param_list);
	param_json = {deleteuUser : JSON.stringify(param_list)}
	console.log(param_json);

	location.href="./user/delete_user/" + $.param(param_json);
}
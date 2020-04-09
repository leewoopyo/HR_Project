var chk_username = false;
var chk_password = false;
var chk_name = false;
var chk_birth = false;
var chk_tel = false;
var chk_email = false;
var chk_stype = false;

////아이디 유효성 검사
function check_username() {
	let username = document.getElementById("username").value.replace(/ /gi,"");
	let username_validation = document.getElementById("username_validation");
	let Params = {
		"username" : document.getElementById('username').value
	};
	//빈칸 체크, 글자수 체크 , 아이디 중복 체크
	if (username === '') {
		username_validation.style.color = 'red';
		username_validation.innerHTML = '아이디를 입력하세요';
		chk_username = false;
	} else if (username.length < 8) {
		username_validation.style.color = 'red';
		username_validation.innerHTML = '아이디는 글자 수 8~15까지 가능합니다';
		chk_username = false;
	} else {
		$.ajax({
			url : "./user/username_check",
			type : "get",
			data : Params,
			success : function(responsedata) {
				if (responsedata.message === '사용가능한 아이디입니다.') {
					username_validation.style.color = 'blue';
					username_validation.innerHTML = responsedata.message
					chk_username = true;
				} else {
					username_validation.style.color = 'red';
					username_validation.innerHTML = responsedata.message
					chk_username = false;
				}
			},
			error : function(request, status, error) {
				console.log("AJAX_ERROR");
			}
		});
	}
}
//
////비밀번호 일치 확인
function check_password() {
	let password = document.getElementById("password").value;
	let password_confirm = document.getElementById("password_confirm").value;
	let password_validation = document.getElementById("password_validation");
	let password_confirm_validation = document
			.getElementById("password_confirm_validation");

	if (password === '') {
		password_validation.style.color = "red";
		password_validation.innerHTML = "비밀번호를 입력하세요.";
		chk_password = false;
	} else if (password === password_confirm) {
		password_validation.style.color = "blue";
		password_validation.innerHTML = "비밀번호가 일치합니다.";
		password_confirm_validation.style.color = "blue";
		password_confirm_validation.innerHTML = "비밀번호가 일치합니다.";
		chk_password = true;
	} else {
		password_validation.style.color = "red";
		password_validation.innerHTML = "비밀번호가 일치하지 않습니다..";
		password_confirm_validation.style.color = "red";
		password_confirm_validation.innerHTML = "비밀번호가 일치하지 않습니다.";
		chk_password = false;
	}
}
//
////이름 한글 체크
function check_name() {
	let name = document.getElementById("name").value.replace(/ /gi,"");
	const validation_korean = /[a-z0-9ㄱ-ㅎㅏ-ㅣ]|[ \[\]{}()<>?|`~!@#$%^&*-_+=,.;:\"'\\]/g;
	let name_validation = document.getElementById("name_validation");

	if (name == '') {
		name_validation.innerHTML = "이름이 공백입니다.";
		chk_name = false;
	} else if (validation_korean.test(name)) {
		name_validation.innerHTML = "이름은 한글만 가능합니다.";
		chk_name = false;
	} else if (name.length <= 1) {
		name_validation.innerHTML = "이름은 2글자 이상 이어야 합니다.";
		chk_name = false;
	} else {
		name_validation.innerHTML = "";
		//검사에 통과하면 체크하는 변수를 true로 한다.
		chk_name = true;
	}
}

//생년월일 체크
function check_bitrh(){
	let birth = document.getElementById("birth").value;
	let birth_validation = document.getElementById("birth_validation");
	if(birth === ''){
		birth_validation.innerHTML = '생년월일을 입력해 주세요 .';
		chk_birth = false;
	}else{
		birth_validation.innerHTML = '';
		chk_birth = true;
	}
}

//전화번호 입력 시 4글자 제한 및 포커스 이동 기능
function next_focus() {
	let tel_validation = document.getElementById("tel_validation");
	tel_validation.innerHTML = "";

	let telnum2 = document.getElementById("telnum2");
	let telnum3 = document.getElementById("telnum3");

	//길이 제한
	//maxlength는 태그에서 설정이 되어 있다 (4)
	if (telnum2.value.length > telnum2.maxLength) {
		telnum2.value = telnum2.value.slice(0, telnum2.maxLength);
	}
	//길이 제한
	//maxlength는 태그에서 설정이 되어 있다 (4)
	if (telnum3.value.length > telnum3.maxLength) {
		telnum3.value = telnum3.value.slice(0, telnum3.maxLength);
	}
	//중간 번호 적는 곳에 길이가 4 이상이 되면 뒤 태그로 포커스가 이동한다.
	if (telnum2.value.length >= 4) {
		telnum3.focus();
	}
}

//이메일
function check_eMail(){
	//이메일 값
	let eMail_front = document.getElementById("eMail_front").value;
	let eMail_end = document.getElementById("eMail_end");
	let eMail_end_val = eMail_end.options[eMail_end.selectedIndex].value;
	let eMail_val = eMail_front + '@' + eMail_end_val;
	let eMail = document.getElementById("eMail");
	eMail.value = eMail_val;
	let eMail_validation = document.getElementById("eMail_validation");

	//이메일 유효성 검사
	if (eMail_front === '') {
		eMail_validation.innerHTML = "이메일을 입력하세요.";
		chk_email = false;
	}
	else {
		eMail_validation.innerHTML = "";
		chk_email = true;
	}
}



function insert() {
	let insert_frm = document.getElementById('insert_frm');

	//1.-------------------------------------------------------
	//아이디 체크
	let username = document.getElementById("username").value;
	let username_validation = document.getElementById("username_validation");
	let Params = {
		"username" : document.getElementById('username').value
	};
	//빈칸 체크, 글자수 체크 , 아이디 중복 체크
	if (username === '') {
		username_validation.style.color = 'red';
		username_validation.innerHTML = '아이디를 입력하세요';
		chk_username = false;
	} else if (username.length < 8) {
		username_validation.style.color = 'red';
		username_validation.innerHTML = '아이디는 글자 수 8~15까지 가능합니다';
		chk_username = false;
	} else {
		$.ajax({
			url : "./user/username_check",
			type : "get",
			data : Params,
			success : function(responsedata) {
				if (responsedata.message === '사용가능한 아이디입니다.') {
					username_validation.style.color = 'blue';
					username_validation.innerHTML = responsedata.message
					chk_username = true;
				} else {
					username_validation.style.color = 'red';
					username_validation.innerHTML = responsedata.message
					chk_username = false;
				}
			},
			error : function(request, status, error) {
				console.log("AJAX_ERROR");
			}
		});
	}
	
	//2.-------------------------------------------------------
	//비밀번호 체크
	let password = document.getElementById("password").value;
	let password_confirm = document.getElementById("password_confirm").value;
	let password_validation = document.getElementById("password_validation");
	let password_confirm_validation = document
			.getElementById("password_confirm_validation");

	if (password === '') {
		password_validation.style.color = "red";
		password_validation.innerHTML = "비밀번호를 입력하세요.";
		chk_password = false;
	} else if (password === password_confirm) {
		password_validation.style.color = "blue";
		password_validation.innerHTML = "비밀번호가 일치합니다.";
		password_confirm_validation.style.color = "blue";
		password_confirm_validation.innerHTML = "비밀번호가 일치합니다.";
		chk_password = true;
	} else {
		password_validation.style.color = "red";
		password_validation.innerHTML = "비밀번호가 일치하지 않습니다..";
		password_confirm_validation.style.color = "red";
		password_confirm_validation.innerHTML = "비밀번호가 일치하지 않습니다.";
		chk_password = false;
	}
	
	//3.-------------------------------------------------------
	//이름 체크
	let name = document.getElementById("name").value;
	const validation_korean = /[a-z0-9ㄱ-ㅎㅏ-ㅣ]|[ \[\]{}()<>?|`~!@#$%^&*-_+=,.;:\"'\\]/g;
	let name_validation = document.getElementById("name_validation");

	if (name == '') {
		name_validation.innerHTML = "이름이 공백입니다.";
		chk_name = false;
	} else if (validation_korean.test(name)) {
		name_validation.innerHTML = "이름은 한글만 가능합니다.";
		chk_name = false;
	} else if (name.length <= 1) {
		name_validation.innerHTML = "이름은 2글자 이상 이어야 합니다.";
		chk_name = false;
	} else {
		name_validation.innerHTML = "";
		//검사에 통과하면 체크하는 변수를 true로 한다.
		chk_name = true;
	}
	
	
	
	//4.-------------------------------------------------------
	//생년월일 체크
	let birth = document.getElementById("birth").value;
	let birth_validation = document.getElementById("birth_validation");
	if(birth === ''){
		birth_validation.innerHTML = '생년월일을 입력해 주세요 .';
		chk_birth = false;
	}else{
		birth_validation.innerHTML = '';
		chk_birth = true;
	}
	
	
	//5.-------------------------------------------------------
	//전화번호 값
	let telnum0 = document.getElementById("telnum1");

	//각 적힌 전화번호 값들을 가져오고
	let telnum1 = telnum0.options[telnum0.selectedIndex].value;
	let telnum2 = document.getElementById("telnum2").value;
	let telnum3 = document.getElementById("telnum3").value;
	//각 문자를 조합한다. 
	let tel = telnum1 + '-' + telnum2 + '-' + telnum3;
	//유효성 검사 문구 
	let tel_validation = document.getElementById("tel_validation");
	//조합된 전화번호를 input태그에 value값으로 저장한다.
	document.getElementById("tel").value = tel;

	//전화번호 유효성 검사
	if (tel.length != 13) {
		tel_validation.innerHTML = "전화번호를 올바르게 입력하세요";
		chk_tel = false;
	} else {
		tel_validation.innerHTML = "";
		chk_tel = true;
	}

	//6.-------------------------------------------------------
	//이메일 값
	let eMail_front = document.getElementById("eMail_front").value;
	let eMail_end = document.getElementById("eMail_end");
	let eMail_end_val = eMail_end.options[eMail_end.selectedIndex].value;
	let eMail_val = eMail_front + '@' + eMail_end_val;
	let eMail = document.getElementById("eMail");
	eMail.value = eMail_val;
	let eMail_validation = document.getElementById("eMail_validation");

	//이메일 유효성 검사
	if (eMail_front === '') {
		eMail_validation.innerHTML = "이메일을 입력하세요.";
		chk_email = false;
	}
	else {
		eMail_validation.innerHTML = "";
		chk_email = true;
	}
	
	//7.-------------------------------------------------------
	//성별 결정
	let sType = document.getElementsByName("sType");
	
	for(let i=0; i < sType.length; i++){
		if(sType[i].checked === true){
			chk_stype = true;
			break;
		}else{
			chk_stype = false;
		}
	}

	console.log('username : ' + chk_username);
	console.log('password : ' + chk_password);
	console.log('name : ' + chk_name);
	console.log('birth : ' + chk_birth);
	console.log('tel : ' + chk_tel);
	console.log('email : ' + chk_email);
	console.log('sType : ' + chk_stype);
	console.log('--------------------------');

	if(chk_username === true){
		if(chk_password === true){
			if(chk_name === true){
				if(chk_birth === true){
					if(chk_tel === true){
						if(chk_email === true){
							if(chk_stype === true){
								console.log('submit');
								insert_frm.submit();
							}
						}
					}
				}
			}
		}
	}
}

//서밋하는 메소드
function update() {
	let update_frm = document.getElementById('update_frm');
	update_frm.submit();

	//3.-------------------------------------------------------
	//이름 체크
	let name = document.getElementById("update_name").value;
	const validation_korean = /[a-z0-9ㄱ-ㅎㅏ-ㅣ]|[ \[\]{}()<>?|`~!@#$%^&*-_+=,.;:\"'\\]/g;
	let name_validation = document.getElementById("name_validation");

	if (name == '') {
		name_validation.innerHTML = "이름이 공백입니다.";
		chk_name = false;
	} else if (validation_korean.test(name)) {
		name_validation.innerHTML = "이름은 한글만 가능합니다.";
		chk_name = false;
	} else if (name.length <= 1) {
		name_validation.innerHTML = "이름은 2글자 이상 이어야 합니다.";
		chk_name = false;
	} else {
		name_validation.innerHTML = "";
		//검사에 통과하면 체크하는 변수를 true로 한다.
		chk_name = true;
	}

	//4.-------------------------------------------------------
	//생년월일 체크
	let birth = document.getElementById("update_birth").value;
	let birth_validation = document.getElementById("birth_validation");
	if (birth === '') {
		birth_validation.innerHTML = '생년월일을 입력해 주세요 .';
		chk_birth = false;
	} else {
		birth_validation.innerHTML = '';
		chk_birth = true;
	}

	//5.-------------------------------------------------------
	//전화번호 값
	let telnum0 = document.getElementById("telnum1");

	//각 적힌 전화번호 값들을 가져오고
	let telnum1 = telnum0.options[telnum0.selectedIndex].value;
	let telnum2 = document.getElementById("telnum2").value;
	let telnum3 = document.getElementById("telnum3").value;
	//각 문자를 조합한다. 
	let tel = telnum1 + '-' + telnum2 + '-' + telnum3;
	//유효성 검사 문구 
	let tel_validation = document.getElementById("tel_validation");
	//조합된 전화번호를 input태그에 value값으로 저장한다.
	document.getElementById("update_tel").value = tel;

	//전화번호 유효성 검사
	if (tel.length != 13) {
		tel_validation.innerHTML = "전화번호를 올바르게 입력하세요";
		chk_tel = false;
	} else {
		tel_validation.innerHTML = "";
		chk_tel = true;
	}

	//6.-------------------------------------------------------
	//이메일 값
	let eMail_front = document.getElementById("eMail_front").value;
	let eMail_end = document.getElementById("eMail_end");
	let eMail_end_val = eMail_end.options[eMail_end.selectedIndex].value;
	let eMail_val = eMail_front + '@' + eMail_end_val;
	let eMail = document.getElementById("update_eMail");
	eMail.value = eMail_val;
	let eMail_validation = document.getElementById("eMail_validation");

	//이메일 유효성 검사
	if (eMail_front === '') {
		eMail_validation.innerHTML = "이메일을 입력하세요.";
		chk_email = false;
	} else {
		eMail_validation.innerHTML = "";
		chk_email = true;
	}

	//7.-------------------------------------------------------
	//성별 결정
	let sType = document.getElementsByName("update_sType");

	for (let i = 0; i < sType.length; i++) {
		if (sType[i].checked === true) {
			chk_stype = true;
			break;
		} else {
			chk_stype = false;
		}
	}

	console.log('name : ' + chk_name);
	console.log('birth : ' + chk_birth);
	console.log('tel : ' + chk_tel);
	console.log('eMail : ' + chk_email);
	console.log('sType : ' + chk_stype);
	console.log('--------------------------');

	if (chk_name === true) {
		if (chk_birth === true) {
			if (chk_tel === true) {
				if (chk_email === true) {
					if (chk_stype === true) {
						console.log('submit');
						insert_frm.submit();
					}
				}
			}
		}
	}
}


function setCaptchaCookie(){
    var cookie = getCookie("idcaptcha");
    document.getElementById("idcaptcha").value = cookie;
}
function readURL(input) {
	if (input.files && input.files[0]) {
 		var reader = new FileReader();
		reader.onload = function (e) {
			$('#avatarimage').attr('src', e.target.result).width(150).height(200);
		};
		reader.readAsDataURL(input.files[0]);
	}

}

function getCookie(name) {
	var cookie = " " + document.cookie;
	var search = " " + name + "=";
	var setStr = null;
	var offset = 0;
	var end = 0;
	if (cookie.length > 0) {
		offset = cookie.indexOf(search);
		if (offset != -1) {
			offset += search.length;
			end = cookie.indexOf(";", offset)
			if (end == -1) {
				end = cookie.length;
			}
			setStr = unescape(cookie.substring(offset, end));
		}
	}
	return(setStr);
}

function setFields(){
    var name=getParameterByName('name');
    var subscriptions=getParameterByName('subscriptions');
    var email=getParameterByName('email');
    if(name){
        $("#form_name").val(name);
    }
    if(email){
    $('#form_email').val(email);
    }
}

function getParameterByName(name, url) {
    if (!url) url = window.location.href;
    name = name.replace(/[\[\]]/g, "\\$&");
    var regex = new RegExp("[?&]" + name + "(=([^&#]*)|&|#|$)"),
        results = regex.exec(url);
    if (!results) return null;
    if (!results[2]) return '';
    return decodeURIComponent(results[2].replace(/\+/g, " "));
}
function getCookie(name) {
  var value = "; " + document.cookie;
  var parts = value.split("; " + name + "=");
  if (parts.length == 2) return parts.pop().split(";").shift();
}

var validateForm=function(){
	var error=getParameterByName('error');
	console.log('error'+error)
	var form = document.getElementById('form')
	return validate(form);
}

var validateFormJquery=function(){
	var form = $("#form").find(":input");
	return validate(form);
}

function validateEmail($email) {
  var emailReg = /^([\w-\.]+@([\w-]+\.)+[\w-]{2,4})?$/;
  return emailReg.test( $email );
}


var validatorsMap = {
	name: function(name) {
		if (name.length < 3) {
			$('#warning').html('Invalid name! Please enter more than 3 characters');
			return true
		}
	},
	email: function(email) {
		if (!validateEmail(email) || email.length < 3) {
			$('#warning').html('Invalid email! Please enter a valid email');
			return true
		}
	},
	password: function(password, rep_password) {
		if (password.length < 3) {
			$('#warning').html('Invalid password! Please enter more than 3 characters');
			return true
		}
		if (password != rep_password) {
			$('#warning').html('Passwords do not match!');
			return true
		}
	}
};

function validate(form){
	for (var i = 0; i < form.length - 1; i++) {
		if(validatorsMap[form[i].name](form[i].value, form[i+1].value)) {
			return false
		}
	}
}
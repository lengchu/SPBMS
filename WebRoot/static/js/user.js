function login() { //在表单提交按钮上添加 onclick="login()"属性
	var login = $("#login"); //获取相应表单
	
	login.submit(function() {
		return false; //关闭表单的自动提交
	});
	
	/**
	 * login.attr('action')	表单的action属性 即post的url
	 * login.serialize()	表单需提交的数据
	 */
	$.post(login.attr('action'), login.serialize(), function(data) { //回调函数
		// 返回的数据 {"msg":"登录失败","statu":"0","error":"用户名或密码错误"} 是文本
		var jsondata = eval("(" + data + ")"); // 把返回文本转换成json对象
		if(jsondata.statu === "1") {
			window.location.href = "index.html";
//			alert(jsondata.msg);
		} else {
			alert(jsondata.msg);
			alert(jsondata.error);
			// TODO
		}
	});
}
function reg() {
	var reg = $("#reg");
	reg.submit(function() {
		return false;
	});
	$.post(reg.attr('action'), reg.serialize(), function(data) {
		var jsondata = eval("(" + data + ")");
		if(jsondata.statu === "1") {
			alert(jsondata.msg);
			window.location.href = "index.html";
		} else {
			alert(jsondata.msg);
			alert(jsondata.error);
			// TODO
		}
	});
}

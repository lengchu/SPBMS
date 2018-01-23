var index = {};

window.onload = function() {
	$.post("UserServlet?action=getLoginedUser", function(data) {
		var user = eval("(" + data + ")");
		index.user = user;
		if(user != "") {
			var user_div = $("#user");
			user_div.html(user.username);
			user_div.attr("onclick", "user_details()");
			user_div.parent().append(`<a href="javascript:void(0)" onclick="logout()">logout</a>`);
		}
	});
	create_table();
}

function logout() {
	$.post('UserServlet?action=logout');
	window.location.reload();
}

function updateUser() {
	$.post("UserServlet?action=getLoginedUser", function(data) {
		var user = eval("(" + data + ")");
		index.user = user;
	});
}

function user_details() {
	$('#btn-dialogBox').dialogBox({
		hasClose: true,
		hasBtn: true,
		confirmValue: '确定修改',
		confirm: function(){
			var form = $("#updateUser");
			var action = form.attr('action');
			var postdata = form.serialize();
			$.post(action, postdata, function(data) {
				var returndata = eval("(" + data + ")");
				updateUser();
				alert(returndata.msg);
			});
		},
		cancelValue: '取消',
		title: '用户信息',
		content: `<form action="UserServlet?action=update" id="updateUser" method="post">
					<input type="text" disabled="disabled" Name="username" value="${index.user.username}">
					<br>
					<input type="password" Name="originalPassword" placeholder="原始密码">
					<br>
					<input type="password" Name="password" placeholder="新密码">
					<br>
					<input type="text" Name="email" value="${index.user.email}">
					<br>
				  </form>`
	});
}

function create_table() {
	var $content = $("#content");
	var height = window.innerHeight - 77;
	$content.css("height", height);
	var table = $("<table></table>");
	var tr = $("<tr></tr>");
	var td1 = $("<td>书号</td>");
	var td2 = $("<td>书名</td>");
	var td3 = $("<td>作者</td>");
	var td4 = $("<td>价格</td>");
	var td5 = $("<td>购买时间</td>");
	var td6 = $("<td>操作</td>");
	tr.append(td1);
	tr.append(td2);
	tr.append(td3);
	tr.append(td4);
	tr.append(td5);
	tr.append(td6);
	table.append(tr);
	$content.append(table);
	table.attr('border', '1');
	table.attr('width', '95%');
	table.css('margin','20px');
	index.booktable = table;
	load_data(table);
}

function load_data(table) {
	var childs = table[0].childNodes;  
    for(var i=childs.length-1; i > 0; i--){      
        table[0].removeChild(childs.item(i));      
    }
	$.post('BookServlet?action=queryList', table.postdata, function(data) {
		var book_list = eval("(" + data + ")");
		index.book_list = new Map();
		for(var i = 0; i < book_list.length; i++) {
			var book = book_list[i];
			index.book_list.set(book.bid, book);
			var tr = $("<tr></tr>");
			var td1 = $("<td></td>");
			var td2 = $("<td></td>");
			var td3 = $("<td></td>");
			var td4 = $("<td></td>");
			var td5 = $("<td></td>");
			var td6 = $(`<td><a href='javascript:void(0)' onclick='update(this)'>修改</a>&nbsp;<a href='javascript:void(0)' onclick='del(this)'>删除</a></td>`);
			td6.attr("bid", book.bid);
			td1.html(book.bid);
			td2.html(book.bookname);
			td3.html(book.author);
			td4.html(book.price);
			td5.html(book.buytime);
			tr.append(td1);
			tr.append(td2);
			tr.append(td3);
			tr.append(td4);
			tr.append(td5);
			tr.append(td6);
			table.append(tr);
		}
		var ltr = $(`<tr></tr>`);
		var ltd = $("<td></td>");
		ltd.attr('colspan', '6');
		ltr.append(ltd);
		ltd.html(`共有${book_list.length}本书 &nbsp;<a href="javascript:void(0)" onclick="add()">添加</a>
		&nbsp;<a href="javascript:void(0)" onclick="search_book()">条件查找</a>`);
		table.append(ltr);
	});
}

function add() {
	$('#btn-dialogBox').dialogBox({
		hasClose: true,
		hasBtn: true,
		confirmValue: '确定添加',
		confirm: function(){
			var form = $("#addBook");
			var action = form.attr('action');
			var jsonbook = {};
			form.serializeArray().map(function(x){jsonbook[x.name]=x.value;});
			var postdata = `book=${JSON.stringify(jsonbook)}`;
			$.post(action, postdata, function(data) {
				var returndata = eval("(" + data + ")");
				alert(returndata.msg);
				load_data(index.booktable);
			});
		},
		cancelValue: '取消',
		title: '添加书籍',
		content: `<form action="BookServlet?action=save" id="addBook" method="post">
					<input type="text" Name="bid" placeholder="书号" required="">
					<br>
					<input type="text" Name="bookname" placeholder="书名" required="">
					<br>
					<input type="text" Name="author" placeholder="作者" required="">
					<br>
					<input type="text" Name="price" placeholder="价格" required="">
					<br>
					<input type="text" id="txtDate" Name="buytime" placeholder="购买时间" required="">
					<br>
				  </form>`
	});
}

function update(val) {
	var td = $(val).parent();
	var bid = td.attr("bid");
	var book = index.book_list.get(bid);
	$('#btn-dialogBox').dialogBox({
		hasClose: true,
		hasBtn: true,
		confirmValue: '确定修改',
		confirm: function(){
			var form = $("#updateBook");
			var action = form.attr('action');
			var jsonbook = {};
			form.serializeArray().map(function(x){jsonbook[x.name]=x.value;});
			var postdata = `bid=${bid}&book=${JSON.stringify(jsonbook)}`;
			$.post(action, postdata, function(data) {
				var returndata = eval("(" + data + ")");
				alert(returndata.msg);
				load_data(index.booktable);
			});
		},
		cancelValue: '取消',
		title: `修改书籍: ${book.bookname}`,
		content: `<form action="BookServlet?action=update" id="updateBook" method="post">
					<input type="text" Name="bid" value="${book.bid}" required="">
					<br>
					<input type="text" Name="bookname" value="${book.bookname}" required="">
					<br>
					<input type="text" Name="author" value="${book.author}" required="">
					<br>
					<input type="text" Name="price" value="${book.price}" required="">
					<br>
					<input type="text" Name="buytime" id="txtDate" value="${book.buytime}">
					<br>
				  </form>`
	});
}

function del(val) {
	var td = $(val).parent();
	var bid = td.attr("bid");
//	alert(bid);
	$.post("BookServlet?action=delete", `bid=${bid}`, function(data) {
		var returndata = eval("(" + data + ")");
//		if(jsondata.statu === "1") {}  // 删除成功
		alert(returndata.msg);
		load_data(index.booktable);
	});
}

//[{"bid":"1","bookname":"纳兰词","author":"苏缨","price":19.98,"buytime":"2018-01-03 21:04:42","uid":"admin"},
// {"bid":"2","bookname":"Java核心技术卷Ⅰ","author":"凯S.霍斯特曼","price":39.98,"buytime":"2018-01-03 20:42:36","uid":"admin"},
// {"bid":"3","bookname":"红楼梦","author":"曹雪芹","price":20.0,"buytime":"2018-01-03 20:44:00","uid":"admin"}]

function search_book() {
	$('#btn-dialogBox').dialogBox({
		hasClose: true,
		hasBtn: true,
		confirmValue: '搜索',
		confirm: function(){
			var form = $("#searchBook");
			var postdata = ``;
			form.children("input").each(function(input_index, input_ele) {
				if(input_ele.value != "")
					postdata = `type=${input_ele.name}&${input_ele.name}=${input_ele.value}`;
			});
			index.booktable.postdata = postdata;
			load_data(index.booktable);
		},
		cancelValue: '取消',
		title: `搜索书籍`,
		content: `<form action="BookServlet?action=queryList" id="searchBook" method="post">
					选择一个进行搜索,全部留空查找全部<br>
					<input type="text" Name="bookname" placeholder="书名">
					<br>
					<input type="text" Name="author" placeholder="作者">
					<br>
				  </form>`
	});
}

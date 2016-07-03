function submitMessageForm() {
	if (check()) {
		var options = {
			success : showResponse,
			error : showerror,
			url : 'pluginInfo_add.action',
			dataType : 'json'
		};
		$('#messageForm').ajaxForm(options).submit(function() {
		});
		$('#messageForm').submit();// 传统form提交
		load();
	}
}

function showResponse(data, statusText) {
	disLoad();
	// console.info(data);
	// console.info(statusText);
	if (data.result == true) {
		$.messager.alert('提示', "添加成功!", 'info', function() {
			$('#messageForm')[0].reset();
		});

	} else {
		$.messager.alert('提示', "添加失败!", 'info');
	}
}
function showerror(data) {
	disLoad();
	console.info(data);
	console.info(data.message);
	$.messager.alert('提示', "添加异常!", 'info');
}

function check() {

	if ($("#pName").val() == "") {
		$.messager.alert('提示', "插件名不能为空!", 'info');
		return false;
	}

	if ($("#pDetail").val() == "") {
		$.messager.alert('提示', "简介不能为空!", 'info');
		return false;
	}

	if ($("#pType").val() == "-1") {
		$.messager.alert('提示', "请选择插件类型!", 'info');
		return false;
	}

	if ($("#pluginfile").val() == "") {
		$.messager.alert('提示', "请选择插件文件!", 'info');
		return false;
	}

	return true;
}

// 弹出加载层
function load() {
	$("<div class=\"datagrid-mask\"></div>").css({
		display : "block",
		width : "100%",
		height : $(window).height()
	}).appendTo("body");
	$("<div class=\"datagrid-mask-msg\"></div>").html("正在提交，请稍候。。。").appendTo(
			"body").css({
		display : "block",
		left : ($(document.body).outerWidth(true) - 190) / 2,
		top : ($(window).height() - 45) / 2
	});
}

// 取消加载层
function disLoad() {
	$(".datagrid-mask").remove();
	$(".datagrid-mask-msg").remove();
}
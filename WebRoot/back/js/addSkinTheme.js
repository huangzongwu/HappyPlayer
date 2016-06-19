function submitMessageForm() {
	if (check()) {
		var options = {
			success : showResponse,
			error : showerror,
			url : 'skinTheme_add.action',
			dataType : 'json'
		};
		$('#messageForm').ajaxForm(options).submit(function() {
		});
		$('#messageForm').submit();// 传统form提交
		load();
	}
}
$(function() {
	var result = document.getElementById("result");
	var input = document.getElementById("file");
	if (typeof FileReader === 'undefined') {
		result.innerHTML = "抱歉，你的浏览器不支持 FileReader";
		input.setAttribute('disabled', 'disabled');
	} else {
		input.addEventListener('change', readFile, false);
	}
});

function readFile() {
	var file = this.files[0];
	if (file == 'undefined' || file == null) {
		result.innerHTML = '';
	} else if (!/image\/\w+/.test(file.type)) {
		alert("文件必须为图片！");
		return false;
	}
	var reader = new FileReader();
	reader.readAsDataURL(file);
	reader.onload = function(e) {
		result.innerHTML = '<img  height="300" width="200" src="' + this.result
				+ '" alt=""/>';
	};
}

function showResponse(data, statusText) {
	disLoad();
	// console.info(data);
	// console.info(statusText);
	if (data.result == true) {
		$.messager.alert('提示', "添加成功!", 'info', function() {
			result.innerHTML = '';
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

	if ($("#themeName").val() == "") {
		$.messager.alert('提示', "主题名称不能为空!", 'info');
		return false;
	}

	if ($("#file").val() == "") {
		$.messager.alert('提示', "预览图片文件不能为空!", 'info');
		return false;
	}

	if ($("#skinfile").val() == "") {
		$.messager.alert('提示', "皮肤文件不能为空!", 'info');
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
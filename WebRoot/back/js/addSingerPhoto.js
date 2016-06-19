function submitMessageForm() {
	if (check()) {
		var options = {
			success : showResponse,
			error : showerror,
			url : 'singerPhoto_add.action',
			dataType : 'json'
		};
		$('#messageForm').ajaxForm(options).submit(function() {
		});
		$('#messageForm').submit();// 传统form提交
		load();
	}
};
$(function() {
	var result1 = document.getElementById("result1");
	var result2 = document.getElementById("result2");
	var result3 = document.getElementById("result3");
	var input1 = document.getElementById("file1");
	var input2 = document.getElementById("file2");
	var input3 = document.getElementById("file3");
	if (typeof FileReader === 'undefined') {
		result1.innerHTML = "抱歉，你的浏览器不支持 FileReader";
		input1.setAttribute('disabled', 'disabled');

		result2.innerHTML = "抱歉，你的浏览器不支持 FileReader";
		input2.setAttribute('disabled', 'disabled');

		result3.innerHTML = "抱歉，你的浏览器不支持 FileReader";
		input3.setAttribute('disabled', 'disabled');
	} else {
		// input1.addEventListener('change', readFile1(), false);
		// input2.addEventListener('change', readFile2(), false);
		// input3.addEventListener('change', readFile3(), false);
	}
});

function readFile1(file) {
	// var file = this.files[0];
	if (file == 'undefined' || file == null) {
		result1.innerHTML = '';
	} else if (!/image\/\w+/.test(file.type)) {
		alert("文件必须为图片！");
		return false;
	}
	var reader = new FileReader();
	reader.readAsDataURL(file);
	reader.onload = function(e) {
		result1.innerHTML = '<img  height="300" width="200" src="'
				+ this.result + '" alt=""/>';
	};
};

function readFile2(file) {
	// var file = this.files[0];
	if (file == 'undefined' || file == null) {
		result2.innerHTML = '';
	} else if (!/image\/\w+/.test(file.type)) {
		alert("文件必须为图片！");
		return false;
	}
	var reader = new FileReader();
	reader.readAsDataURL(file);
	reader.onload = function(e) {
		result2.innerHTML = '<img  height="300" width="200" src="'
				+ this.result + '" alt=""/>';
	};
};

function readFile3(file) {
	// var file = this.files[0];
	if (file == 'undefined' || file == null) {
		result3.innerHTML = '';
	} else if (!/image\/\w+/.test(file.type)) {
		alert("文件必须为图片！");
		return false;
	}
	var reader = new FileReader();
	reader.readAsDataURL(file);
	reader.onload = function(e) {
		result3.innerHTML = '<img  height="300" width="200" src="'
				+ this.result + '" alt=""/>';
	};
};

function showResponse(data, statusText) {
	disLoad();
	// console.info(data);
	// console.info(statusText);
	if (data.result == true) {
		$.messager.alert('提示', "添加成功!", 'info', function() {
			result1.innerHTML = '';
			result2.innerHTML = '';
			result3.innerHTML = '';
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
	if ($("#singer").val() == "") {
		$.messager.alert('提示', "歌手名称不能为空!", 'info');
		return false;
	}

	if ($("#file1").val() == "") {
		$.messager.alert('提示', "歌手写真图片文件1不能为空!", 'info');
		return false;
	}

	if ($("#file2").val() == "") {
		$.messager.alert('提示', "歌手写真图片文件2不能为空!", 'info');
		return false;
	}

	if ($("#file3").val() == "") {
		$.messager.alert('提示', "歌手写真图片文件3不能为空!", 'info');
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
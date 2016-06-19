$(function() {
	$('#win').window('close');
	$('#tt')
			.datagrid(
					{

						url : 'singerPhoto_list.action', // 服务器地址,返回json格式数据
						nowrap : true,
						autoRowHeight : false,
						striped : true,
						collapsible : true,
						pagination : true, // 分页控件
						rownumbers : true, // 行号
						sortName : 'createTime',
						sortOrder : 'desc',
						columns : [ [
								{
									field : 'sid',
									title : '编号',
									width : 120,
									editor : 'numberbox'
								},
								{
									field : 'singer',
									title : '歌手名',
									width : 200,
									editor : 'text'
								},
								{
									field : 'createTime',
									title : '添加时间',
									width : 140,
									editor : 'datebox',
									sortable : true
								},
								{
									field : 'updateTime',
									title : '更新时间',
									width : 140,
									editor : 'datebox',
									sortable : true
								},
								{
									field : 'action',
									title : '操作',
									width : 120,
									align : 'center',
									formatter : function(value, row, index) {
										var e = '<a href="javascript:void(0);" onclick="editrow(\''
												+ row.sid + '\')">编辑</a> ';
										var d = '<a href="javascript:void(0);" onclick="deleterow(\''
												+ row.sid
												+ '\',\''
												+ (index + 1) + '\')">删除</a>';
										return e + d;

									}
								} ] ],
					// toolbar : [ {
					// text : '增加',
					// iconCls : 'icon-add',
					// handler : addrow
					// }, {
					// text : '保存',
					// iconCls : 'icon-save',
					// handler : saveall
					// }, {
					// text : '取消',
					// iconCls : 'icon-cancel',
					// handler : cancelall
					// } ],
					// onBeforeEdit : function(index, row) {
					// row.editing = true;
					// $('#tt').datagrid('refreshRow', index);
					// editcount++;
					// },
					// onAfterEdit : function(index, row) {
					// row.editing = false;
					// $('#tt').datagrid('refreshRow', index);
					// editcount--;
					// },
					// onCancelEdit : function(index, row) {
					// row.editing = false;
					// $('#tt').datagrid('refreshRow', index);
					// editcount--;
					// }
					});
	var p = $('#tt').datagrid('getPager');
	$(p).pagination({
		pageSize : 10,// 每页显示的记录条数，默认为10
		pageList : [ 5, 10, 15, 20 ],// 每页显示几条记录
		beforePageText : '第',// 页数文本框前显示的汉字
		afterPageText : '页    共 {pages} 页',
		displayMsg : '当前显示 {from} - {to} 条记录    共 {total} 条记录',
		onBeforeRefresh : function() {
			$(this).pagination('loading');// 正在加载数据中...
			$(this).pagination('loaded'); // 数据加载完毕
		}
	});
});

function doSearch() {
	if ($("#singers").val() == "") {
		$.messager.alert('提示', "歌手名称不能为空!", 'info');
		return false;
	}

	$('#tt').datagrid('load', {
		singer : $('#singers').val()
	});

	//$("#singers").val('');
}

var imageId = null;
function editrow(sid) {
	imageId = sid;
	var result1 = document.getElementById("result1");
	var result2 = document.getElementById("result2");
	var result3 = document.getElementById("result3");
	var input1 = document.getElementById("file1");
	var input2 = document.getElementById("file2");
	var input3 = document.getElementById("file3");

	result1.innerHTML = '';
	result2.innerHTML = '';
	result3.innerHTML = '';

	if (typeof FileReader === 'undefined') {
		result.innerHTML = "抱歉，你的浏览器不支持 FileReader";
		input1.setAttribute('disabled', 'disabled');
		input2.setAttribute('disabled', 'disabled');
		input3.setAttribute('disabled', 'disabled');
	} else {
		// input.addEventListener('change', readFile, false);
	}
	$('#messageForm')[0].reset();
	loadSingerPhotoDetail(sid);
	$('#win').window('open');
}

function deleterow(sid, index) {

	$.messager.confirm("确认", '是否删除序号为' + index + '的数据', function(r) {
		if (r) {
			$.ajax({
				url : 'singerPhoto_delete',
				data : 'sid=' + sid,
				error : function() {
					$.messager.alert('提示', "删除数据异常!", 'info');
				},
				success : function(data) {
					if (data === 'undefined' || data == null) {
						$.messager.alert('提示', "删除数据失败!", 'info');
					} else {
						if (data.result == true) {
							$.messager.alert('提示', "删除成功!", 'info', function() {
								$('#tt').datagrid('reload');
							});
						} else {
							$.messager.alert('提示', "删除失败!", 'info');
						}
					}
				}
			});
		}
	});
}

function readFile1(file) {
	// var file = this.files[0];
	if (file == 'undefined' || file == null) {
		result1.innerHTML = '<img  height="300" width="200" src="phone/getSingerPhotoImageByID?imageid=1&sid='
				+ imageId + '" alt=""/>';
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
}

function readFile2(file) {
	// var file = this.files[0];
	if (file == 'undefined' || file == null) {
		result2.innerHTML = '<img  height="300" width="200" src="phone/getSingerPhotoImageByID?imageid=2&sid='
				+ imageId + '" alt=""/>';
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
}

function readFile3(file) {
	// var file = this.files[0];
	if (file == 'undefined' || file == null) {
		result3.innerHTML = '<img  height="300" width="200" src="phone/getSingerPhotoImageByID?imageid=3&sid='
				+ imageId + '" alt=""/>';
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
}
function save() {
	if (check()) {
		var options = {
			success : showResponse,
			error : showerror,
			url : 'singerPhoto_edit.action',
			dataType : 'json'
		};
		$('#messageForm').ajaxForm(options).submit(function() {
		});
		$('#messageForm').submit();// 传统form提交
	}
}

function showResponse(data, statusText) {
	if (data.result == true) {
		$.messager.alert('提示', "编辑成功!", 'info', function() {
			$('#win').window('close');
			setTimeout($('#tt').datagrid('reload'), 500);
		});

	} else {
		$.messager.alert('提示', "编辑失败!", 'info');
	}
}
function showerror(data) {
	console.info(data);
	console.info(data.message);
	$.messager.alert('提示', "编辑异常!", 'info');
}

function check() {
	if ($("#singer").val() == "") {
		$.messager.alert('提示', "歌手名称不能为空!", 'info');
		return false;
	}

	return true;
}
/**
 * 加载启动页面详情
 * 
 * @param sid
 */
function loadSingerPhotoDetail(sid) {
	$
			.ajax({
				url : 'singerPhoto_getSingerPhotoByID',
				data : 'sid=' + sid,
				error : function() {
					$.messager.alert('提示', "获取数据异常!", 'info');
				},
				success : function(data) {
					if (data === 'undefined' || data == null) {
						$.messager.alert('提示', "获取数据失败!", 'info');
					} else {
						$('#sid').val(data.sid);
						$('#singer').val(data.singer);
						document.getElementById("result1").innerHTML = '<img  height="300" width="200" src="phone/getSingerPhotoImageByID?imageid=1&sid='
								+ data.sid + '" alt=""/>';
						document.getElementById("result2").innerHTML = '<img  height="300" width="200" src="phone/getSingerPhotoImageByID?imageid=2&sid='
							+ data.sid + '" alt=""/>';
						document.getElementById("result3").innerHTML = '<img  height="300" width="200" src="phone/getSingerPhotoImageByID?imageid=3&sid='
							+ data.sid + '" alt=""/>';
					}
				}
			});
}
function cancel() {
	$('#win').window('close');
}

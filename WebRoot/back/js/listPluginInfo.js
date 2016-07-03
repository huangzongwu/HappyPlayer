$(function() {
	$('#win').window('close');
	$('#tt')
			.datagrid(
					{

						url : 'pluginInfo_list.action', // 服务器地址,返回json格式数据
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
									field : 'pid',
									title : '编号',
									width : 100,
									editor : 'numberbox'
								},
								{
									field : 'pName',
									title : '插件名称',
									width : 100,
									editor : 'text'
								},
								{
									field : 'pDetail',
									title : '简介',
									width : 100,
									editor : 'text'
								},
								{
									field : 'sizeStr',
									title : '文件大小',
									width : 100
								},
								{
									field : 'fileType',
									title : '文件类型',
									width : 100
								},
								{
									field : 'pTypeText',
									title : '插件类型',
									width : 200
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
												+ row.pid + '\')">编辑</a> ';
										var d = '<a href="javascript:void(0);" onclick="deleterow(\''
												+ row.pid
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

function editrow(pid) {

	$('#messageForm')[0].reset();
	loadPluginInfoDetail(pid);
	$('#win').window('open');
}

function deleterow(pid, index) {

	$.messager.confirm("确认", '是否删除序号为' + index + '的数据', function(r) {
		if (r) {
			$.ajax({
				url : 'pluginInfo_delete',
				data : 'pid=' + pid,
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

function save() {
	if (check()) {
		var options = {
			success : showResponse,
			error : showerror,
			url : 'pluginInfo_edit.action',
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
var pTypeData = [ {
	"key" : "0",
	"text" : "原生有页面apk插件"
}, {
	"key" : "1",
	"text" : "原生无页面apk插件"
}, {
	"key" : "2",
	"text" : "有页面web插件"
}, {
	"key" : "3",
	"text" : "无页面web插件"
}, {
	"key" : "4",
	"text" : "混合有页面插件"
} ];
/**
 * 加载启动页面详情
 * 
 * @param sid
 */
function loadPluginInfoDetail(pid) {
	$.ajax({
		url : 'pluginInfo_getPluginInfoByID.action',
		data : 'pid=' + pid,
		dataType : 'json',
		error : function() {
			$.messager.alert('提示', "获取数据异常!", 'info');
		},
		success : function(data) {
			if (data === 'undefined' || data == null) {
				$.messager.alert('提示', "获取数据失败!", 'info');
			} else {
				var pType = document.getElementById("pType");
				pType.innerHTML = '';
				
				$('#pid').val(data.pid);
				$('#pName').val(data.pName);
				$('#pDetail').val(data.pDetail);

				var text = '<option value="-1">选择插件类型</option>';
				for ( var i = 0; i < pTypeData.length; i++) {
					if (data.pType == pTypeData[i].key) {
						text += '<option selected value="' + pTypeData[i].key + '">'
								+ pTypeData[i].text + '</option>';
					} else {
						text += '<option value="' + pTypeData[i].key + '">'
								+ pTypeData[i].text + '</option>';
					}
				}
				pType.innerHTML = text;
			}
		}
	});
}
function cancel() {
	$('#win').window('close');
}

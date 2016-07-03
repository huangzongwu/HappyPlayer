<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">
<title>添加启动页</title>
<link rel="stylesheet" type="text/css"
	href="back/js/themes/default/easyui.css" />
<link rel="stylesheet" type="text/css" href="back/js/themes/icon.css" />

<link rel="stylesheet" href="back/css/demo.css" type="text/css" />
<link href="back/css/bodystyle.css" rel="stylesheet" type="text/css" />


<script type="text/javascript" src="back/js/jquery-1.6.1.min.js"></script>
<script type="text/javascript" src="back/js/jquery.easyui.min.js"></script>
<script type="text/javascript" src="back/js/jquery.form.js"></script>

<script type="text/javascript" src="back/js/listPluginInfo.js"></script>
<script type="text/javascript">
	function myformatter(date) {
		var y = date.getFullYear();
		var m = date.getMonth() + 1;
		var d = date.getDate();
		return y + '-' + (m < 10 ? ('0' + m) : m) + '-'
				+ (d < 10 ? ('0' + d) : d);
	}
	function myparser(s) {
		if (!s)
			return new Date();
		var ss = (s.split('-'));
		var y = parseInt(ss[0], 10);
		var m = parseInt(ss[1], 10);
		var d = parseInt(ss[2], 10);
		if (!isNaN(y) && !isNaN(m) && !isNaN(d)) {
			return new Date(y, m - 1, d);
		} else {
			return new Date();
		}
	}
</script>

</head>

<body class="easyui-layout" style="overflow-y: hidden" scroll="no">
	<table id="tt" class="easyui-datagrid" height="100%" width="100%"
		border="1" cellpadding="0">
	</table>
</body>

<div id="win" class="easyui-window" title="编辑"
	style="width:500px;height:500px;">
	<form enctype="multipart/form-data" method="post" name="messageForm"
		id="messageForm">
		<table width="100%" border="1" cellpadding="0">
			<tr>
				<td height="30" colspan="7">&nbsp;&nbsp; <b class="title"></b>
				</td>
			</tr>
<tr>
				<td height="25px" align='right' nowrap><font color="#FF0000">*</font>编号&nbsp;</td>
				<td><input type="text" value="" name="pid" id="pid"
					readonly="readonly" /></td>
			</tr>
			<tr>
				<td height="25px" align='right' nowrap><font color="#FF0000">*</font>插件名&nbsp;</td>
				<td><input type="text" value="" name="pName" id="pName" /></td>
			</tr>

			<tr>
				<td height="25px" align='right' nowrap><font color="#FF0000">*</font>简介&nbsp;</td>
				<td><input type="text" value="" name="pDetail" id="pDetail" />
				</td>
			</tr>

			<tr>
				<td height="25px" align='right' nowrap><font color="#FF0000">*</font>插件类型&nbsp;</td>
				<td><select name="pType" id="pType">
						<!--  
						<option value="-1">选择插件类型</option>
						<option value="0">原生有页面apk插件</option>
						<option value="1">原生无页面apk插件</option>
						<option value="2">有页面web插件</option>
						<option value="3">无页面web插件</option>
						<option value="4">混合有页面插件</option>
						-->
				</select>
				</td>
			</tr>


			<tr>
				<td height="25px" align='right' nowrap><font color="#FF0000">*</font>插件文件(apk/jar)&nbsp;</td>
				<td><input accept='.apk,.jar' type="file" name="file"
					id="pluginfile" value="" />
				</td>
			</tr>

			<tr style="padding:5px;text-align:center;">
				<td></td>
				<td height=35><input type="button" value="保存"
					onClick="javascript:save()" /> &nbsp;&nbsp;<input type="button"
					value="取消 " onClick="javascript:cancel()" /></td>
			</tr>
		</table>

	</form>
</div>

</html>

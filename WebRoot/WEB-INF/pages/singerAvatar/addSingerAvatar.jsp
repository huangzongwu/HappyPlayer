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
<!-- <link href="back/css/jcDate.css" rel="stylesheet" type="text/css" /> -->


<script type="text/javascript" src="back/js/jquery-1.6.1.min.js"></script>
<script type="text/javascript" src="back/js/jquery.easyui.min.js"></script>
<script type="text/javascript" src="back/js/jquery.form.js"></script>

<script type="text/javascript" src="back/js/addSingerAvatar.js"></script>

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
	<form enctype="multipart/form-data" method="post" name="messageForm"
		id="messageForm">
		<table width="100%" border="1" cellpadding="0">
			<tr>
				<td height="30" colspan="7">&nbsp;&nbsp; <b class="title"></b>
				</td>
			</tr>
			<tr>
				<td height="25px" align='right' nowrap><font color="#FF0000">*</font>歌手名称&nbsp;</td>
				<td><input type="text" value="" name="singer" id="singer" />
				</td>
			</tr>

			<tr>
				<td height="25px" align='right' nowrap><font color="#FF0000">*</font>选择歌手头像图片&nbsp;</td>
				<td><input accept="image/*" type="file" name="file" id="file"
					value="" /></td>
			</tr>

			<tr>
				<td height="25px" align='right' nowrap><font color="#FF0000"></font>图片预览&nbsp;</td>
				<td><div id="result"></div>
				</td>
			</tr>

			<tr>
				<td></td>
				<td height=35><input type="button" value="添加 "
					onClick="javascript:submitMessageForm()" /></td>
			</tr>
		</table>

	</form>
</body>
</html>

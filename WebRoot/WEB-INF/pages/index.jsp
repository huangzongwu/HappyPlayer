<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head id="Head1">
<base href="<%=basePath%>">
<title>乐乐音乐</title>
<link rel="SHORTCUT ICON" href="back/images/icon.png" />
<link href="back/css/default.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" type="text/css"
	href="back/js/themes/default/easyui.css" />
<link rel="stylesheet" type="text/css" href="back/js/themes/icon.css" />
<script type="text/javascript" src="back/js/jquery-1.4.2.min.js"></script>
<script type="text/javascript" src="back/js/jquery.easyui.js"></script>


<script type="text/javascript" src='back/js/outlook2.js'>
	
</script>

<script type="text/javascript">
	var _menus = {
		"menus" : [ {
			"menuid" : "0",
			"icon" : "icon-sys",
			"menuname" : "启动页管理",
			"menus" : [ {
				"menuname" : "添加启动页",
				"icon" : "icon-nav",
				"url" : "menu_addSplash.action"
			}, {
				"menuname" : "启动页管理",
				"icon" : "icon-nav",
				"url" : "menu_listSplash.action"
			} ]
		}, {
			"menuid" : "2",
			"icon" : "icon-sys",
			"menuname" : "APP版本管理",
			"menus" : [ {
				"menuname" : "添加版本",
				"icon" : "icon-nav",
				"url" : "menu_addAPP.action"
			}, {
				"menuname" : "APP管理",
				"icon" : "icon-add",
				"url" : "menu_listAPP.action"
			} ]
		}, {
			"menuid" : "6",
			"icon" : "icon-sys",
			"menuname" : "皮肤管理",
			"menus" : [ {
				"menuname" : "添加皮肤",
				"icon" : "icon-nav",
				"url" : "menu_addSkinTheme.action"
			}, {
				"menuname" : "皮肤管理",
				"icon" : "icon-nav",
				"url" : "menu_listSkinTheme.action"
			} ]
		}, {
			"menuid" : "4",
			"icon" : "icon-sys",
			"menuname" : "歌手头像图片管理",
			"menus" : [ {
				"menuname" : "添加歌手头像图片",
				"icon" : "icon-nav",
				"url" : "menu_addSingerAvatar.action"
			}, {
				"menuname" : "歌手头像图片管理",
				"icon" : "icon-nav",
				"url" : "menu_listSingerAvatar.action"
			} ]
		}, {
			"menuid" : "4",
			"icon" : "icon-sys",
			"menuname" : "歌手写真图片管理",
			"menus" : [ {
				"menuname" : "添加歌手写真图片",
				"icon" : "icon-nav",
				"url" : "menu_addSingerPhoto.action"
			}, {
				"menuname" : "歌手写真图片管理",
				"icon" : "icon-nav",
				"url" : "menu_listSingerPhoto.action"
			} ]
		}, {
			"menuid" : "5",
			"icon" : "icon-sys",
			"menuname" : "歌词管理",
			"menus" : [ {
				"menuname" : "添加歌词",
				"icon" : "icon-nav",
				"url" : "menu_addKsc.action"
			}, {
				"menuname" : "歌词管理",
				"icon" : "icon-nav",
				"url" : "menu_listKsc.action"
			} ]
		}, {
			"menuid" : "3",
			"icon" : "icon-sys",
			"menuname" : "歌曲管理",
			"menus" : [ {
				"menuname" : "添加歌曲",
				"icon" : "icon-nav",
				"url" : "menu_addSongInfo.action"
			}, {
				"menuname" : "歌曲管理",
				"icon" : "icon-add",
				"url" : "menu_listSongInfo.action"
			} ]
		}
		/**, {
			"menuid" : "8",
			"icon" : "icon-sys",
			"menuname" : "EasyTouch皮肤管理",
			"menus" : [ {
				"menuname" : "添加EasyTouch皮肤",
				"icon" : "icon-nav",
				"url" : "menu_addEasyTouchTheme.action"
			}, {
				"menuname" : "EasyTouch皮肤管理",
				"icon" : "icon-add",
				"url" : "menu_listEasyTouchTheme.action"
			} ]
		}**/
		, {
			"menuid" : "9",
			"icon" : "icon-sys",
			"menuname" : "插件管理",
			"menus" : [ {
				"menuname" : "添加插件",
				"icon" : "icon-nav",
				"url" : "menu_addPluginInfo.action"
			}, {
				"menuname" : "插件管理",
				"icon" : "icon-add",
				"url" : "menu_listPluginInfo.action"
			} ]
		} ]
	};
</script>

</head>
<body class="easyui-layout" style="overflow-y: hidden" scroll="no">
	<noscript>
		<div
			style=" position:absolute; z-index:100000; height:2046px;top:0px;left:0px; width:100%; background:white; text-align:center;">
			<img src="back/images/noscript.gif" alt='抱歉，请开启脚本支持！' />
		</div>
	</noscript>
	<div region="north" split="true" border="false"
		style="overflow: hidden; height: 30px;
        background: url(back/images/layout-browser-hd-bg.gif) #7f99be repeat-x center 50%;
        line-height: 20px;color: #fff; font-family: Verdana, 微软雅黑,黑体">
		<!-- <span style="float:right; padding-right:20px;" class="head">欢迎
			16素材 <a href="#" id="editpass">修改密码</a> <a href="#" id="loginOut">安全退出</a>
		</span>  -->
		<span style="padding-left:10px; font-size: 16px; "><img
			src="back/images/lele.png" width="20" height="20" align="absmiddle" />&nbsp;乐乐音乐</span>
	</div>
	<div region="south" split="true"
		style="height: 30px; background: #D2E0F2; ">
		<div class="footer">By zhangliangming Email:316257874@qq.com</div>
	</div>
	<div region="west" split="true" title="导航菜单" style="width:180px;"
		id="west">
		<div class="easyui-accordion" fit="true" border="false">
			<!--  导航内容 -->

		</div>

	</div>
	<div id="mainPanle" region="center"
		style="background: #eee; overflow-y:hidden">
		<div id="tabs" class="easyui-tabs" fit="true" border="false">
			<div title="欢迎使用" style="padding:20px;overflow:hidden;" id="home">

				<h1>Welcome to happy music!</h1>

			</div>
		</div>
	</div>

	<div id="mm" class="easyui-menu" style="width:150px;">
		<div id="mm-tabclose">关闭</div>
		<div id="mm-tabcloseall">全部关闭</div>
		<div id="mm-tabcloseother">除此之外全部关闭</div>
		<div class="menu-sep"></div>
		<div id="mm-tabcloseright">当前页右侧全部关闭</div>
		<div id="mm-tabcloseleft">当前页左侧全部关闭</div>
		<!-- <div class="menu-sep"></div>
		<div id="mm-exit">退出</div> -->
	</div>


</body>
</html>
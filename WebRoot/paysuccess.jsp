<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>

<title>电子书城</title>
<link rel="stylesheet" href="${pageContext.request.contextPath }/css/main.css" type="text/css" />

<script type="text/javascript">
	var interval;

function startSecond() {

	interval = window.setInterval("changeSecond()", 1000);

};

function changeSecond() {
	var second = document.getElementById("second");

	var svalue = second.innerHTML;

	svalue = svalue - 1;

	if (svalue == 0) {
		window.clearInterval(interval);
		location.href = "${pageContext.request.contextPath}/index.jsp";
		return;
	}

	second.innerHTML = svalue;
}
</script>
</head>

<body class="main" onload="startSecond()">

	<jsp:include page="head.jsp"></jsp:include>
	<jsp:include page="menu_search.jsp" />



	<div id="divcontent">
		<table width="850px" border="0" cellspacing="0">
			<tr>
				<td style="padding:30px; text-align:center"><table width="60%"
						border="0" cellspacing="0" style="margin-top:70px">
						<tr>
							<td style="width:98"><img
								src="${pageContext.request.contextPath }/images/IconTexto_WebDev_009.jpg" width="128" height="128" />
							</td>
							<td style="padding-top:30px"><font
								style="font-weight:bold; color:#FF0000">支付成功</font><br />
								<br /> <a href="${pageContext.request.contextPath }/index.jsp"><span id="second">5</span>秒后自动为您转跳回首页</a></td>
						</tr>
					</table>
					<h1>&nbsp;</h1></td>
			</tr>
		</table>
	</div>



	<jsp:include page="foot.jsp"/>


</body>
</html>

<%@page import="java.text.SimpleDateFormat"%>
<%@ page import="com.oracle.ddbookmarket.model.Admin" %>
<%@ page import="java.util.Map" %>
<%@ page import="java.util.Set" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC>
<html>
<head>
<meta charset="UTF-8">
<title>登录</title>
<!-- 告诉浏览器不要缩放 -->
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<!--  <link href="bower_components/bootstrap/dist/css/bootstrap.css"rel="stylesheet" type="text/css" />-->
<link href="bower_components/bootswatch/dist/sketchy/bootstrap.css" rel="stylesheet" type="text/css" />

</head>
<body>
	<div class="container-fluid" style="width: 70%">
		<div class="row">
			<div class="col-md-12">
				<div class="card">
					<div class="card-body">
						<%--
							Map<String,String> errors= (Map<String, String>) request.getAttribute("errors");
							if (errors!=null){
							Set<String> keySet=errors.keySet();
							for (String key:
									keySet) {
								out.print(key+"------"+errors.get(key));
							}}
						--%>
						<form method="post" autocomplete="off" action="login"id="loginFrom">
							<%
								Map<String,String> errors= (Map<String, String>) request.getAttribute("errors");
							Admin admin = (Admin) request.getAttribute("admin");
			                if (admin != null) {
				             %>
							
							<div class="form-group row">
								<label for="inputName" class="col-sm-2 col-form-label text-right">用户名：</label>
								<div class="col-sm-10">
									<%
									if(errors!=null&& errors.get("name")!=null){
									    %>
									<input type="text" class="form-control is-invalid" id="inputName" placeholder="用户名" name="name" value="<%=admin.getName()==null?"":admin.getName()%>">
									<div class="invalid-feedback">
										<%=errors.get("name")%>
									</div>
									<%
									}else {
                                    %>
									<input type="text" class="form-control" id="inputName" placeholder="用户名" name="name" value="<%=admin.getName()%>">
									<%
									}
								%>
								</div>
							</div>
							<div class="form-group row">
								<label for="inputPassword" class="col-sm-2 col-form-label text-right">密码：</label>
								<div class="col-sm-10">
									<%
										if(errors!=null&&errors.get("pwd")!=null){
									    %>
									<input type="password" class="form-control is-invalid" id="inputPassword" placeholder="密码" name="pwd">
									<div class="invalid-feedback">
										<%=errors.get("pwd")%>
									</div>
									<%
									}else {
										    %>
									<input type="password" class="form-control" id="inputPassword" placeholder="密码" name="pwd">
									<%

									}%>
								</div>
							</div>

							<div class="form-group row">
								<label for="inputvcode" class="col-sm-2 col-form-label text-right">验证码：</label>
								<div class="col-sm-5">
									<%
										if (errors!=null&&errors.get("vcode")!=null){
										    %>
									<input  class="form-control is-invalid" id="inputvcode" placeholder="验证码" name="vcode">
									<div class="invalid-feedback"><%=errors.get("vcode")%></div>
									<%
									}else {
										    %>
									<input  class="form-control" id="inputvcode" placeholder="验证码" name="vcode">
									<%
									}%>

								</div>
								<div class="col-sm-5">
									<img src="/vcode.png"id="vcodeimg">
								</div>
							</div>

							<div class="form-group row">
								<div class="col-sm-2"></div>
								<div class="col-sm-10">
									<button type="submit" class="btn btn-primary">登录</button>
								</div>
							</div>
							<%
								} else {
							%>

							<div class="form-group row">
								<label for="inputName" class="col-sm-2 col-form-label text-right">用户名：</label>
								<div class="col-sm-10">
									<input type="text" class="form-control" id="inputName" placeholder="用户名" name="name">
								</div>
							</div>
							<div class="form-group row">
								<label for="inputPassword" class="col-sm-2 col-form-label text-right">密码：</label>
								<div class="col-sm-10">
									<input type="password" class="form-control" id="inputPassword" placeholder="密码" name="pwd">
								</div>
							</div>
							<div class="form-group row">
								<label for="inputvcode" class="col-sm-2 col-form-label text-right">验证码：</label>
								<div class="col-sm-5">
									<input  class="form-control" id="inputvcode" placeholder="验证码" name="vcode">
								</div>
								<div class="col-sm-5">
									<img src="/vcode.png"id="vcodeimg">
								</div>
							</div>
							<div class="form-group row">
								<div class="col-sm-2"></div>
								<div class="col-sm-10">
									<button type="submit" class="btn btn-primary">登录</button>
								</div>
							</div>

							<%
								}
							%>
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>
	<script type="text/javascript" src="bower_components/jquery/dist/jquery.slim.js"></script>
	<script type="text/javascript" src="bower_components/bootstrap/dist/js/bootstrap.js"></script>
<script type="text/javascript">
	$(function () {
        $("#vcodeimg").click(function () {
			$(this).attr("src","vcode.png?k="+Math.random());
        });
    });
</script>
	<%--添加客户端验证        https://jqueryvalidation.org--%>
	<script type="text/javascript" src="bower_components/jquery-validation/dist/jquery.validate.js"></script>
<script type="text/javascript">
    $(function () {
        $("#loginFrom").validate({
            rules:{
                name:{//验证的规则
                    required:true,
                    maxlength:50,
                    minlength:2
            },
                pwd:{//验证的规则
                    required:true,
                    maxlength:50,
					minlength:2
                },
                vcode:{
                    required:true,
                    maxlength:4,
                    minlength:4
				}

            },
            messages:{//验证不通过的提示消息
                name:{//验证的规则
                    required:"必填",
                    maxlength:"最多50个",
                    minlength:"最少2个"
                },
                pwd:{//验证的规则
                    required:"必填",
                    maxlength:"最多50个",
                    minlength:"最少2个"
                },
                vcode:{
                    required:"必填",
                }
            },
            errorElement:"div",
            errorClass:"invalid-feedback",
            highlight: function(element, errorClass, validClass) {//高亮显示
                $(element).addClass("is-invalid").removeClass(validClass);
                //bootstrap4暂不支持对label添加样式
                // $(element.form).find("label[for=" + element.id + "]")
                //     .addClass(errorClass);
            },
            unhighlight: function(element, errorClass, validClass) {//取消高亮显示
                $(element).removeClass("is-invalid").addClass(validClass);
                // $(element.form).find("label[for=" + element.id + "]")
                //     .removeClass(errorClass);
            },
            //validClass:"is-valid"

        });
    });
</script>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<!-- 告诉浏览器不要缩放 -->
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
	<link href="bower_components/bootswatch/dist/sketchy/bootstrap.css" rel="stylesheet" type="text/css" />
<style type="text/css">
.custom-file-label::after {
	content: "浏览"
}
</style>
</head>
<body>
	<div class="container-fluid" style="width: 70%">
		<div class="row">
			<div class="col-md-12">
				<div class="card">
					<div class="card-body">
						<form method="post" autocomplete="off" action="bookAdd" enctype="multipart/form-data"id="bookaddfrom">
							<div class="form-group row">
								<label for="inputbid" class="col-sm-2 col-form-label text-right">大类名：</label>
								<div class="col-sm-10">
									<select name="bid" class="form-control" id="inputbid">
                                         <option>请选择</option>
									</select>
								</div>
							</div>
							<div class="form-group row">
								<label for="inputsid" class="col-sm-2 col-form-label text-right">小类名：</label>
								<div class="col-sm-10">
									<select name="sid" class="form-control" id="inputsid">

									</select>
								</div>
							</div>

							<div class="form-group row">
								<label for="inputName" class="col-sm-2 col-form-label text-right">书名：</label>
								<div class="col-sm-10">
									<input type="text" class="form-control" id="inputName" placeholder="书名" name="name">
								</div>
							</div>
							<div class="form-group row">
								<label for="inputprice" class="col-sm-2 col-form-label text-right">价格：</label>
								<div class="col-sm-10">
									<input type="text" class="form-control" id="inputprice" placeholder="价格" name="price">
								</div>
							</div>
							<div class="form-group row">
								<label for="inputauthor" class="col-sm-2 col-form-label text-right">作者：</label>
								<div class="col-sm-10">
									<input type="text" class="form-control" id="inputauthor" placeholder="作者" name="author">
								</div>
							</div>
							<div class="form-group row">
								<label for="inputcbs" class="col-sm-2 col-form-label text-right">出版社：</label>
								<div class="col-sm-10">
									<input type="text" class="form-control" id="inputcbs" placeholder="出版社" name="cbs">
								</div>
							</div>
							<div class="form-group row">
								<label for="inputcbDate" class="col-sm-2 col-form-label text-right">日期：</label>
								<div class="col-sm-10">
									<input type="text" class="form-control" id="inputcbDate" placeholder="日期" name="cbDate"/>
								</div>
							</div>
							<div class="form-group row">
								<label for="inputdescn" class="col-sm-2 col-form-label text-right">简介：</label>
								<div class="col-sm-10">
									<textarea class="form-control" id="inputdescn" placeholder="简介" name="descn"></textarea>
								</div>
							</div>
							<div class="form-group row">
								<label for="inputPhoto" class="col-sm-2 col-form-label text-right">头像：</label>

								<div class="col-sm-10">
									<div class="custom-file">
										<input type="file" class="custom-file-input" id="inputPhoto" aria-describedby="inputGroupFileAddon04" name="photo"> <label
											class="custom-file-label" for="inputPhoto">选择文件</label>
									</div>
								</div>
							</div>
							<div class="form-group row">
								<div class="col-sm-2"></div>
								<div class="col-sm-10">
									<button type="submit" class="btn btn-primary">添加</button>
								</div>
							</div>
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>
	<script type="text/javascript" src="bower_components/jquery/dist/jquery.js"></script>
	<script type="text/javascript" src="bower_components/bootstrap/dist/js/bootstrap.js"></script>
	<script type="text/javascript" src="bower_components/bootstrap-datepicker/dist/js/bootstrap-datepicker.js"></script>
	<script type="text/javascript" src="bower_components/bootstrap-datepicker/dist/locales/bootstrap-datepicker.zh-CN.min.js"></script>
	<script type="text/javascript">
		/* var xhr = new XMLHttpRequest();
		xhr.open("GET", "findAllBigtype");
		xhr.send();
		xhr.onreadystatechange=function() {
			if (xhr.readyState == 4) {
				if (xhr.status == 200) {
					eval(xhr.responseText);
				}
			}
		}; */
		function fillSel(typs) {
			for (var i = 0; i < typs.length; i++) {
				var op = new Option(typs[i].name, typs[i].id);
				document.getElementById("inputbid").appendChild(op);
			}
		};
		function fillSmallSel(typs) {
			document.getElementById("inputsid").innerHTML="";
			for (var i = 0; i < typs.length; i++) {
				var op = new Option(typs[i].name, typs[i].id);
				document.getElementById("inputsid").appendChild(op);
			}
		};
		/* var script=document.createElement("script");
		script.src="findAllBigtype";
		document.appendChild(script); */
		console.dir($);
		$.ajax({
			url : "findAllBigtype",
			dataType : "jsonp",
			jsonpCallback : "fillSel"
		});
	//大类改变
	$("#inputbid").change(function(){
		$.ajax({
			url : "findAllSmallType",
			dataType : "jsonp",
			data:"bid="+$(this).val(),
			jsonpCallback : "fillSmallSel"
		});
		});

	$('#inputcbDate').datepicker({
		language : 'zh-CN',
		format : 'yyyy-mm-dd',
		autoclose : true,
		defaultViewDate : {
			year : new Date().getFullYear() - 18
		}
	});
		
	</script>
	<!-- <script type="text/javascript" src="findAllBigtype"></script> -->
    <%--添加客户端验证        https://jqueryvalidation.org--%>
<script type="text/javascript" src="bower_components/jquery-validation/dist/jquery.validate.js"></script>
    <%--添加文件判断     查询到https://jqueryvalidation.org/accept-method/--%>
<script type="text/javascript" src="bower_components/jquery-validation/dist/additional-methods.js"></script>
    <script type="text/javascript" src="bower_components/jquery-validation/src/localization/messages_zh.js"></script>
<script type="text/javascript">

	$(function () {
		$("#bookaddfrom").validate({
			rules:{
                price:{//验证的规则
                    required:true,
                    number: true
                },
                descn:{
                    required:true,
                    maxlength:200//最多20个字符
				},
                photo:{
                    required: true,//必填字段
                    accept: "image/*"//文件只能是图片
                }
			},
			messages:{//验证不通过的提示消息
                price:{
                    required:"必填项",
                    number: "必须是数字"
                },
                photo:{
                    accept: "只能是图片"
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
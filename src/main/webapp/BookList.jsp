<%@page import="com.oracle.ddbookmarket.model.BookType"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<!-- 告诉浏览器不要缩放 -->
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<link rel="stylesheet" type="text/css" id="themelink" />
	<link rel="stylesheet" type="text/css"href="bower_components/fontawesome/web-fonts-with-css/css/fontawesome-all.css">
	<script type="text/javascript" src="bower_components/jquery/dist/jquery.js"></script>
	<script type="text/javascript" src="bower_components/jquery.cookie/jquery.cookie.js"></script>

<script type="text/javascript">
	if ($.cookie("bootstraptheme")) {
		$("#themelink").attr("href","bower_components/bootswatch/dist/"+ $.cookie("bootstraptheme") + "/bootstrap.css");
		$("#themesel").val($.cookie("bootstraptheme"));
	} else {
		$("#themelink").attr("href","bower_components/bootswatch/dist/sketchy/bootstrap.css");
	}
</script>
</head>
<body>
	<div class="container-fluid">
		<div class="row">
			<div class="col-md-12">
				<nav class="navbar navbar-expand-lg navbar-light bg-light">

					<button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
						<span class="navbar-toggler-icon"></span>
					</button>
					<a class="navbar-brand" href="#">Brand</a>
					<div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
						<ul class="navbar-nav">
							<li class="nav-item active"><a class="nav-link" href="#">Link <span class="sr-only">(current)</span></a></li>
							<li class="nav-item"><a class="nav-link" href="#">Link</a></li>
							<li class="nav-item dropdown"><a class="nav-link dropdown-toggle" href="http://example.com" id="navbarDropdownMenuLink" data-toggle="dropdown">Dropdown
									link</a>
								<div class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink">
									<a class="dropdown-item" href="#">Action</a> <a class="dropdown-item" href="#">Another action</a> <a class="dropdown-item" href="#">Something else
										here</a>
									<div class="dropdown-divider"></div>
									<a class="dropdown-item" href="#">Separated link</a>
								</div></li>
						</ul>
						<form class="form-inline">
							<input class="form-control mr-sm-2" type="text" />
							<button class="btn btn-primary my-2 my-sm-0" type="submit">Search</button>
						</form>
						<ul class="navbar-nav ml-md-auto">
							<li class="nav-item active"><a class="nav-link" href="javascript:void(0)"> <select id="themesel" class="custom-select">
										<option>sketchy</option>
										<option>darkly</option>
										<option>solar</option>
										<option>cyborg</option>
								</select>
							</a></li>
							<li class="nav-item dropdown">
								<a class="nav-link fa fa-window-close" href="exit.jsp" id="navbarDropdownMenuLink" >

							</a>
								</li>
						</ul>
					</div>
				</nav>
			</div>
		</div>
		<div class="col-md-12">
		    <div class="card-header">
		    <form action="bookList" method="post" id="fo">
  <div class="form-row align-items-center">
    <div class="col-auto">
      <label class="sr-only" for="inlineFormInput">书名</label>
      <input type="text" class="form-control mb-2" id="inlineFormInput"name="name" placeholder="书名" value='<%=request.getAttribute("name")==null?"":request.getAttribute("name")%>'>
    </div>
    
    
    <div class="col-auto">
      <div class="col-sm-20">
	<select name="bid" class="form-control" id="inputbid">
	<option value="-1">--请选择--</option>
	</select>
	</div>
    </div>
    <div class="col-auto">
      <div class="col-sm-20">
	<div class="col-sm-20">
		<select name="sid" class="form-control" id="inputsid">
		<option value="-1">--请选择--</option>
       </select>
		</div>
	</div>
    </div>
    <div class="col-auto">
      <button type="submit" class="btn btn-primary mb-2">查找</button>
    </div>
  </div>
</form >
		    </div>
			<table class="table table-hover table-sm">
				<thead>
					<tr>
						<th>#</th>
						<th>书名</th>
						<th>价格</th>
						<th>作者</th>
						<th>出版社</th>
						<th>出版日期</th>
						<th>简介</th>
						<th>小类</th>
						<th>图片</th>
						<th>操作</th>
					</tr>
				</thead>
				<tbody>
					<%
						List<BookType> ls = (List<BookType>) request.getAttribute("ls");
						for (BookType book : ls) {
					%>
					<tr class="table-warning">
						<td style="width: 30px"><%=book.getId()%></td>
						<td style="width: 200px"><%=book.getName()%></td>
						<td style="width: 50px"><%=book.getPrice()%></td>
						<td style="width: 100px"><%=book.getAuthor()%></td>
						<td style="width: 100px"><%=book.getCbs()%></td>
						<td style="width: 100px"><%=book.getCbDate()%></td>
						<td ><%=book.getDescn()%></td>
						<td style="width: 100px"><%=book.getSid()%></td>
						<td><img src="upload/<%=book.getPhoto()%>" /></td>
						<td style="width: 100px"><a href="#modal-container-645581 "data-toggle="modal" onclick="window.did='<%=book.getId()%>'" class="fa fa-trash fa-2x"title="删除"></a>
						<a href="toBookEdit?id=<%=book.getId()%>" class="fa fa-edit fa-2x" title="修改"></a>
						</td>
					</tr>
					<% 
						}
					%>
				</tbody>
			</table>
			
			<div class="card-footer">
			                 <nav>
								<ul class="pagination">
									
									<%
										int totaPage = (Integer) request.getAttribute("totaPage");
										int currentPage = (Integer) request.getAttribute("currentPage");
										if(currentPage==1){
											%>
											<li class="page-item disabled"><a class="page-link" href="#">上一页</a></li>
											<% 
										}else{
											%>
											<li class="page-item "><a class="page-link" href="bookList?currentPage=<%=currentPage-1%>">上一页</a></li>
											<% 
										}

										if (totaPage <= 5) {//如果总页数小于5
											for (int i = 1; i <= totaPage; i++) {
									%>
									<li class="page-item"><a class="page-link" href="bookList?currentPage=<%=i%>"><%=i%></a></li>
									<%
										}
										} else if (currentPage <= 3) {//如果当前页小于3
									%>
									<li class="page-item"><a class="page-link" href="bookList?currentPage=1">1</a></li>
									<li class="page-item"><a class="page-link" href="bookList?currentPage=2">2</a></li>
									<li class="page-item"><a class="page-link" href="bookList?currentPage=3">3</a></li>
									<li class="page-item"><a class="page-link" href="bookList?currentPage=4">4</a></li>
									<li class="page-item"><a class="page-link" href="bookList?currentPage=<%=totaPage%>">...<%=totaPage%></a></li>
									<%
										} else if (currentPage <= totaPage - 3) {
									%>
									<li class="page-item"><a class="page-link" href="bookList?currentPage=1">1...</a></li>
									<li class="page-item"><a class="page-link" href="bookList?currentPage=<%=currentPage - 1%>"><%=currentPage - 1%></a></li>
									<li class="page-item"><a class="page-link" href="bookList?currentPage=<%=currentPage%>"><%=currentPage%></a></li>
									<li class="page-item"><a class="page-link" href="bookList?currentPage=<%=currentPage + 1%>"><%=currentPage + 1%></a></li>
									<li class="page-item"><a class="page-link" href="bookList?currentPage=<%=totaPage%>"><%=totaPage%></a></li>
									<%
										} else {
									%>
									<li class="page-item"><a class="page-link" href="bookList?currentPage=1">1...</a></li>
									<li class="page-item"><a class="page-link" href="bookList?currentPage=<%=totaPage - 3%>"><%=totaPage - 3%></a></li>
									<li class="page-item"><a class="page-link" href="bookList?currentPage=<%=totaPage - 2%>"><%=totaPage - 2%></a></li>
									<li class="page-item"><a class="page-link" href="bookList?currentPage=<%=totaPage - 1%>"><%=totaPage - 1%></a></li>
									<li class="page-item"><a class="page-link" href="bookList?currentPage=<%=totaPage%>"><%=totaPage%></a></li>
									<%
										}
										if(currentPage==totaPage){
											%>
											<li class="page-item disabled"><a class="page-link" href="#">下一页</a></li>
											<%
										}else{
									%>
									<li class="page-item "><a class="page-link" href="bookList?currentPage=<%=currentPage + 1%>">下一页</a></li>
									<%
										}
									%>

								</ul>
							</nav>
			</div>
		</div>
	</div>
<!-- 删除确认 -->
<div class="modal fade" id="modal-container-645581" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
				<div class="modal-dialog" role="document">
					<div class="modal-content">
						<div class="modal-header">
							<h5 class="modal-title" id="myModalLabel">
								删除确认
							</h5> 
							<button type="button" class="close" data-dismiss="modal">
								<span aria-hidden="true">×</span>
							</button>
						</div>
						<div class="modal-body">
							你确认删除吗
						</div>
						<div class="modal-footer">
							 
							<button type="button" class="btn btn-primary"onclick="exeDel()" >
								确定
							</button> 
							<button type="button" class="btn btn-secondary" data-dismiss="modal">
								取消
							</button>
						</div>
					</div>
					
				</div>
				
			</div>
	<script type="text/javascript" src="bower_components/bootstrap/dist/js/bootstrap.js"></script>
	<script type="text/javascript">
		$(function() {
			$("#themesel").change(
					function(evt) {
						//console.dir(evt.target);
						/* var part=evt.target.options[evt.target.selectedIndex];
						console.dir(part.value); */
						$("#themelink").attr("href","bower_components/bootswatch/dist/"+ $("#themesel").val()+ "/bootstrap.css");
						$.cookie("bootstraptheme", $("#themesel").val(), {expires : 7});
					});
		$('a[href="bookList?currentPage=<%=currentPage%>"]').parent("li").addClass("active");
		});
		function fillSel(typs) {
			
			for (var i = 0; i < typs.length; i++) {
				var op = new Option(typs[i].name, typs[i].id);
				document.getElementById("inputbid").appendChild(op);
			}
			 $("#inputbid").val('<%=request.getAttribute("bid")%>');
			 $("#inputbid").trigger("change");
		};
		function fillSmallSel(typs) {
			document.getElementById("inputsid").innerHTML='<option value="-1">--请选择--</option>';
			for (var i = 0; i < typs.length; i++) {
				var op = new Option(typs[i].name, typs[i].id);
				document.getElementById("inputsid").appendChild(op);
			}
			 $("#inputsid").val('<%=request.getAttribute("sid")%>');
		};
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
	 $('a[class="page-link"][href^="bookList?currentPage="]').click(function(){
               $(this).attr("href",$(this).attr("href")+"&"+$("#fo").serialize());
		 });
	 function exeDel(){
		 window.location.href="bookDel?id="+window.did;

		 }
	</script>
</body>
</html>
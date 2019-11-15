<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="col-sm-3 col-md-2 sidebar">
	<div class="tree">
		<ul style="padding-left:0px;" class="list-group">
			<c:forEach items="${menuList}" var="parent"></c:forEach>
			<li class="list-group-item tree-closed">
				<a href="main.html"><i class="glyphicon glyphicon-dashboard"></i> 控制面板</a>
			</li>
			<li class="list-group-item tree-closed">
				<span><i class="glyphicon glyphicon glyphicon-tasks"></i> 权限管理 <span class="badge" style="float:right">3</span></span>
				<ul style="margin-top:10px;display:none;">
					<li style="height:30px;">
						<a href="user.html"><i class="glyphicon glyphicon-user"></i> 用户维护</a>
					</li>
					<li style="height:30px;">
						<a href="role.html"><i class="glyphicon glyphicon-king"></i> 角色维护</a>
					</li>
					<li style="height:30px;">
						<a href="permission.html" style="color:red;"><i class="glyphicon glyphicon-lock"></i> 许可维护</a>
					</li>
					<li style="height:30px;">
						<a href="menu.html"><i class="glyphicon glyphicon-th-list"></i> 菜单维护</a>
					</li>
				</ul>
			</li>


		</ul>
	</div>
</div>

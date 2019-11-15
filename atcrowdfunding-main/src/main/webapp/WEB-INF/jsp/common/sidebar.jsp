<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
11111111111111111
<div class="col-sm-3 col-md-2 sidebar">
	<div class="tree">
		<ul style="padding-left:0px;" class="list-group">
			<c:forEach items="${menuList}" var="parent">
				<c:if test="${empty parent.childList}">
					<li class="list-group-item tree-closed">
						<a href="${PATH}/${parent.url}"><i class="${parent.icon}"></i> ${parent.name}</a>
					</li>
				</c:if>
				<c:if test="${not empty parent.childList}">
					<li class="list-group-item tree-closed">
						<span><i class="${parent.icon}"></i> ${parent.name} <span class="badge" style="float:right">${parent.childList.size()}</span></span>
						<ul style="margin-top:10px;display:none;">
							<c:forEach items="${parent.childList}" var="child">
								<li style="height:30px;">
									<a href="${PATH}/${child.url}"><i class="${child.icon}"></i> ${child.name}</a>
								</li>
							</c:forEach>
						</ul>
					</li>
				</c:if>
			</c:forEach>
		</ul>
	</div>
</div>

<%@ page language="java" contentType="text/html; charset=utf-8"  pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="pages">
	 <a href="/user/findNewsByPage/1/10" class="p_pre">首页</a>
	 <a href="/user/findNewsByPage/${requestScope.page-1}/10" class="p_pre">上一页</a>
	 <a href="javascript:void(0);" class="cur">${requestScope.page}/${requestScope.pages}</a>
	 <a href="/user/findNewsByPage/${requestScope.page+1}/10" class="p_pre">下一页</a>
	 <a href="/user/findNewsByPage/${requestScope.pages}/10" class="p_pre">尾页</a>
</div>
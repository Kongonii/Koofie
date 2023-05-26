<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %><%-- 
<c:set var="plannerId" value="${param.planner_id}" />
<c:set var="isFavorite" value="${param.isFavorite}" /> --%>

<!-- ���⿡�� plannerId�� isFavorite ���� Ȱ���Ͽ� ���ϴ� �۾��� �����մϴ�. -->

<!DOCTYPE html>
<html>
<head>
    <title>Like</title>
</head>
<body>
    <h1>Like Page</h1>
    
    <form method="post" action="<c:url value='/updateFavorite' />" accept-charset="utf-8">
        <input type="hidden" name="plannerId" value="${plannerId}">
       
    </form>
    
    <p>Planner ID: ${ plannerId}</p>
    <p>Is Favorite: ${isFavorite}</p>
    
    
</body>
</html>
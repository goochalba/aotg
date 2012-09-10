<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page trimDirectiveWhitespaces="true" %>

<c:forEach var="row" items="${menu}">
  <c:set var="id" value="${row.id}"/>
  <c:set var="controller" value="${row.controller}"/>
  <c:set var="prompt" value="${row.prompt}"/>
  <c:set var="parentMenuId" value="${row.parentMenuId}"/>
</c:forEach>

<c:set var="userId" value="${UserID}"/>

<%
  response.setContentType("application/voicexml+xml");
  out.println("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
%>
<vxml version="2.1">
<form id="menu">
  <field name="menuchoice">
    <grammar type="application/x-jsgf" mode="voice">
    <c:forEach items="${menu}" var="row" varStatus="status">
      <c:choose>
        <c:when test="${status.count=='1'}">
          <c:set var="gramList" value="${status.count}" />
        </c:when>
        <c:otherwise>
          <c:set var="gramList" value="${gramList}|${status.count}" />
        </c:otherwise> 
      </c:choose> 
    </c:forEach>
    <c:out value="${gramList}"/>
    </grammar>
    <prompt>
      Put menu text here
      <c:forEach items="${menu}" var="row" begin="1" varStatus="status">
        <c:out value="${row.prompt}"/><c:out value="${status.count}"/>.
      </c:forEach>
    </prompt>
    <filled>
      <c:forEach items="${menu}" var="row" varStatus="status">
        <c:choose>
          <c:when test="${status.count=='1'}">
            <if cond="menuchoice=='<c:out value="${status.count}"/>'"/>
          </c:when>
          <c:otherwise>
            <elseif cond="menuchoice=='<c:out value="${status.count}"/>'"/>
          </c:otherwise> 
        </c:choose> 
        <assign name="UserID" expr="'<c:out value="${userId}"/>'"/>
        <assign name="ParentMenuID" expr="'<c:out value="${row.parentMenuId}"/>'"/>
        <submit next="<c:out value="${row.controller}"/>" namelist="UserID ParentMenuID"/>
      </c:forEach>
    </filled>
  </field>
</form>
</vxml>
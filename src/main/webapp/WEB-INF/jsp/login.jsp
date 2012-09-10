<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%
  response.setContentType("application/voicexml+xml");
  out.println("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
%>

<c:forEach var="row" items="${users}">
  <c:set var="userId" value="${row.id}"/>
  <c:set var="userName" value="${row.userName}"/>
  <c:set var="userPin" value="${row.pin}"/>
</c:forEach>

<vxml version="2.1">
  <form id="getpin">
    <field name="pinnumber" type="digits">
      <grammar xml:lang="en-us" version="1.0" root="pinGrammar" mode="voice">
        <rule id="pinGrammar">
          <item><c:out value="${userPin}"/></item>
        </rule>
      </grammar>
      <prompt>
        Welcome back <c:out value="${userName}"/>. Please speak your pin number.
      </prompt>
      <filled>
        <assign name="UserID" expr="'<c:out value="${userId}"/>'"/>
        <submit next="lastLogin.jsp" namelist="UserID"/>
      </filled>
      <nomatch cond="true">
        That is not the correct pin please try again.
      </nomatch>
    </field>
  </form>
</vxml>


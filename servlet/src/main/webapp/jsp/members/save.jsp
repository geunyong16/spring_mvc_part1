<%@ page import="study.servlet.domain.member.Member" %>
<%@ page import="study.servlet.domain.member.MemberRepository" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
  // request, response 사용 가능(jsp도 결국 servlet으로 바뀌기 때문임)
  MemberRepository memberRepository = MemberRepository.getInstance();

  System.out.println("MemberSaveServlet.service");

  String username = request.getParameter("username");
  int age = Integer.parseInt(request.getParameter("age"));

  Member member = new Member(username, age);
  memberRepository.save(member);
%>
<html>
<head>
    <title>Title</title>
</head>
<body>
성공
<ul>
  <li>id=<%=member.getId()%></li>
  <li>username=<%=member.getUsername()%></li>
  <li>age=<%=member.getAge()%></li>
</ul>
<a href="/index.html">메인</a>
</body>
</html>

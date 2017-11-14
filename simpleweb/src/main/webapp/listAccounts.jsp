<%@page import="curs.banking.model.Account"%>
<%@page import="java.util.Collection"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Account list</title>
</head>
<body>
	<H1>Lista counturi</H1>
	<a href="<%=request.getContextPath()%>">HOME</a>
	<!-- <a href="/banking">HOME</a>  -->

	<TABLE border="2">
		<TR>
			<TH>IBAN</TH>
			<TH>TIP CONT</TH>
			<TH>VALUTA</TH>
			<TH>SOLD</TH>
			<TH>CLIENT</TH>
			<TH>CNP</TH>
			<TH>BANCA</TH>
		</TR>
		<%
			Collection<Account> accList = (Collection<Account>) request.getAttribute("accounts");
			for (Account acc : accList) {
		%>
		<c:forEach items="accounts" var="acc">
		 <TR>
			<TD><%=acc.getIBAN()%></TD>
			<TD><%=acc.getAccountType()%></TD>
			<TD><%=acc.getCurrency()%></TD>
			<TD><%=acc.getAmount()%></TD>
			<TD><%=acc.getCustomer().getName()%></TD>
			<TD><%=acc.getCustomer().getSSN()%></TD>
			<TD><%=acc.getBank().getName()%></TD>

		</TR>
		</c:forEach>
		
		
		<%
			}
		%>
	</TABLE>
	<BR>

</body>
</html>
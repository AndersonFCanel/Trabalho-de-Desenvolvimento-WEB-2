<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>


<c:url value="/entrada" var="linkEntradaServlet"/>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>TELA DE ALTERA��O DE USUARIO</title>
</head>
<body>
	<c:import url="logout-parcial.jsp"/> 
	<form action="${linkEntradaServlet }" method="post">
	
		Nome: <input type="text" name="nome" value="${usuario.nome}"/>
		<br>
		Sobrenome:<input type="text" name="sobrenome" value="${usuario.sobrenome}">
		<br>
		Endere�o:<input type="text" name="endereco" value="${usuario.endereco}">
		<br>
		Login:<input type="text" name="login" value="${usuario.login}">
		<br>
		Senha:<input type="text" name="senha" value="${usuario.senha}">
		<br>
		� Administrador:<input type="text" name="ehAdm" value="${usuario.isAdm}">
		<br>
		Data Nascimento: <input type="text" name="data"  value="${usuario.dataNascimento }" pattern="dd/MM/yyyy"/>
		<br><!-- "<fmt:formatDate value="${usuario.dataNascimento }" pattern="dd/MM/yyyy"/>" /> -->
		<br>
		<input type="hidden" name="id" value="${usuario.id }">
		<input type="hidden" name="acao" value="AlteraUsuario">
		<input type="submit" /> 
	</form>

</body>
</html>
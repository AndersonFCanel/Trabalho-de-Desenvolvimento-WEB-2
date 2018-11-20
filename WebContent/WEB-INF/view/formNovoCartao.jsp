<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:url value="/entrada" var="linkEntradaServlet" />

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<c:import url="script_estilos.jsp" />
<title>Cadastrar Cartao de Credito para ${usuarioLogado.login}</title>
</head>
<body>
	<c:import url="logout-parcial.jsp" />
	<br>
	<p>Ol� ${usuarioLogado.login}, insira os dados de seu cart�o aqui:</p>
	<br> ${erro}
	<form action="${linkEntradaServlet}" method="post">
		Nome: <input type="text" name="nome" value="${usuario.nome}- "
			readonly="true" /> <br> Numero:<input type="text" name="numero"
			value="" /> <br> Data Vencimento:<input type="text"
			name="dataVencimento" value="" /> <br> <input type="hidden"
			name="idUser" value="${usuario.id}" /> <input type="hidden"
			name="acao" value="NovoCartao"> <input type="submit"
			value="Cadastrar Cart�o" />
	</form>
	<br>
	<c:import url="menuLinks.jsp" />
</body>
</html>
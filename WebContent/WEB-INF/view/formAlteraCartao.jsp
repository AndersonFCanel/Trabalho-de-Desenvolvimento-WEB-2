<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>


<c:url value="/entrada" var="linkEntradaServlet" />

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<c:import url="script_estilos.jsp" />
<title>Alterar cadastro de cart�o de credito</title>
</head>
<body>
	<c:import url="logout-parcial.jsp" />
	<br>
<br>
	<form action="${linkEntradaServlet }" method="post">

		<c:if test="${usuarioLogado.isAdm ==  'FALSE'}">
				Nome titular do cart�o: <input type="text" name="nome"
				value="${cartao.titular}" readonly="readonly" />
			<br>
				Numero do cartao: <input type="text" name="numero"
				value="${cartao.numeroCartao}" readonly="readonly" />
			<br>
				Data de Vencimento:<input type="text" name="dataVencimento"
				value="<fmt:formatDate value="${cartao.dataVencimento}" pattern="dd/MM/yyyy"/>" />
			<br>
		</c:if>

		<br> <br> <input type="hidden" name="idUser"
			value="${cartao.idUser }"> <input type="hidden" name="acao"
			value="AlteraCartao"> <input type="submit" value="Alterar Dados" />
	</form>


	<c:import url="menuLinks.jsp" />
</body>
</html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<c:url value="/entrada" var="linkEntradaServlet" />

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<!-- Inclus�o do jQuery via link local -->
<!-- <script src="javascript/jquery.min.js" type="text/javascript"></script>
<script src="javascript/jquery.validate.js" type="text/javascript"></script>
 -->

<!-- Inclus�o do bootstrap via link local -->
<!--  <link rel="stylesheet" href="css/bootstrap-4.1.3/css/bootstrap.min.css">
<script src="css/bootstrap-4.1.3/js/bootstrap.min.js"></script> -->

<!-- Inclus�o do JS  -->
<!-- <script src="javascript/js.js" type="text/javascript"></script> -->

<!-- Inclus�o da folha de estilo-->
<!-- <link rel="stylesheet" type="text/css" href="css/estilo.css"> -->

<c:import url="script_estilos.jsp" />
<title>Acompanhar compra de passagem</title>
</head>
<body>
	<c:import url="logout-parcial.jsp" />
	<br>

	<br> Nome: ${usuario.nome}
	<br> Identificador do voo: ${idvoo.idVoo} - Origem:
	${idvoo.origem} - Destino: ${idvoo.destino} - Ida:
	<fmt:formatDate value="${idvoo.ida}" pattern="dd/MM/yyyy" />
	- Confirma��o:
	<c:if test="${idvoo.confirmacao == 'TRUE'}">
	Confirmado
	</c:if>
	<c:if test="${idvoo.confirmacao == 'FALSE'}">
	N�o confirmado
	</c:if>
	<br>
	<br> Valor por assento: ${idvoo.valorVoo}
	<br>

	<form action="${linkEntradaServlet }" method="post">
		<%-- <input type="text" name="idVooVolta" value="${vooVolta.idVoo}" readonly="readonly"/> --%>
		<c:if test="${cartoes!=null}">
			<h3>Selecione o cart�o que deseja usar nesta compra
				${usuario.nome}</h3>
			Numero do cartao:<select name="numerocartao">
				<c:forEach items="${cartoes}" var="cartao">
					<c:if test="${usuario.id ==  cartao.idUser}">
						<option value="${cartao.numeroCartao}">${cartao.numeroCartao}</option>
						<%-- <li>Numero do cartao: ${cartao.numeroCartao} <input
						type="checkbox" name="numerocartao"
						value="${cartao.numeroCartao}" checked="checked">
					</li> --%>
						<br>
					</c:if>
				</c:forEach>
			</select>
		</c:if>
		<br> <br> Assentos selecionados:
		<c:forEach items="${assentos}" var="assento">
			<%-- <c:out value="${assento.numeroAssento}" /> --%>
			${assento.numeroAssento}<input type="checkbox" name="assento"
				id="numeroAssentoOcupado" value="${assento.numeroAssento}"
				checked="checked" readonly="true">
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 
			<c:set var="precoTotal" value="${precoTotal + idvoo.valorVoo}"
				scope="page" />
		</c:forEach>
		<br> <br> Pre�o total:<input type="text"
			name="valorTotalCompra" value="${precoTotal}" readonly="readonly">
		<input type="hidden" name="idUser" value="${usuarioLogado.id}">
		<input type="hidden" name="idVoo" value="${idvoo.idVoo}"> <input
			type="hidden" name="acao" value="NovoCompraVoo"> <input
			type="submit" value="Confirmar Compra" />
	</form>


	<c:import url="menuLinks.jsp" />
</body>
</html>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.List, com.suam.bean.Usuario"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

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
<title>Listar Detalhes</title>
</head>
<body>
	<c:import url="logout-parcial.jsp" />
	<br>
	<%-- <c:if test="${usuarioLogado.isAdm ==  'TRUE'}"> --%>
		N�mero da Compra: ${compra.idCompra}
		<br>
		Assentos comprados no voo id ${compra.idVoo} pelo usu�rio:<br>
		<ul >
			<c:forEach items="${compra.listaNumeroAssentosIda}" var="assento">
				<li><c:out value="${assento}" /> <input type="checkbox"
					value="${assento}" disabled="disabled">
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</li>
			</c:forEach>
		</ul>
		<br>
		<c:if test="${compra.idVooVolta != '0'}">
			Assentos comprados no voo id ${compra.idVooVolta} pelo usu�rio:<br>
			<ul >
				<c:forEach items="${compra.listaNumeroAssentosVolta}" var="assento">
					<li><c:out value="${assento}" /> <input type="checkbox"
						value="${assento}" disabled="disabled">
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</li>
				</c:forEach>
			</ul>
		</c:if>
		<br>
		<br>
		N�mero do Cart�o utilizado: ${compra.idCartao}
		<br>
		Valor total desta compra: ${compra.valorTotalCompra}  
	<%-- </c:if>
	 --%>

</body>
</html>




<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.List, com.suam.bean.Usuario"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<c:import url="script_estilos.jsp" />
<title>Listar Detalhes</title>
</head>
<body>
	<c:if test="${usuarioLogado.isAdm ==  'TRUE'}">
		<c:import url="navBar.jsp" />
	</c:if>
	<c:if test="${usuarioLogado.isAdm ==  'FALSE'}">
		<c:import url="navBarCli.jsp" />
	</c:if>

	<div class="container-fluid text-center">
		<div class="row content">
			<div class="col-sm-2 sidenav">
				<!--  <p><a href="#">Link</a></p>
      <p><a href="#">Link</a></p>
      <p><a href="#">Link</a></p> -->
			</div>
			<div class="col-sm-8 text-left">
				<h1>Detalhes de compra</h1>
				<br> N�mero da Compra: ${compra.idCompra} <br> Assentos
				comprados no voo id ${compra.idVoo} pelo usu�rio: <br>
				<ul>
					<c:forEach items="${compra.listaNumeroAssentosIda}" var="assento">
						<li><c:out value="${assento}" /> <input type="checkbox"
							value="${assento}" disabled="disabled">
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</li>
					</c:forEach>
				</ul>
				<br>
				<c:if test="${compra.idVooVolta != '0'}">
			Assentos comprados no voo id ${compra.idVooVolta} pelo usu�rio:<br>
					<ul>
						<c:forEach items="${compra.listaNumeroAssentosVolta}"
							var="assento">
							<li><c:out value="${assento}" /> <input type="checkbox"
								value="${assento}" disabled="disabled">
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</li>
						</c:forEach>
					</ul>
				</c:if>
				<br> <br> N�mero do Cart�o utilizado: ${compra.idCartao} <br>
				Valor total desta compra: ${compra.valorTotalCompra}
			</div>
			<div class="col-sm-2 sidenav">
				<!--  <div class="well">
        <p>ADS</p>
      </div>
      <div class="well">
        <p>ADS</p>
      </div> -->
			</div>
		</div>
	</div>
	<br>
	<br>
	<br>
	<br>
	<c:import url="footerBar.jsp" />

</body>
</html>




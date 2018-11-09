<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<c:url value="/entrada" var="linkEntradaServlet"/>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Comprar passagem</title>
</head>
<body>
	<c:import url="logout-parcial.jsp"/> 
	
	Nome: <input type="text" name="nome" value="${usuario.nome}" readonly="readonly"/>
	<form action="${linkEntradaServlet }" method="post">		
	Identificador do voo: <input type="text" name="idVooIda" value="${idvoo.idVoo}" readonly="readonly"/>
	Origem: ${idvoo.origem} -
	Destino: ${idvoo.destino} - 
	Ida: <fmt:formatDate value="${idvoo.ida}" pattern="dd/MM/yyyy"/> -  
	Confirma��o: ${idvoo.confirmacao} - 
	Valor por assento: ${idvoo.valorVoo} <br>
	
	<%-- <input type="text" name="idVooVolta" value="${vooVolta.idVoo}" readonly="readonly"/> --%>
			<c:if test="${cartoes!=null}">	
				<h3>Selecione o cart�o desejado ${usuario.nome}</h3> 
					<c:forEach items="${cartoes}" var="cartao">
						<c:if test="${usuario.id ==  cartao.idUser}">
							<li> 
							 Numero do cartao:  ${cartao.numeroCartao} <input type="checkbox" name="cartaoSelecionado"  value="${cartao.numeroCartao}">
							</li>
							<br>
					</c:if>
				</c:forEach>	
			</c:if>
		
		<c:forEach items="${assentos}" var="assento">
			<c:out value="${assento.numeroAssento}"/>
			<input type="checkbox" name="numeroAssentoOcupado" id="numeroAssentoOcupado" value="${assento.numeroAssento}" checked="checked" disabled="disabled">
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 
			<c:set var="precoTotal" value="${idvoo.valor + $[precoTotal]}" scope="page"/>
		</c:forEach>
		Pre�o total: <c:out value="precoTotal}"/>
		
		
		<br>
		<input type="hidden" name="acao" value="NovoCompraVoo">
		<input type="submit" value="Confirmar Compra"/> 
	</form> 


<c:import url="menuLinks.jsp"/> 
</body>
</html>
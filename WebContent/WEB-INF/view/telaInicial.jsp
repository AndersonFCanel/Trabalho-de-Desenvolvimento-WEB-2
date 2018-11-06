<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.List, com.suam.bean.Usuario"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Inicio</title>
</head>
<body>
	<c:import url="logout-parcial.jsp" />

	Usuario Logado: ${usuarioLogado.login}
	<br>
	<br>
	<br>

	<c:if test="${usuarioLogado.isAdm ==  'TRUE'}">
		<h3>Administrador do Sistema</h3>
		<p>Usuario ${usuarioLogado.nome} com privilegios de Administrador.</p>
		<a href="entrada?acao=FormNovoUsuario">Novo Usu�rio</a>
		descri��o:Tela de CRUD Administrador do Sistema
		<br>
		<a href="entrada?acao=ListaUsuario">Listar usu�rios cadastrados no sistema</a>
		descri��o:Tela de CRUD Administrador do Sistema
		<br>
		<a href="entrada?acao=ListaVoo">Tela administra��o de V�os</a>
		descri��o: Tela de CRUD de V�os
		<br>
		<a href="entrada?acao=">Tela de acompanhamento dos pedidos de v�os</a>
		<br>
		</c:if>
		<br>
		<br>
	<h3>Para teste o adm ira ver op��es disponiveis para clientes tamb�m.</h3>
	<c:if test="${usuarioLogado.isAdm ==  'FALSE' || usuarioLogado.isAdm ==  'TRUE'}">
		<h3>Cliente</h3>
		<a href="entrada?acao=MostraUsuario&id=${usuarioLogado.id}">Alterar Cadastro do Usu�rio</a>
		descri��o: Telas de CRUD Cliente
		<br>
		<a href="entrada?acao=">Tela de Compra de passagem A�rea</a>
		decri��o: Tela de Compra de passagem A�rea OBS: CRIAR Tela de finaliza��o da compra
		<br>
		<a href="entrada?acao=">Tela de acompanhamento dos pedidos de v�os</a>
		<br>
		<a href="entrada?acao=">Tela de escolha de assento e C�lculo do valor final</a>
		<br>
		<a href="entrada?acao=">Tela da Lista de resultados e escolha do v�o do cliente com destaque da linha ap�s ser escolhido e bot�o de enviar.</a>
		<br>
	</c:if>
</body>
</html>




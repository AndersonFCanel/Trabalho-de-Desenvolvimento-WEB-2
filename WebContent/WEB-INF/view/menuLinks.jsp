<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.List, com.suam.bean.Usuario"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

	<c:if test="${usuarioLogado.isAdm ==  'TRUE'}">
		<h3>MENU de Administrador do Sistema</h3>
		<p>Usuario ${usuarioLogado.nome} com privilegios de Administrador.</p>
		<a href="entrada?acao=FormNovoUsuario">NOVO USUARIO</a>
		descri��o:Tela de CRUD Administrador do Sistema
		<br>
		<a href="entrada?acao=ListaCartaoDeCredito">LISTAR TODOS OS CARTOES</a>
		<br>
		<a href="entrada?acao=ListaUsuario">LISTAR TODOS OS USU�RIOS</a>
		descri��o:Tela de CRUD Administrador do Sistema
		<br>
		<a href="entrada?acao=ListaVoo">LISTAR VOOS</a>
		descri��o:Tela CRUD VOOS
		<br>
		<a href="entrada?acao=">Tela administra��o de V�os</a>
		descri��o: Tela de CRUD de V�os
		<br>
		<a href="entrada?acao=">Tela de acompanhamento dos pedidos de v�os</a>
		<br>
		</c:if>
	<br>
	<c:if test="${usuarioLogado.isAdm ==  'FALSE'}">
		<h3>MENU de Cliente</h3>
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
	





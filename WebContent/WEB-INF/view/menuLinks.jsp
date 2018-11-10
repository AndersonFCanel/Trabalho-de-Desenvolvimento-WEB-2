<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.List, com.suam.bean.Usuario"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<br>
<c:if test="${usuarioLogado.isAdm ==  'TRUE'}">
	<h3>Menu do Administrador do Sistema</h3>
	<p>Usuario ${usuarioLogado.nome} com privilegios de Administrador.</p>
	<a href="entrada?acao=FormNovoUsuario"><button>Criar novo
			usu�rio</button></a>
	<br>
	<a href="entrada?acao=ListaUsuario"><button>Usu�rios
			cadastrados no sistema</button></a>
	<br>
	<a href="entrada?acao=ListaVoo"><button>Tela administra��o
			de V�os</button></a>
	<br>
	<a href="entrada?acao="><button>Tela de acompanhamento dos
			pedidos de v�os</button></a>
	<br>
</c:if>
<br>



<h3>==>>Para teste o adm ira ver op��es disponiveis para clientes
	tamb�m.****</h3>
<c:if
	test="${usuarioLogado.isAdm ==  'FALSE' || usuarioLogado.isAdm ==  'TRUE'}">
	<h3>Menu de Cliente</h3>
	<a href="entrada?acao=MostraUsuario&id=${usuarioLogado.id}"><button>Alterar
		Meus Dados</button></a>
		descri��o: Telas de CRUD Cliente
		<br>
	<a href="entrada?acao=ListaVoo"><button>Tela de Compra de passagem A�rea</button></a>
		decri��o: Tela de Compra de passagem A�rea OBS: CRIAR Tela de finaliza��o da compra
		<br>
	<a href="entrada?acao=">Tela de acompanhamento dos pedidos de v�os</a>
	<br>
	<a href="entrada?acao=">Tela de escolha de assento e C�lculo do
		valor final</a>
	<br>
	<a href="entrada?acao=">Tela da Lista de resultados e escolha do
		v�o do cliente com destaque da linha ap�s ser escolhido e bot�o de
		enviar.</a>
	<br>
</c:if>






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
<title>Inicio</title>
</head>
<body>
	<c:import url="logout-parcial.jsp" />
	<br>

	<c:if test="${usuarioLogado.isAdm ==  'TRUE'}">
		<h3>Menu do Administrador</h3>
		<h3>Usuario ${usuarioLogado.nome} com privilegios de
			Administrador.</h3>
		<a href="entrada?acao=FormNovoUsuario"><button>Criar
				Usu�rio</button></a>
		<br>
		<a href="entrada?acao=ListaUsuario"><button>Usu�rios
				cadastrados no sistema</button></a>
		<br>
		<a href="entrada?acao=ListaVoo"><button>Tela
				administra��o de V�os</button></a>
		<br>
		<a href="entrada?acao=ListaCompras"><button>Tela de
				acompanhamento dos pedidos de voos</button></a>
		<br>
		<br>
		<br>
	</c:if>



	<c:if
		test="${usuarioLogado.isAdm ==  'FALSE' || usuarioLogado.isAdm ==  'TRUE'}">
		<h3>Menu do Cliente</h3>
		<h3>Seja bem vindo ${usuarioLogado.nome}.</h3>
		<a href="entrada?acao=MostraUsuario&id=${usuarioLogado.id}"><button>Alterar
				Meus Dados</button></a>
		<br>
		<a href="entrada?acao=ListaVoo"><button>Tela de Compra de
				passagem A�rea</button> </a>
		<br>
		<a href="entrada?acao=ListaCompras"><button>Tela de
				acompanhamento dos pedidos de v�os</button></a>
		<br>
	</c:if>
</body>
</html>




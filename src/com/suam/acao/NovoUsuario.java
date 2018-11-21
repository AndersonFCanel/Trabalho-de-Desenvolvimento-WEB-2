package com.suam.acao;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.suam.bean.Usuario;
import com.suam.service.UsuarioService;

public class NovoUsuario implements Acao {

	public String executa(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		System.out.println("A��O = INSERINDO USUARIO");

		String nome = request.getParameter("nome");
		String sobrenome = request.getParameter("sobrenome");
		String endereco = request.getParameter("endereco");
		String senha = request.getParameter("senha");
		String confirmaSenha = request.getParameter("confirmaSenha");
		String login = request.getParameter("login");
		String data = request.getParameter("data");
		String ehAdm = request.getParameter("ehAdm");

		String info = null;

		if (nome == null || nome.equals("")) {
			info = "Nome n�o prennchido";
			request.setAttribute("erro", info);
			return "forward:erro.jsp";
		} else if (sobrenome == null || sobrenome.equals("")) {
			info = "Sobrenome n�o prennchido";
			request.setAttribute("erro", info);
			return "forward:erro.jsp";
		} else if (endereco == null || endereco.equals("")) {
			info = "Endere�o n�o informado!";
			request.setAttribute("erro", info);
			return "forward:erro.jsp";
		} else if (senha == null || senha.equals("")) {
			info = "Senha n�o informada!";
			request.setAttribute("erro", info);
			return "forward:erro.jsp";
		} else if (login == null || login.equals("")) {
			info = "Login n�o informado!";
			request.setAttribute("erro", info);
			return "forward:erro.jsp";
		} else if (data == null || data.equals("")) {
			info = "Data n�o informada!";
			request.setAttribute("erro", info);
			return "forward:erro.jsp";
		}

		Usuario usuario = new Usuario();
		SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");

		usuario.setNome(nome);
		usuario.setSobrenome(sobrenome);
		usuario.setEndereco(endereco);
		usuario.setSenha(senha);
		usuario.setLogin(login);
		try {
			usuario.setDataNascimento(formato.parse(data));
		} catch (ParseException e1) {
			e1.printStackTrace();
		}
		if (ehAdm != null) {
			System.out.println("NOVO USUARIO E ADM: " + ehAdm);
			if (ehAdm.equals("true") || ehAdm.equals("administrador") || ehAdm.equals("1")) {
				usuario.setIsAdm(true);
			} else if (ehAdm.equals("cliente") || ehAdm.equals("") || ehAdm.equals("0")) {
				usuario.setIsAdm(false);
			}
		} else {
			usuario.setIsAdm(false);
		}

		Boolean validaInsere;
		if (senha.equals(confirmaSenha)) {
			try {
				validaInsere = UsuarioService.inserir(usuario);
				if (validaInsere) {
					System.out.println("Inserido com sucesso");
				} else {
					System.out.println("CORRIGIR LOGIN!!");
					request.setAttribute("usuario", usuario);
					return "forward:formNovoUsuario.jsp";
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return "redirect:entrada?acao=ListaUsuario";
		} else {
			System.out.println("AS SENHAS N�O CONFEREM");
			request.setAttribute("usuario", usuario);
			return "forward:formNovoUsuario.jsp";
		}

	}
}

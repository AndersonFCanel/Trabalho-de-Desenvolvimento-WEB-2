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
		if (ehAdm.equalsIgnoreCase("TRUE")) {
			usuario.setIsAdm(true);
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

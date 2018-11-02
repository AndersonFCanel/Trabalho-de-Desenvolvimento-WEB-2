package com.suam.acao;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.suam.bean.Usuario;
import com.suam.service.UsuarioService;

public class AlteraUsuario implements Acao {

	public String executa(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		System.out.println("A��O = ALTERANDO USUARIO");

		String nome = request.getParameter("nome");
		String sobrenome = request.getParameter("sobrenome");
		String endereco = request.getParameter("endereco");
		String senha = request.getParameter("senha");
		String login = request.getParameter("login");
		String data = request.getParameter("data");
		String paramId = request.getParameter("id");
		String ehAdm = request.getParameter("ehAdm");
		Integer id = Integer.valueOf(paramId);

		System.out.println("acao altera usuario " + id);

		/*
		 * Date dataAbertura = null; try { SimpleDateFormat sdf = new
		 * SimpleDateFormat("dd/MM/yyyy"); dataAbertura = sdf.parse(paramDataEmpresa); }
		 * catch (ParseException e) { throw new ServletException(e); }
		 */

		Usuario usuario = null;
		try {
			usuario = UsuarioService.buscaUsuarioPelaId(id);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		usuario.setNome(nome);
		usuario.setSobrenome(sobrenome);
		usuario.setEndereco(endereco);
		usuario.setSenha(senha);
		usuario.setLogin(login);
		usuario.setDataNascimento(data);
		usuario.setIsAdm(ehAdm);

		/*
		 * UsuarioService us = new UsuarioService(); try { us.update(usuario); } catch
		 * (SQLException e) { // TODO Auto-generated catch block e.printStackTrace(); }
		 */

		UsuarioService us = new UsuarioService();
		Boolean validaInsere;

		try {
			validaInsere = us.update(usuario);
			if (validaInsere) {
				System.out.println("ATUALIZADO com sucesso");
			} else {
				request.setAttribute("usuario", usuario);
				return "forward:formAlteraUsuario.jsp";
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		if (usuario.getIsAdm().equals("TRUE")) {
			return "redirect:entrada?acao=ListaUsuario";
		}else {
			return "redirect:entrada?acao=MostraUsuario&id="+usuario.getId();
		}
		
	}
}

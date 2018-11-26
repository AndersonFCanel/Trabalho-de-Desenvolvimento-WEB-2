package com.suam.acao;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.suam.bean.Usuario;
import com.suam.constantes.Diretorios.Local;
import com.suam.constantes.Info.InfoCampos;
import com.suam.constantes.Parametros.ParametroTela;
import com.suam.service.UsuarioService;
import com.suam.util.DataUtils;

public class AlteraUsuario implements Acao {

	public String executa(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("A��O = ALTERANDO USUARIO");

		String nome = request.getParameter(ParametroTela.NOME);
		String sobrenome = request.getParameter(ParametroTela.SOBRENOME);
		String endereco = request.getParameter(ParametroTela.ENDERECO);
		String senha = request.getParameter(ParametroTela.SENHA);
		String login = request.getParameter(ParametroTela.LOGIN);
		String data = request.getParameter(ParametroTela.DATA);
		String usuarioId = request.getParameter(ParametroTela.ID_USER);
		String ehAdm = request.getParameter(ParametroTela.EH_ADM);
		Integer id = Integer.valueOf(usuarioId);
		String info = null;

		if (nome == null  ||nome.equals("")) {
			info = InfoCampos.NOME_PROBLEMA;
			request.setAttribute(ParametroTela.ERRO, info);
			return "forward:"+Local.ERRO_VIEW;
		} else if (sobrenome == null  ||sobrenome.equals("")) {
			info = InfoCampos.SOBRENOME_PROBLEMA;
			request.setAttribute(ParametroTela.ERRO, info);
			return "forward:"+Local.ERRO_VIEW;
		} else if (endereco == null ||endereco.equals("")) {
			info = InfoCampos.ENDERECO_PROBLEMA;
			request.setAttribute(ParametroTela.ERRO, info);
			return "forward:"+Local.ERRO_VIEW;
		} else if (senha == null  ||senha.equals("")) {
			info = InfoCampos.SENHA_PROBLEMA;
			request.setAttribute(ParametroTela.ERRO, info);
			return "forward:"+Local.ERRO_VIEW;
		} else if (login == null ||login.equals("")) {
			info = InfoCampos.LOGIN_PROBLEMA;
			request.setAttribute(ParametroTela.ERRO, info);
			return "forward:"+Local.ERRO_VIEW;
		} else if (data == null ||data.equals("")) {
			info = InfoCampos.DATA_PROBLEMA;
			request.setAttribute(ParametroTela.ERRO, info);
			return "forward:"+Local.ERRO_VIEW;
		} else if (id == null ||id.equals("")) {
			info = InfoCampos.GENERICO;
			request.setAttribute(ParametroTela.ERRO, info);
			return "forward:"+Local.ERRO_VIEW;
		}

		Usuario usuario = null;
		try {
			usuario = UsuarioService.buscaUsuarioPelaId(id);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		usuario.setNome(nome);
		usuario.setSobrenome(sobrenome);
		usuario.setEndereco(endereco);
		usuario.setSenha(senha);
		usuario.setLogin(login);
		try {
			usuario.setDataNascimento(DataUtils.formatarData().parse(data));
		} catch (ParseException e1) {
			e1.printStackTrace();
		}
		if (ehAdm != null) {
			System.out.println("NOVO USUARIO, LOGIN: " + usuario.getLogin() + " � ADM: " + ehAdm);
			if (ehAdm.equals("true") || ehAdm.equals("administrador") || ehAdm.equals("1")) {
				usuario.setIsAdm(true);
			} else if (ehAdm.equals("cliente") || ehAdm.equals("") || ehAdm.equals("0")) {
				usuario.setIsAdm(false);
			}
		} else {
			usuario.setIsAdm(false);
		}

		Boolean validaInsere;
		try {
			validaInsere = UsuarioService.update(usuario);
			if (validaInsere) {

			} else {
				info = "ERRO,  Alguma coisa n�o funcionou como deveria!";
				request.setAttribute(ParametroTela.OBJETO_USUARIO, usuario);
				request.setAttribute(ParametroTela.ERRO, info);
				return Local.FORM_ALTERA_USUARIO;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		//return "redirect:entrada?acao=MostraUsuario&id=" + usuario.getId();
		return "redirect:entrada?acao=ListaUsuario";
	}
}

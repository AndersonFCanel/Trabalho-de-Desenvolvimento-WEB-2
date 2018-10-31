package com.suam.acao;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.suam.bean.CartaoDeCredito;
import com.suam.bean.Usuario;
import com.suam.service.CartaoDeCreditoService;
import com.suam.service.UsuarioService;



public class MostraCartao  implements Acao{

	public String executa(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("A��O = MOSTRANDO DADOS DO CARTAO");
		
		String paramId = request.getParameter("id");
		Integer id = Integer.valueOf(paramId);
		
		Usuario usuario = null;
		try {
			usuario = UsuarioService.buscaUsuarioPelaId(id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		CartaoDeCredito cartao = null;
		try {
			cartao = CartaoDeCreditoService.buscaUsuarioPelaId(usuario.getId());
			cartao.setTitular(usuario.getNome());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		request.setAttribute("cartao",cartao);
		
		return "forward:formAlteraCartao.jsp";
	}
}

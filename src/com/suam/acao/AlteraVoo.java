package com.suam.acao;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.suam.bean.Voo;
import com.suam.service.VooService;

public class AlteraVoo implements Acao {

	public String executa(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		System.out.println("A��O = ALTERANDO VOO");

		String origem = request.getParameter("origem");
		String destino = request.getParameter("destino");
		String ida = request.getParameter("ida");
		String confirmacao = request.getParameter("confirmacao");
		String idVoo = request.getParameter("idVooIda");
		//Integer idVooIda = Integer.valueOf(paramId);

		System.out.println("acao altera voo: " + idVoo);

		Voo voo = null;
		try {
			voo = VooService.buscaVooPelaId(Integer.parseInt(idVoo));
		} catch (SQLException e) {
			e.printStackTrace();
		}

		SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
		
		try {
			voo.setIda(formato.parse(ida));
		} catch (ParseException e1) {
			e1.printStackTrace();
		}
		
		if (confirmacao.equalsIgnoreCase("TRUE")) {
			voo.setConfirmacao(true);
		} else {
			voo.setConfirmacao(false);
		}
		voo.setOrigem(origem);
		voo.setDestino(destino);

		Boolean validaInsere;

		try {
			validaInsere = VooService.update(voo);
			if (validaInsere) {
			} else {
				request.setAttribute("voo", voo);
				return "forward:formAlteraVoo.jsp";
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return "redirect:entrada?acao=MostraVoo&id=" + idVoo;
	}
}

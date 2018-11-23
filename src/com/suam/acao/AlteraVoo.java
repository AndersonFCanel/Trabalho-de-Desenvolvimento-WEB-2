package com.suam.acao;

import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.suam.bean.Voo;
import com.suam.constantes.Info.InfoCampos;
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
		String valorVoo = request.getParameter("valorVoo");

		System.out.println("acao altera voo: " + idVoo);

		String info = null;

		if (origem == null ||origem.equals("")) {
			info = InfoCampos.ORIGEM_PROBLEMA;
			request.setAttribute("erro", info);
			return "forward:erro.jsp";
		} else if (destino == null ||destino.equals("")) {
			info = InfoCampos.DESTINO_PROBLEMA;
			request.setAttribute("erro", info);
			return "forward:erro.jsp";
		} else if (ida == null ||ida.equals("")) {
			info = InfoCampos.IDA_PROBLEMA;
			request.setAttribute("erro", info);
			return "forward:erro.jsp";
		} else if (confirmacao == null ||confirmacao.equals("")){
			info = InfoCampos.CONFIRMACAO_PROBLEMA;
			request.setAttribute("erro", info);
			return "forward:erro.jsp";
		} else if (valorVoo == null ||valorVoo.equals("")) {
			info = InfoCampos.VALOR_PROBLEMA;
			request.setAttribute("erro", info);
			return "forward:erro.jsp";
		} else if (idVoo == null ||idVoo.equals("")) {
			info = InfoCampos.GENERICO;
			request.setAttribute("erro", info);
			return "forward:erro.jsp";
		}

		Voo voo = null;
		try {
			voo = VooService.buscaVooPelaId(Integer.valueOf(idVoo));
		} catch (SQLException e) {
			e.printStackTrace();
		}

		if (confirmacao != null) {
			if (confirmacao.equals("true") || confirmacao.equals("administrador") || confirmacao.equals("1")) {
				voo.setConfirmacao(true);
			} else if (confirmacao.equals("cliente") || confirmacao.equals("") || confirmacao.equals("0")) {
				voo.setConfirmacao(false);
			}
		} else {
			voo.setConfirmacao(false);
		}
		voo.setOrigem(origem);
		voo.setDestino(destino);
		voo.setValorVoo(Integer.valueOf(valorVoo));

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

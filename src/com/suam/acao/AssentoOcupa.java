package com.suam.acao;

import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.suam.bean.Assento;
import com.suam.constantes.Constantes.InfoCampos;
import com.suam.constantes.Constantes.Local;
import com.suam.constantes.Constantes.ParametroTela;
import com.suam.service.AssentoService;

public class AssentoOcupa implements Acao {

	public String executa(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		System.out.println("A��O => OCUPANDO ASSENTOS");
		String idVoo = request.getParameter(ParametroTela.VOO_ID);
		System.out.println("idvoo" + idVoo);
		String ocupa = request.getParameter(ParametroTela.ASSENTO_OCUPA);
		String desocupa = request.getParameter(ParametroTela.ASSENTO_DESOCUPA);
		String ocupante = request.getParameter(ParametroTela.ASSENTO_OCUPANTE);
		String[] numeroAssento = request.getParameterValues(ParametroTela.ASSENTO_NUMERO);
		String[] numeroAssentoOcupado = request.getParameterValues(ParametroTela.ASSENTO_NUMERO_OCUPADO);
		String[] numeroAssentoVolta = request.getParameterValues(ParametroTela.ASSENTO_NUMERO_VOLTA);
		String[] numeroAssentoOcupadoVolta = request.getParameterValues(ParametroTela.ASSENTO_NUMEROA_OCUPADO_VOLTA);
		String idVooVolta = request.getParameter(ParametroTela.ASSENTO_ID_VOO_VOLTA);
		Assento assentoIda = new Assento();
		Assento assentoVolta = new Assento();
		Integer idOcupante = Integer.valueOf(ocupante);
		Integer idVolta = null;
		String info = null;

		if (idVooVolta != null && idVooVolta != "" && idVooVolta != "null") {
			idVolta = Integer.valueOf(idVooVolta);
		}

		Integer id = null;
		if (idVoo != null && idVoo != "" && idVoo != "null") {
			id = Integer.valueOf(idVoo);
		}

		if (numeroAssento != null || numeroAssentoOcupado != null
				|| (numeroAssentoVolta != null || numeroAssentoOcupadoVolta != null)) {
		} else {
			info = InfoCampos.ASSENTO_NAO_SELECIONADO;
			request.setAttribute(ParametroTela.ERRO, info);
			return "forward:"+Local.ERRO_VIEW;
		}

		if (idVoo != null) {
			if (numeroAssento != null) {
				for (String assentoNum : numeroAssento) {
					System.out.println("Assento numero: " + assentoNum);
					System.out.println("Voo id: " + id);
					Integer numAssento = Integer.valueOf(assentoNum);
					assentoIda.setIdVoo(id);
					assentoIda.setNumeroAssento(numAssento);
					assentoIda.setOcupante(idOcupante);
					if (ocupa != null) {
						assentoIda.setOcupado(true);
					} else {
						assentoIda.setOcupado(false);
					}
					assentoIda.setOcupante(idOcupante);
					try {
						AssentoService.update(assentoIda);
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
			}

			// List<String> listaAssentosOcupados = new ArrayList<String>();
			if (numeroAssentoOcupado != null) {
				for (String assentoNum : numeroAssentoOcupado) {
					System.out.println("Assento numero: " + assentoNum);
					System.out.println("Voo id: " + id);
					Integer numAssento = Integer.valueOf(assentoNum);
					assentoIda.setIdVoo(id);
					assentoIda.setNumeroAssento(numAssento);
					assentoIda.setOcupante(idOcupante);
					if (desocupa != null) {
						assentoIda.setOcupado(false);
					} else {
						assentoIda.setOcupado(true);
					}
					try {
						AssentoService.update(assentoIda);
						// listaAssentosOcupados.add(assentoNum);
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
			}
		}
		if (idVolta != null) {

			if (numeroAssentoVolta != null) {
				for (String assentoNumVolta : numeroAssentoVolta) {
					System.out.println("Assento numero: " + assentoNumVolta);
					System.out.println("Voo id: " + idVooVolta);
					Integer numAssento = Integer.valueOf(assentoNumVolta);
					assentoVolta.setIdVoo(idVolta);
					assentoVolta.setNumeroAssento(numAssento);
					assentoVolta.setOcupante(idOcupante);
					if (ocupa != null) {
						assentoVolta.setOcupado(true);
					} else {
						assentoVolta.setOcupado(false);
					}
					assentoVolta.setOcupante(idOcupante);
					try {
						AssentoService.update(assentoVolta);
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
			}

			// List<String> listaAssentosOcupados = new ArrayList<String>();
			if (numeroAssentoOcupadoVolta != null) {
				for (String assentoNumVolta : numeroAssentoOcupadoVolta) {
					System.out.println("Assento numero: " + assentoNumVolta);
					System.out.println("Voo id: " + idVooVolta);
					Integer numAssento = Integer.valueOf(assentoNumVolta);
					assentoVolta.setIdVoo(idVolta);
					assentoVolta.setNumeroAssento(numAssento);
					assentoVolta.setOcupante(idOcupante);
					if (desocupa != null) {
						assentoVolta.setOcupado(false);
					} else {
						assentoVolta.setOcupado(true);
					}
					try {
						AssentoService.update(assentoVolta);
						// listaAssentosOcupados.add(assentoNum);
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
			}
			return "redirect:entrada?acao=ListaAssento&vooId=" + idVoo + "&voltaId=" + idVooVolta;
		}

		return "redirect:entrada?acao=ListaAssento&vooId=" + assentoIda.getIdVoo();

	}
}

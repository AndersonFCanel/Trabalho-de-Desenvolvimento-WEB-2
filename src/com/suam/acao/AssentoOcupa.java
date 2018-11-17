package com.suam.acao;

import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.suam.bean.Assento;
import com.suam.service.AssentoService;

public class AssentoOcupa implements Acao {

	public String executa(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		System.out.println("A��O => OCUPANDO ASSENTOS");
		String idVoo = request.getParameter("vooId");
		System.out.println("idvoo" + idVoo);
		String ocupa = request.getParameter("ocupa");
		String desocupa = request.getParameter("desocupa");
		String ocupante = request.getParameter("ocupante");
		String[] numeroAssento = request.getParameterValues("numeroAssento");
		String[] numeroAssentoOcupado = request.getParameterValues("numeroAssentoOcupado");
		String[] numeroAssentoVolta = request.getParameterValues("numeroAssentoVolta");
		String[] numeroAssentoOcupadoVolta = request.getParameterValues("numeroAssentoOcupadoVolta");
		String idVooVolta = request.getParameter("idVooVolta");
		Assento assentoIda = new Assento();
		Assento assentoVolta = new Assento();
		Integer idOcupante = Integer.valueOf(ocupante);
		Integer idVolta = null;
		String erro = null;

		if (idVooVolta != null && idVooVolta != "" && idVooVolta != "null") {
			idVolta = Integer.valueOf(idVooVolta);
		}

		Integer id = null;
		if (idVoo != null && idVoo != "" && idVoo != "null") {
			id = Integer.valueOf(idVoo);
		}

		if (numeroAssento != null || numeroAssentoOcupado != null
				|| (numeroAssentoVolta != null || numeroAssentoOcupadoVolta != null)) {
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
		} else {
			erro = "Voc� n�o escolheu nenhum assento";
			request.setAttribute("erro", erro);
			return "forward:erro.jsp";
		}
	}
}

package com.suam.acao;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.suam.bean.Assento;
import com.suam.service.AssentoService;

public class ListaAssento implements Acao {

	public String executa(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("A��O = LISTANDO Assentos");
		String vooIdParam = request.getParameter("vooId");
		Integer vooId =Integer.valueOf(vooIdParam);
		
		List<Assento> listaAssentos = null;
		List<Assento> listaAssentosDesocupados = null;
		try {
			listaAssentos = AssentoService.ListaAssentos(vooId);
			listaAssentosDesocupados = AssentoService.ListaAssentosDesocupados(vooId);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
//		for (Assento assento : listaAssentos) {
//			System.out.println("num "+assento.getNumeroAssento()+" id "+assento.getIdVoo()+" ocupa "+assento.isOcupado());
//		}
		
		request.setAttribute("vooId", vooId);
		request.setAttribute("assentosDesocupados", listaAssentosDesocupados);
		request.setAttribute("assentos", listaAssentos);
		return "forward:listaAssentos.jsp";
	}

}

package com.suam.acao;

import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.suam.VO.CompraVooVO;
import com.suam.bean.Usuario;
import com.suam.constantes.Constantes.InfoCampos;
import com.suam.service.CompraVooService;
import com.suam.service.UsuarioService;

public class MostraCompra implements Acao {

	public String executa(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("A��O = MOSTRANDO COMPRA");

		String idCompra = request.getParameter("idCompra");
		Integer idCompraInt = Integer.valueOf(idCompra);
		
		String info = null;
		if (idCompra == null || idCompra.equals("")) {
			info = InfoCampos.GENERICO;
			request.setAttribute("erro", info);
			return "forward:erro.jsp";
		}
		CompraVooVO compraVooVO = new CompraVooVO();
		Usuario usuario = new Usuario();

		try {
			compraVooVO = CompraVooService.comprasPorId(idCompraInt);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			usuario = UsuarioService.buscaUsuarioPelaIdHistoricoDeCompras(compraVooVO.getIdUser());
		} catch (SQLException e1) {
			e1.printStackTrace();
		}

		try {
			compraVooVO.setListaNumeroAssentosIda(
					CompraVooService.comprasAssentosVooIda(idCompraInt, compraVooVO.getIdVoo()));
		} catch (SQLException e) {
			e.printStackTrace();
		}

		if (compraVooVO.getIdVooVolta() != null) {
			try {
				compraVooVO.setListaNumeroAssentosVolta(
						CompraVooService.comprasAssentosVooVolta(idCompraInt, compraVooVO.getIdVooVolta()));
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	
		compraVooVO.setNomeUsuario(usuario.getNome()+" "+usuario.getSobrenome());

		/*
		 * try { listaAssentoPagamentoConfirmado =
		 * AssentoService.listarAssentosPagosPorUsuarioIdVooId(compraVoo.getIdUser(),
		 * CvooId); } catch (SQLException e) { e.printStackTrace(); }
		 */
		// Integer valorTotal = (listaAssentoPagamentoConfirmado.size() *
		// voo.getValorVoo())+valorTotal;

		request.setAttribute("compra", compraVooVO);
		return "forward:mostraCompra.jsp";
	}
}

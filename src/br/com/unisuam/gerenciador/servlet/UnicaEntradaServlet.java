package br.com.alura.gerenciador.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.com.alura.gerenciador.acao.Acao;

/*
 * Nossa abordagem � funcional, mas v�rios controladores do mercado 
 * usam uma outra abordagem para definir o nome da a��o. 
 * A ideia � que nosso controlador receba qualquer requisi��o para 
 * qualquer URL, atrav�s do mapeamento /:
 * 
 * @WebServlet("/")
 * */

//ATEN��O  ==>>ATRIBUI��ES DESTA SERVLET REPASSADAS AO ControladorFilter.Java

//@WebServlet("/entrada") 
public class UnicaEntradaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String paramAcao = request.getParameter("acao");

		// Verifica��o de seguran�a atribuida aos Filter's
		// HttpSession sessao = request.getSession();
		// boolean usuarioNaoEstalogado = (sessao.getAttribute("usuarioLogado")==null);
		// boolean ehUmaAcaoProtegida =
		// !(paramAcao.equals("Login")||paramAcao.equals("LoginForm"));
		//
		// if(usuarioNaoEstalogado && ehUmaAcaoProtegida) {
		// response.sendRedirect("entrada?acao=LoginForm");
		// return;
		// }

		String nomeDaClasse = "br.com.alura.gerenciador.acao." + paramAcao;

		/*
		 * O m�todo getRequestURI(), do objeto HttpServletRequest, devolve exatamente
		 * essa informa��o:
		 */
		String url = request.getRequestURI();
		System.out.println("URL: " + url);

		String nome;
		try {
			Class classe = Class.forName(nomeDaClasse);// carrega a classe com o nome
			Acao acao = (Acao) classe.newInstance();
			nome = acao.executa(request, response);
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
			throw new ServletException(e);
		}

		String[] tipoEEndereco = nome.split(":");
		if (tipoEEndereco[0].equals("forward")) {
			RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/view/" + tipoEEndereco[1]);
			rd.forward(request, response);
		} else {
			response.sendRedirect(tipoEEndereco[1]);
		}
	}
}

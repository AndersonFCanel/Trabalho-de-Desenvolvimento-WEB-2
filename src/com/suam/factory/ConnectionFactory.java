package com.suam.factory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {

	/* Constantes com os par�metros da conex�o */
	// MYSQL
	private static final String URL = "jdbc:mysql://localhost:3306/dbweb2?useTimezone=true&serverTimezone=UTC";
	private static final String USERNAME = "root";
	private static final String PASSWORD = "root";
	private static final String STRINGCONEXAO = URL + USERNAME + PASSWORD;

	public static java.sql.Connection getConnection() throws SQLException {

		Connection conexao = null;
		try {
			/* Obt�m o driver de conex�o com o banco de dados */
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			/* Tenta se conectar */
			System.out.println("String de conex�o MYSQL: " + STRINGCONEXAO);
			conexao = DriverManager.getConnection(URL, USERNAME, PASSWORD);

			/* Configura commit como N�O Autom�tico */
			conexao.setAutoCommit(false);

			/* Caso a conex�o ocorra com sucesso, a conex�o � retornada */
			// conexao com HSQLDB
			// conexao = DriverManager.getConnection("jdbc:hsqldb:hsql://localhost/dbweb2",
			// "SA", "");
			// System.out.println("CONEXAO FACTORY STRING: "+conexao );

			// TENTANDO SE CONECTAR A HSQLDB
			// Class.forName("org.hsqldb.jdbcDriver");

			/* Caso a conex�o ocorra com sucesso, a conex�o � retornada */
			// conexao com HSQLDB
			// conexao = DriverManager.getConnection("jdbc:hsqldb:hsql://localhost/dbweb2",
			// "SA", "");
			// System.out.println("CONEXAO FACTORY STRING: "+conexao );
			return conexao;

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Nao foi possivel conectar ao banco de dados.");
			return null;
		}
	}
}
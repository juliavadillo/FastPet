package com.fastpetsystem.model.dao;
import java.sql.DriverManager;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class AcessoBD {
	Statement statement =null;
	

public static Connection ObterConexao() {
        Connection conexao = null;
        try {
                 Class.forName("oracle.jdbc.driver.OracleDriver");
                 conexao = DriverManager.getConnection ("jdbc:oracle:thin:@localhost:1521:db01", "pet", "pet");
        } catch (ClassNotFoundException e) {
                 e.printStackTrace();																				
        } catch (SQLException e) {
                 e.printStackTrace();
        }
        return conexao;
}

public void executa (String sql) {
	
    try {
        statement.executeUpdate(sql);
    } catch (SQLException e) {
        System.err.println("Excessão: " + e.toString());
    }
}


    
    

}
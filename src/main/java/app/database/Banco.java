package main.java.app.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Banco {
  private static final String URL = "jdbc:postgresql://localhost:5432/sistema_bancario";
  private static final String USUARIO = "postgres";
  private static final String SENHA = "101504";

  public static Connection conectar() throws SQLException {
    return DriverManager.getConnection(URL, USUARIO, SENHA);
  }
}

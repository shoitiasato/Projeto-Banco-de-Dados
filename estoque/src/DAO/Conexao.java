package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class Conexao
{
  String usuario = "root";
  String senha = "";
  String servidor = "localhost";
  String banco = "estoquetec";
  String url = "jdbc:mysql://" + this.servidor + ":3306/" + this.banco;
  Connection conexao;
  
  public Conexao()
  {
    try
    {
      this.conexao = DriverManager.getConnection(this.url, this.usuario, this.senha);
    }
    catch (SQLException ex)
    {
      JOptionPane.showMessageDialog(null, "N�O CONECTOU: " + ex.getMessage());
    }
  }
  
  public Connection getConexao()
  {
    return this.conexao;
  }
}

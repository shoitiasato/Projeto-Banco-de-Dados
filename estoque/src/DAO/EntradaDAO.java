package DAO;

import Entidade.Entrada;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import javax.swing.JOptionPane;

public class EntradaDAO
{
  Connection conexao;
  
  public EntradaDAO()
  {
    this.conexao = new Conexao().getConexao();
  }
  
  public int inserir(Entrada e, int cod)
  {
    int codigoEntrada = 0;
    try
    {
      String sql = "insert into entrada (fun_cod, ent_data)values(?, curDate())";
      
      PreparedStatement ps = this.conexao.prepareStatement(sql);
      ps.setInt(1, cod);
      ps.execute();
      sql = "select max(ent_cod) from entrada";
      ps = this.conexao.prepareStatement(sql);
      ResultSet rs = ps.executeQuery();
      rs.next();
      codigoEntrada = rs.getInt(1);
      new ItemEntradaDAO().inserir(e.getLista(), codigoEntrada);
    }
    catch (SQLException ex)
    {
      JOptionPane.showMessageDialog(null, "Erro ao cadastrar Entrada" + ex.getMessage());
    }
    return codigoEntrada;
  }
  
}

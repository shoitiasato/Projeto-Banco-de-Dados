package DAO;

import Entidade.Saida;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import javax.swing.JOptionPane;


public class SaidaDAO
{
  Connection conexao;
  
  public SaidaDAO()
  {
    this.conexao = new Conexao().getConexao();
  }
  
  public int inserir(Saida s, int cod)
  {
    int codigoSaida = 0;
    try
    {
      String sql = "insert into saida (fun_cod, sai_data)values(?, curDate())";
      
      PreparedStatement ps = this.conexao.prepareStatement(sql);
      ps.setInt(1, cod);
      ps.execute();
      sql = "select max(sai_cod) from saida";
      ps = this.conexao.prepareStatement(sql);
      ResultSet rs = ps.executeQuery();
      rs.next();
      codigoSaida = rs.getInt(1);
      new ItemSaidaDAO().inserir(s.getLista(), codigoSaida);
    }
    catch (SQLException ex)
    {
      JOptionPane.showMessageDialog(null, "Erro ao cadastrar Saida" + ex.getMessage());
    }
    return codigoSaida;
  }
}

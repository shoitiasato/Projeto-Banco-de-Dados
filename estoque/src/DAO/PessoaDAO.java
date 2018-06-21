package DAO;

import Entidade.Pessoa;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class PessoaDAO
{
  Connection conexao;
  
  public PessoaDAO()
  {
    this.conexao = new Conexao().getConexao();
  }
  
  public int inserir(Pessoa p)
  {
    int codigoPessoa = 0;
    try
    {
      String sql = "insert into pessoa (end_cep, end_cidade, end_uf, end_rua, end_num, end_complemento, pe_situacao)values(?,?,?,?,?,?,?)";
      
      PreparedStatement ps = this.conexao.prepareStatement(sql);
      ps.setString(1, p.getCep());
      ps.setString(2, p.getCidade());
      ps.setString(3, p.getUf());
      ps.setString(4, p.getRua());
      ps.setString(5, p.getNumero());
      ps.setString(6, p.getComplemento());
      ps.setString(7, p.getSituacao());
      ps.execute();
      sql = "select max(pes_codigo) from pessoa";
      ps = this.conexao.prepareStatement(sql);
      ResultSet rs = ps.executeQuery();
      rs.next();
      codigoPessoa = rs.getInt(1);
    }
    catch (SQLException ex)
    {
      JOptionPane.showMessageDialog(null, "Erro ao cadastrar Pessoa" + ex.getMessage());
    }
    return codigoPessoa;
  }
  
  public void alterar(Pessoa p)
    throws SQLException
  {
    try
    {
      String sql = "update pessoa set end_cep = ?, end_cidade = ?, end_uf = ?, end_rua = ?, end_num = ?, end_complemento = ? where pes_codigo = ?";
      PreparedStatement ps = this.conexao.prepareStatement(sql);
      ps.setString(1, p.getCep());
      ps.setString(2, p.getCidade());
      ps.setString(3, p.getUf());
      ps.setString(4, p.getRua());
      ps.setString(5, p.getNumero());
      ps.setString(6, p.getComplemento());
      ps.setInt(7, p.getCodigoP());
      ps.execute();
    }
    catch (SQLException ex)
    {
      JOptionPane.showMessageDialog(null, "Erro na altera��o da pessoa " + ex.getMessage());
    }
  }
  
  public void Demissao(int codigo, String opcao)
    throws SQLException
  {
    int codigoPessoa = 0;
    ResultSet rs;
    switch (opcao)
    {
    case "funcionario": 
      String sql = "select pes_codigo from funcionario where fun_cod = " + codigo;
      PreparedStatement ps = this.conexao.prepareStatement(sql);
      ps.execute();
      rs = ps.executeQuery();
      rs.next();
      codigoPessoa = rs.getInt(1);
      sql = "update funcionario set fun_dataD = curDate() where pes_codigo = ?";
      ps = this.conexao.prepareStatement(sql);
      ps.setInt(1, codigoPessoa);
      ps.execute();
      break;
    case "fornecedor": 
      sql = "select pes_codigo from fornecedor where for_cod = " + codigo;
      ps = this.conexao.prepareStatement(sql);
      ps.execute();
      rs = ps.executeQuery();
      rs.next();
      codigoPessoa = rs.getInt(1);
    }
    Pessoa p = new Pessoa();
    String sql = "update pessoa set pe_situacao = ? where pes_codigo = " + codigoPessoa;
    PreparedStatement ps = this.conexao.prepareStatement(sql);
    ps.setString(1, "DESLIGADO");
    ps.execute();
  }
  
  public void reativarFor(int codigo)
  {
    try
    {
      int codigoPessoa = 0;
      String sql = "select pes_codigo from fornecedor where for_cod = " + codigo;
      
      PreparedStatement ps = this.conexao.prepareStatement(sql);
      ResultSet rs = ps.executeQuery();
      ps = this.conexao.prepareStatement(sql);
      ps.execute();
      rs = ps.executeQuery();
      rs.next();
      codigoPessoa = rs.getInt(1);
      
      Pessoa p = new Pessoa();
      sql = "update pessoa set pe_situacao = ? where pes_codigo = " + codigoPessoa;
      ps = this.conexao.prepareStatement(sql);
      ps.setString(1, "LIGADO");
      ps.execute();
    }
    catch (SQLException ex)
    {
      Logger.getLogger(PessoaDAO.class.getName()).log(Level.SEVERE, null, ex);
    }
  }
}

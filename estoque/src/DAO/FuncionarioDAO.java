package DAO;

import Entidade.Funcionario;
import Entidade.Pessoa;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class FuncionarioDAO
{
  Connection conexao;
  
  public FuncionarioDAO()
  {
    this.conexao = new Conexao().getConexao();
  }
  
  public void inserir(Funcionario f)
    throws SQLException
  {
    int codigoPessoa = new PessoaDAO().inserir(f);
    try
    {
      String sql = "insert into funcionario (pes_codigo, fun_nome, fun_rg, fun_cpf, fun_telefone, fun_celular, fun_nascimento, fun_dataD, fun_dataE, fun_cargo)values(?,?,?,?,?,?,?,?,?,?)";
      
      PreparedStatement ps = this.conexao.prepareStatement(sql);
      ps.setInt(1, codigoPessoa);
      ps.setString(2, f.getNome());
      ps.setString(3, f.getRg());
      ps.setString(4, f.getCpf());
      ps.setString(5, f.getTelefone());
      ps.setString(6, f.getCelular());
      if (f.getNascimento() == null) {
        ps.setNull(7, 93);
      } else {
        ps.setDate(7, new java.sql.Date(f.getNascimento().getTime()));
      }
      if (f.getDemissao() == null) {
        ps.setNull(8, 93);
      } else {
        ps.setDate(8, new java.sql.Date(f.getAdmissao().getTime()));
      }
      if (f.getAdmissao() == null) {
        ps.setNull(9, 93);
      } else {
        ps.setDate(9, new java.sql.Date(f.getAdmissao().getTime()));
      }
      ps.setString(10, f.getCargo());
      ps.execute();
    }
    catch (SQLException ex)
    {
      if (ex.getErrorCode() == 1062) {
        throw new SQLException("Erro CNPJ j� cadastrato no sistema");
      }
      throw new SQLException("Erro ao cadastrar Funcion�rio " + ex.getErrorCode());
    }
  }
  
  public void alterar(Funcionario f)
    throws SQLException
  {
    try
    {
      new PessoaDAO().alterar(f);
      String sql = "update funcionario set fun_nome = ?, fun_rg = ?, fun_cpf = ?, fun_telefone = ?, fun_celular = ?, fun_nascimento = ?, fun_dataD = ?,  fun_dataE = ?, fun_cargo = ? where fun_cod = ? ";
      
      PreparedStatement ps = this.conexao.prepareStatement(sql);
      ps.setString(1, f.getNome());
      ps.setString(2, f.getRg());
      ps.setString(3, f.getCpf());
      ps.setString(4, f.getTelefone());
      ps.setString(5, f.getCelular());
      if (f.getNascimento() == null) {
        ps.setNull(6, 93);
      } else {
        ps.setDate(6, new java.sql.Date(f.getNascimento().getTime()));
      }
      if (f.getDemissao() == null) {
        ps.setNull(7, 93);
      } else {
        ps.setDate(7, new java.sql.Date(f.getDemissao().getTime()));
      }
      if (f.getAdmissao() == null) {
        ps.setNull(8, 93);
      } else {
        ps.setDate(8, new java.sql.Date(f.getAdmissao().getTime()));
      }
      ps.setString(9, f.getCargo());
      ps.setInt(10, f.getCodigo());
      ps.execute();
    }
    catch (SQLException ex)
    {
      if (ex.getErrorCode() == 1062) {
        throw new SQLException("Erro CPF j� cadastrato no sistema");
      }
      throw new SQLException("Erro na altera��o do Funcion�rio " + ex.getMessage());
    }
  }
  
  public List<Funcionario> listar()
    throws SQLException
  {
    List<Funcionario> lista = new ArrayList();
    try
    {
      String sql = "select * from funcionario, pessoa where funcionario.pes_codigo = pessoa.pes_codigo ";
      PreparedStatement ps = this.conexao.prepareStatement(sql);
      ResultSet rs = ps.executeQuery();
      while (rs.next())
      {
        Funcionario f = new Funcionario();
        f.setCodigoP(rs.getInt("pessoa.pes_codigo"));
        f.setCep(rs.getString("end_cep"));
        f.setCidade(rs.getString("end_cidade"));
        f.setUf(rs.getString("end_uf"));
        f.setRua(rs.getString("end_rua"));
        f.setNumero(rs.getString("end_num"));
        f.setComplemento(rs.getString("end_complemento"));
        f.setSituacao(rs.getString("pe_situacao"));
        f.setCodigo(rs.getInt("fun_cod"));
        f.setNome(rs.getString("fun_nome"));
        f.setRg(rs.getString("fun_rg"));
        f.setCpf(rs.getString("fun_cpf"));
        f.setTelefone(rs.getString("fun_telefone"));
        f.setCelular(rs.getString("fun_celular"));
        f.setNascimento(rs.getDate("fun_nascimento"));
        f.setDemissao(rs.getDate("fun_dataD"));
        f.setAdmissao(rs.getDate("fun_dataE"));
        f.setCargo(rs.getString("fun_cargo"));
        lista.add(f);
      }
      rs.close();
      ps.close();
      this.conexao.close();
    }
    catch (SQLException ex)
    {
      JOptionPane.showMessageDialog(null, "Erro " + ex.getMessage());
    }
    return lista;
  }
  
  public Funcionario consulta(int codigo)
  {
    Funcionario f = null;
    Pessoa p = null;
    try
    {
      String sql = "select * from funcionario, pessoa where funcionario.pes_codigo = pessoa.pes_codigo and fun_cod = ?";
      PreparedStatement ps = this.conexao.prepareStatement(sql);
      ps.setInt(1, codigo);
      ResultSet rs = ps.executeQuery();
      if (rs.next())
      {
        f = new Funcionario();
        f.setCodigoP(rs.getInt("pessoa.pes_codigo"));
        f.setCep(rs.getString("end_cep"));
        f.setCidade(rs.getString("end_cidade"));
        f.setUf(rs.getString("end_uf"));
        f.setRua(rs.getString("end_rua"));
        f.setNumero(rs.getString("end_num"));
        f.setComplemento(rs.getString("end_complemento"));
        f.setSituacao(rs.getString("pe_situacao"));
        f.setCodigo(rs.getInt("fun_cod"));
        f.setNome(rs.getString("fun_nome"));
        f.setRg(rs.getString("fun_rg"));
        f.setCpf(rs.getString("fun_cpf"));
        f.setTelefone(rs.getString("fun_telefone"));
        f.setCelular(rs.getString("fun_celular"));
        f.setNascimento(rs.getDate("fun_nascimento"));
        f.setDemissao(rs.getDate("fun_dataD"));
        f.setAdmissao(rs.getDate("fun_dataE"));
        f.setCargo(rs.getString("fun_cargo"));
      }
      rs.close();
      ps.close();
      this.conexao.close();
    }
    catch (SQLException ex)
    {
      JOptionPane.showMessageDialog(null, "Erro " + ex.getMessage());
    }
    return f;
  }
  
  public List<Funcionario> filtrar(String e)
    throws SQLException
  {
    List<Funcionario> contato = new ArrayList();
    try
    {
      String sql = "select * from funcionario, pessoa where fun_nome like '%" + e + "%' and funcionario.pes_codigo = pessoa.pes_codigo";
      PreparedStatement ps = this.conexao.prepareStatement(sql);
      ResultSet rs = ps.executeQuery();
      while (rs.next())
      {
        Funcionario f = new Funcionario();
        f.setCodigoP(rs.getInt("pessoa.pes_codigo"));
        f.setCep(rs.getString("end_cep"));
        f.setCidade(rs.getString("end_cidade"));
        f.setUf(rs.getString("end_uf"));
        f.setRua(rs.getString("end_rua"));
        f.setNumero(rs.getString("end_num"));
        f.setComplemento(rs.getString("end_complemento"));
        f.setSituacao(rs.getString("pe_situacao"));
        f.setCodigo(rs.getInt("fun_cod"));
        f.setNome(rs.getString("fun_nome"));
        f.setRg(rs.getString("fun_rg"));
        f.setCpf(rs.getString("fun_cpf"));
        f.setTelefone(rs.getString("fun_telefone"));
        f.setCelular(rs.getString("fun_celular"));
        f.setNascimento(rs.getDate("fun_nascimento"));
        f.setDemissao(rs.getDate("fun_dataD"));
        f.setAdmissao(rs.getDate("fun_dataE"));
        f.setCargo(rs.getString("fun_cargo"));
        contato.add(f);
      }
      rs.close();
      ps.close();
      this.conexao.close();
    }
    catch (SQLException ex)
    {
      JOptionPane.showMessageDialog(null, "Erro" + ex.getMessage());
    }
    return contato;
  }
  
  public List<Funcionario> desligados()
  {
    List<Funcionario> lista = new ArrayList();
    try
    {
      String sql = "select * from funcionario, pessoa where funcionario.pes_codigo = pessoa.pes_codigo and pessoa.pe_situacao = 'DESLIGADO'";
      PreparedStatement ps = this.conexao.prepareStatement(sql);
      ResultSet rs = ps.executeQuery();
      while (rs.next())
      {
        Funcionario f = new Funcionario();
        f.setCodigoP(rs.getInt("pessoa.pes_codigo"));
        f.setCep(rs.getString("end_cep"));
        f.setCidade(rs.getString("end_cidade"));
        f.setUf(rs.getString("end_uf"));
        f.setRua(rs.getString("end_rua"));
        f.setNumero(rs.getString("end_num"));
        f.setComplemento(rs.getString("end_complemento"));
        f.setSituacao(rs.getString("pe_situacao"));
        f.setCodigo(rs.getInt("fun_cod"));
        f.setNome(rs.getString("fun_nome"));
        f.setRg(rs.getString("fun_rg"));
        f.setCpf(rs.getString("fun_cpf"));
        f.setTelefone(rs.getString("fun_telefone"));
        f.setCelular(rs.getString("fun_celular"));
        f.setNascimento(rs.getDate("fun_nascimento"));
        f.setDemissao(rs.getDate("fun_dataD"));
        f.setAdmissao(rs.getDate("fun_dataE"));
        f.setCargo(rs.getString("fun_cargo"));
        lista.add(f);
      }
      rs.close();
      ps.close();
      this.conexao.close();
    }
    catch (SQLException ex)
    {
      JOptionPane.showMessageDialog(null, "Erro " + ex.getMessage());
    }
    return lista;
  }
}

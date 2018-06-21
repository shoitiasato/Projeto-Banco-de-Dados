package DAO;

import Entidade.Fornecedor;
import Entidade.Pessoa;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class FornecedorDAO
{
  Connection conexao;
  
  public FornecedorDAO()
  {
    this.conexao = new Conexao().getConexao();
  }
  
  public void inserir(Fornecedor f)
    throws SQLException
  {
    int codigoPessoa = new PessoaDAO().inserir(f);
    try
    {
      String sql = "insert into fornecedor (pes_codigo, for_nome, for_cnpj, for_contato, for_telefone, for_celular, for_email, for_site, for_dataCad)values(?,?,?,?,?,?,?,?,?)";
      
      PreparedStatement ps = this.conexao.prepareStatement(sql);
      ps.setInt(1, codigoPessoa);
      ps.setString(2, f.getNome());
      ps.setString(3, f.getCnpj());
      ps.setString(4, f.getContato());
      ps.setString(5, f.getTelefone());
      ps.setString(6, f.getCelular());
      ps.setString(7, f.getEmail());
      ps.setString(8, f.getSite());
      if (f.getCadastro() == null) {
        ps.setNull(9, 93);
      } else {
        ps.setDate(9, new java.sql.Date(f.getCadastro().getTime()));
      }
      ps.execute();
      JOptionPane.showMessageDialog(null, "Fornecedor cadastrado com sucesso");
    }
    catch (SQLException ex)
    {
      if (ex.getErrorCode() == 1062) {
        throw new SQLException("Erro CNPJ j� cadastrato no sistema");
      }
      throw new SQLException("Erro ao cadastrar Fornecedor " + ex.getMessage());
    }
  }
  
  public void alterar(Fornecedor f)
    throws SQLException
  {
    new PessoaDAO().alterar(f);
    try
    {
      String sql = "update fornecedor  set  for_nome = ?, for_cnpj = ?, for_contato = ?, for_telefone = ?, for_celular = ?, for_email = ?, for_site = ?, for_dataCad = ? where for_cod = ?";
      
      PreparedStatement ps = this.conexao.prepareStatement(sql);
      ps.setString(1, f.getNome());
      ps.setString(2, f.getCnpj());
      ps.setString(3, f.getContato());
      ps.setString(4, f.getTelefone());
      ps.setString(5, f.getCelular());
      ps.setString(6, f.getEmail());
      ps.setString(7, f.getSite());
      if (f.getCadastro() == null) {
        ps.setNull(8, 93);
      } else {
        ps.setDate(8, new java.sql.Date(f.getCadastro().getTime()));
      }
      ps.setInt(9, f.getCodigo());
      ps.execute();
      JOptionPane.showMessageDialog(null, "Fornecedor alterado com sucesso");
    }
    catch (SQLException ex)
    {
      if (ex.getErrorCode() == 1062) {
        throw new SQLException("Erro CNPJ j� cadastrato no sistema");
      }
      throw new SQLException("Erro ao cadastrar Fornecedor " + ex.getMessage());
    }
  }
  
  public List<Fornecedor> listar()
    throws SQLException
  {
    List<Fornecedor> lista = new ArrayList();
    try
    {
      String sql = "select * from fornecedor, pessoa where fornecedor.pes_codigo = pessoa.pes_codigo ";
      PreparedStatement ps = this.conexao.prepareStatement(sql);
      ResultSet rs = ps.executeQuery();
      while (rs.next())
      {
        Fornecedor f = new Fornecedor();
        f.setCodigoP(rs.getInt("pessoa.pes_codigo"));
        f.setCep(rs.getString("end_cep"));
        f.setCidade(rs.getString("end_cidade"));
        f.setUf(rs.getString("end_uf"));
        f.setRua(rs.getString("end_rua"));
        f.setNumero(rs.getString("end_num"));
        f.setComplemento(rs.getString("end_complemento"));
        f.setSituacao(rs.getString("pe_situacao"));
        f.setCodigo(rs.getInt("for_cod"));
        f.setNome(rs.getString("for_nome"));
        f.setCnpj(rs.getString("for_cnpj"));
        f.setContato(rs.getString("for_contato"));
        f.setTelefone(rs.getString("for_telefone"));
        f.setCelular(rs.getString("for_celular"));
        f.setEmail(rs.getString("for_email"));
        f.setSite(rs.getString("for_site"));
        f.setCadastro(rs.getDate("for_dataCad"));
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
  
  public Fornecedor consulta(int codigo)
    throws SQLException
  {
    Fornecedor f = null;
    Pessoa p = null;
    try
    {
      String sql = "select * from fornecedor, pessoa where fornecedor.pes_codigo = pessoa.pes_codigo and for_cod = ?";
      PreparedStatement ps = this.conexao.prepareStatement(sql);
      ps.setInt(1, codigo);
      ResultSet rs = ps.executeQuery();
      if (rs.next())
      {
        f = new Fornecedor();
        f.setCodigoP(rs.getInt("pessoa.pes_codigo"));
        f.setCep(rs.getString("end_cep"));
        f.setCidade(rs.getString("end_cidade"));
        f.setUf(rs.getString("end_uf"));
        f.setRua(rs.getString("end_rua"));
        f.setNumero(rs.getString("end_num"));
        f.setComplemento(rs.getString("end_complemento"));
        f.setSituacao(rs.getString("pe_situacao"));
        f.setCodigo(rs.getInt("for_cod"));
        f.setNome(rs.getString("for_nome"));
        f.setCnpj(rs.getString("for_cnpj"));
        f.setContato(rs.getString("for_contato"));
        f.setTelefone(rs.getString("for_telefone"));
        f.setCelular(rs.getString("for_celular"));
        f.setEmail(rs.getString("for_email"));
        f.setSite(rs.getString("for_site"));
        f.setCadastro(rs.getDate("for_dataCad"));
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
  
  public List<Fornecedor> filtrar(String e)
    throws SQLException
  {
    List<Fornecedor> contato = new ArrayList();
    try
    {
      String sql = "select * from fornecedor, pessoa where for_nome like '%" + e + "%' and fornecedor.pes_codigo = pessoa.pes_codigo";
      PreparedStatement ps = this.conexao.prepareStatement(sql);
      ResultSet rs = ps.executeQuery();
      while (rs.next())
      {
        Fornecedor f = new Fornecedor();
        f.setCodigoP(rs.getInt("pessoa.pes_codigo"));
        f.setCep(rs.getString("end_cep"));
        f.setCidade(rs.getString("end_cidade"));
        f.setUf(rs.getString("end_uf"));
        f.setRua(rs.getString("end_rua"));
        f.setNumero(rs.getString("end_num"));
        f.setComplemento(rs.getString("end_complemento"));
        f.setSituacao(rs.getString("pe_situacao"));
        f.setCodigo(rs.getInt("for_cod"));
        f.setNome(rs.getString("for_nome"));
        f.setCnpj(rs.getString("for_cnpj"));
        f.setContato(rs.getString("for_contato"));
        f.setTelefone(rs.getString("for_telefone"));
        f.setCelular(rs.getString("for_celular"));
        f.setEmail(rs.getString("for_email"));
        f.setSite(rs.getString("for_site"));
        f.setCadastro(rs.getDate("for_dataCad"));
        contato.add(f);
      }
      rs.close();
      ps.close();
      this.conexao.close();
    }
    catch (SQLException ex)
    {
      JOptionPane.showMessageDialog(null, "Erro " + ex.getMessage());
    }
    return contato;
  }
  
  public List<Fornecedor> desligados()
  {
    List<Fornecedor> lista = new ArrayList();
    try
    {
      String sql = "select * from fornecedor, pessoa where  fornecedor.pes_codigo = pessoa.pes_codigo and pessoa.pe_situacao = 'DESLIGADO'";
      PreparedStatement ps = this.conexao.prepareStatement(sql);
      ResultSet rs = ps.executeQuery();
      while (rs.next())
      {
        Fornecedor f = new Fornecedor();
        f.setCodigoP(rs.getInt("pessoa.pes_codigo"));
        f.setCep(rs.getString("end_cep"));
        f.setCidade(rs.getString("end_cidade"));
        f.setUf(rs.getString("end_uf"));
        f.setRua(rs.getString("end_rua"));
        f.setNumero(rs.getString("end_num"));
        f.setComplemento(rs.getString("end_complemento"));
        f.setSituacao(rs.getString("pe_situacao"));
        f.setCodigo(rs.getInt("for_cod"));
        f.setNome(rs.getString("for_nome"));
        f.setCnpj(rs.getString("for_cnpj"));
        f.setContato(rs.getString("for_contato"));
        f.setTelefone(rs.getString("for_telefone"));
        f.setCelular(rs.getString("for_celular"));
        f.setEmail(rs.getString("for_email"));
        f.setSite(rs.getString("for_site"));
        f.setCadastro(rs.getDate("for_dataCad"));
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

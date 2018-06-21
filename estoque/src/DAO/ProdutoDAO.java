package DAO;

import Entidade.Categoria;
import Entidade.Produto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class ProdutoDAO
{
  Connection conexao;
  
  public ProdutoDAO()
  {
    this.conexao = new Conexao().getConexao();
  }
  
  public void inserir(Produto p)
  {
    try
    {
      String sql = "insert into produto (cat_cod, pro_nome, pro_valor, pro_descricao, pro_estMin, pro_validade, pro_estoque, pro_uniMedida)values(?,?,?,?,?,?,?,?)";
      
      PreparedStatement ps = this.conexao.prepareStatement(sql);
      ps.setInt(1, p.getCat_codigo().getCodigo());
      ps.setString(2, p.getNome());
      ps.setDouble(3, p.getValor());
      ps.setString(4, p.getDescricao());
      ps.setDouble(5, p.getEstMin());
      if (p.getValidade() == null) {
        ps.setNull(6, 93);
      } else {
        ps.setDate(6, new java.sql.Date(p.getValidade().getTime()));
      }
      ps.setDouble(7, p.getEstoque());
      ps.setString(8, p.getUniMedida());
      ps.execute();
    }
    catch (SQLException ex)
    {
      JOptionPane.showMessageDialog(null, "Erro ao cadastrar Produto" + ex.getMessage());
    }
  }
  
  public void alterar(Produto p)
    throws SQLException
  {
    try
    {
      String sql = "update produto set cat_cod = ?, pro_nome = ?, pro_valor = ?, pro_descricao = ?, pro_estMin = ?, pro_validade = ?, pro_estoque = ?, pro_uniMedida = ? where pro_cod = ?";
      PreparedStatement ps = this.conexao.prepareStatement(sql);
      ps.setInt(1, p.getCat_codigo().getCodigo());
      ps.setString(2, p.getNome());
      ps.setDouble(3, p.getValor());
      ps.setString(4, p.getDescricao());
      ps.setDouble(5, p.getEstMin());
      if (p.getValidade() == null) {
        ps.setNull(6, 93);
      } else {
        ps.setDate(6, new java.sql.Date(p.getValidade().getTime()));
      }
      ps.setDouble(7, p.getEstoque());
      ps.setString(8, p.getUniMedida());
      ps.setInt(9, p.getCodigo());
      ps.execute();
    }
    catch (SQLException ex)
    {
      JOptionPane.showMessageDialog(null, "Erro na altera��o do produto " + ex.getMessage());
    }
  }
  
  public void deletar(int codigo)
    throws SQLException
  {
    try
    {
      String sql = "delete from produto  where pro_cod = ?";
      PreparedStatement ps = this.conexao.prepareStatement(sql);
      ps.setInt(1, codigo);
      ps.execute();
    }
    catch (SQLException ex)
    {
      JOptionPane.showMessageDialog(null, "Erro ao deletar o produto" + ex.getMessage());
    }
  }
  
  public List<Produto> listar()
    throws SQLException
  {
    List<Produto> lista = new ArrayList();
    try
    {
      String sql = "select * from produto ";
      PreparedStatement ps = this.conexao.prepareStatement(sql);
      ResultSet rs = ps.executeQuery();
      while (rs.next())
      {
        Produto p = new Produto();
        p.setCodigo(rs.getInt("pro_cod"));
        Categoria c = new Categoria();
        c.setCodigo(rs.getInt("cat_cod"));
        p.setCat_codigo(c);
        p.setNome(rs.getString("pro_nome"));
        p.setValor(rs.getDouble("pro_valor"));
        p.setDescricao(rs.getString("pro_descricao"));
        p.setEstMin(rs.getDouble("pro_estMin"));
        p.setValidade(rs.getDate("pro_validade"));
        p.setEstoque(rs.getDouble("pro_estoque"));
        p.setUniMedida(rs.getString("pro_uniMedida"));
        lista.add(p);
      }
      rs.close();
      ps.close();
      this.conexao.close();
    }
    catch (SQLException ex)
    {
      JOptionPane.showMessageDialog(null, "Erro" + ex.getMessage());
    }
    return lista;
  }
  
  public Produto consulta(int codigo)
  {
    Produto p = null;
    Categoria categoria = null;
    try
    {
      String sql = "select * from produto,categoria where produto.cat_cod = categoria.cat_cod and pro_cod = ?";
      PreparedStatement ps = this.conexao.prepareStatement(sql);
      ps.setInt(1, codigo);
      ResultSet rs = ps.executeQuery();
      if (rs.next())
      {
        p = new Produto();
        categoria = new Categoria();
        categoria.setCodigo(rs.getInt("categoria.cat_cod"));
        categoria.setNome(rs.getString("cat_nome"));
        p.setCodigo(rs.getInt("pro_cod"));
        p.setCat_codigo(categoria);
        p.setNome(rs.getString("pro_nome"));
        p.setValor(rs.getDouble("pro_valor"));
        p.setDescricao(rs.getString("pro_descricao"));
        p.setEstMin(rs.getDouble("pro_estMin"));
        p.setValidade(rs.getDate("pro_validade"));
        p.setEstoque(rs.getDouble("pro_estoque"));
        p.setUniMedida(rs.getString("pro_uniMedida"));
      }
      rs.close();
      ps.close();
      this.conexao.close();
    }
    catch (SQLException ex)
    {
      JOptionPane.showMessageDialog(null, "Erro " + ex.getMessage());
    }
    return p;
  }
  
  public List<Produto> filtrar(String e)
    throws SQLException
  {
    List<Produto> contato = new ArrayList();
    try
    {
      String sql = "select * from produto where pro_nome like '%" + e + "%'";
      PreparedStatement ps = this.conexao.prepareStatement(sql);
      ResultSet rs = ps.executeQuery();
      while (rs.next())
      {
        Produto p = new Produto();
        p.setCodigo(rs.getInt("pro_cod"));
        Categoria c = new Categoria();
        c.setCodigo(rs.getInt("cat_cod"));
        p.setCat_codigo(c);
        p.setNome(rs.getString("pro_nome"));
        p.setValor(rs.getDouble("pro_valor"));
        p.setDescricao(rs.getString("pro_descricao"));
        p.setEstMin(rs.getDouble("pro_estMin"));
        p.setValidade(rs.getDate("pro_validade"));
        p.setEstoque(rs.getDouble("pro_estoque"));
        p.setUniMedida(rs.getString("pro_uniMedida"));
        contato.add(p);
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
  
  public void saidaItem(double qtd, int cod)
  {
    try
    {
      String sql = "update produto set pro_estoque = pro_estoque - ? where pro_cod = ?";
      PreparedStatement ps = this.conexao.prepareStatement(sql);
      ps.setDouble(1, qtd);
      ps.setDouble(2, cod);
      ps.execute();
    }
    catch (SQLException ex)
    {
      JOptionPane.showMessageDialog(null, "Erro na altera��o do produto " + ex.getMessage());
    }
  }
  
  public void entradaItem(double qtd, int cod)
  {
    try
    {
      String sql = "update produto set pro_estoque = pro_estoque + ? where pro_cod = ?";
      PreparedStatement ps = this.conexao.prepareStatement(sql);
      ps.setDouble(1, qtd);
      ps.setDouble(2, cod);
      ps.execute();
    }
    catch (SQLException ex)
    {
      JOptionPane.showMessageDialog(null, "Erro ao salvar entrada de produtos " + ex.getMessage());
    }
  }
}

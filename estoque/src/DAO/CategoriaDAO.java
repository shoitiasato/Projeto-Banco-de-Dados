package DAO;

import Entidade.Categoria;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class CategoriaDAO
{
  Connection conexao;
  
  public CategoriaDAO()
  {
    this.conexao = new Conexao().getConexao();
  }
  
  public void inserir(Categoria c)
    throws SQLException
  {
    try
    {
      String sql = "insert into categoria (cat_nome) values (?)";
      PreparedStatement ps = this.conexao.prepareStatement(sql);
      ps.setString(1, c.getNome());
      ps.execute();
    }
    catch (SQLException ex)
    {
      throw new SQLException("Erro ao cadastrar Categoria" + ex.getMessage());
    }
  }
  
  public void alterar(Categoria c)
    throws SQLException
  {
    try
    {
      String sql = "update categoria set cat_nome = ? where cat_cod = ?";
      PreparedStatement ps = this.conexao.prepareStatement(sql);
      ps.setString(1, c.getNome());
      ps.setInt(2, c.getCodigo());
      ps.execute();
    }
    catch (SQLException ex)
    {
      throw new SQLException("Erro ao alterar categoria" + ex.getMessage());
    }
  }
  
  public void deletar(int codigo)
    throws SQLException
  {
    try
    {
      String sql = "delete from categoria  where cat_cod = ?";
      PreparedStatement ps = this.conexao.prepareStatement(sql);
      ps.setInt(1, codigo);
      ps.execute();
    }
    catch (SQLException ex)
    {
      throw new SQLException("Erro ao deletar a categoria" + ex.getMessage());
    }
  }
  
  public Categoria consulta(int codigo)
    throws SQLException
  {
    Categoria c = null;
    try
    {
      String sql = "select * from categoria where cat_cod = ?";
      PreparedStatement ps = this.conexao.prepareStatement(sql);
      ps.setInt(1, codigo);
      ResultSet rs = ps.executeQuery();
      if (rs.next())
      {
        c = new Categoria();
        c.setCodigo(rs.getInt("categoria.cat_cod"));
        c.setNome(rs.getString("cat_nome"));
      }
      rs.close();
      ps.close();
      this.conexao.close();
    }
    catch (SQLException ex)
    {
      JOptionPane.showMessageDialog(null, "Erro " + ex.getMessage());
    }
    return c;
  }
  
  public List<Categoria> jCbCat()
    throws SQLException
  {
    List<Categoria> lista = new ArrayList();
    try
    {
      String sql = "select * from categoria order by cat_nome ";
      PreparedStatement ps = this.conexao.prepareStatement(sql);
      ResultSet rs = ps.executeQuery();
      while (rs.next())
      {
        Categoria c = new Categoria();
        c.setCodigo(rs.getInt("cat_cod"));
        c.setNome(rs.getString("cat_nome"));
        lista.add(c);
      }
      rs.close();
      ps.close();
      this.conexao.close();
    }
    catch (SQLException ex)
    {
      throw new SQLException("Erro" + ex.getMessage());
    }
    return lista;
  }
  
  public List<Categoria> filtrar(String e)
    throws SQLException
  {
    List<Categoria> contato = new ArrayList();
    try
    {
      String sql = "select * from categoria where cat_nome like '%" + e + "%'";
      PreparedStatement ps = this.conexao.prepareStatement(sql);
      ResultSet rs = ps.executeQuery();
      while (rs.next())
      {
        Categoria c = new Categoria();
        c.setCodigo(rs.getInt("cat_cod"));
        c.setNome(rs.getString("cat_nome"));
        contato.add(c);
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
}

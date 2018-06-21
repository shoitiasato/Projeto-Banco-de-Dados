package DAO;

import Entidade.ItemEntrada;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import javax.swing.JOptionPane;

public class ItemEntradaDAO
{
  Connection conexao;
  
  public ItemEntradaDAO()
  {
    this.conexao = new Conexao().getConexao();
  }
  
  public void inserir(List<ItemEntrada> listaItens, int codigoEntrada)
  {
    try
    {
      String sql = "insert into item_entrada ( ent_cod,pro_cod, itemEnt_qnt, entValor_unitario, entValor_total)values(?,?,?,?,?)";
      
      PreparedStatement ps = this.conexao.prepareStatement(sql);
      ps.setInt(1, codigoEntrada);
      for (int i = 0; i < listaItens.size(); i++)
      {
        ps.setInt(2, ((ItemEntrada)listaItens.get(i)).getProdutoCod());
        ps.setDouble(3, ((ItemEntrada)listaItens.get(i)).getQntd());
        ps.setDouble(4, ((ItemEntrada)listaItens.get(i)).getValorUni());
        ps.setDouble(5, ((ItemEntrada)listaItens.get(i)).getValorT());
        ps.execute();
        new ProdutoDAO().entradaItem(((ItemEntrada)listaItens.get(i)).getQntd(), ((ItemEntrada)listaItens.get(i)).getProdutoCod());
      }
    }
    catch (SQLException ex)
    {
      JOptionPane.showMessageDialog(null, "Erro ao cadastrar Item Entrada" + ex.getMessage());
    }
  }
}

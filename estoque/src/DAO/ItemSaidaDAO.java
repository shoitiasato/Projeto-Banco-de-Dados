package DAO;

import Entidade.ItemSaida;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import javax.swing.JOptionPane;

public class ItemSaidaDAO
{
  Connection conexao;
  
  public ItemSaidaDAO()
  {
    this.conexao = new Conexao().getConexao();
  }
  
  public void inserir(List<ItemSaida> listaItens, int codigoSaida)
  {
    try
    {
      String sql = "insert into item_saida (pro_cod, sai_cod, itemSai_qnt, saiValor_uni, saiValor_total)values(?,?,?,?,?)";
      
      PreparedStatement ps = this.conexao.prepareStatement(sql);
      ps.setInt(2, codigoSaida);
      for (int i = 0; i < listaItens.size(); i++)
      {
        ps.setInt(1, ((ItemSaida)listaItens.get(i)).getProdutoCod());
        ps.setDouble(3, ((ItemSaida)listaItens.get(i)).getQntd());
        ps.setDouble(4, ((ItemSaida)listaItens.get(i)).getValorUni());
        ps.setDouble(5, ((ItemSaida)listaItens.get(i)).getValorT());
        ps.execute();
        new ProdutoDAO().saidaItem(((ItemSaida)listaItens.get(i)).getQntd(), ((ItemSaida)listaItens.get(i)).getProdutoCod());
      }
    }
    catch (SQLException ex)
    {
      JOptionPane.showMessageDialog(null, "Erro ao cadastrar Item Saida" + ex.getMessage());
    }
  }
}

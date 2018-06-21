package Entidade;

import java.util.Date;
import java.util.List;

public class Entrada
{
  int codigo;
  int funcionarioCod;
  Date data;
  List<ItemEntrada> lista;
  
  public List<ItemEntrada> getLista()
  {
    return this.lista;
  }
  
  public void setLista(List<ItemEntrada> lista)
  {
    this.lista = lista;
  }
  
  public int getCodigo()
  {
    return this.codigo;
  }
  
  public void setCodigo(int codigo)
  {
    this.codigo = codigo;
  }
  
  public int getFuncionarioCod()
  {
    return this.funcionarioCod;
  }
  
  public void setFuncionarioCod(int funcionarioCod)
  {
    this.funcionarioCod = funcionarioCod;
  }
  
  public Date getData()
  {
    return this.data;
  }
  
  public void setData(Date data)
  {
    this.data = data;
  }
}

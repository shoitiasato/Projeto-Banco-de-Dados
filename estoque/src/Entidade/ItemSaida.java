package Entidade;

public class ItemSaida
{
  String nome;
  int codigo;
  int produtoCod;
  double qntd;
  double valorUni;
  double valorT;
  
  public String getNome()
  {
    return this.nome;
  }
  
  public void setNome(String nome)
  {
    this.nome = nome;
  }
  
  public int getCodigo()
  {
    return this.codigo;
  }
  
  public void setCodigo(int codigo)
  {
    this.codigo = codigo;
  }
  
  public int getProdutoCod()
  {
    return this.produtoCod;
  }
  
  public void setProdutoCod(int produtoCod)
  {
    this.produtoCod = produtoCod;
  }
  
  public double getQntd()
  {
    return this.qntd;
  }
  
  public void setQntd(double qntd)
  {
    this.qntd = qntd;
  }
  
  public double getValorUni()
  {
    return this.valorUni;
  }
  
  public void setValorUni(double valorUni)
  {
    this.valorUni = valorUni;
  }
  
  public double getValorT()
  {
    return this.valorT;
  }
  
  public void setValorT(double valorT)
  {
    this.valorT = valorT;
  }
}

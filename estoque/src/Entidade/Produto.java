package Entidade;

import java.util.Date;

public class Produto
{
  int codigo;
  double estMin;
  double estoque;
  double valor;
  String nome;
  String descricao;
  String uniMedida;
  Date validade;
  Categoria cat_codigo;
  
  public Categoria getCat_codigo()
  {
    return this.cat_codigo;
  }
  
  public void setCat_codigo(Categoria cat_codigo)
  {
    this.cat_codigo = cat_codigo;
  }
  
  public int getCodigo()
  {
    return this.codigo;
  }
  
  public void setCodigo(int codigo)
  {
    this.codigo = codigo;
  }
  
  public String getDescricao()
  {
    return this.descricao;
  }
  
  public void setDescricao(String descricao)
  {
    this.descricao = descricao;
  }
  
  public double getEstMin()
  {
    return this.estMin;
  }
  
  public void setEstMin(double estMin)
  {
    this.estMin = estMin;
  }
  
  public double getEstoque()
  {
    return this.estoque;
  }
  
  public void setEstoque(double estoque)
  {
    this.estoque = estoque;
  }
  
  public String getNome()
  {
    return this.nome;
  }
  
  public void setNome(String nome)
  {
    this.nome = nome.toUpperCase();
  }
  
  public String getUniMedida()
  {
    return this.uniMedida;
  }
  
  public void setUniMedida(String uniMedida)
  {
    this.uniMedida = uniMedida.toUpperCase();
  }
  
  public Date getValidade()
  {
    return this.validade;
  }
  
  public void setValidade(Date validade)
  {
    this.validade = validade;
  }
  
  public double getValor()
  {
    return this.valor;
  }
  
  public void setValor(double valor)
  {
    this.valor = valor;
  }
}

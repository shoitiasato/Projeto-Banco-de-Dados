package Entidade;

public class Pessoa
{
  int codigoP;
  String cep;
  String cidade;
  String uf;
  String rua;
  String numero;
  String complemento;
  String situacao;
  
  public String getCep()
  {
    return this.cep;
  }
  
  public void setCep(String cep)
  {
    this.cep = cep;
  }
  
  public String getCidade()
  {
    return this.cidade;
  }
  
  public void setCidade(String cidade)
  {
    this.cidade = cidade;
  }
  
  public int getCodigoP()
  {
    return this.codigoP;
  }
  
  public void setCodigoP(int codigo)
  {
    this.codigoP = codigo;
  }
  
  public String getComplemento()
  {
    return this.complemento;
  }
  
  public void setComplemento(String complemento)
  {
    this.complemento = complemento;
  }
  
  public String getNumero()
  {
    return this.numero;
  }
  
  public void setNumero(String numero)
  {
    this.numero = numero;
  }
  
  public String getRua()
  {
    return this.rua;
  }
  
  public void setRua(String rua)
  {
    this.rua = rua;
  }
  
  public String getUf()
  {
    return this.uf;
  }
  
  public void setUf(String uf)
  {
    this.uf = uf;
  }
  
  public String getSituacao()
  {
    return this.situacao;
  }
  
  public void setSituacao(String situacao)
  {
    this.situacao = situacao;
  }
}
    
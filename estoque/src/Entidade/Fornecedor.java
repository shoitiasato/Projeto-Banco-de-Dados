package Entidade;

import java.util.Date;

public class Fornecedor
  extends Pessoa
{
  int codigo;
  Pessoa pessoa;
  String nome;
  String cnpj;
  String contato;
  String telefone;
  String celular;
  String email;
  String site;
  Date cadastro;
  
  public int getCodigo()
  {
    return this.codigo;
  }
  
  public void setCodigo(int codigo)
  {
    this.codigo = codigo;
  }
  
  public Pessoa getPessoa()
  {
    return this.pessoa;
  }
  
  public void setPessoa(Pessoa pessoa)
  {
    this.pessoa = pessoa;
  }
  
  public String getNome()
  {
    return this.nome;
  }
  
  public void setNome(String nome)
  {
    this.nome = nome;
  }
  
  public String getCnpj()
  {
    return this.cnpj;
  }
  
  public void setCnpj(String cnpj)
  {
    this.cnpj = cnpj;
  }
  
  public String getContato()
  {
    return this.contato;
  }
  
  public void setContato(String contato)
  {
    this.contato = contato;
  }
  
  public String getTelefone()
  {
    return this.telefone;
  }
  
  public void setTelefone(String telefone)
  {
    this.telefone = telefone;
  }
  
  public String getCelular()
  {
    return this.celular;
  }
  
  public void setCelular(String celular)
  {
    this.celular = celular;
  }
  
  public String getEmail()
  {
    return this.email;
  }
  
  public void setEmail(String email)
  {
    this.email = email;
  }
  
  public String getSite()
  {
    return this.site;
  }
  
  public void setSite(String site)
  {
    this.site = site;
  }
  
  public Date getCadastro()
  {
    return this.cadastro;
  }
  
  public void setCadastro(Date cadastro)
  {
    this.cadastro = cadastro;
  }
}

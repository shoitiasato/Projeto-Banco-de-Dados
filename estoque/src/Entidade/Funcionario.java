package Entidade;

import java.util.Date;

public class Funcionario
  extends Pessoa
{
  int codigo;
  Pessoa pessoa;
  String nome;
  String rg;
  String cpf;
  String telefone;
  String celular;
  String cargo;
  Date nascimento;
  Date demissao;
  Date admissao;
  
  public int getCodigo()
  {
    return this.codigo;
  }
  
  public void setCodigo(int codigo)
  {
    this.codigo = codigo;
  }
  
  public Date getAdmissao()
  {
    return this.admissao;
  }
  
  public void setAdmissao(Date admissao)
  {
    this.admissao = admissao;
  }
  
  public String getCargo()
  {
    return this.cargo;
  }
  
  public void setCargo(String cargo)
  {
    this.cargo = cargo;
  }
  
  public String getCelular()
  {
    return this.celular;
  }
  
  public void setCelular(String celular)
  {
    this.celular = celular;
  }
  
  public String getCpf()
  {
    return this.cpf;
  }
  
  public void setCpf(String cpf)
  {
    this.cpf = cpf;
  }
  
  public Date getDemissao()
  {
    return this.demissao;
  }
  
  public void setDemissao(Date demissao)
  {
    this.demissao = demissao;
  }
  
  public Date getNascimento()
  {
    return this.nascimento;
  }
  
  public void setNascimento(Date nascimento)
  {
    this.nascimento = nascimento;
  }
  
  public String getNome()
  {
    return this.nome;
  }
  
  public void setNome(String nome)
  {
    this.nome = nome;
  }
  
  public Pessoa getPessoa()
  {
    return this.pessoa;
  }
  
  public void setPessoa(Pessoa pessoa)
  {
    this.pessoa = pessoa;
  }
  
  public String getRg()
  {
    return this.rg;
  }
  
  public void setRg(String rg)
  {
    this.rg = rg;
  }
  
  public String getTelefone()
  {
    return this.telefone;
  }
  
  public void setTelefone(String telefone)
  {
    this.telefone = telefone;
  }
}

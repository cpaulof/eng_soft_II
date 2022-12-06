package dcomp.es2.locadora.modelo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="imoveis")
public class Imovel {
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;
    
    private String tipoImovel;
    private String endereco;
    private String bairro;
    private Integer cep;
    private double metragem;
    private Integer dormitorios;
    private Integer banheiros;
    private Integer suites;
    private Integer vagasGaragem;
    private double valorAluguelSugerido;
    private String obs;

    



    // getters and setters
    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTipoImovel() {
        return this.tipoImovel;
    }

    public void setTipoImovel(String tipoImovel) {
        this.tipoImovel = tipoImovel;
    }

    public String getEndereco() {
        return this.endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getBairro() {
        return this.bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public Integer getCep() {
        return this.cep;
    }

    public void setCep(Integer cep) {
        this.cep = cep;
    }

    public double getMetragem() {
        return this.metragem;
    }

    public void setMetragem(double metragem) {
        this.metragem = metragem;
    }

    public Integer getDormitorios() {
        return this.dormitorios;
    }

    public void setDormitorios(Integer dormitorios) {
        this.dormitorios = dormitorios;
    }

    public Integer getBanheiros() {
        return this.banheiros;
    }

    public void setBanheiros(Integer banheiros) {
        this.banheiros = banheiros;
    }

    public Integer getSuites() {
        return this.suites;
    }

    public void setSuites(Integer suites) {
        this.suites = suites;
    }

    public Integer getVagasGaragem() {
        return this.vagasGaragem;
    }

    public void setVagasGaragem(Integer vagasGaragem) {
        this.vagasGaragem = vagasGaragem;
    }

    public double getValorAluguelSugerido() {
        return this.valorAluguelSugerido;
    }

    public void setValorAluguelSugerido(double valorAluguelSugerido) {
        this.valorAluguelSugerido = valorAluguelSugerido;
    }

    public String getObs() {
        return this.obs;
    }

    public void setObs(String obs) {
        this.obs = obs;
    }
    
    
}
 
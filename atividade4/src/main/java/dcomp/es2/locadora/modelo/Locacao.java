package dcomp.es2.locadora.modelo;

import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="locacoes")
public class Locacao {
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;

    @OneToOne(cascade = CascadeType.ALL)
    private Imovel imovel;

    @ManyToOne(cascade = CascadeType.ALL)
    private Cliente inquilino;

    private Double valorAluguel;
    private Double percentualMuta;
    private Integer diaVencimento;
    private LocalDate dataInicio;
    private LocalDate dataFim;
    private Boolean ativo;
    private String obs;




    // getters and setters
    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Imovel getImovel() {
        return this.imovel;
    }

    public void setImovel(Imovel imovel) {
        this.imovel = imovel;
    }

    public Cliente getInquilino() {
        return this.inquilino;
    }

    public void setInquilino(Cliente inquilino) {
        this.inquilino = inquilino;
    }

    public Double getValorAluguel() {
        return this.valorAluguel;
    }

    public void setValorAluguel(Double valorAluguel) {
        this.valorAluguel = valorAluguel;
    }

    public double getPercentualMuta() {
        return this.percentualMuta;
    }

    public void setPercentualMuta(double percentualMuta) {
        this.percentualMuta = percentualMuta;
    }

    public Integer getDiaVencimento() {
        return this.diaVencimento;
    }

    public void setDiaVencimento(Integer diaVencimento) {
        this.diaVencimento = diaVencimento;
    }

    public LocalDate getDataInicio() {
        return this.dataInicio;
    }

    public void setDataInicio(LocalDate dataInicio) {
        this.dataInicio = dataInicio;
    }

    public LocalDate getDataFim() {
        return this.dataFim;
    }

    public void setDataFim(LocalDate dataFim) {
        this.dataFim = dataFim;
    }

    public Boolean isAtivo() {
        return this.ativo;
    }

    public Boolean getAtivo() {
        return this.ativo;
    }

    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
    }

    public String getObs() {
        return this.obs;
    }

    public void setObs(String obs) {
        this.obs = obs;
    }


}

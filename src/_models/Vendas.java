package _models;
// Generated 12/05/2018 23:53:27 by Hibernate Tools 4.3.1



import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Vendas generated by hbm2java
 */
@Entity
@Table(name="vendas"
    ,catalog="pet"
)
public class Vendas  implements java.io.Serializable {


     private Integer codvenda;
     private Clientes clientes;
     private Date datavenda;
     private Float totalvenda;
     private Date horavenda;
      private Float descontovenda;
     private Set<RelatamItensVendas> relatamItensVendases = new HashSet<>(0);

    public Vendas() {
    }

	
    public Vendas(Clientes clientes, Date datavenda, Float descontovenda,Float totalvenda, Date horavenda) {
        this.clientes = clientes;
        this.datavenda = datavenda;
        this.totalvenda = totalvenda;
        this.descontovenda = descontovenda;
        this.horavenda = horavenda;
    }
    public Vendas(Clientes clientes, Date datavenda,Float descontovenda ,Float totalvenda, Date horavenda, Set relatamItensVendases) {
       this.clientes = clientes;
       this.datavenda = datavenda;
       this.totalvenda = totalvenda;
       this.descontovenda = descontovenda;
       this.horavenda = horavenda;
       this.relatamItensVendases = relatamItensVendases;
    }
   
     @Id @GeneratedValue(strategy=IDENTITY)

    
    @Column(name="CODVENDA", unique=true, nullable=false)
    public Integer getCodvenda() {
        return this.codvenda;
    }
    
    public void setCodvenda(Integer codvenda) {
        this.codvenda = codvenda;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="CODCLI", nullable=false)
    public Clientes getClientes() {
        return this.clientes;
    }
    
    public void setClientes(Clientes clientes) {
        this.clientes = clientes;
    }

    @Temporal(TemporalType.DATE)
    @Column(name="DATAVENDA", nullable=false, length=10)
    public Date getDatavenda() {
        return this.datavenda;
    }
    
    public void setDatavenda(Date datavenda) {
        this.datavenda = datavenda;
    }

    
    @Column(name="TOTALVENDA", nullable=false, precision=9)
    public Float getTotalvenda() {
        return this.totalvenda;
    }
    
    public void setTotalvenda(Float totalvenda) {
        this.totalvenda = totalvenda;
    }
    
    
    
    @Column(name="DESCOTOVENDA", precision=9)
    public Float getDescontoVenda() {
        return this.descontovenda;
    }
    
    public void setDescontoVenda(Float descontovenda) {
        this.descontovenda = descontovenda;
    }
    
    
    
    

    @Temporal(TemporalType.TIME)
    @Column(name="HORAVENDA",nullable=false, length=8)
    public Date getHoravenda() {
        return this.horavenda;
    }
    
    public void setHoravenda(Date horavenda) {
        this.horavenda = horavenda;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="vendas")
    public Set<RelatamItensVendas> getRelatamItensVendases() {
        return this.relatamItensVendases;
    }
    
    public void setRelatamItensVendases(Set relatamItensVendases) {
        this.relatamItensVendases = relatamItensVendases;
    }


    @Override
    public String toString() {
        return clientes.getNomecli();
    }




}



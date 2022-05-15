package _models;
// Generated 12/05/2018 23:53:27 by Hibernate Tools 4.3.1


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Usuarios generated by hbm2java
 */
@Entity
@Table(name="usuarios"
    ,catalog="pet"
)
public class Usuarios  implements java.io.Serializable {


     private Integer coduser;
     private TipoAcesso tipoAcesso;
     private String nomeuser;
     private String cpfuser;
     private String rguser;
     private String senha;
     private String useruser;
     private String sexouser;

    public Usuarios() {
    }

	
    public Usuarios(TipoAcesso tipoAcesso, String nomeuser, String cpfuser, String senha, String useruser,String sexouser) {
        this.tipoAcesso = tipoAcesso;
        this.nomeuser = nomeuser;
        this.cpfuser = cpfuser;
        this.senha = senha;
        this.useruser = useruser;
        this.useruser = sexouser;
    }
    public Usuarios(TipoAcesso tipoAcesso, String nomeuser, String cpfuser, String rguser, String senha, String useruser,String sexouser) {
       this.tipoAcesso = tipoAcesso;
       this.nomeuser = nomeuser;
       this.cpfuser = cpfuser;
       this.rguser = rguser;
       this.senha = senha;
       this.useruser = sexouser;
       this.useruser = useruser;
    }
   
     @Id @GeneratedValue(strategy=IDENTITY)

    
    @Column(name="CODUSER", unique=true, nullable=false)
    public Integer getCoduser() {
        return this.coduser;
    }
    
    public void setCoduser(Integer coduser) {
        this.coduser = coduser;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="CODACESS")
    public TipoAcesso getTipoAcesso() {
        return this.tipoAcesso;
    }
    
    public void setTipoAcesso(TipoAcesso tipoAcesso) {
        this.tipoAcesso = tipoAcesso;
    }

    
    @Column(name="NOMEUSER", nullable=false, length=50)
    public String getNomeuser() {
        return this.nomeuser;
    }
    
    public void setNomeuser(String nomeuser) {
        this.nomeuser = nomeuser;
    }

    
    @Column(name="CPFUSER", nullable=false, length=14)
    public String getCpfuser() {
        return this.cpfuser;
    }
    
    public void setCpfuser(String cpfuser) {
        this.cpfuser = cpfuser;
    }

    
    @Column(name="RGUSER", length=20)
    public String getRguser() {
        return this.rguser;
    }
    
    public void setRguser(String rguser) {
        this.rguser = rguser;
    }

    
    @Column(name="SENHA", nullable=false, length=10)
    public String getSenha() {
        return this.senha;
    }
    
    public void setSenha(String senha) {
        this.senha = senha;
    }

    
    @Column(name="USERUSER", nullable=false, length=10)
    public String getUseruser() {
        return this.useruser;
    }
    
    public void setUseruser(String useruser) {
        this.useruser = useruser;
    }
    
    @Column(name="USERUSEXO", length=10)
    public String getUserusexo() {
        return this.sexouser;
    }
    
    public void setUserusexo(String sexouser) {
        this.sexouser = sexouser;
    }

    @Override
    public String toString() {
        return nomeuser;
    }




}



package controller;

import CSS.TextFieldFormatter;
import CSS.ValidationFields;
import ProjetoPET.DAO.CidadesDAO;
import ProjetoPET.DAO.EnderecoDAO;
import ProjetoPET.DAO.EstadosDAO;
import _models.Cidades;
import _models.Clientes;
import _models.Endereco;
import _models.Estados;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTabPane;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Tab;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;

public class FXML_Cliente_P_S_cadastrarController implements Initializable {

    @FXML
    private JFXButton btnSalvar;

    @FXML
    private JFXButton btnLimpar;

    @FXML
    private JFXButton btnVoltar;

    @FXML
    private JFXButton btnFechar;

    @FXML
    private JFXTextField txtDepartment;

    @FXML
    private TextField inputCodigo;

    @FXML
    private TextField inputNome;

    @FXML
    private TextField inputCPF;

    @FXML
    private TextField inputRG;

    @FXML
    private TextField inputTelefone;

    @FXML
    private TextField inputRua;

    @FXML
    private TextField inputBairro;

    @FXML
    private TextField inputNumero;

    @FXML
    private RadioButton inputSexoMasculino;

    @FXML
    private ToggleGroup SexoCliente;

    @FXML
    private RadioButton inputSexoFeminino;

    @FXML
    private Text textMesagem;

    @FXML
    private JFXRadioButton inputPessoaFisica;

    @FXML
    private ToggleGroup Cadastro;

    @FXML
    private JFXRadioButton InputPessoaJuridica;

    private JFXTabPane tabPane;

    @FXML
    private Tab tabPessoaFisica;

    @FXML
    private Text TextCasdastroPessoa;

    @FXML
    private JFXDatePicker inputDataNacimento;
    @FXML
    private TextField inputComple;
    @FXML
    private ComboBox<Cidades> inputCidade;

    @FXML
    private ComboBox<Estados> InputEstados;

    private ObservableList<Estados> listaEstados;

    private ObservableList<Cidades> listaCidades;
    @FXML
    private ObservableList<Cidades> observableListClientes;

    @FXML
    void openFechar(ActionEvent event) {
        btnFechar.getScene().getWindow().hide();

    }

    @FXML
    void openVoltar(ActionEvent event) {
        btnVoltar.getScene().getWindow().hide();

    }

    @FXML
    void openEstados(ActionEvent event) {

    }

    @FXML
    void opnelimpar(ActionEvent event) {
        this.resetForm();
    }

    public void initialize(URL url, ResourceBundle rb) {
        carregaEstados();
        carregaEstados_juridica();

    }

    @FXML
    private void maskTel(KeyEvent event) {
        TextFieldFormatter tff = new TextFieldFormatter();
        tff.setMask("(##)####-#####");
        tff.setCaracteresValidos("0123456789");
        tff.setTf(inputTelefone);
        tff.formatter();
        tff.setTf(inputTelefoneJuridica);
        tff.formatter();
    }

    @FXML
    private void maskCPF(KeyEvent event) {
        TextFieldFormatter tff = new TextFieldFormatter();
        tff.setMask("###.###.###-##");
        tff.setCaracteresValidos("0123456789");
        tff.setTf(inputCPF);
        tff.formatter();
    }

    @FXML
    private void maskCNPJ(KeyEvent event) {
        TextFieldFormatter tff = new TextFieldFormatter();
        tff.setMask("##.###.###/####-##");
        tff.setCaracteresValidos("0123456789");
        tff.setTf(inputCNPJ);
        tff.formatter();
    }

    @FXML
    public void carregaEstados() {

        EstadosDAO eDAO = new EstadosDAO();
        List<Estados> listaTemp = new ArrayList<>();
        listaTemp = eDAO.lista();
        listaEstados = FXCollections.observableArrayList(listaTemp);
        InputEstados.setItems(listaEstados);
    }

    @FXML
    public void carregaCidades(MouseEvent event) {
        Estados e = new Estados();

        e = InputEstados.getSelectionModel().getSelectedItem();

        CidadesDAO cDAO = new CidadesDAO();
        List<Cidades> listaTemp = new ArrayList<>();
        listaTemp = cDAO.listaCidadesPorEstado(e);
        for (Cidades c : listaTemp) {
            System.out.println(c.getNomecid());
        }
        listaCidades = FXCollections.observableArrayList(listaTemp);
        inputCidade.setItems(listaCidades);
    }

    @FXML
    void opensalvar(ActionEvent event) {
        boolean VerificaCampo = ValidationFields.checkEmptyFields(inputNome, inputCPF, inputRua, inputBairro, inputCidade, InputEstados);

        Clientes cliente = new Clientes();

        cliente.setNomecli(inputNome.getText());

        cliente.setCpfCnpjcli(inputCPF.getText());

        cliente.setTelcli(inputTelefone.getText());

        cliente.setRgcli(inputRG.getText());

        cliente.setNasccli(java.sql.Date.valueOf(inputDataNacimento.getValue()));

        boolean sexoF = inputSexoFeminino.isSelected();
        boolean sexoM = inputSexoMasculino.isSelected();
        String sexoEscolhido = null;
        if (sexoF) {
            sexoEscolhido = "Feminino";
        }
        if (sexoM) {
            sexoEscolhido = "Masculino";
        }
        cliente.setSexocli(sexoEscolhido);
        Endereco ed = new Endereco();

        EnderecoDAO enderecoDAO = new EnderecoDAO();

        ed.setRuaed(inputRua.getText());

        ed.setBairroed(inputBairro.getText());

        ed.setNumed(inputNumero.getText());

        ed.setComped(inputComple.getText());

        ed.setCidades(inputCidade.getSelectionModel().getSelectedItem());

        if (VerificaCampo == true) {
            ed.setClientes(cliente);

            enderecoDAO.inserir(ed);

            cliente = new Clientes();
            ed = new Endereco();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Cadastro");
            alert.setHeaderText("Cadastro de cliente realizado com sucesso");
            alert.setContentText("");
            alert.showAndWait();
            btnSalvar.getScene().getWindow().hide();

        }

    }

    @FXML
    void tabSJuridica(ActionEvent event) {

    }

    @FXML
    private Tab tabPessoaJuridica;

    @FXML
    private TextField inputNomeEmpresa;

    @FXML
    private TextField inputCNPJ;

    @FXML
    private TextField inputTelefoneJuridica;

    @FXML
    private TextField inputRuaJuridica;

    @FXML
    private TextField inputCompleJuridica;

    @FXML
    private TextField inputBairroJuridica;

    @FXML
    private TextField inputNumeroJuridica;

    @FXML
    private JFXButton btnSalvarJuridica;

    @FXML
    private JFXButton btnLimparJuridica;

    @FXML
    private JFXButton btnVoltarJuridica;

    @FXML
    private ComboBox<Cidades> inputCidadeJuridica;

    @FXML
    private ComboBox<Estados> InputEstadosJuridica;

    @FXML
    void opensalvarJuridica(ActionEvent event) {
        boolean VerificaCampoJuridico = ValidationFields.checkEmptyFields(inputNomeEmpresa, inputCNPJ, inputRuaJuridica, inputBairroJuridica, inputCidadeJuridica, InputEstadosJuridica);

        Clientes cliente = new Clientes();

        cliente.setNomecli(inputNomeEmpresa.getText());

        cliente.setCpfCnpjcli(inputCNPJ.getText());

        cliente.setTelcli(inputTelefoneJuridica.getText());

        Endereco ed = new Endereco();
        EnderecoDAO enderecoDAO = new EnderecoDAO();
        ed.setRuaed(inputRuaJuridica.getText());

        ed.setBairroed(inputBairroJuridica.getText());

        ed.setNumed(inputNumeroJuridica.getText());

        ed.setComped(inputCompleJuridica.getText());

        ed.setCidades(inputCidadeJuridica.getSelectionModel().getSelectedItem());
        if (VerificaCampoJuridico == true) {
            ed.setClientes(cliente);
            enderecoDAO.inserir(ed);

            cliente = new Clientes();
            ed = new Endereco();

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Cadastro");
                alert.setHeaderText("Cadastro da empresa realizado com sucesso");
                alert.setContentText("");
                alert.showAndWait();
                btnSalvarJuridica.getScene().getWindow().hide();

            

        }
    }

    @FXML
    void openVoltarJuridica(ActionEvent event) {
        btnVoltarJuridica.getScene().getWindow().hide();

    }

    @FXML
    void opnelimparJuridica(ActionEvent event) {
        resetFormJuridica();
    }

    @FXML
    public void carregaEstados_juridica() {

        EstadosDAO eDAO = new EstadosDAO();
        List<Estados> listaTemp = new ArrayList<>();
        listaTemp = eDAO.lista();
        listaEstados = FXCollections.observableArrayList(listaTemp);
        InputEstadosJuridica.setItems(listaEstados);
    }

    @FXML
    public void carregaCidades_Juridica(MouseEvent event) {
        Estados e = new Estados();

        e = InputEstadosJuridica.getSelectionModel().getSelectedItem();

        CidadesDAO cDAO = new CidadesDAO();
        List<Cidades> listaTemp = new ArrayList<>();
        listaTemp = cDAO.listaCidadesPorEstado(e);
        for (Cidades c : listaTemp) {
            System.out.println(c.getNomecid());
        }
        listaCidades = FXCollections.observableArrayList(listaTemp);
        inputCidadeJuridica.setItems(listaCidades);
    }

    private void resetFormJuridica() {
        this.inputNomeEmpresa.setText("");
        this.inputBairroJuridica.setText("");
        this.inputCNPJ.setText("");
        this.inputNumeroJuridica.setText("");
        this.inputRuaJuridica.setText("");
        this.inputCompleJuridica.setText("");
        this.inputTelefoneJuridica.setText("");
        this.InputEstadosJuridica.setValue(null);
        this.inputCidadeJuridica.setValue(null);

    }

    private void resetForm() {
        this.inputNome.setText("");
        this.inputBairro.setText("");
        this.inputCPF.setText("");
        this.inputRG.setText("");
        this.inputNumero.setText("");
        this.inputRua.setText("");
        this.inputComple.setText("");
        this.inputTelefone.setText("");
        this.SexoCliente.selectToggle(null);
        this.InputEstados.setValue(null);
        this.inputCidade.setValue(null);
        this.inputDataNacimento.setValue(null);
    }

}

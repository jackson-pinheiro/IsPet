package controller;

import static CSS.DateConvertUtils.asLocalDate;
import CSS.TextFieldFormatter;
import CSS.ValidationFields;
import static controller.DashboardController.retornaClienteEditar;
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
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Tab;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;

public class FXML_Cliente_P_S_alterarController implements Initializable {

    Clientes edita = retornaClienteEditar;

    EnderecoDAO eDao = new EnderecoDAO();
    Endereco ed = eDao.buscarEd(edita);

    @FXML
    private JFXButton btnSalvar;

    @FXML
    private JFXButton btnLimpar;
    @FXML
    private JFXButton btnCancela;

    @FXML
    private JFXButton btnVoltar;

    @FXML
    private JFXButton btnFechar;

    @FXML
    private JFXTextField txtDepartment;

    @FXML
    private TextField inputCodigo;

    @FXML
    private ToggleGroup sexo;
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
    void opencidade(ActionEvent event) {

    }

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

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        carregaEstados();
        carregaEstados_juridica();

        if (edita.getRgcli() == null) {
            handle();
            tabPessoaFisica.setDisable(true);
            setaClienteJuridica();
        } else {
            tabPessoaJuridica.setDisable(true);
            setaCliente();

        }

    }

    public void handle() {
        tabPaneAlterar.getSelectionModel().select(1);
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

        boolean VerificaCampo = ValidationFields.checkEmptyFields(inputNome, inputCPF, inputRua, inputBairro, inputRG, inputCidade, InputEstados);

        edita.setNomecli(inputNome.getText());

        edita.setCpfCnpjcli(inputCPF.getText());

        edita.setTelcli(inputTelefone.getText());

        edita.setRgcli(inputRG.getText());

        edita.setNasccli(java.sql.Date.valueOf(inputDataNacimento.getValue()));

        boolean sexoF = inputSexoFeminino.isSelected();
        boolean sexoM = inputSexoMasculino.isSelected();
        String sexoEscolhido = null;
        if (sexoF) {
            sexoEscolhido = "Feminino";
        }
        if (sexoM) {
            sexoEscolhido = "Masculino";
        }
        edita.setSexocli(sexoEscolhido);
        EnderecoDAO enderecoDAO = new EnderecoDAO();
        ed.setRuaed(inputRua.getText());

        ed.setBairroed(inputBairro.getText());

        ed.setNumed(inputNumero.getText());

        ed.setComped(inputComple.getText());

        ed.setCidades(inputCidade.getSelectionModel().getSelectedItem());

        if (VerificaCampo == true) {
            ed.setClientes(edita);
            enderecoDAO.alterar(ed);

            edita = new Clientes();
            ed = new Endereco();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Atualização de dados");
            alert.setHeaderText("Cadastro de cliente alterado com sucesso");
            alert.setContentText("");
            alert.showAndWait();
            btnSalvar.getScene().getWindow().hide();
        }

    }

    public void setaCliente() {
        inputCPF.setText(edita.getCpfCnpjcli());
        inputNome.setText(edita.getNomecli());
        inputRG.setText(edita.getRgcli());
        inputTelefone.setText(edita.getTelcli());
        inputNumero.setText((ed.getNumed()));
        inputRua.setText(ed.getRuaed());
        inputBairro.setText((ed.getBairroed()));
        inputComple.setText(ed.getComped());
        inputDataNacimento.setValue(asLocalDate(edita.getNasccli()));
        inputCidade.setValue(ed.getCidades());
        InputEstados.setValue(ed.getCidades().getEstados());

        String sexoEscolhido = edita.getSexocli();
        if ("Masculino".equals(sexoEscolhido)) {
            inputSexoMasculino.setSelected(true);
        }
        if ("Feminino".equals(sexoEscolhido)) {
            inputSexoFeminino.setSelected(true);
        }

        edita.setSexocli(sexoEscolhido);

    }

    @FXML
    private JFXTabPane tabPaneAlterar;

    @FXML
    private Tab tabPessoaFisica;
    @FXML
    private Tab tabPessoaJuridica;

    @FXML
    private TextField inputNomeEmpresa;

    @FXML
    private TextField inputCNPJ;

    @FXML
    private JFXButton btnCancelarJuridica;
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
        boolean VerificaCampo = ValidationFields.checkEmptyFields(inputNomeEmpresa, inputCNPJ, inputRuaJuridica, inputBairroJuridica, inputTelefoneJuridica, inputCidadeJuridica, InputEstadosJuridica);

        edita.setNomecli(inputNomeEmpresa.getText());

        edita.setCpfCnpjcli(inputCNPJ.getText());

        edita.setTelcli(inputTelefoneJuridica.getText());

        EnderecoDAO enderecoDAO = new EnderecoDAO();
        ed.setRuaed(inputRuaJuridica.getText());

        ed.setBairroed(inputBairroJuridica.getText());

        ed.setNumed(inputNumeroJuridica.getText());

        ed.setComped(inputCompleJuridica.getText());

        ed.setCidades(inputCidadeJuridica.getSelectionModel().getSelectedItem());

        if (VerificaCampo == true) {
            ed.setClientes(edita);
            enderecoDAO.alterar(ed);

            edita = new Clientes();
            ed = new Endereco();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Atualização de dados");
            alert.setHeaderText("Cadastro de cliente alterado com sucesso");
            alert.setContentText("");
            alert.showAndWait();
            btnSalvar.getScene().getWindow().hide();
        }
    }

    @FXML
    void openVoltarJuridica(ActionEvent event) {
        btnVoltarJuridica.getScene().getWindow().hide();

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

    public void setaClienteJuridica() {
        inputCNPJ.setText(edita.getCpfCnpjcli());
        inputNomeEmpresa.setText(edita.getNomecli());
        inputTelefoneJuridica.setText(edita.getTelcli());
        inputNumeroJuridica.setText((ed.getNumed()));
        inputRuaJuridica.setText(ed.getRuaed());
        inputBairroJuridica.setText((ed.getBairroed()));
        inputCompleJuridica.setText(ed.getComped());
        inputCidadeJuridica.setValue(ed.getCidades());
        InputEstadosJuridica.setValue(ed.getCidades().getEstados());
    }

    @FXML
    void openCancela(ActionEvent event) {

    }

    @FXML
    void openCancelaJuridica(ActionEvent event) {

    }

}

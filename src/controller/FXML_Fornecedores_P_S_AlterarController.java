/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import CSS.TextFieldFormatter;
import CSS.ValidationFields;
import ProjetoPET.DAO.CidadesDAO;
import ProjetoPET.DAO.EnderecoDAO;
import ProjetoPET.DAO.EstadosDAO;
import ProjetoPET.DAO.FornecedoresDAO;
import _models.Cidades;
import _models.Endereco;
import _models.Estados;
import _models.Fornecedores;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTabPane;
import static controller.DashboardController.retornaFornecedorEditar;
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
import javafx.scene.control.Tab;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.text.Text;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author Jackson
 */
public class FXML_Fornecedores_P_S_AlterarController implements Initializable {

    Fornecedores edita = retornaFornecedorEditar;

    EnderecoDAO eDao = new EnderecoDAO();
    Endereco ed = eDao.buscarEdFornecedor(retornaFornecedorEditar);

    @FXML
    private JFXButton btnFechar;

    @FXML
    private Text textMesagem;

    @FXML
    private JFXTabPane tabPanePessoa;

    @FXML
    private Tab tabPessoaFisica;

    @FXML
    private TextField inputNome;

    @FXML
    private TextField inputRG;

    @FXML
    private TextField inputCPF;

    @FXML
    private TextField inputTelefone;

    @FXML
    private TextField inputRua;

    @FXML
    private TextField inputComple;

    @FXML
    private TextField inputBairro;

    @FXML
    private TextField inputNumero;

    @FXML
    private JFXButton btnSalvar;

    @FXML
    private JFXButton btnLimpar;

    @FXML
    private JFXButton btnVoltar;

    @FXML
    private ComboBox<Cidades> inputCidade;

    @FXML
    private ComboBox<Estados> InputEstados;

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
    void openEstados(ActionEvent event) {

    }

    @FXML
    void openFechar(ActionEvent event) {

    }

    @FXML
    void openVoltar(ActionEvent event) {
        btnVoltar.getScene().getWindow().hide();

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        carregaEstados();
        carregaEstadosJuridico();

        if (edita.getRgfor() == null) {
            tabPanePessoa.getSelectionModel().select(1);
            tabPessoaFisica.setDisable(true);
            setaFornecedoresJuridico();
        } else {
            tabPessoaJuridica.setDisable(true);
            setaFornecedoresFisica();
        }

    }

    public void handle() {
        tabPanePessoa.getSelectionModel().select(1);
    }

    //-------------------------------------------------------pessoa Fisica----------------------------
    FornecedoresDAO fornecedorDAO = new FornecedoresDAO();
    private ObservableList<Estados> listaEstados;

    private ObservableList<Cidades> listaCidades;
    @FXML
    private ObservableList<Cidades> observableListFornecedor;

    @FXML

    void opensalvar(ActionEvent event) {
        try {
            boolean VerificaCampo = ValidationFields.checkEmptyFields(inputNome, inputCPF, inputRua, inputBairro, inputRG, inputCidade, InputEstados);

            Fornecedores fornecedor = retornaFornecedorEditar;

            fornecedor.setNomefor(inputNome.getText());

            fornecedor.setCpfCnpjfor(inputCPF.getText());

            fornecedor.setTelfor(inputTelefone.getText());

            fornecedor.setRgfor(inputRG.getText());

            EnderecoDAO enderecoDAO = new EnderecoDAO();
            ed.setRuaed(inputRua.getText());

            ed.setBairroed(inputBairro.getText());

            ed.setNumed(inputNumero.getText());

            ed.setComped(inputComple.getText());

            ed.setCidades(inputCidade.getSelectionModel().getSelectedItem());
            if (VerificaCampo == true) {
                ed.setFornecedores(fornecedor);
                enderecoDAO.alterarEdFornecedor(ed);
                fornecedor = new Fornecedores();
                ed = new Endereco();
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Atualização de dados");
                alert.setHeaderText("Cadastro de fornecedor alterado com sucesso");
                alert.setContentText("");
                alert.showAndWait();
                btnSalvar.getScene().getWindow().hide();
            }
        } catch (Exception e) {

        }

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
    void opnelimpar(ActionEvent event) {
        btnLimpar.getScene().getWindow().hide();

    }

    public void setaFornecedoresFisica() {
        inputCPF.setText(retornaFornecedorEditar.getCpfCnpjfor());
        inputNome.setText(retornaFornecedorEditar.getNomefor());
        inputRG.setText(retornaFornecedorEditar.getRgfor());
        inputTelefone.setText(retornaFornecedorEditar.getTelfor());
        inputNumero.setText((ed.getNumed()));
        inputRua.setText(ed.getRuaed());
        inputBairro.setText((ed.getBairroed()));
        inputComple.setText(ed.getComped());
        inputCidade.setValue(ed.getCidades());
        InputEstados.setValue(ed.getCidades().getEstados());

    }

    //--------------------------------------pessoa Juridica ou empresa----------------------------------------------
    private ObservableList<Estados> listaEstadosJuridico;

    private ObservableList<Cidades> listaCidadesJuridico;
    @FXML
    private ObservableList<Cidades> observableListFornecedorJuridico;

    @FXML
    void opensalvarJuridica(ActionEvent event) {
        try {
            boolean VerificaCampoJuridico = ValidationFields.checkEmptyFields(inputNomeEmpresa, inputCNPJ, inputRuaJuridica, inputBairroJuridica, inputCidadeJuridica, InputEstadosJuridica);

            Fornecedores fornecedorJuridica = retornaFornecedorEditar;

            fornecedorJuridica.setNomefor(inputNomeEmpresa.getText());

            fornecedorJuridica.setCpfCnpjfor(inputCNPJ.getText());

            fornecedorJuridica.setTelfor(inputTelefoneJuridica.getText());
            EnderecoDAO enderecoDAO = new EnderecoDAO();
            ed.setRuaed(inputRuaJuridica.getText());

            ed.setBairroed(inputBairroJuridica.getText());

            ed.setNumed(inputNumeroJuridica.getText());

            ed.setComped(inputCompleJuridica.getText());

            ed.setCidades(inputCidadeJuridica.getSelectionModel().getSelectedItem());
            if (VerificaCampoJuridico == true) {
                ed.setFornecedores(fornecedorJuridica);
                enderecoDAO.alterarEdFornecedor(ed);
                fornecedorJuridica = new Fornecedores();
                ed = new Endereco();
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Atualização de dados");
                alert.setHeaderText("Cadastro de fornecedor alterado com sucesso");
                alert.setContentText("");
                alert.showAndWait();
                btnSalvarJuridica.getScene().getWindow().hide();
            }
        } catch (Exception e) {

        }

    }

    public void carregaEstadosJuridico() {

        EstadosDAO eDAO = new EstadosDAO();
        List<Estados> listaTemp = new ArrayList<>();
        listaTemp = eDAO.lista();
        listaEstados = FXCollections.observableArrayList(listaTemp);
        InputEstadosJuridica.setItems(listaEstados);
    }

    @FXML
    void carregarCidadeJuridica(MouseEvent event) {

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

    @FXML
    void openVoltarJuridica(ActionEvent event) {
        btnVoltarJuridica.getScene().getWindow().hide();

    }

    public void setaFornecedoresJuridico() {
        inputCNPJ.setText(retornaFornecedorEditar.getCpfCnpjfor());
        inputNomeEmpresa.setText(retornaFornecedorEditar.getNomefor());
        inputTelefoneJuridica.setText(retornaFornecedorEditar.getTelfor());
        inputNumeroJuridica.setText((ed.getNumed()));
        inputRuaJuridica.setText(ed.getRuaed());
        inputBairroJuridica.setText((ed.getBairroed()));
        inputCompleJuridica.setText(ed.getComped());
        inputCidadeJuridica.setValue(ed.getCidades());
        InputEstadosJuridica.setValue(ed.getCidades().getEstados());

    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import CSS.TextFieldFormatter;
import CSS.ValidationFields;
import static controller.DashboardController.retornaProdutoFornecedor;

import ProjetoPET.DAO.ProdutosServicosDAO;
import ProjetoPET.DAO.UnidadesDAO;
import _models.ProdutosServicos;
import _models.Unidades;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author Jackson
 */
public class FXML_Produto_P_I_cadastroController implements Initializable {

    @FXML
    private JFXButton btnFechar;

    @FXML
    private TextField inputNomeProdutos;

    @FXML
    private TextField inputprecoProdutos;

    @FXML
    private TextField inputEstoqueProdutos;

    @FXML
    private JFXButton btnSalvarProdutos;

    @FXML
    private JFXButton btnLimparProdutos;

    @FXML
    private JFXButton btnVoltarProdutos;

    @FXML
    private ComboBox<Unidades> InputUnidade;

    @FXML
    private TextField inputMarcaProdutos;

    @FXML
    private TextField inputDescontoProdutos;

    @FXML
    private TextArea inputDescricaoProdutos;

    @FXML
    private JFXButton btnFornecedor;

    @FXML
    private JFXTextField txtFornecedor;

    @FXML
    private JFXTextField txtCodigo;

    @FXML
    private JFXTextField txtCpfCnpj;

    @FXML
    private TextField inputNomeServico;

    @FXML
    private TextField inputprecoServico;

    @FXML
    private JFXButton btnSalvar2;

    @FXML
    private JFXButton btnLimpar2;

    @FXML
    private JFXButton btnVoltar2;

    @FXML
    private TextArea inputDescricaoServico;

    @FXML
    private TextField inputprecoVenda;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        retornaProdutoFornecedor = null;
        carregarUnidade();

    }

    //-----------------------produtos----------------------------
    @FXML
    void openFornecedor(ActionEvent event) throws IOException {
        try {

            Parent root = FXMLLoader.load(getClass().getResource("/view/FXML_Produto_P_T_Fornecedor.fxml"));
            Stage stage = new Stage();
            Scene scene = new Scene(root);
            scene.setFill(Color.TRANSPARENT);
            stage.initStyle(StageStyle.TRANSPARENT);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setResizable(false);
            stage.setScene(scene);
            stage.setX(202);
            stage.setY(38);
            stage.showAndWait();
            carregaFornecedor();

        } catch (Exception e) {
            System.out.println("ERRO  " + e.getLocalizedMessage());
        }
    }

    @FXML
    void openVoltarProdutos(ActionEvent event) {
        btnVoltar2.getScene().getWindow().hide();

    }

    ProdutosServicosDAO produtosDao = new ProdutosServicosDAO();

    @FXML
    void opensalvarProdutos(ActionEvent event) {
        try {
            boolean VerificaCampoProduto = ValidationFields.checkEmptyFields(inputNomeProdutos, inputMarcaProdutos, inputprecoProdutos, inputprecoVenda, InputUnidade);

            ProdutosServicos produtos = new ProdutosServicos();

            produtos.setDescps(inputDescricaoProdutos.getText());
            produtos.setEstoqueps(0);
            produtos.setPrecops((Float.parseFloat(inputprecoProdutos.getText())));
            produtos.setPrecoVend(Float.parseFloat(inputprecoVenda.getText()));

            produtos.setMarcasprod(inputMarcaProdutos.getText());
            produtos.setUnidades(InputUnidade.getSelectionModel().getSelectedItem());
            produtos.setNomeps(inputNomeProdutos.getText());
            if (VerificaCampoProduto == true) {
                produtosDao.inserir(produtos);

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Cadastro");
                alert.setHeaderText("Cadastro de produto realizado com sucesso");
                alert.setContentText("");
                alert.showAndWait();
                btnSalvarProdutos.getScene().getWindow().hide();
            }
        } catch (Exception e) {
        }

    }

    @FXML
    void opnelimparProdutos(ActionEvent event) {
        resetFormProdutos();

    }

    private void resetFormProdutos() {
        this.inputDescricaoProdutos.setText("");
        this.inputMarcaProdutos.setText("");
        this.inputNomeProdutos.setText("");
        this.inputprecoVenda.setText("");
        this.inputprecoProdutos.setText("");
        this.txtFornecedor.setText("");
        this.txtCpfCnpj.setText("");
        this.txtCodigo.setText("");
        retornaProdutoFornecedor = null;
        this.InputUnidade.setValue(null);

    }

    private ObservableList<Unidades> listaUnidade;

    @FXML
    public void carregarUnidade() {

        UnidadesDAO uDAO = new UnidadesDAO();

        List<Unidades> listaTemp = new ArrayList<>();

        listaTemp = uDAO.lista();

        listaUnidade = FXCollections.observableArrayList(listaTemp);

        InputUnidade.setItems(listaUnidade);
    }

    //------------------------------ servicos-------------------------------
    @FXML
    void opensalvar(ActionEvent event) {
    boolean VerificaCampoService = ValidationFields.checkEmptyFields(inputNomeServico, inputprecoServico);

        ProdutosServicos Servico = new ProdutosServicos();
        Servico.setDescps(inputDescricaoServico.getText());
        Servico.setPrecoVend((Float.parseFloat(inputprecoServico.getText())));
        Servico.setNomeps(inputNomeServico.getText());
        if(VerificaCampoService==true){            
                            produtosDao.inserir(Servico);
                            
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Cadastro");
                alert.setHeaderText("Cadastro de produto realizado com sucesso");
                alert.setContentText("");
                alert.showAndWait();
                btnSalvar2.getScene().getWindow().hide();

            
        }

    }

    @FXML
    void opnelimpar(ActionEvent event) {
        resetFormServicos();

    }

    @FXML
    void openVoltar(ActionEvent event) {
        btnVoltar2.getScene().getWindow().hide();
        retornaProdutoFornecedor = null;

    }

    public void carregaFornecedor() {
        txtFornecedor.setText(retornaProdutoFornecedor.getNomefor());
        txtCpfCnpj.setText(retornaProdutoFornecedor.getCpfCnpjfor());
        txtCodigo.setText(Integer.toString(retornaProdutoFornecedor.getCodfor()));

    }

    private void resetFormServicos() {
        this.inputprecoServico.setText("");
        this.inputDescricaoServico.setText("");
        this.inputNomeServico.setText("");

    }

    @FXML
    void maskPreco(KeyEvent event) {
        TextFieldFormatter tff = new TextFieldFormatter();
        tff.setMask("############");
        tff.setCaracteresValidos("0123456789");
        //     tff.setTf();
        tff.formatter();
    }

    @FXML
    void maskVenda(KeyEvent event) {

    }

}

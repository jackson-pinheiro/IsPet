/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import CSS.ValidationFields;
import ProjetoPET.DAO.ComprasDAO;
import ProjetoPET.DAO.ProdutosServicosDAO;
import _models.Compras;
import _models.ProdutosServicos;
import _models.RelatamItensCompras;
import _models.Vendas;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import static controller.DashboardController.retornaProdutoFornecedor;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author Jackson
 */
public class FXML_Compras_P_S_CadastrarController implements Initializable {

    @FXML
    private AnchorPane anhchopane;

    @FXML
    private TextField imputAddEstoque;
    @FXML
    private ComboBox<ProdutosServicos> comboAddProdutoNome;

    @FXML
    private TextField inputCodigo;

    @FXML
    private TextField imputTotal;

    @FXML
    private TextField imputAddProdutoQuantidade;

    @FXML
    private JFXButton btnAddProduto;

    @FXML
    private TextField imputAddProdutoValor;

    @FXML
    private JFXButton btnCancelar;

    @FXML
    private JFXButton btnFinalizar;

    @FXML
    private JFXButton btnImprimir;

    @FXML
    private TableView<RelatamItensCompras> tableProdutos;

    @FXML
    private TableColumn<RelatamItensCompras, Integer> clCodigo;

    @FXML
    private TableColumn<RelatamItensCompras, RelatamItensCompras> clProdutos;

    @FXML
    private TableColumn<RelatamItensCompras, Float> clPreco;

    @FXML
    private TableColumn<RelatamItensCompras, Float> clQuantidade;

    @FXML
    private TableColumn<RelatamItensCompras, Float> clprecoUnitario;

    @FXML
    private JFXButton btnRemover;

    @FXML
    private JFXButton btnCliente;

    @FXML
    private JFXTextField txtCodigoFor;

    @FXML
    private JFXTextField txtNomeFor;

    @FXML
    private JFXTextField txtCnpj;
    @FXML
    private JFXButton btnFornecedor;

    @FXML
    private JFXButton btnVoltar;

    private ObservableList<RelatamItensCompras> relatamItensComprases;
    private ObservableList<ProdutosServicos> produtoServicose;
    private ProdutosServicosDAO pDAO = new ProdutosServicosDAO();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        compra.setTotalcompra(a);
        retornaProdutoFornecedor = null;
        clProdutos.setCellValueFactory(new PropertyValueFactory<>("produtosServicos"));
        clCodigo.setCellValueFactory(new PropertyValueFactory<>("coditemC"));
        clPreco.setCellValueFactory(new PropertyValueFactory<>("totalitemC"));
        clQuantidade.setCellValueFactory(new PropertyValueFactory<>("qtditemC"));
        clprecoUnitario.setCellValueFactory(new PropertyValueFactory<>("precoitemC"));
        anhchopane.setDisable(true);

    }

    Compras compra = new Compras();
    float a = 0;

    @FXML
    void openAddProduto(ActionEvent event) {
        ProdutosServicos produtosServicos;
        RelatamItensCompras itemDeCompras = new RelatamItensCompras();
        if (comboAddProdutoNome.getSelectionModel().getSelectedItem() != null) {
            produtosServicos = (ProdutosServicos) comboAddProdutoNome.getSelectionModel().getSelectedItem();
            itemDeCompras.setProdutosServicos((ProdutosServicos) comboAddProdutoNome.getSelectionModel().getSelectedItem());
            itemDeCompras.setQtditemC(Float.parseFloat(imputAddProdutoQuantidade.getText()));

            itemDeCompras.setTotalitemC(itemDeCompras.getProdutosServicos().getPrecops() * itemDeCompras.getQtditemC());
            itemDeCompras.setCoditemC(itemDeCompras.getProdutosServicos().getCodprod());
            itemDeCompras.setPrecoitemC(itemDeCompras.getProdutosServicos().getPrecops());
            compra.getRelatamItensComprases().add(itemDeCompras);
            compra.setDatacompra(new Date());
            compra.setHoracompra(new Date());
            compra.setFornecedores(retornaProdutoFornecedor);

            relatamItensComprases = FXCollections.observableArrayList(compra.getRelatamItensComprases());

            tableProdutos.setItems(relatamItensComprases);

            produtosServicos.setEstoqueps(produtosServicos.getEstoqueps() + Integer.parseInt(imputAddProdutoQuantidade.getText()));

            itemDeCompras.setQtditemC(Float.parseFloat(imputAddProdutoQuantidade.getText()));

            compra.setTotalcompra(itemDeCompras.getTotalitemC() + compra.getTotalcompra());

            imputTotal.setText(Float.toString(compra.getTotalcompra()));

            this.comboAddProdutoNome.setValue(null);
            this.imputAddProdutoQuantidade.setText("");
            this.imputAddProdutoValor.setText("");
            this.inputCodigo.setText("");
            this.imputAddEstoque.setText("");
            this.imputAddEstoque.setText("");
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setAlertType(Alert.AlertType.ERROR);
            alert.show();

        }

    }

    public void carregaFornecedor() {
        txtNomeFor.setText(retornaProdutoFornecedor.getNomefor());
        txtCnpj.setText(retornaProdutoFornecedor.getCpfCnpjfor());
        txtCodigoFor.setText(Integer.toString(retornaProdutoFornecedor.getCodfor()));

    }

    @FXML
    void openCancelar(ActionEvent event) {
        btnCancelar.getScene().getWindow().hide();
    }

    @FXML
    void openProduto(ActionEvent event) throws IOException {
        ProdutosServicos produtosServicos = new ProdutosServicos();
        produtosServicos = (ProdutosServicos) comboAddProdutoNome.getSelectionModel().getSelectedItem();
        inputCodigo.setText(Integer.toString(produtosServicos.getCodprod()));
        imputAddProdutoValor.setText(Float.toString(produtosServicos.getPrecops()));
        imputAddEstoque.setText(Float.toString(produtosServicos.getEstoqueps()));

    }

    @FXML
    void openFinalizar(ActionEvent event) {
        boolean VerificaCampo = ValidationFields.checkEmptyFields(txtCnpj, txtCodigoFor, txtNomeFor, tableProdutos);
        if (VerificaCampo == true) {
            pDAO.alterarEstoqueVenda(produtoServicose);
            ComprasDAO vDAO = new ComprasDAO();
            vDAO.salvarItensCompras(compra, relatamItensComprases);
            btnFinalizar.getScene().getWindow().hide();

        }

    }

    @FXML
    void openImprimir(ActionEvent event) {

    }

    @FXML
    void openRemove(ActionEvent event) {

        RelatamItensCompras relatamItensComprasDelete = tableProdutos.getSelectionModel().getSelectedItem();
        relatamItensComprasDelete.getProdutosServicos().setEstoqueps(relatamItensComprasDelete.getProdutosServicos().getEstoqueps() - relatamItensComprasDelete.getQtditemC());

        compra.setTotalcompra(compra.getTotalcompra() - relatamItensComprasDelete.getTotalitemC());
        relatamItensComprases.remove(relatamItensComprasDelete);
        compra.getRelatamItensComprases().remove(relatamItensComprasDelete);
        imputTotal.setText(Float.toString(compra.getCodcompra()));

    }

    public void carregaProdutosnome() {
        List<ProdutosServicos> listaTemp = new ArrayList<>();
        listaTemp = pDAO.lista();
        for (ProdutosServicos p : listaTemp) {
            System.out.println(p.getNomeps());

        }
        produtoServicose = FXCollections.observableArrayList(listaTemp);
        comboAddProdutoNome.setItems(produtoServicose);

    }

    @FXML
    void openFornecedor(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/view/FXML_Produto_P_T_Fornecedor.fxml"));
        Stage stage = new Stage();
        Scene scene = new Scene(root);
        scene.setFill(Color.TRANSPARENT);
        stage.initStyle(StageStyle.TRANSPARENT);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setResizable(false);
        stage.setX(202);
        stage.setY(38);
        stage.setScene(scene);
        stage.showAndWait();

        if (retornaProdutoFornecedor != null) {
            carregaFornecedor();
            anhchopane.setDisable(false);
            carregaProdutosnome();
        }

    }

    @FXML
    void openVoltar(ActionEvent event) {
        btnVoltar.getScene().getWindow().hide();

    }
}

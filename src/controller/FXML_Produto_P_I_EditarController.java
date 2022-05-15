/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import ProjetoPET.DAO.FornecedoresDAO;
import static controller.DashboardController.retornaprodutoservicosEditar;
import ProjetoPET.DAO.ProdutosServicosDAO;
import ProjetoPET.DAO.UnidadesDAO;
import _models.Fornecedores;
import _models.ProdutosServicos;
import _models.Unidades;
import com.jfoenix.controls.JFXButton;
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
import javafx.scene.control.Tab;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author Jackson
 */
public class FXML_Produto_P_I_EditarController implements Initializable {

    @FXML
    private JFXButton btnFechar;

    @FXML
    private ComboBox<Unidades> InputUnidade;

    @FXML
    private JFXTextField txtFornecedor;

    @FXML
    private JFXTextField txtCodigo;

    @FXML
    private JFXTextField txtCpfCnpj;

    @FXML
    private TextField inputNomeProdutos;

    @FXML
    private TextField inputprecoProdutos;
    @FXML
    private TextField inputMarcaProdutos;

    @FXML
    private TextArea inputDescricaoProdutos;

    @FXML
    private JFXButton btnSalvarProdutos;

    @FXML
    private JFXButton btnVoltarProdutos;

    @FXML
    private TextField inputNomeServico;

    @FXML
    private TextField inputprecoServico;

    @FXML
    private TextArea inputDescricaoServico;

    @FXML
    private JFXTabPane tableAlterar;

    @FXML
    private Tab tabProduto;

    @FXML
    private Tab tabService;

    @FXML
    private JFXButton btnSalvarService;

    @FXML
    private TextField inputprecoVenda;
    @FXML
    private JFXButton btnVoltarService;

    private ObservableList<Unidades> listaUnidade;

    ProdutosServicos produtos = retornaprodutoservicosEditar;
    ProdutosServicosDAO produtosDao = new ProdutosServicosDAO();

    @FXML
    void openSalvarProduto(ActionEvent event) {
        try {
            produtos.setDescps(inputDescricaoProdutos.getText());
            produtos.setMarcasprod(inputMarcaProdutos.getText());
            produtos.setNomeps(inputNomeProdutos.getText());
            produtos.setPrecops(Float.parseFloat(inputprecoProdutos.getText()));
            produtos.setUnidades(InputUnidade.getSelectionModel().getSelectedItem());
            produtos.setPrecoVend(Float.parseFloat(inputprecoVenda.getText()));
            produtosDao.alterar(produtos);
            btnSalvarProdutos.getScene().getWindow().hide();

        } catch (Exception e) {
            System.out.println("ERRO:" + e);

        }

    }

    @FXML
    void openCancelar(ActionEvent event) {
        btnVoltarProdutos.getScene().getWindow().hide();

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        if (retornaprodutoservicosEditar.getMarcasprod() == null) {
            tabProduto.setDisable(true);
            handle();
            CarregarService();
            carregarUnidade();

        } else {
            tabService.setDisable(true);
            carregarUnidade();
            CarregarProduto();

        }
    }

    public void handle() {
        tableAlterar.getSelectionModel().select(1);
    }

    @FXML
    public void carregarUnidade() {

        UnidadesDAO uDAO = new UnidadesDAO();

        List<Unidades> listaTemp = new ArrayList<>();

        listaTemp = uDAO.lista();

        listaUnidade = FXCollections.observableArrayList(listaTemp);

        InputUnidade.setItems(listaUnidade);
    }

    public void CarregarProduto() {

        inputNomeProdutos.setText(produtos.getNomeps());
        InputUnidade.setValue(produtos.getUnidades());
        inputDescricaoProdutos.setText(produtos.getDescps());
        inputMarcaProdutos.setText(produtos.getMarcasprod());
        inputprecoProdutos.setText(Float.toString(produtos.getPrecops()));
        /*   */
    }

    //-----------------------produtos----------------------------
    public void CarregarService() {

        inputNomeServico.setText(produtos.getNomeps());
        inputDescricaoServico.setText(produtos.getDescps());
        inputprecoServico.setText(Float.toString(produtos.getPrecoVend()));

    }

    @FXML
    void openVoltarProdutos(ActionEvent event) {
        btnVoltarProdutos.getScene().getWindow().hide();
    }

    @FXML
    void openVoltarService(ActionEvent event) {
        btnSalvarService.getScene().getWindow().hide();

    }

    @FXML
    void opensalvarService(ActionEvent event) {

        produtos.setDescps(inputDescricaoServico.getText());
        produtos.setNomeps(inputNomeServico.getText());
        produtos.setPrecoVend(Float.parseFloat(inputprecoServico.getText()));
        produtosDao.alterar(produtos);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Atualização de dados");
                alert.setHeaderText("Serviço alterado com sucesso");
                alert.setContentText("");
                alert.showAndWait();
        btnSalvarService.getScene().getWindow().hide();

    }



}

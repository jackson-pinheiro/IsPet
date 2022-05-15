/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import ProjetoPET.DAO.AnimaisDAO;
import ProjetoPET.DAO.ProdutosServicosDAO;
import ProjetoPET.DAO.VendasDAO;
import _models.Animais;
import _models.ProdutosServicos;
import _models.RelatamItensVendas;
import _models.Vendas;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import static controller.DashboardController.retornaClienteAnimal;
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
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
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
public class FXML_Vendas_P_S_CadastrarController implements Initializable {

    @FXML
    private ComboBox<ProdutosServicos> comboAddProdutoNome;

    @FXML
    private TextField InputCodigoCliente;

    @FXML
    private AnchorPane anhchopane;

    @FXML
    private TextField imputAddProdutoQuantidade;

    @FXML
    private JFXButton btnAddProduto;

    @FXML
    private TextField imputAddProdutoValor;

    @FXML
    private TextField imputAddEstoque;

    @FXML
    private TableView<RelatamItensVendas> tableProdutos;

    @FXML
    private TableColumn<RelatamItensVendas, Integer> clCodigo;

    @FXML
    private TableColumn<RelatamItensVendas, RelatamItensVendas> clProdutos;

    @FXML
    private TableColumn<RelatamItensVendas, Float> clPreco;

    @FXML
    private TableColumn<RelatamItensVendas, Float> clQuantidade;

    @FXML
    private TableColumn<RelatamItensVendas, Float> clprecoUnitario;

    @FXML
    private TextField imputTotal;

    @FXML
    private TextField imputDesconto;

    @FXML
    private JFXButton btnCliente;

    @FXML
    private TextField inputCodigo;

    @FXML
    private JFXTextField txtNomeCLi;

    @FXML
    private JFXTextField txtEspecieAnimal;

    @FXML
    private JFXTextField txtCpf;

    @FXML
    private JFXButton btnCancelar;

    @FXML
    private JFXButton btnGravarSair;

    @FXML
    private JFXButton btnFinalizar;

    @FXML
    private JFXButton btnImprimir;

    @FXML
    private JFXButton btnFechar;

    @FXML
    private TextField inputTotalComDesconto;

    @FXML
    private TextField inputValorRecebido;

    @FXML
    private TextField ImputTroco;

    @FXML
    private JFXTextField txtCodAminal;

    @FXML
    private JFXTextField txtCodigoCli;

    @FXML
    private JFXButton btnRemover;

    @FXML
    private JFXButton btnVoltar;

    @FXML
    private ComboBox<Animais> comboxAnimal;

    @FXML
    private TextField inputCodigoAnimal;

    private ObservableList<RelatamItensVendas> relatamItensVendases;
    private ObservableList<ProdutosServicos> produtoServicose;
    private final ProdutosServicosDAO pDAO = new ProdutosServicosDAO();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        btnRemover.setDisable(true);
        venda.setTotalvenda(a);
        retornaClienteAnimal = null;
        carregaProdutosnome();
        clProdutos.setCellValueFactory(new PropertyValueFactory<>("produtosServicos"));
        clCodigo.setCellValueFactory(new PropertyValueFactory<>("coditemV"));
        clPreco.setCellValueFactory(new PropertyValueFactory<>("totalitemV"));
        clQuantidade.setCellValueFactory(new PropertyValueFactory<>("qtditemV"));
        clprecoUnitario.setCellValueFactory(new PropertyValueFactory<>("precoitemV"));
        anhchopane.setDisable(true);

    }

    Vendas venda = new Vendas();
    float a = 0;
    float b = 0;
    float porcentagem;
    float total = 0;

    @FXML
    void openAddProduto(ActionEvent event) {
        ProdutosServicos produtosServicos;
        RelatamItensVendas itemDeVenda = new RelatamItensVendas();
        float qtd = 0;
        if (comboAddProdutoNome.getSelectionModel().getSelectedItem() != null) {
            produtosServicos = (ProdutosServicos) comboAddProdutoNome.getSelectionModel().getSelectedItem();

            if (produtosServicos.getEstoqueps() >= Integer.parseInt(imputAddProdutoQuantidade.getText()) && produtosServicos.getEstoqueps() > 0) {

                itemDeVenda.setProdutosServicos((ProdutosServicos) comboAddProdutoNome.getSelectionModel().getSelectedItem());
                float recebe = Float.parseFloat(imputAddProdutoQuantidade.getText());
                if (recebe > 0) {
                    itemDeVenda.setQtditemV(recebe);
                }
                itemDeVenda.setTotalitemV(itemDeVenda.getProdutosServicos().getPrecoVend() * itemDeVenda.getQtditemV());
                itemDeVenda.setCoditemV(itemDeVenda.getProdutosServicos().getCodprod());
                itemDeVenda.setPrecoitemV(itemDeVenda.getProdutosServicos().getPrecoVend());
                venda.getRelatamItensVendases().add(itemDeVenda);
                venda.setDatavenda(new Date());
                venda.setHoravenda(new Date());
                venda.setDescontoVenda(Float.parseFloat(imputDesconto.getText()));
                venda.setClientes(retornaClienteAnimal);
                itemDeVenda.setAnimais(comboxAnimal.getSelectionModel().getSelectedItem());

                relatamItensVendases = FXCollections.observableArrayList(venda.getRelatamItensVendases());

                tableProdutos.setItems(relatamItensVendases);
                produtosServicos.setEstoqueps(produtosServicos.getEstoqueps() - Integer.parseInt(imputAddProdutoQuantidade.getText()));

                itemDeVenda.setQtditemV(Float.parseFloat(imputAddProdutoQuantidade.getText()));

                venda.setTotalvenda(itemDeVenda.getTotalitemV() + venda.getTotalvenda());

                imputTotal.setText(Float.toString(venda.getTotalvenda()));

                inputTotalComDesconto.setText(Float.toString(venda.getTotalvenda()));

            } else if (produtosServicos.getUnidades() == null) {
                itemDeVenda.setProdutosServicos((ProdutosServicos) comboAddProdutoNome.getSelectionModel().getSelectedItem());
                float recebe = Float.parseFloat(imputAddProdutoQuantidade.getText());
                if (recebe > 0) {
                    itemDeVenda.setQtditemV(recebe);
                }
                itemDeVenda.setTotalitemV(itemDeVenda.getProdutosServicos().getPrecoVend() * itemDeVenda.getQtditemV());
                itemDeVenda.setCoditemV(itemDeVenda.getProdutosServicos().getCodprod());
                itemDeVenda.setPrecoitemV(itemDeVenda.getProdutosServicos().getPrecoVend());
                venda.getRelatamItensVendases().add(itemDeVenda);
                venda.setDatavenda(new Date());
                venda.setHoravenda(new Date());
                venda.setDescontoVenda(Float.parseFloat(imputDesconto.getText()));
                venda.setClientes(retornaClienteAnimal);
                itemDeVenda.setAnimais(comboxAnimal.getSelectionModel().getSelectedItem());

                relatamItensVendases = FXCollections.observableArrayList(venda.getRelatamItensVendases());

                tableProdutos.setItems(relatamItensVendases);
                itemDeVenda.setQtditemV(Float.parseFloat(imputAddProdutoQuantidade.getText()));

                venda.setTotalvenda(itemDeVenda.getTotalitemV() + venda.getTotalvenda());

                imputTotal.setText(Float.toString(venda.getTotalvenda()));
                resetarProduto();
                inputTotalComDesconto.setText(Float.toString(venda.getTotalvenda()));

            } else {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setAlertType(Alert.AlertType.INFORMATION);
                alert.setTitle("Aviso");
                alert.setContentText("Estoque Inferior A quantidade a ser vendida");

                alert.show();

            }

        }
    }

    @FXML
    void openCancelar(ActionEvent event) {
        btnCancelar.getScene().getWindow().hide();
    }

    @FXML
    void openCliente(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/view/FXML_Animal_P_T_CLiente.fxml"));
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
        carregaNome();
        if (retornaClienteAnimal != null) {
            carregaAnimais();

            anhchopane.setDisable(false);
        }
    }

    @FXML
    void openFechar(ActionEvent event) {
        btnFechar.getScene().getWindow().hide();

    }

    @FXML
    void openRemove(ActionEvent event) {
        RelatamItensVendas relatamItensVendasDelete = tableProdutos.getSelectionModel().getSelectedItem();

        relatamItensVendasDelete.getProdutosServicos().setEstoqueps(relatamItensVendasDelete.getProdutosServicos().getEstoqueps() + relatamItensVendasDelete.getQtditemV());
        venda.setTotalvenda(venda.getTotalvenda() - relatamItensVendasDelete.getTotalitemV());
        relatamItensVendases.remove(relatamItensVendasDelete);
        produtoServicose.remove(tableProdutos.getSelectionModel().getSelectedItem());
        venda.getRelatamItensVendases().remove(relatamItensVendasDelete);
        inputTotalComDesconto.setText(Float.toString(venda.getTotalvenda()));

        imputTotal.setText(Float.toString(venda.getTotalvenda()));

        imputDesconto.setText(Float.toString(0));
        if (venda.getTotalvenda() < 0) {
            imputTotal.setText(Integer.toString(0));
            imputDesconto.setText(Integer.toString(0));
        }
    }

    @FXML
    void openFinalizar(ActionEvent event) {
        pDAO.alterarEstoqueVenda(produtoServicose);
        VendasDAO vDAO = new VendasDAO();
        vDAO.salvarItensVenda(venda, relatamItensVendases);

        btnFinalizar.getScene().getWindow().hide();

    }

    @FXML
    void openImprimir(ActionEvent event) {

    }

    public void carregaNome() {
        txtNomeCLi.setText(retornaClienteAnimal.getNomecli());
        txtCpf.setText(retornaClienteAnimal.getCpfCnpjcli());
        txtCodigoCli.setText(Integer.toString(retornaClienteAnimal.getCodcli()));
    }

    public void carregaProdutosnome() {
        ProdutosServicosDAO pDAO = new ProdutosServicosDAO();
        List<ProdutosServicos> listaTemp = new ArrayList<>();
        listaTemp = pDAO.lista();
        produtoServicose = FXCollections.observableArrayList(listaTemp);
        comboAddProdutoNome.setItems(produtoServicose);
    }

    @FXML
    void openProdutoServico(ActionEvent event) {
        ProdutosServicos produtosServicos = new ProdutosServicos();
        produtosServicos = (ProdutosServicos) comboAddProdutoNome.getSelectionModel().getSelectedItem();
        inputCodigo.setText(Integer.toString(produtosServicos.getCodprod()));
        imputAddProdutoValor.setText(Float.toString(produtosServicos.getPrecoVend()));
        imputAddProdutoQuantidade.setText(Integer.toString(1));
        inputTotalComDesconto.setText(Integer.toString(1));
        if (produtosServicos.getMarcasprod() == null) {
            imputAddEstoque.setVisible(true);
        } else {
            imputAddEstoque.setText(Float.toString(produtosServicos.getEstoqueps()));
        }

    }

    @FXML
    private List<Animais> listaAnimais = new ArrayList<>();

    private ObservableList relatamAnimais;

    public void carregaAnimais() {

        AnimaisDAO aDAO = new AnimaisDAO();

        List<Animais> listaTemp = new ArrayList<>();
        listaTemp = aDAO.listaAnimaisPorCliente(retornaClienteAnimal);
        for (Animais a : listaTemp) {
            System.out.println(a.getNomeani());
        }
        relatamAnimais = FXCollections.observableArrayList(listaTemp);
        comboxAnimal.setItems(relatamAnimais);
    }

    @FXML
    void openAnimal(ActionEvent event) {
        Animais animal = new Animais();
        animal = (Animais) comboxAnimal.getSelectionModel().getSelectedItem();
        txtCodAminal.setText(Integer.toString(animal.getCodani()));
        txtEspecieAnimal.setText(animal.getRacas().getEspecies().getNomeesp());

    }

    @FXML
    void desconto(KeyEvent event) {

        porcentagem = (Float.parseFloat(imputDesconto.getText()));
        if (porcentagem <= 100 && porcentagem >= 0) {
            total = (Float.parseFloat(imputTotal.getText()));
            total = total - ((total * porcentagem) / 100);
            inputTotalComDesconto.setText(Float.toString(total));
            venda.setTotalvenda(total);
        } else {

        }
    }

    @FXML
    void valorRecebido(KeyEvent event) {
        float totaldescoonto = (Float.parseFloat(inputValorRecebido.getText()));
        totaldescoonto = totaldescoonto - total;
        if (totaldescoonto < 0) {
            ImputTroco.setText(Float.toString(0));
        } else {
            ImputTroco.setText(Float.toString(totaldescoonto));

        }
    }

    @FXML
    void openVoltar(ActionEvent event) {
        btnVoltar.getScene().getWindow().hide();
    }

    public void resetar() {
        this.comboAddProdutoNome.setValue(null);
        this.imputAddProdutoQuantidade.setText("");
        this.imputAddProdutoValor.setText("");
        this.inputCodigo.setText("");
        this.imputAddEstoque.setText("");
        this.txtCodAminal.setText("");
        this.txtCodigoCli.setText("");
        this.txtCpf.setText("");
        this.txtEspecieAnimal.setText("");
        this.txtNomeCLi.setText("");
        this.ImputTroco.setText("");
        this.imputDesconto.setText("");
        this.imputTotal.setText("");
        this.inputValorRecebido.setText("");
        this.comboxAnimal.setValue(null);

    }

    public void resetarProduto() {
        this.comboAddProdutoNome.setValue(null);
        this.imputAddProdutoQuantidade.setText("");
        this.imputAddProdutoValor.setText("");
        this.inputCodigo.setText("");
        this.imputAddEstoque.setText("");

    }

    @FXML
    void ativa(MouseEvent event) {
        resetarProduto();
        btnRemover.setDisable(false);

    }
}

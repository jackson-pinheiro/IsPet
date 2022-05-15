/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import static controller.DashboardController.retornaprodutoservicosEditar;
import ProjetoPET.DAO.ProdutosServicosDAO;
import _models.Fornecedores;
import _models.ProdutosServicos;
import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import static controller.LoginController.usuarioVerifica;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author Jackson
 */
public class FXML_Produto_P_IController implements Initializable {

    @FXML
    private TableView<ProdutosServicos> tableViewProduto;

    @FXML
    private TableColumn<ProdutosServicos, Integer> clnCodProdutos;

    @FXML
    private TableColumn<ProdutosServicos, String> clnNome;

    @FXML
    private TableColumn<ProdutosServicos, Float> clnPreco;

    @FXML
    private TableColumn<ProdutosServicos, String> clnMarca;

    @FXML
    private TableColumn<ProdutosServicos, String> clnUnidade;

    @FXML
    private TableColumn<ProdutosServicos, String> clnDescriçao;

    @FXML
    private TableColumn<ProdutosServicos, Float> clnEstoque;

    @FXML
    private TextField inputProcurar;

    @FXML
    private JFXButton btnCompra;

    @FXML
    private JFXButton btnAdicionar;

    @FXML
    private JFXButton btnRemover;

    @FXML
    private JFXButton btnEditar;

    @FXML
    private JFXButton btnVisualizar;

    @FXML
    void openVisualizar(ActionEvent event) throws IOException {
        ProdutosServicos produtoservico = tableViewProduto.getSelectionModel().getSelectedItem();
        if (produtoservico != null) {
            retornaprodutoservicosEditar = produtoservico;
            Parent root = FXMLLoader.load(getClass().getResource("/view/FXML_Produtos_P_S_Visualizar.fxml"));
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
            carregaProdutos();

        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Por favor, selecione um produto para esssa operação!");
            alert.show();
        }

    }

    ProdutosServicosDAO pDAO = new ProdutosServicosDAO();

    @FXML
    void OpenRemover(ActionEvent event) {
        ProdutosServicos produtos = tableViewProduto.getSelectionModel().getSelectedItem();
        boolean confirmaExclusao;
        if (produtos != null) {
            Alert alert = new Alert(AlertType.CONFIRMATION);
            alert.setTitle("Aviso!");
            alert.setHeaderText("Tem certeza de que deseja remover o produto: " + produtos.getNomeps() + "?");
            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK) {
             confirmaExclusao= pDAO.delete(produtos);
                if (!confirmaExclusao) {
                    Alert alerta = new Alert(AlertType.WARNING);

                    alerta.setTitle("Acesso negado");
                    alerta.setHeaderText("Produto nao pode ser removido ");
                    alerta.setContentText("");

                    alerta.showAndWait();

                } else {
                   carregaProdutos();
                }
               
            } else {
                carregaProdutos();
            }

        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Por favor, selecione um produto para esssa operação!");
            alert.show();
        }

    }

    @FXML
    void openAdicionar(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/view/FXML_Produto_P_I_cadastro.fxml"));
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
        carregaProdutos();

    }

    @FXML
    void openEditar(ActionEvent event) {
        try {
            ProdutosServicos produtoservico = tableViewProduto.getSelectionModel().getSelectedItem();
            if (produtoservico != null) {
                retornaprodutoservicosEditar = produtoservico;
                Parent root = FXMLLoader.load(getClass().getResource("/view/FXML_Produto_P_I_Editar.fxml"));
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
                carregaProdutos();

            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Por favor, selecione um produto para esssa operação!");
                alert.show();
            }
        } catch (IOException e) {
            System.out.println("ERRO: " + e.getMessage());

        }
    }

    @FXML
    void openCompra(ActionEvent event) throws IOException {

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        btnAdicionar.setDisable(usuarioVerifica);
        btnEditar.setDisable(usuarioVerifica);
        btnRemover.setDisable(usuarioVerifica);
        
        btnEditar.setDisable(true);
        btnRemover.setDisable(true);
        btnVisualizar.setDisable(true);

        carregaProdutos();

        inputProcurar.textProperty().addListener(new ChangeListener() {
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                procuararProduto((String) oldValue, (String) newValue);

            }
        });

    }

    @FXML
    private List<ProdutosServicos> listaTemp = new ArrayList<>();

    @FXML
    private ObservableList ProdutosServicosList;

    @FXML
    public void carregaProdutos() {
        try {

            ProdutosServicosDAO pDAO = new ProdutosServicosDAO();
            listaTemp = pDAO.lista();

            ProdutosServicosList = FXCollections.observableArrayList(listaTemp);
            clnNome.setCellValueFactory(new PropertyValueFactory<>("nomeps"));
            clnCodProdutos.setCellValueFactory(new PropertyValueFactory<>("codprod"));
            clnEstoque.setCellValueFactory(new PropertyValueFactory<>("estoqueps"));
            clnMarca.setCellValueFactory(new PropertyValueFactory<>("marcasprod"));
            clnPreco.setCellValueFactory(new PropertyValueFactory<>("precops"));
          //  clnDescriçao.setCellValueFactory(new PropertyValueFactory<>("descps"));
            clnUnidade.setCellValueFactory(new PropertyValueFactory<>("unidades"));

            tableViewProduto.setItems(ProdutosServicosList);

        } catch (Exception e) {
            System.out.println("ERRO:" + e);
        }
    }

    public void procuararProduto(String oldValue, String newValue) {
        ObservableList<ProdutosServicos> filtraList = FXCollections.observableArrayList();
        if (inputProcurar == null || (newValue.length() < oldValue.length()) || newValue == null) {
            tableViewProduto.setItems(ProdutosServicosList);
        } else {
            newValue = newValue.toUpperCase();
            for (ProdutosServicos produto : tableViewProduto.getItems()) {
                String filterFirstName = produto.getNomeps();
                String filterLastName = Integer.toString(produto.getCodprod());

                if (filterFirstName.toUpperCase().contains(newValue) || filterLastName.toUpperCase().contains(newValue)) {
                    filtraList.add(produto);
                }
            }
            tableViewProduto.setItems(filtraList);
        }
    }
 

    @FXML
    void ativa(MouseEvent event) {        
        btnEditar.setDisable(usuarioVerifica);
        btnRemover.setDisable(usuarioVerifica);
        btnVisualizar.setDisable(false);

    }
}

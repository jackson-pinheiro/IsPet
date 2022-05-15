/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import static controller.DashboardController.retornaFornecedorEditar;
import ProjetoPET.DAO.FornecedoresDAO;
import _models.Clientes;
import _models.Fornecedores;
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
import javafx.scene.control.cell.PropertyValueFactory;
import static controller.LoginController.usuarioVerifica;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author Jackson
 */
public class FXML_Fornecedores_P_IController implements Initializable {

    @FXML
    private JFXButton btnAdicionar;

    @FXML
    private JFXButton btnRemover;

    @FXML
    private JFXButton btnEditar;

    @FXML
    private JFXButton btnVisualizar;
    @FXML
    private TableView<Fornecedores> tabelaViem;

    @FXML
    private TableColumn<Fornecedores, Integer> clCod;

    @FXML
    private TableColumn<Fornecedores, String> clNome;

    @FXML
    private TableColumn<Fornecedores, String> clTelefone;

    @FXML
    private TableColumn<Fornecedores, String> clrCpfCnpj;

    @FXML
    private TableColumn<Fornecedores, String> clRg;
    @FXML
    private TextField btnProcurar;

    FornecedoresDAO fornecedorDAO = new FornecedoresDAO();

    @FXML

    void OpenRemover(ActionEvent event) {
        boolean confirmaExclusao;
        Fornecedores fornecedor = tabelaViem.getSelectionModel().getSelectedItem();
        if (fornecedor != null) {
            Alert alert = new Alert(AlertType.CONFIRMATION);
            alert.setTitle("Aviso");
            alert.setHeaderText("Tem Certeza que deseja remover o fornecedor: " + fornecedor.getNomefor());

            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK) {
                confirmaExclusao = fornecedorDAO.delete(fornecedor);
                if (!confirmaExclusao) {
                    Alert alerta = new Alert(AlertType.WARNING);

                    alerta.setTitle("Acesso negado");
                    alerta.setHeaderText("Fornecedor nao pode ser removido ");
                    alerta.setContentText("");

                    alerta.showAndWait();

                } else {
                    carregaFornecedor();
                }

            } else {

                carregaFornecedor();
            }

        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Por favor, escolha um cliente na Tabela!");
            alert.show();
        }

    }

    @FXML
    void openEditar(ActionEvent event) {
        try {
            Fornecedores fornecedor = tabelaViem.getSelectionModel().getSelectedItem();//Obtendo cliente selecionado
            if (fornecedor != null) {
                retornaFornecedorEditar = fornecedor;
                Parent root = FXMLLoader.load(getClass().getResource("/view/FXML_Fornecedores_P_S_Alterar.fxml"));
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

            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Por favor, escolha um cliente na Tabela!");
                alert.show();
            }
        } catch (IOException e) {
            System.out.println("Erro: " + e.getMessage());

        }
    }

    @FXML
    void openFornecedores(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/view/FXML_Fornecedores_P_S_Cadastrar.fxml"));
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

        } catch (IOException e) {
            System.out.println("Erro: " + e.getMessage());

        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        btnEditar.setDisable(true);
        btnRemover.setDisable(true);
        btnVisualizar.setDisable(true);
        btnAdicionar.setDisable(usuarioVerifica);
        btnEditar.setDisable(usuarioVerifica);
        btnRemover.setDisable(usuarioVerifica);
        carregaFornecedor();

        btnProcurar.textProperty().addListener(new ChangeListener() {
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                procuararFornecedor((String) oldValue, (String) newValue);

            }
        });

    }

    @FXML
    private List<Fornecedores> listaTemp = new ArrayList<>();

    @FXML
    private ObservableList fornecedoresList;

    @FXML
    public void carregaFornecedor() {
        try {

            FornecedoresDAO fornecodorDAO = new FornecedoresDAO();
            listaTemp = fornecodorDAO.lista();
            fornecedoresList = FXCollections.observableArrayList(listaTemp);

            clNome.setCellValueFactory(new PropertyValueFactory<>("nomefor"));
            clCod.setCellValueFactory(new PropertyValueFactory<>("codfor"));
            clTelefone.setCellValueFactory(new PropertyValueFactory<>("telfor"));
            clrCpfCnpj.setCellValueFactory(new PropertyValueFactory<>("cpfCnpjfor"));
            clRg.setCellValueFactory(new PropertyValueFactory<>("rgfor"));

            tabelaViem.setItems(fornecedoresList);

        } catch (Exception e) {
            System.out.println("ERRO :" + e);
        }
    }

    public void procuararFornecedor(String oldValue, String newValue) {
        ObservableList<Fornecedores> filtraList = FXCollections.observableArrayList();
        if (btnProcurar == null || (newValue.length() < oldValue.length()) || newValue == null) {
            tabelaViem.setItems(fornecedoresList);
        } else {
            newValue = newValue.toUpperCase();
            for (Fornecedores fornecedor : tabelaViem.getItems()) {
                String filterFirstName = fornecedor.getNomefor();
                String filterLastName = Integer.toString(fornecedor.getCodfor());
                if (filterFirstName.toUpperCase().contains(newValue) || filterLastName.toUpperCase().contains(newValue)) {
                    filtraList.add(fornecedor);
                }
            }
            tabelaViem.setItems(filtraList);
        }
    }

    @FXML
    void openVisualizar(ActionEvent event) throws IOException {
        Fornecedores fornecedor = tabelaViem.getSelectionModel().getSelectedItem();//Obtendo cliente selecionado
        if (fornecedor != null) {
            retornaFornecedorEditar = fornecedor;
            Parent root = FXMLLoader.load(getClass().getResource("/view/FXML_Fornecedores_P_S_Visualizar.fxml"));
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

        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Por favor, escolha um cliente na Tabela!");
            alert.show();
        }

    }

    @FXML
    void ativa(MouseEvent event) {
        btnEditar.setDisable(usuarioVerifica);
        btnRemover.setDisable(usuarioVerifica);
        btnVisualizar.setDisable(false);

    }
}

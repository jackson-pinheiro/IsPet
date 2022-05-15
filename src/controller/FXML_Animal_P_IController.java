/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import static controller.DashboardController.retornaAnimalEditar;
import ProjetoPET.DAO.AnimaisDAO;
import _models.Animais;
import _models.Clientes;
import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
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
public class FXML_Animal_P_IController implements Initializable {

    @FXML
    private JFXButton btnAddAnimal;

    @FXML
    private JFXButton btnRemoveAnimal;

    @FXML
    private JFXButton btnEditarAnimal;

    @FXML
    private TableView<Animais> tableViewAnimal;

    @FXML
    private TableColumn<Animais, Integer> clnCodAnimal;

    @FXML
    private TableColumn<Animais, String> clnNomeAnimal;

    @FXML
    private TableColumn<Animais, String> clnEspeAnimal;

    @FXML
    private TableColumn<Animais, String> clnApeliAnimal;

    @FXML
    private TableColumn<Animais, String> clnCorAnimal;

    @FXML
    private TableColumn<Animais, Date> clnDataAnimal;

    @FXML
    private TableColumn<Animais, Float> clnPesoAnimal;
    @FXML
    private TableColumn<Animais, Clientes> clnCliente;
    @FXML
    private JFXButton btnVisualizar;

    @FXML
    private TextField inputPesquisar;

    @FXML
    private TableColumn<Animais, String> clnsexo;

    AnimaisDAO aDAO = new AnimaisDAO();

    @FXML
    void openAddAnimal(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/view/FXML_Animal_P_S_Cadastrar.fxml"));
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
        carregaAnimal();

    }

    @FXML
    private List<Animais> listaTemp = new ArrayList<>();

    @FXML
    private ObservableList animalsList;

    @FXML
    void openEditarAnimal(ActionEvent event) throws IOException {
        try {
            Animais animal = tableViewAnimal.getSelectionModel().getSelectedItem();//Obtendo cliente selecionado
            if (animal != null) {
                retornaAnimalEditar = animal;
                Parent root = FXMLLoader.load(getClass().getResource("/view/FXML_Animal_P_S_Alterar.fxml"));
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
                carregaAnimal();

            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Por favor, escolha um Animal na Tabela!");
                alert.show();
            }
        } catch (IOException e) {
            System.out.println("Erro: " + e.getMessage());

        }
    }

    @FXML
    void openRemoveAminal(ActionEvent event) {
        boolean confirmaExclusao;
        Animais animal = tableViewAnimal.getSelectionModel().getSelectedItem();
        if (animal != null) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("AVISO");
            alert.setHeaderText("Tem certeza que deseja remover: " + animal.getNomeani());

            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK) {
                confirmaExclusao = aDAO.delete(animal);
                if (!confirmaExclusao) {
                    Alert alerta = new Alert(AlertType.WARNING);

                    alerta.setTitle("Acesso negado!");
                    alerta.setHeaderText("Animal não pode ser removido!");
                    alerta.setContentText("Animal relacionado a VENDAS.");

                    alerta.showAndWait();

                } else {
                    carregaAnimal();
                }
            } else {

                carregaAnimal();
            }

        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Por favor, escolha um cliente na Tabela!");
            alert.show();
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        btnEditarAnimal.setDisable(true);
        btnRemoveAnimal.setDisable(true);
        btnVisualizar.setDisable(true);
        carregaAnimal();

        inputPesquisar.textProperty().addListener(new ChangeListener() {
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                procuararAnimal((String) oldValue, (String) newValue);

            }
        });

        // TODO
    }

    @FXML
    public void carregaAnimal() {
        try {

            AnimaisDAO aDAO = new AnimaisDAO();

            listaTemp = aDAO.lista();

            animalsList = FXCollections.observableArrayList(listaTemp);
            clnNomeAnimal.setCellValueFactory(new PropertyValueFactory<>("nomeani"));
            clnApeliAnimal.setCellValueFactory(new PropertyValueFactory<>("apelidoani"));
            clnCodAnimal.setCellValueFactory(new PropertyValueFactory<>("codani"));
           // clnsexo.setCellValueFactory(new PropertyValueFactory<>("sexoani"));
            clnDataAnimal.setCellValueFactory(new PropertyValueFactory<>("datanasciani"));
            clnEspeAnimal.setCellValueFactory(new PropertyValueFactory<>("racas"));
            clnPesoAnimal.setCellValueFactory(new PropertyValueFactory<>("pesoani"));
            clnCorAnimal.setCellValueFactory(new PropertyValueFactory<>("cores"));
            clnCliente.setCellValueFactory(new PropertyValueFactory<>("clientes"));

            tableViewAnimal.setItems(animalsList);

        } catch (Exception e) {
            System.out.println("ERRO :" + e);
        }
    }

    public void procuararAnimal(String oldValue, String newValue) {
        ObservableList<Animais> filtraList = FXCollections.observableArrayList();
        if (inputPesquisar == null || (newValue.length() < oldValue.length()) || newValue == null) {
            tableViewAnimal.setItems(animalsList);
        } else {
            newValue = newValue.toUpperCase();
            for (Animais animais : tableViewAnimal.getItems()) {
                String filterFirstName = animais.getNomeani();
                String filterLastName = Integer.toString(animais.getCodani());
                if (filterFirstName.toUpperCase().contains(newValue) || filterLastName.toUpperCase().contains(newValue)) {
                    filtraList.add(animais);
                }
            }
            tableViewAnimal.setItems(filtraList);
        }
    }

    @FXML
    void openVisualizar(ActionEvent event) throws IOException {

        Animais animal = tableViewAnimal.getSelectionModel().getSelectedItem();//Obtendo cliente selecionado
        if (animal != null) {
            retornaAnimalEditar = animal;
            Parent root = FXMLLoader.load(getClass().getResource("/view/FXML_Animal_P_S_Visualizar.fxml"));
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
            carregaAnimal();

        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Por favor, escolha um Animal na Tabela!");
            alert.show();
        }

    }

    @FXML
    void ativa(MouseEvent event) {
        btnEditarAnimal.setDisable(false);
        btnRemoveAnimal.setDisable(false);
        btnVisualizar.setDisable(false);

    }

}

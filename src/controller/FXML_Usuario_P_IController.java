/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import ProjetoPET.DAO.UsuariosDAO;
import _models.Clientes;
import _models.TipoAcesso;
import _models.Usuarios;
import com.jfoenix.controls.JFXButton;
import static controller.DashboardController.retornaUsuariosEditar;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.effect.DropShadow;
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
public class FXML_Usuario_P_IController implements Initializable {

    @FXML
    private JFXButton btnAdicionarUsuario;

    @FXML
    private JFXButton btnRemover;

    @FXML
    private JFXButton btnEditarusuario;

    @FXML
    private TableView<Usuarios> tabelaViemUsuario;

    @FXML
    private TableColumn<Usuarios, Integer> clCod;

    @FXML
    private TableColumn<Usuarios, String> clNome;

    @FXML
    private TableColumn<Usuarios, String> clrCPF;

    @FXML
    private TableColumn<Usuarios, String> clrUsuario;

    @FXML
    private TableColumn<Usuarios, TipoAcesso> clTipoDeAcesso;

    @FXML
    private TextField btnProcurar;

    @FXML
    private JFXButton btnVisualizar;

    @FXML
    void OpenRemover(ActionEvent event) {

    }

    @FXML
    void openAdicionar(ActionEvent event) throws IOException {

        Region veil = new Region();

        Parent root = FXMLLoader.load(getClass().getResource("/view/FXML_Usuario_P_S_Cadastrar.fxml"));
        Scene scene = new Scene(root);
        Stage driverStage = new Stage();
        veil.setStyle("-fx-background-color:rgba(0,0,0,0.8)");
        scene.setFill(Color.TRANSPARENT);
        driverStage.setScene(scene);
        driverStage.initStyle(StageStyle.TRANSPARENT);
        driverStage.initModality(Modality.APPLICATION_MODAL);
        driverStage.setResizable(false);
        driverStage.getScene().getRoot().setEffect(new DropShadow());
        driverStage.centerOnScreen();
        driverStage.setX(202);
        driverStage.setY(38);
        driverStage.showAndWait();
        carregaUsuarios();

    }

    @FXML
    void openEditar(ActionEvent event) throws IOException {

        Usuarios usuario = tabelaViemUsuario.getSelectionModel().getSelectedItem();//Obtendo cliente selecionado
        if (usuario != null) {
            retornaUsuariosEditar = usuario;
            Parent root = FXMLLoader.load(getClass().getResource("/view/FXML_Usuario_P_S_Alterar.fxml"));
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
            carregaUsuarios();

        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Por favor, escolha um cliente na Tabela!");
            alert.show();

        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
                btnEditarusuario.setDisable(true);
        btnRemover.setDisable(true);
        btnVisualizar.setDisable(true);
        carregaUsuarios();

        btnProcurar.textProperty().addListener(new ChangeListener() {
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                procuararUsuario((String) oldValue, (String) newValue);
            }
        });
    }

    @FXML
    private List<Usuarios> listaTemp = new ArrayList<>();

    @FXML
    private ObservableList UsuariosList;

    @FXML
    public void carregaUsuarios() {
        try {

            UsuariosDAO uDAO = new UsuariosDAO();
            listaTemp = uDAO.lista();
            UsuariosList = FXCollections.observableArrayList(listaTemp);

            clNome.setCellValueFactory(new PropertyValueFactory<>("nomeuser"));
            clrCPF.setCellValueFactory(new PropertyValueFactory<>("cpfuser"));
            // clTipoDeAcesso.setCellValueFactory(new PropertyValueFactory<>("tipoAcesso"));
            clrUsuario.setCellValueFactory(new PropertyValueFactory<>("useruser"));
            clCod.setCellValueFactory(new PropertyValueFactory<>("coduser"));

            tabelaViemUsuario.setItems(UsuariosList);

        } catch (Exception e) {
            System.out.println("ERRO :" + e);
        }
    }

    public void procuararUsuario(String oldValue, String newValue) {

        ObservableList<Usuarios> filtraList = FXCollections.observableArrayList();
        if (btnProcurar == null || (newValue.length() < oldValue.length()) || newValue == null) {
            tabelaViemUsuario.setItems(UsuariosList);
        } else {
            newValue = newValue.toUpperCase();
            for (Usuarios usuario : tabelaViemUsuario.getItems()) {
                String filterFirstName = (usuario.getNomeuser());
                String filterLastName = Integer.toString(usuario.getCoduser());
                if (filterFirstName.toUpperCase().contains(newValue) || filterLastName.toUpperCase().contains(newValue)) {
                    filtraList.add(usuario);
                }

            }
            tabelaViemUsuario.setItems(filtraList);
        }
    }

    @FXML
    void openVisualizar(ActionEvent event) throws IOException {
             Usuarios usuario = tabelaViemUsuario.getSelectionModel().getSelectedItem();//Obtendo cliente selecionado
        if (usuario != null) {
            retornaUsuariosEditar = usuario;
            Parent root = FXMLLoader.load(getClass().getResource("/view/FXML_Usuario_P_S_Visualizar.fxml"));
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
            carregaUsuarios();

        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Por favor, escolha um cliente na Tabela!");
            alert.show();

        }

    }
    
    
    @FXML
    void ativa(MouseEvent event) {
             btnEditarusuario.setDisable(false);
        btnRemover.setDisable(false);
        btnVisualizar.setDisable(false);

    }
}

package controller;

import static controller.DashboardController.retornaClienteEditar;
import ProjetoPET.DAO.ClientesDAO;
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
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class FXML_CLiente_P_IController implements Initializable {

    @FXML
    private JFXButton btnAdicionarCliente;

    @FXML
    private JFXButton btnRemoverCliente;
    @FXML
    private TextField btnProcurarCliente;
    @FXML
    private JFXButton btnEditarCliente;

    @FXML
    private ToggleGroup filter;

    @FXML
    private TableView<Clientes> tabelaViemCliente;

    @FXML
    private TableColumn<Clientes, Integer> clCodCliente;
    @FXML
    private TableColumn<Clientes, String> clNome;

    @FXML
    private TableColumn<Clientes, String> clrCpfCnpj;

    @FXML
    private TableColumn<Clientes, String> clTelefone;

    @FXML
    private TableColumn<Clientes, String> clRg;
    @FXML
    private TableColumn<Clientes, String> clSexo;
    @FXML
    private TableColumn<Clientes, Date> clDataNacimento;
    @FXML
    private JFXButton btnVisualizar;

    @FXML
    ClientesDAO cDAO = new ClientesDAO();

    @FXML
    void OpenRemover(ActionEvent event) {
        boolean confirmaExclusao;
        Clientes cliente = tabelaViemCliente.getSelectionModel().getSelectedItem();
        if (cliente != null) {
            Alert alert = new Alert(AlertType.CONFIRMATION);
            alert.setTitle("Aviso");
            alert.setHeaderText("Tem Certeza Que deseja Remover o Cliente : " + cliente.getNomecli());
            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK) {
                confirmaExclusao = cDAO.delete(cliente);
                if (!confirmaExclusao) {
                    Alert alerta = new Alert(AlertType.WARNING);
                    alerta.setTitle("Acesso negado");
                    alerta.setHeaderText("Cliente nao pode ser removido ");
                    alerta.setContentText("");

                    alerta.showAndWait();

                } else {
                    carregaclientes();
                }
                carregaclientes();
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
            Clientes cliente = tabelaViemCliente.getSelectionModel().getSelectedItem();//Obtendo cliente selecionado
            if (cliente != null) {
                retornaClienteEditar = cliente;
                Parent root = FXMLLoader.load(getClass().getResource("/view/FXML_Cliente_P_S_alterar.fxml"));
                Stage stage = new Stage();
                Scene scene = new Scene(root);
                scene.setFill(Color.TRANSPARENT);
                stage.initStyle(StageStyle.TRANSPARENT);
                stage.initModality(Modality.APPLICATION_MODAL);
                stage.setResizable(false);
                stage.setScene(scene);
                stage.setX(202);
                stage.setY(38);
                stage.showAndWait();//esse comando aqui que faz esperar quando fechar ele executa o que ta embaixo
                carregaclientes();

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
    private void openCliente(ActionEvent event) {
        try {
            Region veil = new Region();

            Parent root = FXMLLoader.load(getClass().getResource("/view/FXML_Cliente_P_S_cadastrar.fxml"));
            Scene scene = new Scene(root);
            Stage driverStage = new Stage();
            veil.setStyle("-fx-background-color:rgba(0,0,0,0.3)");
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
            carregaclientes();
        } catch (IOException e) {
            System.out.println("Erro: " + e.getMessage());

        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        btnEditarCliente.setDisable(true);
        btnRemoverCliente.setDisable(true);
        btnVisualizar.setDisable(true);
        carregaclientes();

        btnProcurarCliente.textProperty().addListener(new ChangeListener() {
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                procuararCliente((String) oldValue, (String) newValue);
            }
        });

    }

    @FXML
    void openVisualizar(ActionEvent event) throws IOException {
        Clientes cliente = tabelaViemCliente.getSelectionModel().getSelectedItem();//Obtendo cliente selecionado
        if (cliente != null) {
            retornaClienteEditar = cliente;
            Parent root = FXMLLoader.load(getClass().getResource("/view/FXML_Cliente_P_S_Visualizar.fxml"));
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
            carregaclientes();

        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Por favor, escolha um cliente na Tabela!");
            alert.show();
        }
    }

    @FXML
    private List<Clientes> listaTemp = new ArrayList<>();
    @FXML
    private ObservableList clientesList;

    @FXML
    public void carregaclientes() {
        try {
            ClientesDAO cDAO = new ClientesDAO();
            listaTemp = cDAO.lista();
            clientesList = FXCollections.observableArrayList(listaTemp);

            clNome.setCellValueFactory(new PropertyValueFactory<>("nomecli"));
            clTelefone.setCellValueFactory(new PropertyValueFactory<>("telcli"));

            clrCpfCnpj.setCellValueFactory(new PropertyValueFactory<>("CpfCnpjcli"));

            clCodCliente.setCellValueFactory(new PropertyValueFactory<>("codcli"));
            clRg.setCellValueFactory(new PropertyValueFactory<>("rgcli"));
            clSexo.setCellValueFactory(new PropertyValueFactory<>("sexocli"));
            clDataNacimento.setCellValueFactory(new PropertyValueFactory<>("nasccli"));

            tabelaViemCliente.setItems(clientesList);

        } catch (Exception e) {
            System.out.println("ERRO :" + e);
        }
    }

    public void procuararCliente(String oldValue, String newValue) {

        ObservableList<Clientes> filtraList = FXCollections.observableArrayList();
        if (btnProcurarCliente == null || (newValue.length() < oldValue.length()) || newValue == null) {
            tabelaViemCliente.setItems(clientesList);
        } else {
            newValue = newValue.toUpperCase();
            for (Clientes cliente : tabelaViemCliente.getItems()) {
                String filterFirstName = (cliente.getNomecli());
                String filterLastName = Integer.toString(cliente.getCodcli());
                if (filterFirstName.toUpperCase().contains(newValue) || filterLastName.toUpperCase().contains(newValue)) {
                    filtraList.add(cliente);
                }

            }
            tabelaViemCliente.setItems(filtraList);
        }
    }

    @FXML
    void ativa(MouseEvent event) {
        btnEditarCliente.setDisable(false);
        btnRemoverCliente.setDisable(false);
        btnVisualizar.setDisable(false);

    }
}

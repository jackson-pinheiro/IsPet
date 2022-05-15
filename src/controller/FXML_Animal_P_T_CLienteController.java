/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import static controller.DashboardController.retornaClienteAnimal;
import ProjetoPET.DAO.ClientesDAO;
import _models.Clientes;
import com.jfoenix.controls.JFXButton;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import controller.FXML_Animal_P_S_CadastrarController;

/**
 * FXML Controller class
 *
 * @author Jackson
 */
public class FXML_Animal_P_T_CLienteController implements Initializable {

    FXML_Animal_P_S_CadastrarController carregar = new FXML_Animal_P_S_CadastrarController();

    @FXML
    private JFXButton btnAdicionarCliente;

    @FXML
    private JFXButton btnEditarCliente;

    @FXML
    private TableView<Clientes> tabelaViemCliente;

    @FXML
    private TableColumn<Clientes, Integer> clCodCliente;
    @FXML
    private TableColumn<Clientes, String> clNome;
    @FXML
    private TableColumn<Clientes, String> clTelefone;
       @FXML
    private TableColumn<Clientes, String> clrCpfCnpj;
    @FXML
    private TableColumn<Clientes, String> clRg;
    @FXML
    private TableColumn<Clientes, String> clSexo;
    @FXML
    private TableColumn<Clientes, Date> clDataNacimento;
    @FXML
    private TextField btnProcurarCliente;

    @FXML
    private JFXButton btnVoltarCliente;  
  
    ClientesDAO cDAO = new ClientesDAO();

    public Clientes getClienteSeliciando() {
        return retornaClienteAnimal;
    }

    @FXML
    void openVoltar(ActionEvent event) {
        btnVoltarCliente.getScene().getWindow().hide();

    }

    @FXML
    void openCliente(ActionEvent event) {
        Clientes cliente = tabelaViemCliente.getSelectionModel().getSelectedItem();
        if (cliente != null) {
            retornaClienteAnimal = cliente;

            btnAdicionarCliente.getScene().getWindow().hide();

        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Por favor, escolha um cliente na Tabela!");
            alert.show();
        }
    }

    // --------------
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        carregaclientes();

        btnProcurarCliente.textProperty().addListener(new ChangeListener() {
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                procuararCliente((String) oldValue, (String) newValue);

            }
        });

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
            clDataNacimento.setCellValueFactory(new PropertyValueFactory<>("nasccli"));
            clTelefone.setCellValueFactory(new PropertyValueFactory<>("telcli"));
            clRg.setCellValueFactory(new PropertyValueFactory<>("rgcli"));
            clSexo.setCellValueFactory(new PropertyValueFactory<>("sexocli"));
            clrCpfCnpj.setCellValueFactory(new PropertyValueFactory<>("CpfCnpjcli"));
            clCodCliente.setCellValueFactory(new PropertyValueFactory<>("codcli"));

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
                String filterFirstName = cliente.getNomecli();
                String filterLastName = Integer.toString(cliente.getCodcli());
                if (filterFirstName.toUpperCase().contains(newValue) || filterLastName.toUpperCase().contains(newValue)) {
                    filtraList.add(cliente);
                }
            }
            tabelaViemCliente.setItems(filtraList);
        }
    }

}

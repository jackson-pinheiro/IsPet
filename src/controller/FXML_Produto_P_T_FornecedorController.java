/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import static controller.DashboardController.retornaProdutoFornecedor;
import ProjetoPET.DAO.FornecedoresDAO;
import _models.Fornecedores;
import com.jfoenix.controls.JFXButton;
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
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author Jackson
 */
public class FXML_Produto_P_T_FornecedorController implements Initializable {

    @FXML
    private JFXButton btnAdicionarFornecedor;

    @FXML
    private TableView<Fornecedores> tabelaViemFornecedor;

    @FXML
    private TableColumn<Fornecedores, Integer> clCodFornecedor;

    @FXML
    private TableColumn<Fornecedores, String> clNomeFornecedor;

    @FXML
    private TableColumn<Fornecedores, String> clrCPFFornecedor;

    @FXML
    private TableColumn<Fornecedores, String> clTelefoneFornecedor;
    @FXML
    private TableColumn<Fornecedores, String> clRG;
    

    @FXML
    private TextField btnProcurarFornecedor;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        carregaFornecedor();

        btnProcurarFornecedor.textProperty().addListener(new ChangeListener() {
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                procuararFornecedor((String) oldValue, (String) newValue);

            }
        });
    }

    FornecedoresDAO fDAO = new FornecedoresDAO();

    @FXML
    void openCliente(ActionEvent event) {
    }

    @FXML
    void openFornecedor(ActionEvent event) {

        Fornecedores fornecedor = tabelaViemFornecedor.getSelectionModel().getSelectedItem();
        if (fornecedor != null) {
            retornaProdutoFornecedor = fornecedor;

            btnAdicionarFornecedor.getScene().getWindow().hide();

        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Por favor, escolha um cliente na Tabela!");
            alert.show();
        }
    }


    // --------------
    @FXML
    private List<Fornecedores> listaTemp = new ArrayList<>();

    @FXML
    private ObservableList fornecedorList;

    @FXML
    public void carregaFornecedor() {
        try {

            FornecedoresDAO fDAO = new FornecedoresDAO();

            listaTemp = fDAO.lista();

            fornecedorList = FXCollections.observableArrayList(listaTemp);

            clCodFornecedor.setCellValueFactory(new PropertyValueFactory<>("codfor"));
            clNomeFornecedor.setCellValueFactory(new PropertyValueFactory<>("nomefor"));
            clTelefoneFornecedor.setCellValueFactory(new PropertyValueFactory<>("telcli"));
            clrCPFFornecedor.setCellValueFactory(new PropertyValueFactory<>("cpfCnpjfor"));
            clRG.setCellValueFactory(new PropertyValueFactory<>("rgfor"));

            tabelaViemFornecedor.setItems(fornecedorList);

        } catch (Exception e) {
            System.out.println("ERRO :" + e);
        }
    }

    public void procuararFornecedor(String oldValue, String newValue) {
        ObservableList<Fornecedores> filtraList = FXCollections.observableArrayList();
        if (btnProcurarFornecedor == null || (newValue.length() < oldValue.length()) || newValue == null) {
            tabelaViemFornecedor.setItems(fornecedorList);
        } else {
            newValue = newValue.toUpperCase();
            for (Fornecedores fornecedor : tabelaViemFornecedor.getItems()) {
                String filterFirstName = fornecedor.getNomefor();
                String filterLastName = Integer.toString(fornecedor.getCodfor());

                if (filterFirstName.toUpperCase().contains(newValue) || filterLastName.toUpperCase().contains(newValue)) {
                    filtraList.add(fornecedor);
                }
            }
            tabelaViemFornecedor.setItems(filtraList);
        }
    }

    @FXML
    private JFXButton btnVoltaFornecedor;

    @FXML
    void openVolta(ActionEvent event) {

        btnVoltaFornecedor.getScene().getWindow().hide();

    }

    




   
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import ProjetoPET.DAO.VendasDAO;
import _models.Clientes;
import _models.Vendas;
import com.jfoenix.controls.JFXButton;
import java.io.IOException;
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
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
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
public class FXML_Vendas_P_IController implements Initializable {

    @FXML
    private TableView<Vendas> tableViewVendas;

    @FXML
    private TableColumn<Vendas, Integer> clnCodVenda;

    @FXML
    private TableColumn<Vendas, Date> clnDataVenda;

    @FXML
    private TableColumn<Vendas, Float> clTotalVenda;

    @FXML
    private TableColumn<Vendas, Clientes> clnClienteVenda;

    @FXML
    private TableColumn<Vendas, Date> clHoraDaVenda;

    @FXML
    private TextField inputProcurar;

    @FXML
    private JFXButton btnNovaVenda;

    @FXML
    private JFXButton btnAlterar;

    @FXML
    private JFXButton btnVisualizar;

    @FXML
    void openAlterar(ActionEvent event) {

    }

    @FXML
    void openNovaVenda(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/view/FXML_Vendas_P_S_Cadastrar.fxml"));
        Region veil = new Region();
        Stage stage = new Stage();
        Scene scene = new Scene(root);
        veil.setStyle("-fx-background-color:rgba(0,0,2,1.3)");
        scene.setFill(Color.TRANSPARENT);
        stage.initStyle(StageStyle.TRANSPARENT);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setResizable(false);
        stage.setX(202);
        stage.setY(38);
        stage.setScene(scene);
        stage.showAndWait();
        carregaVendas();

    }

    @FXML
    void openVisualizar(ActionEvent event) {
        

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        carregaVendas();

        inputProcurar.textProperty().addListener(new ChangeListener() {
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                procuararVendas((String) oldValue, (String) newValue);
            }
        });
    }

    @FXML
    private List<Vendas> listaTemp = new ArrayList<>();

    @FXML
    private ObservableList vendaList;

    @FXML
    public void carregaVendas() {
        try {

            VendasDAO vDAO = new VendasDAO();
            listaTemp = vDAO.lista();
            vendaList = FXCollections.observableArrayList(listaTemp);

            clnCodVenda.setCellValueFactory(new PropertyValueFactory<>("codvenda"));
            clnClienteVenda.setCellValueFactory(new PropertyValueFactory<>("clientes"));
            clTotalVenda.setCellValueFactory(new PropertyValueFactory<>("totalvenda"));
            clnDataVenda.setCellValueFactory(new PropertyValueFactory<>("datavenda"));
            clHoraDaVenda.setCellValueFactory(new PropertyValueFactory<>("horavenda"));

            tableViewVendas.setItems(vendaList);

        } catch (Exception e) {
            System.out.println("ERRO :" + e);
        }
    }

    public void procuararVendas(String oldValue, String newValue) {

        ObservableList<Vendas> filtraList = FXCollections.observableArrayList();
        if (inputProcurar == null || (newValue.length() < oldValue.length()) || newValue == null) {
            tableViewVendas.setItems(vendaList);
        } else {
            newValue = newValue.toUpperCase();
            for (Vendas venda : tableViewVendas.getItems()) {
                String filterFirstName = (venda.getClientes().getNomecli());
                String filterLastName = Integer.toString(venda.getCodvenda());
                if (filterFirstName.toUpperCase().contains(newValue) || filterLastName.toUpperCase().contains(newValue)) {
                    filtraList.add(venda);
                }

            }
            tableViewVendas.setItems(filtraList);
        }
    }

    @FXML
    void ativa(MouseEvent event) {

    }
}

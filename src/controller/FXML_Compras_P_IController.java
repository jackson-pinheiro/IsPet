/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import ProjetoPET.DAO.ComprasDAO;
import _models.Compras;
import _models.Fornecedores;
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
public class FXML_Compras_P_IController implements Initializable {

    @FXML
    private TableView<Compras> tableViewCompras;

    @FXML
    private TableColumn<Compras, Integer> clnCodCompras;

    @FXML
    private TableColumn<Compras, Fornecedores> clnFonecedorCompras;

    @FXML
    private TableColumn<Compras, Date> clnDataCompra;

    @FXML
    private TableColumn<Compras, Date> clHoraCompra;

    @FXML
    private TableColumn<Compras, Float> clTotalVenda;

    @FXML
    private TextField inputProcurar;

    @FXML
    private JFXButton btnNovaCompra;

    @FXML
    private JFXButton btnVisualizar;

    @FXML
    void openNovaCompra(ActionEvent event) throws IOException {
        Region veil = new Region();

        Parent root = FXMLLoader.load(getClass().getResource("/view/FXML_Compras_P_S_Cadastrar.fxml"));
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
        carregaCompras();

    }

    @FXML
    void openVisualizar(ActionEvent event) {

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        carregaCompras();
        
        
         inputProcurar.textProperty().addListener(new ChangeListener() {
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                procuararCompras((String) oldValue, (String) newValue);
            }
        });
    }

    @FXML
    private List<Compras> listaTemp = new ArrayList<>();

    @FXML
    private ObservableList compraList;

    @FXML
    public void carregaCompras() {
        try {

            ComprasDAO cDAO = new ComprasDAO();
            listaTemp = cDAO.lista();
            compraList = FXCollections.observableArrayList(listaTemp);

            clnCodCompras.setCellValueFactory(new PropertyValueFactory<>("codcompra"));
            clnFonecedorCompras.setCellValueFactory(new PropertyValueFactory<>("fornecedores"));
            clTotalVenda.setCellValueFactory(new PropertyValueFactory<>("totalcompra"));
            clnDataCompra.setCellValueFactory(new PropertyValueFactory<>("datacompra"));
            clHoraCompra.setCellValueFactory(new PropertyValueFactory<>("horacompra"));

            tableViewCompras.setItems(compraList);

        } catch (Exception e) {
            System.out.println("ERRO :" + e);
        }
    }
    
    
    
        public void procuararCompras(String oldValue, String newValue) {

        ObservableList<Compras> filtraList = FXCollections.observableArrayList();
        if (inputProcurar == null || (newValue.length() < oldValue.length()) || newValue == null) {
            tableViewCompras.setItems(compraList);
        } else {
            newValue = newValue.toUpperCase();
            for (Compras compra : tableViewCompras.getItems()) {
                String filterFirstName = (compra.getFornecedores().getNomefor());
                String filterLastName = Integer.toString(compra.getCodcompra());
                if (filterFirstName.toUpperCase().contains(newValue) || filterLastName.toUpperCase().contains(newValue)) {
                    filtraList.add(compra);
                }

            }
            tableViewCompras.setItems(filtraList);
        }
    }

        
    @FXML
    void ativa(MouseEvent event) {

    }
}

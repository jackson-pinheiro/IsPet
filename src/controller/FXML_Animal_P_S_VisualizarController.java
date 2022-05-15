/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import static CSS.DateConvertUtils.asLocalDate;
import _models.Animais;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import static controller.DashboardController.retornaAnimalEditar;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

/**
 * FXML Controller class
 *
 * @author Jackson
 */
public class FXML_Animal_P_S_VisualizarController implements Initializable {

    @FXML
    private JFXButton btnVoltar;

    @FXML
    private JFXTextField txtNomed;

    @FXML
    private JFXTextField txtCPFd;

    @FXML
    private JFXTextField txtRGd;

    @FXML
    private JFXTextField txtTelefoned;

    @FXML
    private JFXTextField txtSexod;

    @FXML
    private JFXDatePicker txtDated;

    @FXML
    private JFXTextField txtNomeA;

    @FXML
    private JFXTextField txtApelidoA;

    @FXML
    private JFXTextField txtPesoA;

    @FXML
    private JFXTextField txtEspecieA;

    @FXML
    private JFXTextField txtRacaA;

    @FXML
    private JFXTextField txtCorA;

    @FXML
    private JFXTextField txtSexoA;

    @FXML
    private JFXDatePicker txtDataA;

   
    @FXML
    private JFXTextField txtDescricaoA;

    @FXML
    void openVoltar(ActionEvent event) {
        btnVoltar.getScene().getWindow().hide();

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        carregarAnimais();
    }
    Animais editaAnimal = retornaAnimalEditar;

    public void carregarAnimais() {
        txtApelidoA.setText(editaAnimal.getApelidoani());
        txtNomeA.setText(editaAnimal.getNomeani());
        txtPesoA.setText(Float.toString(editaAnimal.getPesoani()));
        txtCorA.setText(editaAnimal.getCores().getNomecor());
        txtRacaA.setText(editaAnimal.getRacas().getNomeraca());
        txtEspecieA.setText(editaAnimal.getRacas().getEspecies().getNomeesp());
        txtDescricaoA.setText(editaAnimal.getDescani());
        txtSexoA.setText(editaAnimal.getSexoani());
        txtDataA.setValue(asLocalDate(editaAnimal.getDatanasciani()));

        txtNomed.setText(editaAnimal.getClientes().getNomecli());
        txtRGd.setText(editaAnimal.getClientes().getRgcli());
        txtCPFd.setText(editaAnimal.getClientes().getCpfCnpjcli());
        txtDated.setValue(asLocalDate(editaAnimal.getClientes().getNasccli()));
        txtSexod.setText(editaAnimal.getClientes().getSexocli());
        txtTelefoned.setText(editaAnimal.getClientes().getTelcli());

    }

}

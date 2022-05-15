/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import static controller.DashboardController.retornaUsuariosEditar;
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
public class FXML_Usuario_P_S_VisualizarController implements Initializable {

    @FXML
    private JFXButton btnVoltar;

    @FXML
    private JFXTextField txtNome;

    @FXML
    private JFXTextField txtCPF;

    @FXML
    private JFXTextField txtRG;

    @FXML
    private JFXTextField txtTelefone;

    @FXML
    private JFXTextField txtSexo;

    @FXML
    private JFXTextField txtUsuario;

    @FXML
    private JFXPasswordField txtSenha;

    @FXML
    private JFXPasswordField txtRSenha;

    @FXML
    void openVoltar(ActionEvent event) {
        btnVoltar.getScene().getWindow().hide();

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
       setaUsuarios();
    }    
        public void setaUsuarios() {
        txtCPF.setText(retornaUsuariosEditar.getCpfuser());
        txtNome.setText(retornaUsuariosEditar.getNomeuser());
        txtRG.setText(retornaUsuariosEditar.getRguser());
        txtRSenha.setText(retornaUsuariosEditar.getSenha());
        txtSenha.setText((retornaUsuariosEditar.getSenha()));
        txtUsuario.setText(retornaUsuariosEditar.getUseruser());
        txtSexo.setText(retornaUsuariosEditar.getUserusexo());
    
}
}

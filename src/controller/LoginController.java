/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import ProjetoPET.DAO.UsuariosDAO;
import _models.Usuarios;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.PauseTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

/**
 *
 * @author danml
 */
public class LoginController implements Initializable {

    private Label label;
    @FXML
    private JFXTextField txtUsername;
    @FXML
    private JFXPasswordField txtPassword;
    @FXML
    private JFXButton btnLogin;
    @FXML
    private StackPane rootPane;
    @FXML
    private ImageView imgProgress;

    public static boolean usuarioVerifica = true;
    public static Usuarios LogoCliente = new Usuarios();

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        imgProgress.setVisible(false);
        txtPassword.setOnKeyPressed(k -> {
            final KeyCombination ENTER = new KeyCodeCombination(KeyCode.ENTER);
            if (ENTER.match(k)) {

                imgProgress.setVisible(true);
                PauseTransition pauseTransition = new PauseTransition();
                pauseTransition.setDuration(Duration.seconds(3));
                pauseTransition.setOnFinished(ev -> {
                    Validar();
                });
             
                pauseTransition.play();
                txtPassword.requestFocus();

            }
        });

    }
    boolean verficarimagem = false;

    @FXML
    private void login(ActionEvent event) {
        imgProgress.setVisible(true);
        PauseTransition pauseTransition = new PauseTransition();
        pauseTransition.setDuration(Duration.seconds(3));
        pauseTransition.setOnFinished(ev -> {
            Validar();
        });
        pauseTransition.play();

    }

    private void Validar() {
        UsuariosDAO dao = new UsuariosDAO();
        Usuarios user = new Usuarios();
        user = dao.buscauser(txtPassword.getText(), txtUsername.getText());
        LogoCliente = user;
        if (user != null) {
            if (2 == user.getTipoAcesso().getCodacess()) {
                usuarioVerifica = true;
                completeLogin();
            }
            if (1 == user.getTipoAcesso().getCodacess()) {
                usuarioVerifica = false;
                completeLogin();

            }
        } else {
            Alert dialogoAviso = new Alert(Alert.AlertType.WARNING);
            dialogoAviso.setTitle("Aviso");
            dialogoAviso.setHeaderText("Usuario ou Senha Invalido");
            dialogoAviso.showAndWait();
            imgProgress.setVisible(false);
        }

    }

    private void completeLogin() {
        btnLogin.getScene().getWindow().hide();
        try {
            imgProgress.setVisible(false);
            Stage dashboardStage = new Stage();
            dashboardStage.setTitle("");
            Parent root = FXMLLoader.load(getClass().getResource("/view/Dashboard.fxml"));
            Scene scene = new Scene(root);
            scene.setFill(Color.TRANSPARENT);
            dashboardStage.initStyle(StageStyle.TRANSPARENT);
            dashboardStage.initModality(Modality.APPLICATION_MODAL);
            dashboardStage.setScene(scene);
            dashboardStage.setResizable(false);
            dashboardStage.setMaximized(true);
            dashboardStage.show();
        } catch (IOException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}

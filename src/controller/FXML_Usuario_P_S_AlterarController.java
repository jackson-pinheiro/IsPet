/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import static CSS.DateConvertUtils.asLocalDate;
import CSS.TextFieldFormatter;
import CSS.ValidationFields;
import ProjetoPET.DAO.TipoAcessoDAO;
import ProjetoPET.DAO.UsuariosDAO;
import _models.TipoAcesso;
import _models.Usuarios;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import static controller.DashboardController.retornaUsuariosEditar;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.KeyEvent;

/**
 * FXML Controller class
 *
 * @author Jackson
 */
public class FXML_Usuario_P_S_AlterarController implements Initializable {

    @FXML
    private TextField inputNome;

    @FXML
    private TextField inputRG;

    @FXML
    private TextField inputCPF;

    @FXML
    private TextField inputTelefone;

    @FXML
    private TextField inputUsuario;

    @FXML
    private JFXButton btnSalvar;

    @FXML
    private JFXButton btnLimpar;

    @FXML
    private JFXButton btnVoltar;

    @FXML
    private RadioButton inputSexoMasculino;

    @FXML
    private ToggleGroup SexoCliente;

    @FXML
    private RadioButton inputSexoFeminino;

    @FXML
    private JFXDatePicker inputDataNacimento;

    @FXML
    private PasswordField inputSenha;

    @FXML
    private PasswordField inputRsenha;

    @FXML
    private ComboBox<TipoAcesso> comboxAcesso;

    @FXML
    void openVoltar(ActionEvent event) {
        btnVoltar.getScene().getWindow().hide();
    }

    @FXML
    void opnelimpar(ActionEvent event) {

    }

    @FXML
    void opensalvar(ActionEvent event) {

        if (inputSenha.getText() == null ? inputRsenha.getText() != null : !inputSenha.getText().equals(inputRsenha.getText())) {

            inputRsenha.setText("");
        }

        boolean VerificaCampo = ValidationFields.checkEmptyFields(inputNome, inputRsenha, inputSenha, inputUsuario);
        Usuarios user = new Usuarios();
        UsuariosDAO uDAO = new UsuariosDAO();
        user.setNomeuser(inputNome.getText());
        user.setUseruser(inputUsuario.getText());
        user.setCpfuser(inputCPF.getText());
        user.setRguser(inputRG.getText());
        user.setSenha(inputSenha.getText());

        boolean sexoF = inputSexoFeminino.isSelected();
        boolean sexoM = inputSexoMasculino.isSelected();
        String sexoEscolhido = null;
        if (sexoF) {
            sexoEscolhido = "Feminino";
        }
        if (sexoM) {
            sexoEscolhido = "Masculino";
        }
        user.setUserusexo(sexoEscolhido);
        if (VerificaCampo == true) {

            uDAO.alterar(user);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Atualização de dados");
            alert.setHeaderText("Cadastro de usuario atualizado com sucesso");
            alert.setContentText("");
            alert.showAndWait();
            btnSalvar.getScene().getWindow().hide();
            user = new Usuarios();
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        setaUsuarios();

    }

    @FXML
    private void maskTel(KeyEvent event) {
        TextFieldFormatter tff = new TextFieldFormatter();
        tff.setMask("(##)####-#####");
        tff.setCaracteresValidos("0123456789");
        tff.setTf(inputTelefone);
        tff.formatter();
        tff.setTf(inputTelefone);
        tff.formatter();
    }

    @FXML
    private void maskCPF(KeyEvent event) {
        TextFieldFormatter tff = new TextFieldFormatter();
        tff.setMask("###.###.###-##");
        tff.setCaracteresValidos("0123456789");
        tff.setTf(inputCPF);
        tff.formatter();
    }

    private void resetForm() {
        this.inputNome.setText("");
        this.inputUsuario.setText("");
        this.inputCPF.setText("");
        this.inputRG.setText("");
        this.inputRsenha.setText("");
        this.inputSenha.setText("");
        this.inputTelefone.setText("");
        this.SexoCliente.selectToggle(null);
        this.comboxAcesso.setValue(null);
    }

    private ObservableList<TipoAcesso> listaAcesso;

    @FXML
    public void carregaAcesso() {

        TipoAcessoDAO tDAO = new TipoAcessoDAO();

        List<TipoAcesso> listaTemp = new ArrayList<>();

        listaTemp = tDAO.lista();

        listaAcesso = FXCollections.observableArrayList(listaTemp);

        comboxAcesso.setItems(listaAcesso);
    }

        public void setaUsuarios() {
        inputCPF.setText(retornaUsuariosEditar.getCpfuser());
        inputNome.setText(retornaUsuariosEditar.getNomeuser());
        inputRG.setText(retornaUsuariosEditar.getRguser());
        inputRsenha.setText(retornaUsuariosEditar.getSenha());
        inputSenha.setText((retornaUsuariosEditar.getSenha()));
        inputUsuario.setText(retornaUsuariosEditar.getUseruser());
        String sexoEscolhido = retornaUsuariosEditar.getUserusexo();
        if ("Masculino".equals(sexoEscolhido)) {
            inputSexoMasculino.setSelected(true);
        }
        if ("Feminino".equals(sexoEscolhido)) {
            inputSexoFeminino.setSelected(true);
        }

        retornaUsuariosEditar.setUserusexo(sexoEscolhido);

    }
    
}

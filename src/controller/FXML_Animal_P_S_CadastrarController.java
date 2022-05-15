/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import CSS.ValidationFields;
import static controller.DashboardController.retornaClienteAnimal;
import ProjetoPET.DAO.AnimaisDAO;
import ProjetoPET.DAO.CoresDAO;
import ProjetoPET.DAO.EspeciesDAO;
import ProjetoPET.DAO.RacasDAO;
import _models.Animais;
import _models.Clientes;
import _models.Cores;
import _models.Especies;
import _models.Racas;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
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
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author Jackson
 */
public class FXML_Animal_P_S_CadastrarController implements Initializable {

    @FXML
    private JFXDatePicker ImputData;
    @FXML
    private JFXButton btnAddCliente;
    @FXML
    private Text textMesagen;
    @FXML
    private ToggleGroup sexoAnimal;
    @FXML
    private JFXButton btnFechar;
    @FXML
    private JFXTextField txtCodigo;

    @FXML
    private JFXTextField txtNomeDono;

    @FXML
    private JFXTextField txtCpfCnpj;

    @FXML
    public TextField inputNome;

    @FXML
    private TextField inputApelido;

    @FXML
    private TextField inputPeso;

    @FXML
    private JFXButton btnSalvar;

    @FXML
    private JFXButton btnLimpar;
    @FXML
    private RadioButton inputMacho;

    @FXML
    private RadioButton InputFemea;

    @FXML
    private JFXButton btnVoltar;

    @FXML
    private ComboBox<Cores> comboxCor;

    @FXML
    private ComboBox<Racas> comboxRaça;

    @FXML
    private ComboBox<Especies> comboxEspecie;

    @FXML
    private JFXButton btnPropeitario;

    @FXML
    private TextArea inputDescriçao;

    private ObservableList<Especies> listaEspecie;

    private ObservableList<Racas> listaRaca;

    private ObservableList<Cores> listaCor;

    private ObservableList<Clientes> listaClient;

    @FXML
    void openFechar(ActionEvent event) {
        btnFechar.getScene().getWindow().hide();
    }

    @FXML
    void openVoltar(ActionEvent event) {

        btnVoltar.getScene().getWindow().hide();
    }

    @FXML
    void openPropietario(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/view/FXML_Animal_P_T_CLiente.fxml"));
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
        carregaNome();

    }

    @FXML
    void opensalvar(ActionEvent event) throws Exception {
        try {
            boolean VerificaCampo = ValidationFields.checkEmptyFields(comboxEspecie, comboxRaça, inputNome, txtCpfCnpj, inputPeso, txtNomeDono, txtCodigo);

            AnimaisDAO aDao = new AnimaisDAO();

            Animais animal = new Animais();

            animal.setNomeani(inputNome.getText());

            animal.setApelidoani(inputApelido.getText());

            animal.setDescani(inputDescriçao.getText());
            animal.setPesoani(Float.parseFloat(inputPeso.getText()));

            animal.setDatanasciani(java.sql.Date.valueOf(ImputData.getValue()));

            boolean sexoF = InputFemea.isSelected();
            boolean sexoM = inputMacho.isSelected();
            String sexoEscolhido = null;
            if (sexoF) {
                sexoEscolhido = "Femea";
            }
            if (sexoM) {
                sexoEscolhido = "Macho";
            }

            animal.setSexoani(sexoEscolhido);
            animal.setRacas(comboxRaça.getSelectionModel().getSelectedItem());
            animal.setCores(comboxCor.getSelectionModel().getSelectedItem());
            animal.setClientes(retornaClienteAnimal);
            if (VerificaCampo == true) {
                aDao.inserir(animal);
                Alert alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("Cadastro");
                alert.setHeaderText("Cadastro de animal realizado com sucesso");
                alert.setContentText("");
                alert.showAndWait();
                btnSalvar.getScene().getWindow().hide();
            }

        } catch (Exception e) {

        }
    }

    @FXML
    void opnelimpar(ActionEvent event) {
        resetForm();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        carregaEspecie();
        carregaCor();

    }

    @FXML
    public void carregaEspecie() {
        EspeciesDAO eDAO = new EspeciesDAO();
        List<Especies> listaTemp = new ArrayList<>();
        listaTemp = eDAO.lista();
        listaEspecie = FXCollections.observableArrayList(listaTemp);
        comboxEspecie.setItems(listaEspecie);
    }

    @FXML
    public void carregaRaça(MouseEvent event) {

        Especies e = new Especies();
        e = comboxEspecie.getSelectionModel().getSelectedItem();
        RacasDAO rDAO = new RacasDAO();
        List<Racas> listaTemp = new ArrayList<>();
        listaTemp = rDAO.listaRacaPorEspecie(e);
        for (Racas r : listaTemp) {
            System.out.println(r.getNomeraca());
        }
        listaRaca = FXCollections.observableArrayList(listaTemp);
        comboxRaça.setItems(listaRaca);
    }

    @FXML
    public void carregaCor() {

        CoresDAO cDAO = new CoresDAO();

        List<Cores> listaTemp = new ArrayList<>();

        listaTemp = cDAO.lista();

        listaCor = FXCollections.observableArrayList(listaTemp);

        comboxCor.setItems(listaCor);
    }

    private void resetForm() {

        this.inputNome.setText("");
        this.inputApelido.setText("");
        this.inputDescriçao.setText("");
        this.sexoAnimal.selectToggle(null);
        this.inputPeso.setText("");
        this.comboxRaça.setValue(null);
        this.comboxCor.setValue(null);
        this.comboxEspecie.setValue(null);
        this.ImputData.setValue(null);
        this.txtCodigo.setText("");
        this.txtNomeDono.setText("");
        this.txtCpfCnpj.setText("");

    }

    public void carregaNome() {

        txtNomeDono.setText(retornaClienteAnimal.getNomecli());
        txtCpfCnpj.setText(retornaClienteAnimal.getCpfCnpjcli());
        txtCodigo.setText(Integer.toString(retornaClienteAnimal.getCodcli()));

    }

}

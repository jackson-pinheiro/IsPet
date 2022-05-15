/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import static CSS.DateConvertUtils.asLocalDate;
import CSS.ValidationFields;
import static controller.DashboardController.retornaAnimalEditar;
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
import static controller.DashboardController.retornaClienteAnimal;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
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
public class FXML_Animal_P_S_AlterarController implements Initializable {

    @FXML
    private JFXDatePicker ImputData;
    @FXML

    private JFXButton btnFechar;

    @FXML
    private ToggleGroup sexoAnimal;
   @FXML
    private JFXTextField txtCodigo;

    @FXML
    private JFXTextField txtNomeDono;

    @FXML
    private JFXTextField txtCpfCnpj;
    @FXML
    private Text varTxt;

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
    private ComboBox<Clientes> comboxDono;

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
        stage.showAndWait();
        carregaNome();

    }

    @FXML
    void opensalvar(ActionEvent event) {
        boolean VerificaCampo = ValidationFields.checkEmptyFields(comboxEspecie, comboxRaça, inputNome, inputPeso);

        AnimaisDAO aDao = new AnimaisDAO();

     

        editaAnimal.setNomeani(inputNome.getText());

        editaAnimal.setApelidoani(inputApelido.getText());

        editaAnimal.setDescani(inputDescriçao.getText());
        editaAnimal.setPesoani(Float.parseFloat(inputPeso.getText()));

        editaAnimal.setDatanasciani(java.sql.Date.valueOf(ImputData.getValue()));
        boolean sexoF = InputFemea.isSelected();
        boolean sexoM = inputMacho.isSelected();
        String sexoEscolhido = null;
        if (sexoF) {
            sexoEscolhido = "Femea";
        }
        if (sexoM) {
            sexoEscolhido = "Macho";
        }
        editaAnimal.setSexoani(sexoEscolhido);

        editaAnimal.setRacas(comboxRaça.getSelectionModel().getSelectedItem());
        editaAnimal.setCores(comboxCor.getSelectionModel().getSelectedItem());
        if (VerificaCampo == true) {
            aDao.alterar(editaAnimal);
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Atualização de dados");
                alert.setHeaderText("Cadastro de animal atualizado com sucesso");
                alert.setContentText("");
                alert.showAndWait();
            btnSalvar.getScene().getWindow().hide();
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        carregaEspecie();
        carregaCor();
        carregarAnimais();
        carregaNome();

        // TODO
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

    Animais editaAnimal = retornaAnimalEditar;
    AnimaisDAO cDao = new AnimaisDAO();

    public void carregarAnimais() {
        inputApelido.setText(editaAnimal.getApelidoani());
        inputNome.setText(editaAnimal.getNomeani());
        inputPeso.setText(Float.toString(editaAnimal.getPesoani()));
        comboxCor.setValue(editaAnimal.getCores());
        comboxRaça.setValue(editaAnimal.getRacas());
       comboxEspecie.setValue(editaAnimal.getRacas().getEspecies());
        inputDescriçao.setText(editaAnimal.getDescani());
       

        String sexoEscolhido = editaAnimal.getSexoani();
        if ("Macho".equals(sexoEscolhido)) {
            inputMacho.setSelected(true);
        }
        if ("Femea".equals(sexoEscolhido)) {
            InputFemea.setSelected(true);
        }        
        editaAnimal.setSexoani(sexoEscolhido);
        ImputData.setValue(asLocalDate(editaAnimal.getDatanasciani()));

    }

    public void carregaNome() {
        txtNomeDono.setText(editaAnimal.getClientes().getNomecli());
        txtCpfCnpj.setText(editaAnimal.getClientes().getCpfCnpjcli());
        txtCodigo.setText(Integer.toString(editaAnimal.getClientes().getCodcli()));

    }

    


 



}

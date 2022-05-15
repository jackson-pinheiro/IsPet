/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import static CSS.DateConvertUtils.asLocalDate;
import ProjetoPET.DAO.EnderecoDAO;
import _models.Clientes;
import _models.Endereco;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import static controller.DashboardController.retornaClienteEditar;
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
public class FXML_Cliente_P_S_VisualizarController implements Initializable {

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
    private JFXTextField txtRua;

    @FXML
    private JFXTextField txtBairro;

    @FXML
    private JFXTextField txtNumero;

    @FXML
    private JFXTextField txtEstado;

    @FXML
    private JFXTextField txtCidade;

    @FXML
    private JFXTextField txtComplemento;

    @FXML
    private JFXDatePicker txtDate;

    @FXML
    private JFXButton btnVoltar;

    @FXML
    void openVoltar(ActionEvent event) {
        btnVoltar.getScene().getWindow().hide();

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        CarregaCliente();
    }

    Clientes edita = retornaClienteEditar;
    EnderecoDAO eDao = new EnderecoDAO();
    Endereco ed = eDao.buscarEd(edita);

    public void CarregaCliente() {
        txtCPF.setText(edita.getCpfCnpjcli());
        txtNome.setText(edita.getNomecli());
        txtRG.setText(edita.getRgcli());
        txtTelefone.setText(edita.getTelcli());
        txtNumero.setText((ed.getNumed()));
        txtRua.setText(ed.getRuaed());
        txtBairro.setText((ed.getBairroed()));
        txtComplemento.setText(ed.getComped());
        txtDate.setValue(asLocalDate(edita.getNasccli()));
        txtCidade.setText(ed.getCidades().getNomecid());
        txtEstado.setText(ed.getCidades().getEstados().getSiglaest());
        txtSexo.setText(edita.getSexocli());

    }
}

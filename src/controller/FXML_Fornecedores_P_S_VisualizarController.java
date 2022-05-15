/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import ProjetoPET.DAO.EnderecoDAO;
import _models.Endereco;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import static controller.DashboardController.retornaFornecedorEditar;
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
public class FXML_Fornecedores_P_S_VisualizarController implements Initializable {

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
    void openVoltar(ActionEvent event) {

        btnVoltar.getScene().getWindow().hide();

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        setaFornecedores();
    }

    EnderecoDAO eDao = new EnderecoDAO();
    Endereco ed = eDao.buscarEdFornecedor(retornaFornecedorEditar);

    public void setaFornecedores() {
        txtCPF.setText(retornaFornecedorEditar.getCpfCnpjfor());
        txtNome.setText(retornaFornecedorEditar.getNomefor());
        txtRG.setText(retornaFornecedorEditar.getRgfor());
        txtTelefone.setText(retornaFornecedorEditar.getTelfor());
        txtNumero.setText((ed.getNumed()));
        txtRua.setText(ed.getRuaed());
        txtBairro.setText((ed.getBairroed()));
        txtComplemento.setText(ed.getComped());
        txtCidade.setText(ed.getCidades().getNomecid());
        txtEstado.setText(ed.getCidades().getEstados().getSiglaest());

    }
}

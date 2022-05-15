/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import _models.ProdutosServicos;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import static controller.DashboardController.retornaprodutoservicosEditar;
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
public class FXML_Produtos_P_S_VisualizarController implements Initializable {

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
    private JFXTextField txtNomeP;

    @FXML
    private JFXTextField txtEstoque;

    @FXML
    private JFXTextField txtMarca;

    @FXML
    private JFXTextField txtPreco;

    @FXML
    private JFXTextField txtDescri;

    @FXML
    private JFXTextField txtUnidade;

    @FXML
    void openVoltar(ActionEvent event) {
        btnVoltar.getScene().getWindow().hide();

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        if (retornaprodutoservicosEditar.getMarcasprod() == null) {

            CarregarServico();

        } else {
            CarregarProduto();
        }
    }

    ProdutosServicos produtos = retornaprodutoservicosEditar;

    public void CarregarProduto() {

        txtNomeP.setText(produtos.getNomeps());
        txtUnidade.setText(produtos.getUnidades().getTipouni());
        txtDescri.setText(produtos.getDescps());
        txtMarca.setText(produtos.getMarcasprod());
        txtEstoque.setText(Float.toString(produtos.getEstoqueps()));
        txtPreco.setText(Float.toString(produtos.getPrecops()));
        /*
        txtNome.setText(produtos.getFornecedores().getNomefor());
        txtCPF.setText(produtos.getFornecedores().getCpfCnpjfor());
        txtRG.setText(produtos.getFornecedores().getRgfor());
        txtTelefone.setText(produtos.getFornecedores().getTelfor());*/
    }

    public void CarregarServico() {

        txtNomeP.setText(produtos.getNomeps());
        txtDescri.setText(produtos.getDescps());
        txtPreco.setText(Float.toString(produtos.getPrecops()));

        txtMarca.setDisable(true);
        txtEstoque.setDisable(true);
        txtUnidade.setDisable(true);
        txtNome.setDisable(true);
        txtCPF.setDisable(true);
        txtRG.setDisable(true);
        txtTelefone.setDisable(true);
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import _models.Animais;
import _models.Clientes;
import _models.Endereco;
import _models.Fornecedores;
import _models.ProdutosServicos;
import _models.Usuarios;
import static antlr.build.ANTLR.root;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPopup;
import com.jfoenix.controls.JFXRippler;
import static controller.LoginController.LogoCliente;
import static controller.LoginController.usuarioVerifica;
import static java.awt.Color.red;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.FadeTransition;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import static javafx.scene.paint.Color.RED;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;
import javax.swing.plaf.ColorUIResource;

/**
 * FXML Controller class
 *
 * @author Jackson
 */
public class DashboardController implements Initializable {

    @FXML
    private Text txt_Funçao;
    @FXML
    private Label lblDash;
    @FXML
    public StackPane stackPane;
    @FXML
    private AnchorPane holderPane;
    @FXML
    private AnchorPane sideAnchor;
    @FXML
    private Label lblMenu;
    @FXML
    private HBox toolBarRight;
    @FXML
    private VBox overflowContainer;

    @FXML
    private JFXButton btnFornecedores;

    @FXML
    private JFXButton btnAnimal;

    @FXML
    private JFXButton btnProdutos;

    @FXML
    private JFXButton btnClose;

    @FXML
    private JFXButton btnCliente;

    @FXML
    private JFXButton btnVendas;

    @FXML
    private JFXButton btnHome3;

    @FXML
    private JFXButton btnUsuario;

    @FXML
    private JFXButton btnLogOut;

    @FXML
    private JFXButton btnCompras;

    @FXML
    private Text txt_usuario;
    @FXML
    private ImageView imagemMasculina;

    @FXML
    private ImageView imagenFemina;

    @FXML
    private AnchorPane home, Servico, Cliente_menu, Usuario, produto, Vendas_P_I, Animal_P_I, fornecedores, compras;

    public static Clientes retornaClienteEditar = new Clientes();
    public static Endereco retornaEnderecoEditar = new Endereco();
    public static Clientes retornaClienteAnimal = new Clientes();
    public static Animais retornaAnimalEditar = new Animais();
    public static Fornecedores retornaFornecedorEditar = new Fornecedores();
    public static Fornecedores retornaProdutoFornecedor = new Fornecedores();
    public static ProdutosServicos retornaprodutoservicosEditar = new ProdutosServicos();
    public static Usuarios retornaUsuariosEditar = new Usuarios();

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        Userlogo();

        btnUsuario.setDisable(usuarioVerifica);
        btnCompras.setDisable(usuarioVerifica);
        JFXRippler fXRippler = new JFXRippler(lblDash);
        JFXRippler fXRippler2 = new JFXRippler(lblMenu);
        fXRippler2.setMaskType((JFXRippler.RipplerMask.RECT));
        sideAnchor.getChildren().add(fXRippler);
        toolBarRight.getChildren().add(fXRippler2);

        createPages();

    }

    private void setNode(Node node) {
        holderPane.getChildren().clear();
        holderPane.getChildren().add((Node) node);
        FadeTransition ft = new FadeTransition(Duration.millis(1500));
        ft.setNode(node);

        ft.setFromValue(0.1);
        ft.setToValue(1);
        ft.setCycleCount(1);
        ft.setAutoReverse(false);
        ft.play();
    }

    private void createPages() {
        try {

            //   Vendas_P_I = FXMLLoader.load(getClass().getResource("/view/FXML_Vendas_P_I.fxml"));
            home = FXMLLoader.load(getClass().getResource("/view/FXML_Homer.fxml"));
            //   Cliente_menu = FXMLLoader.load(getClass().getResource("/view/FXML_CLiente_P_I.fxml"));
            //   Servico = FXMLLoader.load(getClass().getResource("/view/FXML_Vendas_P_I.fxml"));
            //   Animal_P_I = FXMLLoader.load(getClass().getResource("/view/FXML_Animal_P_I.fxml"));
            //  produto = FXMLLoader.load(getClass().getResource("/view/FXML_Produto_P_I.fxml"));
            //  fornecedores = FXMLLoader.load(getClass().getResource("/view/FXML_Fornecedores_P_I.fxml"));
            // Usuario = FXMLLoader.load(getClass().getResource("/view/FXML_Usuario_P_I.fxml"));
            // compras = FXMLLoader.load(getClass().getResource("/view/FXML_Compras_P_I.fxml"));
            setNode(home);

        } catch (IOException ex) {
            Logger.getLogger(DashboardController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    void openCliente(ActionEvent event) throws IOException {
        Parent Cliente_menu = FXMLLoader.load(getClass().getResource("/view/FXML_CLiente_P_I.fxml"));
        holderPane.getChildren().removeAll();
        holderPane.getChildren().setAll(Cliente_menu);

    }

    @FXML
    void openNotaServico(ActionEvent event) throws IOException {
        Parent Servico = FXMLLoader.load(getClass().getResource("/view/FXML_Homer.fxml"));
        holderPane.getChildren().removeAll();
        holderPane.getChildren().setAll(Servico);

    }

    public void homer3() throws IOException {
     
    }

    @FXML
    private void openHome(ActionEvent event) throws IOException {

        Parent Servico = FXMLLoader.load(getClass().getResource("/view/FXML_Homer.fxml"));
        holderPane.getChildren().removeAll();
        holderPane.getChildren().setAll(home);

    }

    @FXML
    private void logOff(ActionEvent event) throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("/view/Login.fxml"));
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
        btnLogOut.getScene().getWindow().hide();

    }

    @FXML
    void openProdutos(ActionEvent event) throws IOException {
        Parent produto = FXMLLoader.load(getClass().getResource("/view/FXML_Produto_P_I.fxml"));

        holderPane.getChildren().removeAll();
        holderPane.getChildren().setAll(produto);

    }

    @FXML
    void openVendas(ActionEvent event) throws IOException {
        Parent Vendas_P_I = FXMLLoader.load(getClass().getResource("/view/FXML_Vendas_P_I.fxml"));

        holderPane.getChildren().removeAll();
        holderPane.getChildren().setAll(Vendas_P_I);

    }

    @FXML
    void openAnimal(ActionEvent event) throws IOException {
        Parent Animal_P_I = FXMLLoader.load(getClass().getResource("/view/FXML_Animal_P_I.fxml"));

        holderPane.getChildren().removeAll();
        holderPane.getChildren().setAll(Animal_P_I);

    }

    @FXML
    void openFornecedores(ActionEvent event) throws IOException {
        Parent fornecedores = FXMLLoader.load(getClass().getResource("/view/FXML_Fornecedores_P_I.fxml"));

        holderPane.getChildren().removeAll();
        holderPane.getChildren().setAll(fornecedores);

    }

    @FXML
    void openUsuario(ActionEvent event) throws IOException {
        Parent Usuario = FXMLLoader.load(getClass().getResource("/view/FXML_Usuario_P_I.fxml"));

        holderPane.getChildren().removeAll();
        holderPane.getChildren().setAll(Usuario);

    }

    @FXML
    void openCompras(ActionEvent event) throws IOException {
        Parent compras = FXMLLoader.load(getClass().getResource("/view/FXML_Compras_P_I.fxml"));

        holderPane.getChildren().removeAll();
        holderPane.getChildren().setAll(compras);
    }

    @FXML
    private void exit(ActionEvent event) {
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Sair");
        alert.setHeaderText("");
        alert.setContentText("Tem certeza de que deseja sair?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            Platform.exit();
            System.exit(0);
        } else {

        }
    }

    public void Userlogo() {
        String sexoEscolhido = LogoCliente.getUserusexo();
        if ("Masculino".equals(sexoEscolhido)) {
            imagemMasculina.setVisible(true);
            txt_usuario.setText(LogoCliente.getNomeuser());
        }

        if ("Feminino".equals(sexoEscolhido)) {
            txt_usuario.setText(LogoCliente.getNomeuser());
            imagenFemina.setVisible(true);
        }

        if (LogoCliente.getTipoAcesso().getCodacess() == 1) {
            txt_Funçao.setText("Administrador");
        }
        if (LogoCliente.getTipoAcesso().getCodacess() == 2) {
            txt_Funçao.setText("Usuario");
        }

    }
}

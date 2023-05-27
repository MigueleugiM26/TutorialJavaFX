package tutorial.tutorial;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import org.controlsfx.control.action.Action;

import java.util.List;
import java.util.Map;

public class controller {

    @FXML
    private Button btnConsultar;

    @FXML
    private Button btnCriar;

    @FXML
    private Button btnExcluir;

    @FXML
    private Button btnInserir;

    @FXML
    private Button btnRemover;

    @FXML
    private Button btnVoltar;

    @FXML
    private AnchorPane consultarAnchor;

    @FXML
    private AnchorPane criarAnchor;

    @FXML
    private AnchorPane excluirAnchor;

    @FXML
    private Label lblAno;

    @FXML
    private Label lblDVDNaoEncontrado;

    @FXML
    private Label lblDuracao;

    @FXML
    private Label lblGenero;

    @FXML
    private Label lblNome;

    @FXML
    private VBox submenuDVD;

    @FXML
    private VBox submenuLista;

    @FXML
    private TextField txtInputAno;

    @FXML
    private TextField txtInputDuracao;

    @FXML
    private TextField txtInputGenero;

    @FXML
    private TextField txtInputNome;

    @FXML
    private TextField txtInputRemoveNome;

    @FXML
    private ListView<Button> consulta;


    @FXML
    private void handleClick (ActionEvent event) {
        if (event.getSource() == btnCriar) {
            criarAnchor.setVisible(true);
            excluirAnchor.setVisible(false);
            consultarAnchor.setVisible(false);

            if (btnCriar.getStyleClass().contains("buttonMenu")) {
                btnCriar.getStyleClass().remove("buttonMenu");
                btnCriar.getStyleClass().add("buttonSelected");
                btnConsultar.getStyleClass().remove("buttonSelected");
                btnConsultar.getStyleClass().add("buttonMenu");
                btnExcluir.getStyleClass().remove("buttonSelected");
                btnExcluir.getStyleClass().add("buttonMenu");
            }
        }
        else if (event.getSource() == btnConsultar) {
            Map<String, main.DVD> lista = main.DVD.getListaDVDs();

            if (lista != null) {
                consulta.getItems().clear();

                for (Map.Entry<String, main.DVD> entry : lista.entrySet()) {
                    String nome = entry.getKey();

                    Button button = new Button();
                    button.setText(nome);
                    button.getStyleClass().add("buttonMenu");
                    button.setStyle("-fx-font-size : 20px;");
                    button.setOnAction(event2 -> handleButtons(event2, nome));
                    consulta.getItems().add(button);
                }
            }

            criarAnchor.setVisible(false);
            excluirAnchor.setVisible(false);
            consultarAnchor.setVisible(true);

            submenuLista.setVisible(true);
            submenuDVD.setVisible(false);

            if (btnConsultar.getStyleClass().contains("buttonMenu")) {
                btnCriar.getStyleClass().remove("buttonSelected");
                btnCriar.getStyleClass().add("buttonMenu");
                btnConsultar.getStyleClass().remove("buttonMenu");
                btnConsultar.getStyleClass().add("buttonSelected");
                btnExcluir.getStyleClass().remove("buttonSelected");
                btnExcluir.getStyleClass().add("buttonMenu");
            }
        }
        else if (event.getSource() == btnExcluir) {
            criarAnchor.setVisible(false);
            excluirAnchor.setVisible(true);
            consultarAnchor.setVisible(false);
            lblDVDNaoEncontrado.setVisible(false);

            if (btnExcluir.getStyleClass().contains("buttonMenu")) {
                btnCriar.getStyleClass().remove("buttonSelected");
                btnCriar.getStyleClass().add("buttonMenu");
                btnConsultar.getStyleClass().remove("buttonSelected");
                btnConsultar.getStyleClass().add("buttonMenu");
                btnExcluir.getStyleClass().remove("buttonMenu");
                btnExcluir.getStyleClass().add("buttonSelected");
            }
        }
        else if (event.getSource() == btnInserir) {
            if (!txtInputNome.getText().isEmpty() && !txtInputNome.getText().isBlank()) {
                String nome = txtInputNome.getText();
                String lancamento = txtInputAno.getText();
                String duracao = txtInputDuracao.getText();
                String genero = txtInputGenero.getText();

                main.DVD dvd = new main.DVD (nome, lancamento, duracao, genero);

                txtInputNome.clear();
                txtInputAno.clear();
                txtInputDuracao.clear();
                txtInputGenero.clear();
            }
        }
        else if (event.getSource() == btnRemover) {
            String nome = txtInputRemoveNome.getText();
            Map<String, main.DVD> lista = main.DVD.getListaDVDs();

            if (lista.containsKey(nome)) {
                main.DVD.removerDVD(nome);
                lblDVDNaoEncontrado.setVisible(false);
                txtInputRemoveNome.clear();
            }
            else {
                lblDVDNaoEncontrado.setVisible(true);
                txtInputRemoveNome.clear();
            }
        }
        else if (event.getSource() == btnVoltar) {
            Map<String, main.DVD> lista = main.DVD.getListaDVDs();

            if (lista != null) {
                consulta.getItems().clear();

                for (Map.Entry<String, main.DVD> entry : lista.entrySet()) {
                    String nome = entry.getKey();

                    Button button = new Button();
                    button.setText(nome);
                    button.getStyleClass().add("buttonMenu");
                    button.setStyle("-fx-font-size : 20px;");
                    button.setOnAction(event2 -> handleButtons(event2, nome));
                    consulta.getItems().add(button);
                }
            }

            submenuLista.setVisible(true);
            submenuDVD.setVisible(false);
        }
    }

    @FXML
    private void handleButtons (ActionEvent event, String nome) {
        Map<String, main.DVD> lista = main.DVD.getListaDVDs();
        main.DVD dvd = lista.get(nome);

        lblNome.setText("Nome: " + dvd.getNome());
        lblAno.setText("Ano de Lançamento: " + dvd.getLancamento());
        lblDuracao.setText("Duração: " + dvd.getDuracao());
        lblGenero.setText("Gênero: " + dvd.getGenero());

        submenuLista.setVisible(false);
        submenuDVD.setVisible(true);
    }
}

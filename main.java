package tutorial.tutorial;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class main extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(main.class.getResource("main.fxml"));

        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Tutorial");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

    public static class DVD {
        public static Map<String, DVD> listaDVDs = new HashMap<>();

        String nome;
        String lancamento;
        String duracao;
        String genero;

        public DVD (String nome, String lancamento, String duracao, String genero) {
            this.nome = nome;
            this.lancamento = lancamento;
            this.duracao = duracao;
            this.genero = genero;

            listaDVDs.put(nome, this);
        }

        public String getNome () { return this.nome; }
        public String getLancamento () { return this.lancamento; }
        public String getDuracao () { return this.duracao; }
        public String getGenero () { return this.genero; }
        public static Map<String, DVD> getListaDVDs() { return listaDVDs; }

        public static void removerDVD (String nome) {
            listaDVDs.remove(nome);
        }
    }
}

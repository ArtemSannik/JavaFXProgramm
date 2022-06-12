package com.example.diploms;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class HelloController {


    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button enter;

    @FXML
    private VBox panelVBox;

    @FXML
    private TextField user_info;

    @FXML
    private TextField user_intro;

    @FXML
    private Label label_info;

    private DB db = new DB();


    @FXML
    void initialize() throws SQLException, IOException {

        ResultSet resultSet = db.getArticle();
        while (resultSet.next()) {
            Node node = FXMLLoader.load(Objects.requireNonNull(HelloApplication.class.getResource("article.fxml")));

            Label info = (Label) node.lookup("#info");
            info.setText(resultSet.getString("info"));

            Label intro = (Label) node.lookup("#intro");
            intro.setText(resultSet.getString("title"));

            panelVBox.getChildren().add(node);
            panelVBox.setSpacing(10);
        }

        enter.setOnAction(event -> {
            String info = user_info.getCharacters().toString();
            String title = user_intro.getCharacters().toString();
            if (title.isEmpty() || info.isEmpty()) {
                user_info.setPromptText("пустая строка");
                user_intro.setPromptText("пустая строка");
            } else if (info.length() <= 4) {
                user_info.setText("Вы ввели не корректную ссылку");

            } else if (db.isExistsArticle(title)) {
                label_info.setText("Введите новое сокращение");

            } else {
                label_info.setText("Успешно добавлено");
                db.regArticle(info, title);
                db.getArticle();
                user_info.setText("");
                user_intro.setText("");

            }


        });


    }
}
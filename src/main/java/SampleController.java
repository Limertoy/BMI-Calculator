import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DialogPane;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.net.URL;
import java.text.DecimalFormat;
import java.util.ResourceBundle;

public class SampleController implements Initializable {


    public Button exit_button;
    public Text result;
    public Group elems;
    public TextField weight, height;
    public Text small, normal, big;

    public void exit(ActionEvent actionEvent) {
        Stage stage = (Stage) exit_button.getScene().getWindow();
        stage.close();
    }

    public void minimize(ActionEvent actionEvent) {
        Stage stage = (Stage) exit_button.getScene().getWindow();
        stage.setIconified(true);
    }

    public void count(ActionEvent actionEvent) {
        double bmi;
        if(weight.getText().equals("") || height.getText().equals("")){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            DialogPane dialogPane = alert.getDialogPane();
            dialogPane.getStylesheets().add(
                    getClass().getResource("myDialogs.css").toExternalForm());
            dialogPane.getStyleClass().add("myDialog");
            alert.setHeaderText("Please input everything!");
            alert.setTitle("Error!");
            alert.show();
        } else {
            int kg = Integer.parseInt(weight.getText());
            int cm = Integer.parseInt(height.getText());
            double m = (double) cm / 100;
            bmi = kg / (m * m);
            if (bmi < 18.5) {
                big.setVisible(false);
                normal.setVisible(false);
                small.setVisible(true);
            } else if (bmi >= 18.5 && bmi < 25.0) {
                big.setVisible(false);
                small.setVisible(false);
                normal.setVisible(true);
            } else {
                normal.setVisible(false);
                small.setVisible(false);
                big.setVisible(true);
            }
            result.setText(new DecimalFormat("##.#").format(bmi));
            result.setVisible(true);
            elems.setVisible(true);
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        weight.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue,
                                String newValue) {
                if (!newValue.matches("\\d*")) {
                    weight.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });

        height.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue,
                                String newValue) {
                if (!newValue.matches("\\d*")) {
                    height.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });
    }
}

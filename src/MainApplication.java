import builder.DynamicContentBuilderV1;
import components.MyFirstForm;
import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class MainApplication extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setScene(new Scene(builder()));
        primaryStage.setMaximized(true);
        primaryStage.show();
    }

    Parent builder(){
        AnchorPane pane = new AnchorPane();
        pane.getStylesheets().add("style/light.css");
        pane.getStyleClass().add("main");
        pane.getChildren().add(form1());
        return pane;
    }

    BorderPane form1(){
        MyFirstForm myForm = new MyFirstForm();
        myForm.setName("Ismael");
        myForm.setNickname("Correia");
        myForm.setAge("23");

        return DynamicContentBuilderV1.build(myForm);
    }
}

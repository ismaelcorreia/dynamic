package builder;

import annotations.DynamicAction;
import annotations.DynamicForm;
import annotations.DynamicTextField;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class DynamicContentBuilderV1 {

    static final BorderPane pane = new BorderPane();
    static Object myObj;

    public static BorderPane build(final Object object){
        myObj = object;
        buildContainer();
        return pane;
    }

    static void buildContainer() {
        if(myObj.getClass().isAnnotationPresent(DynamicForm.class)){



            // Field content
            VBox box = new VBox();
            box.getChildren().addAll(fields());


            // Action content
            HBox actionBox = new HBox();
            actionBox.getChildren().addAll(actions());

            // Layout for forms

            pane.setCenter(box);
            pane.setBottom(actionBox);
            pane.getStyleClass().add("form");
            anchor();




        }
    }

    static void anchor(){
        AnchorPane.setRightAnchor(pane, 0.0);
        AnchorPane.setTopAnchor(pane, 0.0);
        AnchorPane.setLeftAnchor(pane, 0.0);
        AnchorPane.setBottomAnchor(pane, 0.0);
    }


    // Fields...

    static List<Node> fields(){

        Class<?> clazz = myObj.getClass();
        List<Node> arr = new ArrayList<>();

        for (Field field : clazz.getDeclaredFields()) {
            if (field.isAnnotationPresent(DynamicTextField.class)) {

                field.setAccessible(true);
                try {
                    arr.add(getNodeField(field));
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
        return arr;
    }

    static Node getNodeField(Field field) throws IllegalAccessException {
        return DynamicTextFieldBuilderV1.build(field, myObj);
    }

    // Actions...


    static List<Node> actions(){

        Class<?> clazz = myObj.getClass();
        List<Node> arr = new ArrayList<>();

        for (Method method : clazz.getDeclaredMethods()) {
            if (method.isAnnotationPresent(DynamicAction.class)) {
                method.setAccessible(true);
                arr.add(DynamicActionButtonBuilderV1.build(method, myObj));
            }
        }

        return arr;
    }


}

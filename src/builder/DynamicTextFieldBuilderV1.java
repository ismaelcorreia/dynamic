package builder;

import annotations.DynamicTextField;
import annotations.FieldLayout;
import com.sun.javafx.scene.control.behavior.TextBinding;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.lang.reflect.Field;
import java.util.Properties;

public class DynamicTextFieldBuilderV1 {


    public static Node build(Field field, Object myObj) throws IllegalAccessException {
            field.setAccessible(true);
            StringProperty f = (StringProperty) field.get(myObj);
            return DynamicTextFieldBuilderV1.build(field.getAnnotation(DynamicTextField.class), f);
    }

    public static Node build(DynamicTextField textField, StringProperty value) {

        // Title
        Label title = new Label(textField.label());


        // Text
        TextField text = new TextField();
        text.setPromptText(textField.prompt());

        // text Binding
        text.textProperty().bindBidirectional(value);



        if (!textField.regex().isEmpty())
            addValidation(text, textField.regex());

        HBox textBox = new HBox(text);

        if (textField.fieldLayout() == FieldLayout.INLINE)
            return new HBox(title, textBox);
        return new VBox(title, textBox);
    }

    static void addValidation(TextField tf, String regex){
        tf.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches(regex) && !newValue.isEmpty())
                tf.setText(oldValue);
        });
    }
}

package builder;

import annotations.DynamicAction;
import annotations.DynamicTextField;
import annotations.FieldLayout;
import com.jfoenix.controls.JFXButton;
import javafx.beans.property.StringProperty;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import util.SVGIcons;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class DynamicActionButtonBuilderV1 {

    public static JFXButton build(Method method, Object myObj){

        DynamicAction dynamicAction = method.getAnnotation(DynamicAction.class);
        JFXButton buttonAction = new JFXButton(dynamicAction.title());
        buttonAction.setGraphic(SVGIcons.iconFrom(dynamicAction.icon()));
        buttonAction.getStyleClass().add(dynamicAction.styleClass());
        buttonAction.setOnAction(e-> {
            try {
                method.invoke(myObj);
            } catch (IllegalAccessException ex) {
                ex.printStackTrace();
            } catch (InvocationTargetException ex) {
                ex.printStackTrace();
            }
        });
        return buttonAction;
    }

}

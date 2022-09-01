package components;

import annotations.DynamicAction;
import annotations.DynamicForm;
import annotations.DynamicTextField;
import annotations.FieldLayout;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.event.Event;

import java.io.Serializable;

@DynamicForm(title = "HELLO WORLD")
public class MyFirstForm implements Serializable {

    @DynamicTextField(label = "First Name",fieldLayout = FieldLayout.INLINE)
    private final StringProperty name = new SimpleStringProperty();
    @DynamicTextField(label = "Last Name")
    private final StringProperty nickname = new SimpleStringProperty();
    @DynamicTextField(label = "Age", regex = "^[0-9]{0,3}$")
    private final StringProperty age = new SimpleStringProperty();

    public String getName() {
        return name.get();
    }

    public StringProperty nameProperty() {
        return name;
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public String getNickname() {
        return nickname.get();
    }

    public StringProperty nicknameProperty() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname.set(nickname);
    }

    public String getAge() {
        return age.get();
    }

    public StringProperty ageProperty() {
        return age;
    }

    public void setAge(String age) {
        this.age.set(age);
    }

    @DynamicAction
    void action(){
        System.out.println(this);
    }


    @Override
    public String toString() {
        return "MyFirstForm{" +
                "name='" + name.getValueSafe() + '\'' +
                ", nickname='" + nickname.getValueSafe() + '\'' +
                ", age=" + age.getValue()+
                '}';
    }
}

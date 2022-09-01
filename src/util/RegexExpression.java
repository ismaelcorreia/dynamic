package util;

public final class RegexExpression {

     static final String PHONE_NUMBER = "^(\\+\\d{1,3}( )?)?((\\(\\d{3}\\))|\\d{3})[- .]?\\d{3}[- .]?\\d{4}$";
     static final String LITERAL_NUMBER = "^(\\d{3}[- .]?){2}\\d{4}$";

}

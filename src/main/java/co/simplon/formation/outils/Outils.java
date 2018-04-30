package co.simplon.formation.outils;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;
import org.apache.commons.lang.StringUtils;

import java.util.regex.Pattern;

@Data
@NoArgsConstructor
@Service
public class Outils {

    public static String toUpperecase(String string) {
        return string.toUpperCase();

    }

    public static String formatPrenom(String string) {
        return StringUtils.capitalize(string.toLowerCase());
    }

//public static String formatIdrh(String string){
  //  Pattern p = Pattern.compile("^[A-Z]{3}[0-9]{3}$");
//}
}

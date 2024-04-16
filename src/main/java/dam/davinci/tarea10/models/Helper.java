package dam.davinci.tarea10.models;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Helper {

    private static final String uudiRegex ="^[\\p{XDigit}]{8}(-[\\p{XDigit}]{4}){3}-[\\p{XDigit}]{12}$";

    public static final boolean checkUUID(String uuid) {
        Pattern pattern = Pattern.compile(uudiRegex);
        Matcher matcher = pattern.matcher(uuid);
        return matcher.matches();
    }

    // ==== PARTE IMPORTADA DEL EJERCICIO 6 ====

    private static final String dniRegex = "^([0-9]{1,9})([A-Za-z])$";

    public static boolean checkDni(String dni){
        if (dni == null || dni.isEmpty()) return false;
        Pattern p = Pattern.compile(dniRegex);
        Matcher m = p.matcher(dni);
        if (m.matches()){
            int dniNumber = Integer.parseInt(m.group(1));
            char dniLetter = m.group(2).toUpperCase().charAt(0);
            return checkDni(dniNumber,dniLetter);
        }
        return false;

    }

    private static final char[] dniLetters = {'T', 'R', 'W', 'A', 'G', 'M', 'Y', 'F', 'P', 'D','X',
            'B', 'N', 'J', 'Z', 'S', 'Q', 'V', 'H', 'L', 'C', 'K', 'E'};

    /** Comprueba si un DNI es válido según la normativa vigente.
     * @param dniNumber El número del DNI. Valores válidos entre 0 y 99999999
     * @param dniLetter Letra que corresponda con el DNI
     * @return
     */
    private static boolean checkDni(int dniNumber, char dniLetter) {
        if (dniNumber < 0 || dniNumber > 99999999) {
            return false;
        }
        return dniLetters[dniNumber % 23] == Character.toUpperCase(dniLetter);
    }
}

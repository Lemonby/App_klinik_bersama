package util;

public class Validator {

    public static boolean isValidNIK(String nik) {
        return nik != null && nik.matches("\\d{16}");
    }
    
    public static boolean isValidNIP(String nip) {
        return nip != null && nip.matches("\\d{18}");
    }

    public static boolean isValidPhone(String phone) {
        return phone != null && phone.matches("\\d{10,15}");
    }

    public static boolean isNotEmpty(String value) {
        return value != null && !value.trim().isEmpty();
    }
    
    public static boolean isValidUserType(String userType) {
        if (userType == null) {
            return false;
        }
        return userType.equalsIgnoreCase("ADMIN") || userType.equalsIgnoreCase("USER");
    }

    public static boolean isValidAction(String action) {
        if (action == null) {
            return false;
        }
        return action.equalsIgnoreCase("LOGIN") || action.equalsIgnoreCase("LOGOUT");
    }

    public static boolean isNotName(String name) {
        return name != null && !name.trim().isEmpty() && name.matches("^[a-zA-Z\\s]+$");
    }

    public static boolean isValidGender(String kelamin) {
        return "L".equalsIgnoreCase(kelamin) || "P".equalsIgnoreCase(kelamin);
    }

    public static boolean isValidObat(String value){
        return value != null && !value.trim().isEmpty()
        && value.matches("[a-zA-Z0-9\\s]+$");
    }

    public boolean isValidInteger(String value) {
        return value != null && value.matches("\\d+");
    }
    
    public static boolean isValidUsername(String username) {
        return username != null && !username.trim().isEmpty() && username.matches("^[a-zA-Z0-9_]{5,15}$");
    }

    public static boolean isValidPassword(String password) {
        return password != null && password.length() >= 8 &&
            password.matches(".*[A-Z].*") &&  // Harus ada huruf besar
            password.matches(".*[a-z].*") &&  // Harus ada huruf kecil
            password.matches(".*[0-9].*");  // Harus ada angka
    }

    public static boolean isValidRole(String role) {
        return role != null && (role.equals("admin") || role.equals("dokter") || role.equals("pasien"));
    }

}

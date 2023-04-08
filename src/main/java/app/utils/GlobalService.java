package app.utils;

import java.util.UUID;

public class GlobalService {
    public static String generateUUID() {
        UUID uuid = UUID.randomUUID();
        String uuidString = uuid.toString();
        String formattedUUID = uuidString.substring(0, 8) + "-" + uuidString.substring(8, 12) + "-"
                + uuidString.substring(12, 16) + "-" + uuidString.substring(16, 20) + "-" + uuidString.substring(20);
        return formattedUUID;
    }
}

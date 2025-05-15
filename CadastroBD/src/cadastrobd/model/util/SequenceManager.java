package cadastrobd.model.util;

import java.sql.*;

public class SequenceManager {

    public static int getValue(String sequenceName) {
        int value = 0;
        String sql = "SELECT NEXT VALUE FOR " + sequenceName + " AS nextVal";

        try (Connection conn = ConectorBD.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            if (rs.next()) {
                value = rs.getInt("nextVal");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return value;
    }
}
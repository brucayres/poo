import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Properties;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.io.BufferedWriter;
import java.io.InputStream;

public class ActorExportApp {
    public static void main(String[] args) {
        Properties props = new Properties();
        try (InputStream in = Files.newInputStream(Paths.get("db.properties"))) {
            props.load(in);
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }

        String dbUrl = props.getProperty("db.url");
        String dbUser = props.getProperty("db.user");
        String dbPassword = props.getProperty("db.password");
        String csvPath = props.getProperty("csv.path");

        ArrayList<ActorExport> actors = new ArrayList<>();

        String sql = "SELECT\n" +
                     "    actor_id,\n" +
                     "    first_name,\n" +
                     "    last_name\n" +
                     "FROM actor\n" +
                     "ORDER BY actor_id\n" +
                     "LIMIT 20;";

        try (Connection conn = DriverManager.getConnection(dbUrl, dbUser, dbPassword);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                int id = rs.getInt("actor_id");
                String firstName = rs.getString("first_name");
                String lastName = rs.getString("last_name");
                actors.add(new ActorExport(id, firstName, lastName));
            }

        } catch (Exception e) {
            e.printStackTrace();
            return;
        }

        try (BufferedWriter writer = Files.newBufferedWriter(Paths.get(csvPath))) {
            writer.write("Id,Nome,Sobrenome");
            writer.newLine();
            for (ActorExport actor : actors) {
                writer.write(actor.toCsvLine());
                writer.newLine();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

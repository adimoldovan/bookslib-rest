package am.tau.bookslib.service;

import am.tau.bookslib.db.MySQLClient;
import com.ibatis.common.jdbc.ScriptRunner;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.*;
import java.sql.SQLException;

@Service
public class MaintService {
    MySQLClient mySQLClient = new MySQLClient();

    public void fullMaint() throws IOException, SQLException {
        File file = new ClassPathResource("db_maint.sql").getFile();
        try {
            ScriptRunner sr = new ScriptRunner();
            sr.setDriver("com.mysql.jdbc.Driver");
            sr.setUsername("bookslibraryrest");
            sr.setPassword("bookslibraryrest");
            sr.setUrl("jdbc:mysql://localhost:3306/bookslib?useSSL=false");

            Reader reader = new BufferedReader(new FileReader(file.getAbsoluteFile()));

            sr.runScript(reader);
        } catch (Exception e) {
            System.err.println("Failed to Execute" + file.getName()
                    + " The error is " + e.getMessage());
        }
    }
}

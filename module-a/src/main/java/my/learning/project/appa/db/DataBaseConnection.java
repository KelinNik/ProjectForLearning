package my.learning.project.appa.db;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.sql.*;

@Slf4j
@Component
public class DataBaseConnection {

    private static final String URL= "jdbc:postgresql://localhost:5432/db";
    @Value("${db.user}")
    private static String USER;
    @Value("${db.password}")
    private static String PASSWORD;

    private final Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);

    public DataBaseConnection() throws SQLException {
    }

    @Bean
    public boolean getConnectionStatuses() throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement("SELECT * FROM account")) {
            ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            if(resultSet.getBoolean("is_active") || !resultSet.getBoolean("is_active")){
                return true;
            }
                return false;
        }
    }
}

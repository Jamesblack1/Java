package com.core.api.component;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class StatusAppJdbcComponent {

    private static final Logger LOG = LoggerFactory.getLogger(StatusAppJdbcComponent.class);


//    @Value("${spring.datasource.url}")

    private String appUrlJDBC = "jdbc:postgresql://localhost:5433/test";

//    @Value("${spring.datasource.username}")
    private String appUserJDBC = "postgres";

//    @Value("${spring.datasource.password}")
    private String appPassJDBC = "12345678";

//    @Value("${spring.datasource.driverClassName}")
    private String appDriverClassJDBC = "org.postgresql.Driver";


    public static final String SQL_SELECT_FROM_DUAL = "select current_timestamp";

    public boolean testConnectionBD() {
        return this.connectAviable(this.appUrlJDBC, this.appDriverClassJDBC, this.appUserJDBC, this.appPassJDBC);
    }


    protected boolean connectAviable(String url, String driverClass, String user, String pass) {
        LOG.info(" URL :: ->{}", url);
        LOG.info(" DRIVER :: ->{}", driverClass);
        LOG.info(" USER :: ->{}", user);
        LOG.info(" PASS :: ->{}", pass);
        boolean response = Boolean.FALSE;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            Class.forName(driverClass);
            connection = DriverManager.getConnection(url, user, pass);
            preparedStatement = connection.prepareStatement(SQL_SELECT_FROM_DUAL);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                LOG.info("{}", resultSet.getObject(1));
            }
            response = Boolean.TRUE;
        } catch (Exception e) {
            LOG.error(e.toString());
        } finally {
            CloseResources.getInstance().close(connection, preparedStatement, resultSet);
        }
        return response;
    }

    public String getAppUrlJDBC() {
        LOG.info("aca  appUrlJDBC:: ->  {}", this.appUrlJDBC);
        return this.appUrlJDBC;
    }

    public void setAppUrlJDBC(String appUrlJDBC) {
        this.appUrlJDBC = appUrlJDBC;
    }

}

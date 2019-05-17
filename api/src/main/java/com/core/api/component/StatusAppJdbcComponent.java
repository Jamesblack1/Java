package com.core.api.component;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.core.api.Utils.Tools.ToolBD;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;


@Component
public class StatusAppJdbcComponent {

    private static final Logger LOG = LoggerFactory.getLogger(StatusAppJdbcComponent.class);

    @Autowired
    private ToolBD toolBD;

    @Value("${spring.datasource.url}")

    private String appUrlJDBC;

    @Value("${spring.datasource.username}")
    private String appUserJDBC;

    @Value("${spring.datasource.password}")
    private String appPassJDBC;

    @Value("${spring.datasource.driverClassName}")
    private String appDriverClassJDBC;


    public static final String SQL_SELECT_FROM_DUAL = "select current_timestamp";

    public boolean testConnectionBD() {
        return this.connectAviable(this.appUrlJDBC, this.appDriverClassJDBC, this.appUserJDBC, this.appPassJDBC);
    }


    protected boolean connectAviable(String url, String driverClass, String user, String pass) {
        boolean response = Boolean.FALSE;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String connection_string = toolBD.builderUrlConnection(url, user, pass);
        try {
            Class.forName(driverClass);
            connection = DriverManager.getConnection(connection_string);
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

    public String getAppUrlJDBC() { return this.appUrlJDBC; }

    public void setAppUrlJDBC(String appUrlJDBC) {
        this.appUrlJDBC = appUrlJDBC;
    }

}

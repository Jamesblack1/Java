package com.core.api.component;

import java.io.BufferedReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class CloseResources {

    private static final CloseResources _instance = new CloseResources();

    public static CloseResources getInstance() {
        return _instance;
    }

    public void close(BufferedReader component) {
        try {
            if (component != null) {
                component.close();
            }
        } catch (Exception e) {
            this.closeAgain(component);
        }
    }

    private void closeAgain(BufferedReader component) {
        try {
            if (component != null) {
                component.close();
            }
        } catch (Exception e) {
        }
    }

    public void close(PrintWriter component) {
        try {
            if (component != null) {
                component.close();
            }
        } catch (Exception e) {
            this.closeAgain(component);
        }
    }

    private void closeAgain(PrintWriter component) {
        try {
            if (component != null) {
                component.close();
            }
        } catch (Exception e) {
        }
    }

    public void close(Socket component) {
        try {
            if (component != null) {
                component.close();
            }
        } catch (Exception e) {
            this.closeAgain(component);
        }
    }

    private void closeAgain(Socket component) {
        try {
            if (component != null) {
                component.close();
            }
        } catch (Exception e) {
        }
    }

    public void close(Connection component) {
        try {
            if (component != null) {
                component.close();
            }
        } catch (Exception e) {
            this.closeAgain(component);
        }
    }

    private void closeAgain(Connection component) {
        try {
            if (component != null) {
                component.close();
            }
        } catch (Exception e) {
        }
    }

    public void close(PreparedStatement component) {
        try {
            if (component != null) {
                component.close();
            }
        } catch (Exception e) {
            this.closeAgain(component);
        }
    }

    private void closeAgain(PreparedStatement component) {
        try {
            if (component != null) {
                component.close();
            }
        } catch (Exception e) {
        }
    }

    public void close(ResultSet component) {
        try {
            if (component != null) {
                component.close();
            }
        } catch (Exception e) {
            this.closeAgain(component);
        }
    }

    private void closeAgain(ResultSet component) {
        try {
            if (component != null) {
                component.close();
            }
        } catch (Exception e) {
        }
    }

    public void close(Connection connection, PreparedStatement preparedStatement, ResultSet resultSet) {
        this.close(resultSet);
        this.close(preparedStatement);
        this.close(connection);
    }
}
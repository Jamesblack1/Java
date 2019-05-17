package com.core.api.Utils;


import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HttpRemoteClient {

    private static final Logger LOG = LoggerFactory.getLogger(HttpRemoteClient.class);

    private static final HttpRemoteClient _instance = new HttpRemoteClient();

    public static HttpRemoteClient getInstance() {
        return _instance;
    }

    public String getContentMethodPOST(String url, String requestData) {
        String response = null;
        if (url != null && url.startsWith("http://")) {
            response = this.getHttpContentMethodPOST(url);
        } else if (url != null && url.startsWith("https://")) {
            response = this.getHttpsContentMethodPOST(url);
        }
        return response;
    }

    public String getContentJSonMethodPOST(String url, String requestData) {
        String response = null;
        if (url != null && url.startsWith("http://")) {
            response = this.getHttpContentJSonMethodPOST(url, requestData);
        } else if (url != null && url.startsWith("https://")) {
            response = this.getHttpsContentJSonMethodPOST(url, requestData);
        }
        return response;
    }

    public String getContentMethodGET(String url) {
        String response = null;
        if (url != null && url.startsWith("http://")) {
            response = this.getHttpContentMethodGET(url);
        } else if (url != null && url.startsWith("https://")) {
            response = this.getHttpsContentMethodGET(url);
        }
        return response;
    }

    public String getHttpsContentMethodGET(String httpsURL) {
        String response = null;
        URL url = null;
        try {
            url = new URL(httpsURL);
            HttpsURLConnection con = (HttpsURLConnection) url.openConnection();
            con.addRequestProperty("Accept-Language", "en-US,en;q=0.8");
            con.addRequestProperty("User-Agent", "Mozilla");
            con.setRequestMethod("GET");
            response = printContent(con);
            this.close(con);
        } catch (Exception e) {
            LOG.error(e.toString());
        }
        return response;
    }

    public String getHttpsContentMethodPOST(String httpsURL) {
        String response = null;
        URL url = null;
        try {
            url = new URL(httpsURL);
            HttpsURLConnection con = (HttpsURLConnection) url.openConnection();
            con.addRequestProperty("Accept-Language", "en-US,en;q=0.8");
            con.addRequestProperty("User-Agent", "Mozilla");
            con.setRequestMethod("POST");
            response = printContent(con);
            this.close(con);
        } catch (Exception e) {
            LOG.error(e.toString());
        }
        return response;
    }

    public String getHttpsContentJSonMethodPOST(String httpsURL, String requestJSon) {
        String response = null;
        URL url = null;
        DataOutputStream wr = null;
        try {
            url = new URL(httpsURL);
            HttpsURLConnection con = (HttpsURLConnection) url.openConnection();
            con.addRequestProperty("Accept-Language", "en-US,en;q=0.8");
            con.addRequestProperty("User-Agent", "Mozilla");
            con.addRequestProperty("Content-Type", "application/json");
            con.setRequestMethod("POST");
            con.setDoOutput(true);
            wr = new DataOutputStream(con.getOutputStream());
            wr.writeBytes(requestJSon);
            wr.flush();
            wr.close();
            response = printContent(con);
            this.close(con);
        } catch (Exception e) {
            LOG.error(e.toString());
        }
        return response;
    }

    public String getHttpContentMethodGET(String httpURL) {
        String response = null;
        URL url = null;
        try {
            url = new URL(httpURL);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.addRequestProperty("Accept-Language", "en-US,en;q=0.8");
            con.addRequestProperty("User-Agent", "Mozilla");
            con.setRequestMethod("GET");
            response = printContent(con);
            this.close(con);
        } catch (Exception e) {
            LOG.error(e.toString());
        }
        return response;
    }

    public String getHttpContentMethodPOST(String httpURL) {
        String response = null;
        URL url = null;
        try {
            url = new URL(httpURL);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.addRequestProperty("Accept-Language", "en-US,en;q=0.8");
            con.addRequestProperty("User-Agent", "Mozilla");
            con.setRequestMethod("POST");
            response = printContent(con);
            this.close(con);
        } catch (Exception e) {
            LOG.error(e.toString());
        }
        return response;
    }

    public String getHttpContentJSonMethodPOST(String httpURL, String requestJSon) {
        String response = null;
        URL url = null;
        DataOutputStream wr = null;
        try {
            url = new URL(httpURL);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.addRequestProperty("Accept-Language", "en-US,en;q=0.8");
            con.addRequestProperty("User-Agent", "Mozilla");
            con.addRequestProperty("Content-Type", "application/json");
            con.setRequestMethod("POST");
            con.setDoOutput(true);
            wr = new DataOutputStream(con.getOutputStream());
            wr.writeBytes(requestJSon);
            wr.flush();
            wr.close();
            response = printContent(con);
            this.close(con);
        } catch (Exception e) {
            LOG.error(e.toString());
        }
        return response;
    }

    private String printContent(HttpsURLConnection con) {
        StringBuilder response = new StringBuilder();
        BufferedReader br = null;
        if (con != null) {
            try {
                if (con.getResponseCode() == 200) {
                    br = new BufferedReader(new InputStreamReader(con.getInputStream()));
                } else {
                    br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
                }
                String input;
                while ((input = br.readLine()) != null) {
                    response.append(input);
                }
            } catch (IOException e) {
                LOG.error(e.toString());
            } finally {
                this.close(br);
            }
        }
        return response.toString();
    }

    private String printContent(HttpURLConnection con) {
        StringBuilder response = new StringBuilder();
        BufferedReader br = null;
        if (con != null) {
            try {
                if (con.getResponseCode() == 200) {
                    br = new BufferedReader(new InputStreamReader(con.getInputStream()));
                } else {
                    br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
                }
                String input;
                while ((input = br.readLine()) != null) {
                    response.append(input);
                }
            } catch (IOException e) {
                LOG.error(e.toString());
            } finally {
                this.close(br);
            }
        }
        return response.toString();
    }

    private void close(HttpURLConnection connection) {
        try {
            if (connection != null) {
                connection.disconnect();
            }
        } catch (Exception e) {
            LOG.error(e.toString());
        }
    }

    private void close(HttpsURLConnection connection) {
        try {
            if (connection != null) {
                connection.disconnect();
            }
        } catch (Exception e) {
            LOG.error(e.toString());
        }
    }

    private void close(BufferedReader component) {
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

}

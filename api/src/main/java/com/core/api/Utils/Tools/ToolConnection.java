package com.core.api.Utils.Tools;

import com.core.api.component.CloseResources;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;

@Component
public class ToolConnection {

    private static final Logger LOG = LoggerFactory.getLogger(ToolConnection.class);

    /**
     * @param address
     * @param port
     * @return
     */
    public boolean telnet(String address, Integer port) {
        Boolean response = Boolean.FALSE;
        Socket pingSocket = null;
        PrintWriter out = null;
        BufferedReader in = null;
        try {
            LOG.info("Iniciando telnet.");
            LOG.info("- address : {}", address);
            LOG.info("- port    : {}", port);
            pingSocket = new Socket(address, port);
            out = new PrintWriter(pingSocket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(pingSocket.getInputStream()));
            LOG.info("Telnet");
            LOG.info(" - Init -> ", in.readLine());
            response = Boolean.TRUE;
        } catch (IOException e) {
            LOG.error(e.toString());
        } finally {
            CloseResources.getInstance().close(out);
            CloseResources.getInstance().close(in);
            CloseResources.getInstance().close(pingSocket);
        }
        return response;
    }

    /**
     * @param address
     * @param port
     * @param timeout
     * @return
     * @throws Exception
     */
    public boolean ping(String address, Integer port, Integer timeout) throws Exception {
        Boolean response = Boolean.FALSE;
        try {
            LOG.info("Iniciando ping.");
            LOG.info("- address : {}", address);
            LOG.info("- port    : {}", port);
            LOG.info("- timeout : {}", timeout);
            Socket socket = null;
            try {
                socket = new Socket();
                socket.connect(new InetSocketAddress(address, port), timeout);
            } finally {
                CloseResources.getInstance().close(socket);
            }
            response = Boolean.TRUE;
        } catch (Exception e) {
            LOG.error(e.toString());
        }
        return response;
    }
}

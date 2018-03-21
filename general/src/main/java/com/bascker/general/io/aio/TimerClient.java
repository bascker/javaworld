package com.bascker.general.io.aio;

import com.bascker.bsutil.Sample;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * Timer Client
 *
 * @author bascker
 */
public class TimerClient implements Sample {

    private static final Logger LOG = LoggerFactory.getLogger(TimerClient.class);
    private String mHostname;
    private int mPort;

    public TimerClient(final String hostname, final int port) {
        mHostname = hostname;
        mPort = port;
    }

    @Override
    public void start () {
        try {
            final AsynchronousSocketChannel client = AsynchronousSocketChannel.open();
            // Connect Timer Server
            final InetSocketAddress local = new InetSocketAddress(mHostname, mPort);
            final Future<Void> future = client.connect(local);
            future.get();

            // Get data
            final ByteBuffer buffer = ByteBuffer.allocate(1024);
            client.read(buffer, null, new CompletionHandler<Integer, Void>() {
                @Override
                public void completed(final Integer result, final Void attachment) {
                    LOG.info("Client received: " + new String(buffer.array()));
                }

                @Override
                public void failed(final Throwable exc, final Void attachment) {
                    exc.printStackTrace();
                    try {
                        client.close();
                    } catch (IOException e) {
                        LOG.error("failed", e);
                    }

                }
            });

            // Wait
            Thread.sleep(1000);
        } catch (IOException | InterruptedException | ExecutionException e) {
            LOG.error("start", e);
        }
    }

    public static void main(String[] args) {
        TimerClient client;
        for (int i = 0; i < 50; i ++) {
            client = new TimerClient("127.0.0.1", 8013);
            client.start();
        }
    }
}

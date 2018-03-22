package com.bascker.general.io.aio;

import com.bascker.bsutil.CharsetUtils;
import com.bascker.bsutil.Sample;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.AsynchronousChannelGroup;
import java.nio.channels.AsynchronousServerSocketChannel;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/**
 * Timer Server: send a time str to client when a client was connected
 *
 * @author bascker
 */
public class TimerSever implements Sample {

    private static final Logger LOG = LoggerFactory.getLogger(TimerSever.class);
    private String mHostname;
    private int mPort;

    public TimerSever(final String hostname, final int port) {
        mHostname = hostname;
        mPort = port;
    }

    @Override
    public void start (final Object... args) {
        try {
            final AsynchronousChannelGroup group = AsynchronousChannelGroup.withThreadPool(Executors.newFixedThreadPool(5));
            final InetSocketAddress address = new InetSocketAddress(mHostname, mPort);
            final AsynchronousServerSocketChannel server = AsynchronousServerSocketChannel.open(group).bind(address);

            // CompletionHandler: Callable Interface
            server.accept(null, new CompletionHandler<AsynchronousSocketChannel, Void>() {

                @Override
                public void completed(final AsynchronousSocketChannel result, final Void attachment) {
                    // Accept next connection
                    server.accept(null, this);

                    // Get current time
                    final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    final String currentTime = dateFormat.format(new Date());
                    try {
                        // Write data to client by Future
                        final ByteBuffer buffer = CharsetUtils.ENCODER_UTF8.encode(CharBuffer.wrap(currentTime + "\r\n"));
                        final Future<Integer> future = result.write(buffer);

                        // Wait task completed
                        future.get();
                        LOG.info("sent to client: " + currentTime);

                        // Close current connection
                        result.close();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void failed(final Throwable exc, final Void attachment) {
                    exc.printStackTrace();
                }
            });

            group.awaitTermination(Long.MAX_VALUE, TimeUnit.SECONDS);
        } catch (IOException | InterruptedException e) {
            LOG.error("start", e);
        }
    }

    public static void main(String[] args) {
        final TimerSever sever = new TimerSever("0.0.0.0", 8013);
        sever.start();
    }

}

package cosm0s.stats4was.sender.graphite;

import cosm0s.stats4was.log.L4j;
import org.jboss.netty.bootstrap.ClientBootstrap;
import org.jboss.netty.channel.ChannelEvent;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.channel.ChannelStateEvent;
import org.jboss.netty.channel.ExceptionEvent;
import org.jboss.netty.channel.MessageEvent;
import org.jboss.netty.channel.SimpleChannelUpstreamHandler;
import org.jboss.netty.handler.timeout.ReadTimeoutException;
import org.jboss.netty.util.Timeout;
import org.jboss.netty.util.Timer;
import org.jboss.netty.util.TimerTask;

import java.net.ConnectException;
import java.net.InetSocketAddress;
import java.text.SimpleDateFormat;
import java.util.concurrent.TimeUnit;

public class Handler extends SimpleChannelUpstreamHandler {

    private ClientBootstrap clientBootstrap;
    private static final SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss dd.MM.yyyy");
    private Timer timer;
    private int reconnectTimeout;
    private long startTime;

    public Handler(ClientBootstrap clientBootstrap, Timer timer, int reconnectTimeout){
        this.clientBootstrap = clientBootstrap;
        this.timer = timer;
        this.reconnectTimeout = reconnectTimeout;
        this.startTime = -1;
    }

    public InetSocketAddress getRemoteAddress() {
        return (InetSocketAddress) this.clientBootstrap.getOption("remoteAddress");
    }

    @Override
    public void channelDisconnected(ChannelHandlerContext ctx, ChannelStateEvent e) {
        L4j.getL4j().info(new StringBuilder("[Netty] disconnected from: ").append(this.getRemoteAddress()).toString());
    }

    @Override
    public void channelClosed(ChannelHandlerContext ctx, ChannelStateEvent e) {
        L4j.getL4j().info(new StringBuilder("[Netty] Sleeping for: ").append(this.reconnectTimeout).append("s").toString());
        L4j.getL4j().info(new StringBuilder("[Netty] ChannelClosed: ").append(GraphiteSender.isShuttingDown()).toString());
        if (!GraphiteSender.isShuttingDown()) 	  {
            timer.newTimeout(new TimerTask() {
                public void run(Timeout timeout) throws Exception {
                    try {
                        L4j.getL4j().info(new StringBuilder("[Netty] Reconnecting to: ").append(getRemoteAddress()).toString());
                        clientBootstrap.connect();
                    } catch (Exception e) {
                        L4j.getL4j().error(new StringBuilder("[Netty] Error trying to reconnect after Reconnect timeout expired: ").append(e.getMessage()).toString(), e);
                    }
                }}, this.reconnectTimeout, TimeUnit.SECONDS);
        }
    }

    @Override
    public void handleUpstream(ChannelHandlerContext ctx, ChannelEvent e) throws Exception {
        if (e instanceof ChannelStateEvent) {
            L4j.getL4j().error(e.toString());
        }
        super.handleUpstream(ctx, e);
    }

    @Override
    public void messageReceived(ChannelHandlerContext ctx, MessageEvent e) {
        L4j.getL4j().info(new StringBuilder("[Netty] GRAPHITE SERVER SEND MESSAGE").append(e.getMessage()).toString());
    }


    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, ExceptionEvent e) {
        Throwable cause = e.getCause();
        if (cause instanceof ConnectException) {
            this.startTime = -1;
            L4j.getL4j().error(new StringBuilder("[Netty] Failed to connect").append(cause.getMessage()).toString(), cause);
        }
        if (cause instanceof ReadTimeoutException) {
            L4j.getL4j().error(new StringBuilder("[Netty] Disconnecting due to read timeout ").append(cause.getMessage()).toString());
        } else {
            L4j.getL4j().error(new StringBuilder("[Netty] Disconnecting due to no inbound traffic ").append(cause.getMessage()).toString());
        }
        ctx.getChannel().close();
    }

    @Override
    public void channelConnected(ChannelHandlerContext ctx, ChannelStateEvent e) {
        if (this.startTime < 0) {
            this.startTime = System.currentTimeMillis();
        }
        L4j.getL4j().info(new StringBuilder("[Netty] Connected to: ").append(this.getRemoteAddress()).toString());
    }
}

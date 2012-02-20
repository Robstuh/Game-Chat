package org.rschat;

import java.net.InetSocketAddress;
import java.util.concurrent.Executors;

import org.jboss.netty.bootstrap.ServerBootstrap;
import org.jboss.netty.channel.ChannelPipeline;
import org.jboss.netty.channel.ChannelPipelineFactory;
import org.jboss.netty.channel.Channels;
import org.jboss.netty.channel.socket.nio.NioServerSocketChannelFactory;
import org.rschat.net.ConnectionHandler;
import org.rschat.net.codec.PacketDecoder;
import org.rschat.net.codec.PacketEncoder;

public class Server {

	private static ServerBootstrap bootstrap;

	static {
		bootstrap = new ServerBootstrap(new NioServerSocketChannelFactory(
				Executors.newCachedThreadPool(),
				Executors.newCachedThreadPool()));
		bootstrap.setPipelineFactory(new ChannelPipelineFactory() {
			@Override
			public ChannelPipeline getPipeline() throws Exception {
				return Channels.pipeline(new PacketEncoder(),
						new PacketDecoder(), new ConnectionHandler());
			}
		});
	}

	public static void main(String[] args) {
		bootstrap.bind(new InetSocketAddress(Constants.PORT));
		System.out.println("Server listening on port " + Constants.PORT + ".");

	}

}

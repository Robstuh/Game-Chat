package org.rschat.net.codec;

import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.buffer.ChannelBuffers;
import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.handler.codec.oneone.OneToOneEncoder;
import org.rschat.net.Packet;

public class PacketEncoder extends OneToOneEncoder {

	@Override
	protected Object encode(ChannelHandlerContext arg0, Channel session,
			Object message) throws Exception {
		Packet packet = (Packet) message;
		ChannelBuffer buffer = ChannelBuffers.directBuffer(packet.getLength());
		buffer.writeBytes(packet.getBuffer());
		return buffer;
	}

}
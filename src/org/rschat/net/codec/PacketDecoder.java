package org.rschat.net.codec;

import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.handler.codec.frame.FrameDecoder;
import org.rschat.net.Packet;

public class PacketDecoder extends FrameDecoder {

	@Override
	protected Object decode(ChannelHandlerContext context, Channel session,
			ChannelBuffer in) throws Exception {
		if (in.readableBytes() < 2) {
			return null;
		}
		byte[] buffer = null;
		in.markReaderIndex();
		int packetLength = in.readShort();
		if (in.readableBytes() >= packetLength) {
			buffer = new byte[packetLength];
			in.readBytes(buffer, 0, buffer.length);
		} else {
			in.resetReaderIndex();
			return null;
		}
		return new Packet(buffer);
	}

}
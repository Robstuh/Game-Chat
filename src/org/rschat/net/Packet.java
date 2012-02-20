package org.rschat.net;

import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.buffer.ChannelBuffers;

public class Packet {

	private byte[] inBuffer;
	private ChannelBuffer outBuffer;
	private int caretPosition;

	public Packet(int opcode) {
		this.outBuffer = ChannelBuffers.dynamicBuffer(32);
		this.addByte(opcode);
	}

	public Packet(byte[] inBuffer) {
		this.inBuffer = inBuffer;
	}

	public Packet addByte(int i) {
		outBuffer.writeByte(i);
		caretPosition++;
		return this;
	}

	public int getByte() {
		return inBuffer[caretPosition++] & 0xff;
	}

	public Packet addBoolean(boolean val) {
		addByte(val == true ? 1 : 0);
		return this;
	}

	public boolean getBoolean() {
		return getByte() == 1;
	}

	public Packet addShort(int i) {
		outBuffer.writeShort(i);
		caretPosition += 2;
		return this;
	}

	public int getShort() {
		return (getByte() << 8) | getByte();
	}

	public Packet addInt(int i) {
		outBuffer.writeInt(i);
		caretPosition += 4;
		return this;
	}

	public int getInt() {
		return (getShort() << 16) | getShort();
	}

	public Packet addLong(long l) {
		outBuffer.writeLong(l);
		caretPosition += 8;
		return this;
	}

	public long getLong() {
		long value = 0;
		value |= (long) getByte() << 56L;
		value |= (long) getByte() << 48L;
		value |= (long) getByte() << 40L;
		value |= (long) getByte() << 32L;
		value |= (long) getByte() << 24L;
		value |= (long) getByte() << 16L;
		value |= (long) getByte() << 8L;
		value |= getByte();
		return value;

	}

	public Packet addString(String s) {
		for (byte b : s.getBytes()) {
			addByte(b);
		}
		addByte(10);
		return this;
	}

	public String getString() {
		int c;
		StringBuilder builder = new StringBuilder();
		while ((c = getByte()) != 10) {
			builder.append((char) c);
		}
		return builder.toString();
	}

	public byte[] getBuffer() {
		byte[] newBuffer = new byte[caretPosition + 2];
		newBuffer[0] = (byte) (caretPosition >> 8);
		newBuffer[1] = (byte) caretPosition;
		System.arraycopy(outBuffer.array(), 0, newBuffer, 2, caretPosition);
		return newBuffer;
	}

	public int getLength() {
		return caretPosition + 2;
	}

}
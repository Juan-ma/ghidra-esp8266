package ghidraesp8266_2;

import java.io.IOException;

import ghidra.app.util.bin.BinaryReader;
import ghidra.app.util.bin.StructConverter;
import ghidra.program.model.data.DataType;
import ghidra.program.model.data.Structure;
import ghidra.program.model.data.StructureDataType;
import ghidra.util.Msg;
import ghidra.util.exception.DuplicateNameException;

public class ESP8266Header_2 implements StructConverter {

	private byte magic_1;
	private byte magic_2;
	private short config;
	private long entry;
	private long unused;
	private long textSegmentLength;
	
	public ESP8266Header_2(BinaryReader reader) throws IOException {
		Msg.info(this, "Start parsing header_2");
		magic_1 = reader.readNextByte();
		magic_2 = reader.readNextByte();
		Msg.info(this, String.format("Magic_1 = %02x", magic_1));
		Msg.info(this, String.format("Magic_2 = %02x", magic_2));
		if (ESP8266Constants.ESP_MAGIC_BASE_1 != getMagic_1() || ESP8266Constants.ESP_MAGIC_BASE_2 != getMagic_2()){
			throw new IOException("Magic error: not an ESP8266 file.");
		}
		config = reader.readNextShort();
		Msg.info(this, String.format("Config = %04x", config));
		entry = reader.readNextInt();
		Msg.info(this, String.format("Entry = %08x", entry));
		unused = reader.readNextInt();
		Msg.info(this, String.format("Unused = %08x", unused));
		textSegmentLength = reader.readNextInt();
		Msg.info(this, String.format("Irom text segment length = %08x", textSegmentLength));
	}

	@Override
	public DataType toDataType() throws DuplicateNameException, IOException {
		Structure structure = new StructureDataType("header_2_item", 0);
		structure.add(BYTE, 1, "magic_1", null);
		structure.add(BYTE, 1, "magic_2", null);
		structure.add(BYTE, 2, "config", null);
		structure.add(BYTE, 4, "entry", null);
		structure.add(BYTE, 4, "unused", null);
		structure.add(BYTE, 4, "textSegmentLength", null);
		return structure;
	}
	
	public byte getMagic_1() {
		return magic_1;
	}
	
	public byte getMagic_2() {
		return magic_2;
	}
	
	public void setMagic_1(byte magic_1) {
		this.magic_1 = magic_1;
	}
	
	public void setMagic_2(byte magic_2) {
		this.magic_2 = magic_2;
	}
	
	public short getConfig( ) {
		return config;
	}

	public void setConfig(short config) {
		this.config = config;
	}

	public long getEntry() {
		return entry;
	}

	public void setEntry(long entry) {
		this.entry = entry;
	}
	
	public long getUnused() {
		return unused;
	}

	public void setUnused(long unused) {
		this.unused = unused;
	}

	public long getTextSegmentLength() {
		return textSegmentLength;
	}

	public void setTextSegmentLength(long textSegmentLength) {
		this.textSegmentLength = textSegmentLength;
	}
}

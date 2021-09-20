package ghidraesp8266_2;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import ghidra.app.util.bin.BinaryReader;
import ghidra.util.Msg;

public class ESP8266Module  {
	
	private ESP8266Header header;
	private ESP8266Header userheader;
	private ESP8266Header_2 userheader_2;
	private List<ESP8266Section> sections = new ArrayList<ESP8266Section>();
	private byte check_magic;
	
	public ESP8266Module(BinaryReader reader) throws IOException {
		header = new ESP8266Header(reader);
		for(int i=0; i < header.getSegmentCount(); ++i) {
			sections.add(new ESP8266Section(reader));
		}
		// Parse user ROM
		reader.setPointerIndex(0x1000);
		check_magic = reader.readNextByte();
		reader.setPointerIndex(0x1000);
		
		Msg.info(this, "Parsing ROM");
		
		
		// Check header's version magic = 0xe9 --> old version; magic = 0xea --> new version 
		if(check_magic == ESP8266Constants.ESP_MAGIC_BASE_1){
			Msg.info(this, String.format("user_header: new version, magic = %02x", check_magic));
			userheader_2 = new ESP8266Header_2(reader);
			Msg.info(this, String.format("Text segment length = %02x", userheader_2.getTextSegmentLength()));
			reader.setPointerIndex(0x1000 + 0x01 + 0x0F + userheader_2.getTextSegmentLength());
			userheader = new ESP8266Header(reader);
			Msg.info(this, String.format("Read old user_header = %02x", userheader.getMagic()));
		}
		else{
			Msg.info(this, String.format("user_header: old version, magic = %02x", check_magic));
			userheader = new ESP8266Header(reader);
		}
		
		for(int i=0; i < userheader.getSegmentCount(); ++i) {
			Msg.info(this, "Adding section");
			// WARNING: quiza aqui hay que volver a la posicion del reader de despues del header, o sea:
			// Creo que no hay  que volver
			// reader.setPointerIndex(0x1000 + 0x08);
			sections.add(new ESP8266Section(reader));
		}
	}

	public ESP8266Section getSection(int id) {
		return sections.get(id);
	}
	
	public ESP8266Header getHeader() {
		return header;
	}
	
	public List<ESP8266Section> getSections() {
		return sections;
	}
}

package de.slackspace.openkeepass.xml;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import de.slackspace.openkeepass.domain.KeePassFile;

public class KeePassDatabaseXmlParser {

    public KeePassFile fromXml(InputStream inputStream) {
        ObjectMapper xmlMapper = new XmlMapper();
        try {
            return xmlMapper.readValue(inputStream, KeePassFile.class);
        } catch (JsonParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (JsonMappingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }

    public ByteArrayOutputStream toXml(KeePassFile keePassFile) {
        ObjectMapper xmlMapper = new XmlMapper();
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        try {
            xmlMapper.writeValue(outputStream, keePassFile);
        } catch (JsonGenerationException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (JsonMappingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return outputStream;
    }
}

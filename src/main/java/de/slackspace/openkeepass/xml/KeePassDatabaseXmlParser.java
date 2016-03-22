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
import de.slackspace.openkeepass.exception.KeePassDatabaseUnreadable;
import de.slackspace.openkeepass.exception.KeePassDatabaseUnwriteable;

public class KeePassDatabaseXmlParser {

    private static final String COULD_NOT_DESERIALIZE = "Could not deserialize KeePassFile from XML";
    private static final String COULD_NOT_SERIALIZE = "Could not serialize KeePassFile to XML";

    public KeePassFile fromXml(InputStream inputStream) {
        ObjectMapper xmlMapper = new XmlMapper();
        try {
            return xmlMapper.readValue(inputStream, KeePassFile.class);
        } catch (JsonParseException e) {
            throw new KeePassDatabaseUnreadable(COULD_NOT_DESERIALIZE, e);
        } catch (JsonMappingException e) {
            throw new KeePassDatabaseUnreadable(COULD_NOT_DESERIALIZE, e);
        } catch (IOException e) {
            throw new KeePassDatabaseUnreadable(COULD_NOT_DESERIALIZE, e);
        }
    }

    public ByteArrayOutputStream toXml(KeePassFile keePassFile) {
        ObjectMapper xmlMapper = new XmlMapper();
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        try {
            xmlMapper.writeValue(outputStream, keePassFile);
        } catch (JsonGenerationException e) {
            throw new KeePassDatabaseUnwriteable(COULD_NOT_SERIALIZE, e);
        } catch (JsonMappingException e) {
            throw new KeePassDatabaseUnwriteable(COULD_NOT_SERIALIZE, e);
        } catch (IOException e) {
            throw new KeePassDatabaseUnwriteable(COULD_NOT_SERIALIZE, e);
        }

        return outputStream;
    }
}

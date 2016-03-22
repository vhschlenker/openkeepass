package de.slackspace.openkeepass.xml;

import java.io.IOException;
import java.util.UUID;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdScalarDeserializer;

import de.slackspace.openkeepass.util.ByteUtils;

/**
 * This class is a Jackson adapter to read UUIDs from xml.
 *
 */
public class UUIDDeserializer extends StdScalarDeserializer<UUID> {

    public UUIDDeserializer() {
        super(UUID.class);
    }

    /* (non-Javadoc)
     * @see com.fasterxml.jackson.databind.JsonDeserializer#deserialize(com.fasterxml.jackson.core.JsonParser, com.fasterxml.jackson.databind.DeserializationContext)
     */
    @Override
    public UUID deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JsonProcessingException {
        return ByteUtils.bytesToUUID(p.getBinaryValue());
    }

}

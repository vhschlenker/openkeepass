package de.slackspace.openkeepass.xml;

import java.io.IOException;
import java.util.UUID;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdScalarDeserializer;

import de.slackspace.openkeepass.util.ByteUtils;

public class UUIDDeserializer extends StdScalarDeserializer<UUID> {

    public UUIDDeserializer() {
        super(UUID.class);
    }

    @Override
    public UUID deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JsonProcessingException {
        return ByteUtils.bytesToUUID(p.getBinaryValue());
    }

}

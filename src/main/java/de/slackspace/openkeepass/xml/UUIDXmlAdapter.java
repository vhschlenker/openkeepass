package de.slackspace.openkeepass.xml;

import java.io.IOException;
import java.util.UUID;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdScalarSerializer;

import de.slackspace.openkeepass.util.ByteUtils;

/**
 * This class is a JAXB adapter to transform UUIDs to/from xml using JAXB.
 * <p>
 * This works because JAXB is representing bytes by default as Base64 in xml.
 *
 */
public class UUIDXmlAdapter extends StdScalarSerializer<UUID> {

    public UUIDXmlAdapter() {
        super(UUID.class);
    }

    @Override
    public void serialize(UUID value, JsonGenerator gen, SerializerProvider provider) throws IOException {
        gen.writeBinary(ByteUtils.uuidToBytes(value));
    }

}

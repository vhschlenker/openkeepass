package de.slackspace.openkeepass.xml;

import java.io.IOException;
import java.util.UUID;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdScalarSerializer;

import de.slackspace.openkeepass.util.ByteUtils;

/**
 * This class is a Jackson adapter to transform UUIDs to xml.
 *
 */
public class UUIDSerializer extends StdScalarSerializer<UUID> {

    public UUIDSerializer() {
        super(UUID.class);
    }

    /* (non-Javadoc)
     * @see com.fasterxml.jackson.databind.ser.std.StdSerializer#serialize(java.lang.Object, com.fasterxml.jackson.core.JsonGenerator, com.fasterxml.jackson.databind.SerializerProvider)
     */
    @Override
    public void serialize(UUID value, JsonGenerator gen, SerializerProvider provider) throws IOException {
        gen.writeBinary(ByteUtils.uuidToBytes(value));
    }

}

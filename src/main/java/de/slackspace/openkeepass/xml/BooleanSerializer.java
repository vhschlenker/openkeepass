package de.slackspace.openkeepass.xml;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.NonTypedScalarSerializerBase;

/**
 * This class is a Jackson adapter to transform boolean values to xml.
 *
 */
public class BooleanSerializer extends NonTypedScalarSerializerBase<Boolean> {

    public BooleanSerializer() {
        super(Boolean.class);
    }

    @Override
    public void serialize(Boolean value, JsonGenerator gen, SerializerProvider provider) throws IOException {
        if (value != null && value) {
            gen.writeString("True");
        }
        else {
            gen.writeString("False");
        }
    }
}

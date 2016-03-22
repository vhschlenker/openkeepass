package de.slackspace.openkeepass.xml;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.junit.Test;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import de.slackspace.openkeepass.domain.EntryBuilder;
import de.slackspace.openkeepass.domain.Group;
import de.slackspace.openkeepass.domain.GroupBuilder;
import de.slackspace.openkeepass.domain.KeePassFile;
import de.slackspace.openkeepass.domain.KeePassFileBuilder;

public class KeePassDatabaseXmlParserTest {

    @Test
    public void fu() {
        Group root = new GroupBuilder().addEntry(new EntryBuilder("First entry").build())
                .addGroup(new GroupBuilder("Banking").build())
                .addGroup(new GroupBuilder("Internet")
                        .addGroup(
                                new GroupBuilder("Shopping").addEntry(new EntryBuilder("Second entry").build()).build())
                        .build())
                .build();

        KeePassFile keePassFile = new KeePassFileBuilder("writeTreeDB").addTopGroups(root).build();


        ObjectMapper xmlMapper = new XmlMapper();
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        try {
            FileOutputStream fos = new FileOutputStream("d:\\complete.xml");
            xmlMapper.writeValue(fos, keePassFile);
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

    }
}

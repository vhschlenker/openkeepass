package de.slackspace.openkeepass.parser;

import java.io.FileInputStream;
import java.io.IOException;

import org.junit.Assert;
import org.junit.Test;

import de.slackspace.openkeepass.domain.KeyFile;
import de.slackspace.openkeepass.parser.KeyFileXmlParser;
import de.slackspace.openkeepass.util.StreamUtils;

public class KeyFileXmlParserTest {

    @Test
    public void whenInputIsKeyFileShouldParseFileAndReturnCorrectData() throws IOException {
        FileInputStream fileInputStream = new FileInputStream(this.getClass().getClassLoader().getResource("DatabaseWithKeyfile.key").getPath());
        byte[] keyFileContent = StreamUtils.toByteArray(fileInputStream);

        KeyFile keyFile = new KeyFileXmlParser().fromXml(keyFileContent);

        Assert.assertEquals("RP+rYNZL4lrGtDMBPzOuctlh3NAutSG5KGsT38C+qPQ=", keyFile.getKey().getData());
    }
}

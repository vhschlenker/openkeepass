package de.slackspace.openkeepass.domain;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Root implements GroupContainer {

    @JacksonXmlProperty(localName = "Group")
    @JacksonXmlElementWrapper(useWrapping = false)
    private List<Group> groups = new ArrayList<Group>();

    Root() {
    }

    public Root(RootBuilder rootBuilder) {
        this.groups = rootBuilder.groups;
    }

    public List<Group> getGroups() {
        return groups;
    }
}

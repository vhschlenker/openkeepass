package de.slackspace.openkeepass.domain;

import java.util.ArrayList;
import java.util.List;

public class RootBuilder {

    List<Group> groups = new ArrayList<Group>();

    public RootBuilder() {
    }

    public RootBuilder(Root root) {
        if (root == null) {
            throw new IllegalArgumentException("Parameter root must not be null");
        }

        this.groups = root.getGroups();
    }

    public RootBuilder addGroup(Group group) {
        groups.add(group);
        return this;
    }

    public RootBuilder removeGroup(Group group) {
        groups.remove(group);
        return this;
    }

    public Root build() {
        return new Root(this);
    }

}

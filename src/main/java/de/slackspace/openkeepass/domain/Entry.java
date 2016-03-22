package de.slackspace.openkeepass.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.UUIDSerializer;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

import de.slackspace.openkeepass.xml.UUIDDeserializer;

/**
 * Represents an entry in the KeePass database. It typically consists of a
 * title, username and a password.
 *
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Entry implements KeePassFileElement {

    private static final String USER_NAME = "UserName";
    private static final String NOTES = "Notes";
    private static final String URL = "URL";
    private static final String PASSWORD = "Password";
    private static final String TITLE = "Title";
    private static final List<String> PROPERTY_KEYS = new ArrayList<String>();

    static {
        PROPERTY_KEYS.add(USER_NAME);
        PROPERTY_KEYS.add(NOTES);
        PROPERTY_KEYS.add(URL);
        PROPERTY_KEYS.add(PASSWORD);
        PROPERTY_KEYS.add(TITLE);
    }

    @JacksonXmlProperty(localName = "UUID")
    @JsonSerialize(using=UUIDSerializer.class)
    @JsonDeserialize(using=UUIDDeserializer.class)
    private UUID uuid;

    @JacksonXmlProperty(localName = "IconID")
    private int iconId = 0;

    private transient byte[] iconData;

    @JacksonXmlProperty(localName = "CustomIconUUID")
    @JsonSerialize(using=UUIDSerializer.class)
    @JsonDeserialize(using=UUIDDeserializer.class)
    private UUID customIconUUID;

    @JacksonXmlProperty(localName = "String")
    @JacksonXmlElementWrapper(useWrapping = false)
    private List<Property> properties = new ArrayList<Property>();

    @JacksonXmlProperty(localName = "History")
    private History history;

    Entry() {
        this.uuid = UUID.randomUUID();
    }

    public Entry(EntryBuilder builder) {
        this.history = builder.history;
        this.uuid = builder.uuid;
        this.iconData = builder.iconData;
        this.iconId = builder.iconId;
        this.customIconUUID = builder.customIconUUID;

        setValue(false, NOTES, builder.notes);
        setValue(true, PASSWORD, builder.password);
        setValue(false, TITLE, builder.title);
        setValue(false, USER_NAME, builder.username);
        setValue(false, URL, builder.url);

        this.properties.addAll(builder.customPropertyList);
    }

    public UUID getUuid() {
        return uuid;
    }

    /**
     * Returns the icon id of this group.
     *
     * @return the icon id of this group
     */
    public int getIconId() {
        return iconId;
    }

    /**
     * Retrieves the custom icon of this group.
     *
     * @return the uuid of the custom icon or null
     */
    @JsonIgnore
    public UUID getCustomIconUuid() {
        return customIconUUID;
    }

    /**
     * Returns the raw data of either the custom icon (if specified) or the
     * chosen stock icon.
     *
     * @return the raw icon data if available or null otherwise
     */
    @JsonIgnore
    public byte[] getIconData() {
        return iconData;
    }

    public List<Property> getProperties() {
        return properties;
    }

    @JsonIgnore
    public List<Property> getCustomProperties() {
        List<Property> customProperties = new ArrayList<Property>();

        for (Property property : properties) {
            if (!PROPERTY_KEYS.contains(property.getKey())) {
                customProperties.add(property);
            }
        }

        return customProperties;
    }

    @JsonIgnore
    public String getTitle() {
        return getValueFromProperty(TITLE);
    }

    @JsonIgnore
    public String getPassword() {
        return getValueFromProperty(PASSWORD);
    }

    @JsonIgnore
    public String getUrl() {
        return getValueFromProperty(URL);
    }

    @JsonIgnore
    public String getNotes() {
        return getValueFromProperty(NOTES);
    }

    @JsonIgnore
    public String getUsername() {
        return getValueFromProperty(USER_NAME);
    }

    @JsonIgnore
    public boolean isTitleProtected() {
        return getPropertyByName(TITLE).isProtected();
    }

    @JsonIgnore
    public boolean isPasswordProtected() {
        return getPropertyByName(PASSWORD).isProtected();
    }

    private void setValue(boolean isProtected, String propertyName, String propertyValue) {
        Property property = getPropertyByName(propertyName);
        if (property == null) {
            property = new Property(propertyName, propertyValue, isProtected);
            properties.add(property);
        } else {
            properties.remove(property);
            properties.add(new Property(propertyName, propertyValue, isProtected));
        }
    }

    private String getValueFromProperty(String name) {
        Property property = getPropertyByName(name);
        if (property != null) {
            return property.getValue();
        }

        return null;
    }

    /**
     * Retrieves a property by it's name (ignores case)
     *
     * @param name
     *            the name of the property to find
     * @return the property if found, null otherwise
     */
    public Property getPropertyByName(String name) {
        for (Property property : properties) {
            if (property.getKey().equalsIgnoreCase(name)) {
                return property;
            }
        }

        return null;
    }

    public History getHistory() {
        return history;
    }

    @Override
    public final int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((history == null) ? 0 : history.hashCode());
        result = prime * result + ((properties == null) ? 0 : properties.hashCode());
        result = prime * result + ((uuid == null) ? 0 : uuid.hashCode());
        return result;
    }

    @Override
    public final boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (!(obj instanceof Entry))
            return false;
        Entry other = (Entry) obj;
        if (history == null) {
            if (other.history != null)
                return false;
        } else if (!history.equals(other.history))
            return false;
        if (properties == null) {
            if (other.properties != null)
                return false;
        } else if (!properties.equals(other.properties))
            return false;
        if (uuid == null) {
            if (other.uuid != null)
                return false;
        } else if (!uuid.equals(other.uuid))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Entry [uuid=" + uuid + ", getTitle()=" + getTitle() + ", getPassword()=" + getPassword() + ", getUsername()=" + getUsername() + "]";
    }

}

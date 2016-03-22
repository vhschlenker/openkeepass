package de.slackspace.openkeepass.domain;

import java.util.Calendar;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

/**
 * Represents statistical information of an {@link Entry}.
 *
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Times {

    @JacksonXmlProperty(localName = "LastModificationTime")
    private Calendar lastModificationTime;

    @JacksonXmlProperty(localName = "CreationTime")
    private Calendar creationTime;

    @JacksonXmlProperty(localName = "LastAccessTime")
    private Calendar lastAccessTime;

    @JacksonXmlProperty(localName = "ExpiryTime")
    private Calendar expiryTime;

    @JacksonXmlProperty(localName = "Expires")
    private Boolean expires;

    @JacksonXmlProperty(localName = "UsageCount")
    private int usageCount;

    @JacksonXmlProperty(localName = "LocationChanged")
    private Calendar locationChanged;

    Times() {
    }

    public Times(TimesBuilder timesBuilder) {
        this.creationTime = timesBuilder.creationTime;
        this.expires = timesBuilder.expires;
        this.expiryTime = timesBuilder.expiryTime;
        this.lastAccessTime = timesBuilder.lastAccessTime;
        this.lastModificationTime = timesBuilder.lastModificationTime;
        this.locationChanged = timesBuilder.locationChanged;
        this.usageCount = timesBuilder.usageCount;
    }

    public Calendar getLastModificationTime() {
        return lastModificationTime;
    }

    public Calendar getCreationTime() {
        return creationTime;
    }

    public Calendar getLastAccessTime() {
        return lastAccessTime;
    }

    public Calendar getExpiryTime() {
        return expiryTime;
    }

    public boolean expires() {
        return expires;
    }

    public int getUsageCount() {
        return usageCount;
    }

    public Calendar getLocationChanged() {
        return locationChanged;
    }

    @Override
    public final int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((creationTime == null) ? 0 : creationTime.hashCode());
        result = prime * result + ((expires == null) ? 0 : expires.hashCode());
        result = prime * result + ((expiryTime == null) ? 0 : expiryTime.hashCode());
        result = prime * result + ((lastAccessTime == null) ? 0 : lastAccessTime.hashCode());
        result = prime * result + ((lastModificationTime == null) ? 0 : lastModificationTime.hashCode());
        result = prime * result + ((locationChanged == null) ? 0 : locationChanged.hashCode());
        result = prime * result + usageCount;
        return result;
    }

    @Override
    public final boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (!(obj instanceof Times))
            return false;
        Times other = (Times) obj;
        if (creationTime == null) {
            if (other.creationTime != null)
                return false;
        } else if (!creationTime.equals(other.creationTime))
            return false;
        if (expires == null) {
            if (other.expires != null)
                return false;
        } else if (!expires.equals(other.expires))
            return false;
        if (expiryTime == null) {
            if (other.expiryTime != null)
                return false;
        } else if (!expiryTime.equals(other.expiryTime))
            return false;
        if (lastAccessTime == null) {
            if (other.lastAccessTime != null)
                return false;
        } else if (!lastAccessTime.equals(other.lastAccessTime))
            return false;
        if (lastModificationTime == null) {
            if (other.lastModificationTime != null)
                return false;
        } else if (!lastModificationTime.equals(other.lastModificationTime))
            return false;
        if (locationChanged == null) {
            if (other.locationChanged != null)
                return false;
        } else if (!locationChanged.equals(other.locationChanged))
            return false;
        if (usageCount != other.usageCount)
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Times [lastModificationTime=" + lastModificationTime + ", creationTime=" + creationTime + "]";
    }

}

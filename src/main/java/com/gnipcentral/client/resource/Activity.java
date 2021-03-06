package com.gnipcentral.client.resource;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;

import javax.xml.bind.annotation.*;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import java.util.GregorianCalendar;

@XmlRootElement(name = "activity")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "activityType")
public class Activity implements Resource {

    private static DatatypeFactory datatypeFactory;

    static {
        try {
            datatypeFactory = DatatypeFactory.newInstance();
        } catch (DatatypeConfigurationException e) {
            throw new RuntimeException(e);
        }
    }

    @XmlAttribute(required = true)
    private XMLGregorianCalendar at;
    @XmlAttribute(required=true)
    private String actor;
    @XmlAttribute(required=true)
    private String action;
    @XmlAttribute
    @XmlSchemaType(name = "anyURI")    
    private String url;
    @XmlAttribute
    private String to;
    @XmlAttribute
    private String regarding;
    @XmlAttribute
    private String source;
    @XmlAttribute
    @XmlSchemaType(name="csvType")
    private String tags;
    @XmlElement(required = false)
    private Payload payload;

    @SuppressWarnings({"UnusedDeclaration"})
    private Activity() {
        // empty constructor for jaxb
    }

    public Activity(String actor, String action) {
        this.at = toXMLGregorianCalendar(new DateTime());
        this.actor = actor;
        this.action = action;
    }

    public Activity(String actor, String action, Payload payload) {
        this.at = toXMLGregorianCalendar(new DateTime());
        this.actor = actor;
        this.action = action;
        this.payload = payload;
    }

    public Activity(DateTime at, String actor, String action, String url, String to,
                    String regarding, String source, String tags, Payload payload) {
        this.at = toXMLGregorianCalendar(at);
        this.actor = actor;
        this.action = action;
        this.url = url;
        this.to = to;
        this.regarding = regarding;
        this.source = source;
        this.tags = tags;
        this.payload = payload;
    }

    public DateTime getAt() {
        return fromXMLGregorianCalendar(at);
    }

    public String getActor() {
        return actor;
    }

    public String getAction() {
        return action;
    }

    public String getUrl() {
        return url;
    }

    public String getTo() {
        return to;
    }

    public String getRegarding() {
        return regarding;
    }

    public String getSource() {
        return source;
    }

    public String getTags() {
        return tags;
    }

    public Payload getPayload() {
        return payload;
    }

    public String getValue(RuleType ruleType) {
        switch (ruleType) {
            case ACTOR:
                return actor;
            case REGARDING:
                return regarding;
            case SOURCE:
                return source;
            case TAG:
                return tags;
            case TO:
                return to;
        }
        return null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Activity activity = (Activity) o;

        if (!action.equals(activity.action)) return false;
        if (!actor.equals(activity.actor)) return false;
        if (!at.equals(activity.at)) return false;
        if (regarding != null ? !regarding.equals(activity.regarding) : activity.regarding != null) return false;
        if (source != null ? !source.equals(activity.source) : activity.source != null) return false;
        if (tags != null ? !tags.equals(activity.tags) : activity.tags != null) return false;
        if (to != null ? !to.equals(activity.to) : activity.to != null) return false;
        if (url != null ? !url.equals(activity.url) : activity.url != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = at.hashCode();
        result = 31 * result + actor.hashCode();
        result = 31 * result + action.hashCode();
        result = 31 * result + (url != null ? url.hashCode() : 0);
        result = 31 * result + (to != null ? to.hashCode() : 0);
        result = 31 * result + (regarding != null ? regarding.hashCode() : 0);
        result = 31 * result + (source != null ? source.hashCode() : 0);
        result = 31 * result + (tags != null ? tags.hashCode() : 0);
        return result;
    }

    private static XMLGregorianCalendar toXMLGregorianCalendar(DateTime dateTime) {
        return datatypeFactory.newXMLGregorianCalendar(dateTime.toGregorianCalendar());
    }

    private static DateTime fromXMLGregorianCalendar(XMLGregorianCalendar xmlTime) {
        GregorianCalendar calendar = xmlTime.toGregorianCalendar();
        return new DateTime(calendar.getTimeInMillis(), DateTimeZone.forTimeZone(calendar.getTimeZone()));
    }
}
package com.example.arif.spinnerwithdatabase;

/**
 * Created by Arif on 13/10/16.
 */
public class Information {
    String name,subject,topics;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getTopics() {
        return topics;
    }

    public void setTopics(String topics) {
        this.topics = topics;
    }

    Information(String name, String subject, String topics){
        this.name=name;
        this.subject=subject;
        this.topics=topics;

    }
}

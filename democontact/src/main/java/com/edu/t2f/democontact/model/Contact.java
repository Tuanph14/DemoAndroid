package com.edu.t2f.democontact.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tuan on 06/06/2016.
 */
public class Contact {

    long _id;
    List<Phone> phones;
    List<Email> emails;
    String displayName;

    public Contact() {
        phones = new ArrayList<>();
        emails = new ArrayList<>();
    }

    public void addPhone(Phone phone){
        phones.add(phone);
    }
    public void addEmail(Email email){
        emails.add(email);
    }

    public long get_id() {
        return _id;
    }

    public void set_id(long _id) {
        this._id = _id;
    }

    public List<Phone> getPhones() {
        return phones;
    }

    public void setPhones(List<Phone> phones) {
        this.phones = phones;
    }

    public List<Email> getEmails() {
        return emails;
    }

    public void setEmails(List<Email> emails) {
        this.emails = emails;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }
}

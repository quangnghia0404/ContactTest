package vn.edu.ntu.quangnghia.controller;

import java.util.List;

import vn.edu.ntu.quangnghia.model.Contact;

public interface IContactController {
    public List<Contact> getAllContact();
    public void addContact(Contact contact);
    public int layID();
    public void updateContact(int postion, Contact contact);

}

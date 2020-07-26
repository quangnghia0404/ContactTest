package vn.edu.ntu.quangnghia.controller;

import android.app.Application;

import java.util.ArrayList;
import java.util.List;

import vn.edu.ntu.quangnghia.model.Contact;

public class ContactController extends Application implements IContactController {
    List<Contact> listContacts = new ArrayList<>();

    public ContactController() {
        listContacts.add(new Contact(1,"Luan","07/07/1999","Nha Trang","123456789"));
        listContacts.add(new Contact(2,"Luan1","07/07/1999","Nha Trang","123456789"));
        listContacts.add(new Contact(3,"Luan2","07/07/1999","Nha Trang","123456789"));
        listContacts.add(new Contact(4,"Luan3","07/07/1999","Nha Trang","123456789"));
    }

    @Override
    public List<Contact> getAllContact() {
        return listContacts;
    }

    @Override
    public void addContact(Contact contact) {
        listContacts.add(contact);
    }

    @Override
    public int layID() {
        return listContacts.get(listContacts.size() - 1).getId() +1;
    }

    @Override
    public void updateContact(int postion, Contact contact) {
        listContacts.get(postion).setName(contact.getName());
        listContacts.get(postion).setAddress(contact.getAddress());
        listContacts.get(postion).setPhoneNumber(contact.getPhoneNumber());
        listContacts.get(postion).setBirthday(contact.getBirthday());
    }
}

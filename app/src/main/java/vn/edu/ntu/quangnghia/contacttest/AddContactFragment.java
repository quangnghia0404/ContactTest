package vn.edu.ntu.quangnghia.contacttest;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import vn.edu.ntu.quangnghia.controller.IContactController;
import vn.edu.ntu.quangnghia.model.Contact;

public class AddContactFragment extends Fragment {
    EditText edtTen, edtNgaySinh, edtSDT, edtDiaChi, edtID;
    Button btnSua;
    IContactController controller;
    NavController navController;
    List<Contact> listContacts = new ArrayList<>();
    int position;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_add_contact, container, false);
        addViews(view);
        addEvents();
        return view;
    }

    private void addEvents() {
        btnSua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(position == -1){
                    Contact contact = new Contact();
                    contact.setId(controller.layID());
                    contact.setName(edtTen.getText().toString());
                    contact.setBirthday(edtNgaySinh.getText().toString());
                    contact.setPhoneNumber(edtSDT.getText().toString());
                    contact.setAddress(edtDiaChi.getText().toString());
                    controller.addContact(contact);
                    Toast.makeText(getActivity(),"Đã thêm thành công",Toast.LENGTH_LONG).show();

                }

                else{
                    Contact contact = new Contact();
                    contact.setId(controller.layID());
                    contact.setName(edtTen.getText().toString());
                    contact.setBirthday(edtNgaySinh.getText().toString());
                    contact.setPhoneNumber(edtSDT.getText().toString());
                    contact.setAddress(edtDiaChi.getText().toString());
                    controller.updateContact(position,contact);
                    Toast.makeText(getActivity(),"Đã sửa thành công",Toast.LENGTH_LONG).show();
                }
            }
        });

    }

    private void addViews(View view) {
        edtID = view.findViewById(R.id.edtID);
        edtTen = view.findViewById(R.id.edtTen);
        edtNgaySinh = view.findViewById(R.id.edtNgaySinh);
        edtSDT = view.findViewById(R.id.edtSDT);
        edtDiaChi = view.findViewById(R.id.edtDiaChi);
        btnSua = view.findViewById(R.id.btnSua);

        controller = (IContactController)((MainActivity)getActivity()).getApplication();
        listContacts = controller.getAllContact();

        navController= NavHostFragment.findNavController(AddContactFragment.this);
        ((MainActivity)getActivity()).navController = navController;
        ((MainActivity)getActivity()).toolbar.setSubtitle("Detal off friends");
        ((MainActivity)getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Bundle bundle = getArguments();
        position = bundle.getInt("key");

        if(position == -1){
            edtID.setText(Integer.toString(controller.layID()));
            btnSua.setText("Thêm");
        }
        else{
            Contact contact = listContacts.get(position);
            edtID.setText(Integer.toString(contact.getId()) );
            edtTen.setText(contact.getName());
            edtDiaChi.setText(contact.getAddress());
            edtNgaySinh.setText(contact.getBirthday());
            edtSDT.setText(contact.getPhoneNumber());
            btnSua.setText("Sửa");

        }

    }

    @Override
    public void onPause() {
        super.onPause();
        ((MainActivity)getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(false);
    }
}
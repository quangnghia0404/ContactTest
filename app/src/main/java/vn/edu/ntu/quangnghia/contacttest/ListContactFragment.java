package vn.edu.ntu.quangnghia.contacttest;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.zip.Inflater;

import vn.edu.ntu.quangnghia.controller.IContactController;
import vn.edu.ntu.quangnghia.model.Contact;

public class ListContactFragment extends Fragment {
    RecyclerView rvlist;
    IContactController controller;
    NavController navController;
    ContactAdapter adapter;

    List<Contact> listContacts = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_list_contact, container, false);
        addView(view);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.my_mnu,menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if(id == R.id.itemAdd){
            Bundle bundle = new Bundle();
            bundle.putInt("key",-1);
            navController.navigate(R.id.action_listContactFragment_to_addContactFragment,bundle);
        }
        return super.onOptionsItemSelected(item);
    }

    private void addView(View view) {
        rvlist = view.findViewById(R.id.rvlist);
        controller = (IContactController) ((MainActivity)getActivity()).getApplication();
        listContacts = controller.getAllContact();
        adapter = new ContactAdapter(listContacts);

        rvlist.setLayoutManager(new LinearLayoutManager(getActivity()));
        rvlist.setAdapter(adapter);

        navController= NavHostFragment.findNavController(ListContactFragment.this);
        ((MainActivity)getActivity()).navController = navController;
        ((MainActivity)getActivity()).toolbar.setSubtitle("List of friends");
    }

    public class ContactViewHolder extends RecyclerView.ViewHolder{
        TextView txtName, txtPhone, txtBirth;
        ImageView imgEdit;


        public ContactViewHolder(@NonNull View itemView) {
            super(itemView);

            txtName =  itemView.findViewById(R.id.txtName);
            txtPhone =  itemView.findViewById(R.id.txtPhone);
            txtBirth =  itemView.findViewById(R.id.txtBirth);
            imgEdit =  itemView.findViewById(R.id.imgEdit);
        }

        public void bind(Contact contact){
            txtName.setText(contact.getName());
            txtPhone.setText(contact.getPhoneNumber());
            txtBirth.setText(contact.getBirthday());
        }
    }

    public class ContactAdapter extends RecyclerView.Adapter<ContactViewHolder>{
        List<Contact> listContacts = new ArrayList<>();

        public ContactAdapter(List<Contact> listContacts) {
            this.listContacts = listContacts;
        }

        @NonNull
        @Override
        public ContactViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            LayoutInflater inflater = getLayoutInflater();
            View view = inflater.inflate(R.layout.contact,parent,false);
            return new ContactViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull ContactViewHolder holder, final int position) {
            holder.bind(listContacts.get(position));
            holder.imgEdit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Bundle bundle = new Bundle();
                    bundle.putInt("key",position);
                    navController.navigate(R.id.action_listContactFragment_to_addContactFragment,bundle);
                }
            });
        }

        @Override
        public int getItemCount() {
            return listContacts.size();
        }
    }
}
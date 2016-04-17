package com.example.michal.rentmate.ui.apartment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.michal.rentmate.R;
import com.example.michal.rentmate.model.pojo.Apartment;
import com.example.michal.rentmate.model.repositories.ApartmentRepository;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Michal on 05/04/2016.
 */
public class MyApptListFragment extends Fragment {

  @Bind(R.id.recyclerView_apartment_list)
  RecyclerView recyclerView;
  @Bind(R.id.add_first_apartment_layout)
  RelativeLayout addFirstAptLayout;
  @Bind(R.id.add_first_apartment_button)
  FloatingActionButton addFirstAptFAB;

  private AptAdapter adapter;
  private Callbacks callbacks;


  public interface Callbacks {
    void onApartmentSelected();
  }

  public static MyApptListFragment newInstance() {
    return new MyApptListFragment();

  }

  @Override
  public void onAttach(Context context) {
    super.onAttach(context);
    callbacks = (Callbacks) context;
  }

  @Override
  public void onDetach() {
    super.onDetach();
    callbacks = null;
  }

  @Nullable
  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.fragment_my_appt_list, container, false);
    setHasOptionsMenu(true);
    ButterKnife.bind(this, view);

    addFirstAptFAB.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        callbacks.onApartmentSelected();
      }
    });
    recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
    updateUi();
    return view;
  }

  @Override
  public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
    super.onCreateOptionsMenu(menu, inflater);
    inflater.inflate(R.menu.fragment_apartment_list_menu, menu);
  }

  private void updateUi() {
    ApartmentRepository repository = ApartmentRepository.getInstance();
    final List<Apartment> apartmentList = repository.getApartmentList();

    if (adapter == null) {
      adapter = new AptAdapter(apartmentList);
      recyclerView.setAdapter(adapter);
    }
    else
      adapter.notifyDataSetChanged();

    if (apartmentList.size() > 0) {
      addFirstAptLayout.setVisibility(View.GONE);

    }
    else {
      addFirstAptLayout.setVisibility(View.VISIBLE);
      addFirstAptFAB.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
          Apartment apartment = new Apartment();
          ApartmentRepository.getInstance().getApartmentList().add(apartment);
          callbacks.onApartmentSelected();

        }
      });

    }
  }


  public class ApartmentHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    @Bind(R.id.list_item_textview_apartment_name)
    TextView aptName;
    @Bind(R.id.list_item_textview_apartment_address)
    TextView aptAddress;


    public ApartmentHolder(View itemView) {
      super(itemView);
      itemView.setOnClickListener(this);
      ButterKnife.bind(this, itemView);

    }

    public void onBind(Apartment apartment) {
      aptName.setText(apartment.getAddress());
      aptAddress.setText(apartment.getPostalCode());
    }

    @Override
    public void onClick(View v) {
      callbacks.onApartmentSelected();
    }
  }

  private class AptAdapter extends RecyclerView.Adapter<ApartmentHolder> {

    private List<Apartment> aptList;

    public AptAdapter(List<Apartment> aptList) {
      this.aptList = aptList;

    }


    @Override
    public ApartmentHolder onCreateViewHolder(ViewGroup parent, int viewType) {
      LayoutInflater inflater = LayoutInflater.from(getActivity());
      View view = inflater.inflate(R.layout.apartment_view_in_recyclerview, parent, false);
      return new ApartmentHolder(view);
    }

    @Override
    public void onBindViewHolder(ApartmentHolder holder, int position) {
      Apartment apartment = aptList.get(position);
      holder.onBind(apartment);
    }

    @Override
    public int getItemCount() {
      return aptList.size();
    }
  }


}

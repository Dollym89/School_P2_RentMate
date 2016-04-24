package com.example.michal.rentmate.ui.homescreen;

import android.content.Context;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.michal.rentmate.R;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Michal on 24/04/2016.
 */
public class NoticeFragment extends Fragment {

  public static NoticeFragment newInstance() {
    return new NoticeFragment();
  }

  private HomeScreenContract.Callbacks callbacks;

  @Bind(R.id.notice_home_welcome_text)
  TextView welcomText;
  @Bind(R.id.claims_to_solve_carview)
  CardView claimsCardView;
  @Bind(R.id.apartments_to_fill_cardview)
  CardView apartmentsCardView;


  @Override
  public void onAttach(Context context) {
    super.onAttach(context);
    callbacks = (HomeScreenContract.Callbacks) context;
  }

  @Override
  public void onDetach() {
    super.onDetach();
    callbacks = null;
  }

  @Nullable
  @Override
  public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.frag_notice, container, false);
    ButterKnife.bind(this, view);
    setFancyText(welcomText);

    return view;
  }


  //  Listeners
  @OnClick(R.id.claims_to_solve_carview)
  public void openClaims() {
    callbacks.openClaimList();
  }
  @OnClick(R.id.apartments_to_fill_cardview)
  public void openApartments(){
    callbacks.openApartmentList();
  }


  private void setFancyText(TextView welcomeText) {
    Typeface fancyOne = Typeface.createFromAsset(getActivity().getAssets(), "fonts/Roboto-Thin.ttf");
    welcomeText.setTypeface(fancyOne);
  }


}
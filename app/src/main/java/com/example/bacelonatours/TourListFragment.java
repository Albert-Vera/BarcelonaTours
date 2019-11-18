package com.example.bacelonatours;


import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.bacelonatours.model.BarceloninaResponse;
import com.example.bacelonatours.model.Tour;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class TourListFragment extends Fragment  {

    TourAdapter tourAdapter;
    MainViewModel mainViewModel;
    List<Tour> tourList = new ArrayList<>();
    TextView txtMessage;   /// cosas del Fade IN
    Animation animFadein;
    public TourListFragment() {}


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_tour_list, container, false);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mainViewModel = ViewModelProviders.of(this).get(MainViewModel.class);

        RecyclerView recyclerView = view.findViewById(R.id.itemList);
        recyclerView.addItemDecoration(new DividerItemDecoration(recyclerView.getContext(), DividerItemDecoration.HORIZONTAL));

        tourAdapter = new TourAdapter();
        recyclerView.setAdapter(tourAdapter);

        mainViewModel.obtenerTours().observe(this, new Observer<BarceloninaResponse>() {
            @Override
            public void onChanged(BarceloninaResponse barceloninaResponse) {
                tourList =  barceloninaResponse.tours;
                tourAdapter.notifyDataSetChanged();
            }
        });

//        txtMessage = (TextView) view.findViewById(R.id.tour_desc);
//        // load the animation
//        animFadein = AnimationUtils.loadAnimation(requireContext(),
//                R.drawable.animationfade);

    }

    class TourViewHolder extends RecyclerView.ViewHolder {
        TextView name, desc;
        ImageView tourImage1,tourImage2,tourImage3,tourImage4, imagenArray;
        ListView imageList;

        TourViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.tour_name);
            //desc = itemView.findViewById(R.id.tour_desc);

            //for (int i = 0; i <tourimages.length ; i++) {
                //String imgxml = "R.id.tour_image"+ (Integer.toString(i));
            tourImage1 = itemView.findViewById(R.id.tour_image1);
            tourImage2 = itemView.findViewById(R.id.tour_image2);
            tourImage3 = itemView.findViewById(R.id.tour_image3);
            tourImage4 = itemView.findViewById(R.id.tour_image4);
            //}


        }
    }

    class TourAdapter extends RecyclerView.Adapter<TourViewHolder>{

        @NonNull
        @Override
        public TourViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new TourViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_tour, parent, false));
        }

        @Override
        public void onBindViewHolder(@NonNull TourViewHolder holder, final int position) {

            final Tour tour = tourList.get(position);

            holder.name.setText(tour.tourName);
            Log.e("ABCD", " lala" + tour.tourImages.get(0));

//            Glide.with(requireActivity()).load(tour.tourImages.get(0)).into(holder.tourImage1);


            for (int i = 0; i < tour.tourImages.size() ; i++) {

                switch (i) {
                    case 0:

                        holder.imagenArray = holder.tourImage1;
                        break;
                    case 1:
                        holder.imagenArray = holder.tourImage2;
                        break;
                    case 2:
                        holder.imagenArray = holder.tourImage3;
                        break;
                    case 3:
                        holder.imagenArray = holder.tourImage4;
                        break;
                }


                if (tour.tourImages.get(i) != null) {
                    Glide.with(requireActivity()).load(tour.tourImages.get(i)).into(holder.imagenArray);
                } else {
                    holder.imagenArray.setVisibility(View.GONE);
                }
//
//
//                holder.imagenArray.setOnTouchListener(new View.OnTouchListener() {
//                    @Override
//                    public boolean onTouch(View v, MotionEvent event) {
//                        mainViewModel.tourId = tour.tourId;
//                        String s = "POR QUÉ TOCAS ?";
//                        new AlertDialog.Builder(requireContext()).setTitle(tour.tourName)
//                                .setMessage("precio tal y cual")
//                                .setCancelable(true)
//                                .create()
//                                .show();
//                        return true;
//                    }
//
//
//                });
            }

            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mainViewModel.tourId = tour.tourId;

                    Navigation.findNavController(view).navigate(R.id.detailFragment);
                }
            });
        }

        @Override
        public int getItemCount() {
            return tourList.size();
        }
    }
}

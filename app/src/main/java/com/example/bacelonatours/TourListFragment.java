package com.example.bacelonatours;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.bacelonatours.model.BarceloninaResponse;
import com.example.bacelonatours.model.Tour;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class TourListFragment extends Fragment {

    TourAdapter tourAdapter;
    MainViewModel mainViewModel;
    List<Tour> tourList = new ArrayList<>();

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
    }

    class TourViewHolder extends RecyclerView.ViewHolder {
        TextView name, desc;
        ImageView imageTour;

        TourViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.tour_name);
            desc = itemView.findViewById(R.id.tour_desc);
            imageTour = itemView.findViewById(R.id.tour_image);
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
            holder.desc.setText(tour.tourDescription);
            Glide.with(requireActivity()).load(tour.tourImage).into(holder.imageTour);

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

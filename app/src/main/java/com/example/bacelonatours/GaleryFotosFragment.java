package com.example.bacelonatours;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.RecyclerView;


import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.bacelonatours.model.BarceloninaResponse;
import com.example.bacelonatours.model.Tour;

import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class GaleryFotosFragment extends Fragment {


    private MainViewModel mainViewModel;
    private GalleryAdapter galleryAdapter;

    public GaleryFotosFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_galery_fotos, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mainViewModel = ViewModelProviders.of(requireActivity()).get(MainViewModel.class);

        RecyclerView recyclerView = view.findViewById(R.id.recycler_gallery_fotos);
//        recyclerView.addItemDecoration(new DividerItemDecoration(recyclerView.getContext(), DividerItemDecoration.HORIZONTAL));

        galleryAdapter = new GalleryAdapter();
        recyclerView.setAdapter(galleryAdapter);

        mainViewModel.obtenerGalleria().observe(this, new Observer<BarceloninaResponse>() {
            @Override
            public void onChanged(BarceloninaResponse barceloninaResponse) {
                galleryAdapter.establecerListaTours(barceloninaResponse.tours);
            }
        });
    }

    class GalleryAdapter extends RecyclerView.Adapter<GalleryAdapter.GalleryViewHolder>{

        List<Tour> tourList;

        @NonNull
        @Override
        public GalleryAdapter.GalleryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new GalleryAdapter.GalleryViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_galery_foto, parent, false));
        }

        @Override
        public void onBindViewHolder(@NonNull final GalleryAdapter.GalleryViewHolder holder, final int position) {

            final Tour tour = tourList.get(position);

            //holder.name.setText(tour.tourName);
            holder.nameGalery.setText(tour.tourName);
            Log.e("ABCD", " lala " + tour.tourName);
            Glide.with(requireActivity()).load(tour.tourImages).into(holder.imageItemGalery);
            holder.ratingBar.setRating(0.5f);

            holder.imageItemGalery.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mainViewModel.tour.setValue(tour);

                    Navigation.findNavController(view).navigate(R.id.galeryFotosFragment);
                }
            });
        }

        @Override
        public int getItemCount() {
            return tourList.size();
        }

        void establecerListaTours(List<Tour> t){
            tourList = t;
            notifyDataSetChanged();
        }


        class GalleryViewHolder extends RecyclerView.ViewHolder {
            TextView nameGalery;
            ImageView imageItemGalery;
            RatingBar ratingBar;

            GalleryViewHolder(@NonNull View itemView) {
                super(itemView);
                nameGalery = itemView.findViewById(R.id.textview_nombre);
                imageItemGalery = itemView.findViewById(R.id.imageview_photo);
                ratingBar = itemView.findViewById(R.id.ratingbar_valoracion);
            }
        }
    }
}

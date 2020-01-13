package com.example.bacelonatours;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.Navigation;
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
 * Esto de momento tiene un nombre cambiado....
 * Clase para tourSeleccionado mas bien valorados
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


        ////  PRUEBA DE ARRASTRE DE IMAGENES  //////////


//        final float mLastTouchX, mLastTouchY, mPosX, mPosY;
//        final ScaleGestureDetector mScaleDetector = new ScaleGestureDetector(this, new ScaleListener());
//
//        view.setOnTouchListener(new View.OnTouchListener() {
//
//            // The ‘active pointer’ is the one currently moving our object.
//            private int mActivePointerId = INVALID_POINTER_ID;
//
//
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
//
//                return false;
//            }
//
//            @Override
//            public boolean onTouchEvent(MotionEvent ev) {
//                // Let the ScaleGestureDetector inspect all events.
//                mScaleDetector.onTouchEvent(ev);
//
//                final int action = MotionEventCompat.getActionMasked(ev);
//
//                switch (action) {
//                    case MotionEvent.ACTION_DOWN: {
//                        final int pointerIndex = MotionEventCompat.getActionIndex(ev);
//                        final float x = MotionEventCompat.getX(ev, pointerIndex);
//                        final float y = MotionEventCompat.getY(ev, pointerIndex);
//
//                        // Remember where we started (for dragging)
//                        mLastTouchX = x;
//                        mLastTouchY = y;
//                        // Save the ID of this pointer (for dragging)
//                        mActivePointerId = MotionEventCompat.getPointerId(ev, 0);
//                        break;
//                    }
//
//                    case MotionEvent.ACTION_MOVE: {
//                        // Find the index of the active pointer and fetch its position
//                        final int pointerIndex =
//                                MotionEventCompat.findPointerIndex(ev, mActivePointerId);
//
//                        final float x = MotionEventCompat.getX(ev, pointerIndex);
//                        final float y = MotionEventCompat.getY(ev, pointerIndex);
//
//                        // Calculate the distance moved
//                        final float dx = x - mLastTouchX;
//                        final float dy = y - mLastTouchY;
//
//                        mPosX += dx;
//                        mPosY += dy;
//
//                        invalidate();
//
//                        // Remember this touch position for the next move event
//                        mLastTouchX = x;
//                        mLastTouchY = y;
//
//                        break;
//                    }
//
//                    case MotionEvent.ACTION_UP: {
//                        mActivePointerId = INVALID_POINTER_ID;
//                        break;
//                    }
//
////                    case MotionEvent.ACTION_CANCEL: {
////                        mActivePointerId = INVALID_POINTER_ID;
////                        break;
////                    }
//
//
//                    case MotionEvent.ACTION_POINTER_UP: {
//
//                        final int pointerIndex = MotionEventCompat.getActionIndex(ev);
//                        final int pointerId = MotionEventCompat.getPointerId(ev, pointerIndex);
//
//                        if (pointerId == mActivePointerId) {
//                            // This was our active pointer going up. Choose a new
//                            // active pointer and adjust accordingly.
//                            final int newPointerIndex = pointerIndex == 0 ? 1 : 0;
//                            mLastTouchX = MotionEventCompat.getX(ev, newPointerIndex);
//                            mLastTouchY = MotionEventCompat.getY(ev, newPointerIndex);
//                            mActivePointerId = MotionEventCompat.getPointerId(ev, newPointerIndex);
//                        }
//                        break;
//                    }
//                }
//                return true;
//
//            }
//        });
    }
        class GalleryAdapter extends RecyclerView.Adapter<GalleryAdapter.GalleryViewHolder> {

            List<Tour> tourList;

            @NonNull
            @Override
            public GalleryAdapter.GalleryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                return new GalleryAdapter.GalleryViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_galery_foto, parent, false));
            }

            @Override
            public void onBindViewHolder(@NonNull final GalleryAdapter.GalleryViewHolder holder, final int position) {

                final Tour tour = tourList.get(position);

                //holder.name.setText(tourSeleccionado.tourName);
                holder.nameGalery.setText(tour.tourName);
                Log.e("ABCD", " lala " + tour.tourName);
                Glide.with(requireActivity()).load(tour.tourImages).into(holder.imageItemGalery);
                holder.ratingBar.setRating(0.5f);

                holder.imageItemGalery.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        mainViewModel.tourSeleccionado.setValue(tour);

                        Navigation.findNavController(view).navigate(R.id.galeryFotosFragment);
                    }
                });
            }

            @Override
            public int getItemCount() {
                return tourList.size();
            }

            void establecerListaTours(List<Tour> t) {
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

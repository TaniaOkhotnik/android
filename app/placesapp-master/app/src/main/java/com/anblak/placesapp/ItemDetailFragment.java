package com.anblak.placesapp;

import android.app.Activity;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.anblak.placesapp.models.Comment;
import com.anblak.placesapp.models.Place;

import java.util.Objects;

/**
 * A fragment representing a single Item detail screen.
 * This fragment is either contained in a {@link ItemListActivity}
 * in two-pane mode (on tablets) or a {@link ItemDetailActivity}
 * on handsets.
 */
public class ItemDetailFragment extends Fragment {
    /**
     * The fragment argument representing the item ID that this fragment
     * represents.
     */
    public static final String ARG_ITEM_ID = "item_id";
    private static final String TAG = "MyActivity";
    /**
     * The dummy content this fragment is presenting.
     */
    private static Place mItem;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public ItemDetailFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments().containsKey(ARG_ITEM_ID)) {
            // Load the dummy content specified by the fragment
            // arguments. In a real-world scenario, use a Loader
            // to load content from a content provider.
            Log.v(TAG, getArguments().getString(ARG_ITEM_ID));
            for (Place place:
                 ItemListActivity.PLACES) {
                if(place.getDescription().contains(Objects.requireNonNull(getArguments().getString(ARG_ITEM_ID))))
                    mItem = place;
            }
            Activity activity = this.getActivity();
            CollapsingToolbarLayout appBarLayout = (CollapsingToolbarLayout) activity.findViewById(R.id.toolbar_layout);
            if (appBarLayout != null) {
                appBarLayout.setTitle(mItem.getName());
            }
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.item_detail, container, false);

        StringBuilder commentsBlock = new StringBuilder();
        if (mItem != null) {
            for(Comment comment: mItem.getComments()) {
                commentsBlock.append(comment.getUser().getLogin())
                        .append("\n")
                        .append(comment.getComment())
                        .append("\n\n");
            }
            ((TextView) rootView.findViewById(R.id.item_detail)).setText(
                    String.format("%s\nAverage Rating : %s\nComments : \n%s", mItem.getDescription(),
                            mItem.getAvgRating(), commentsBlock.toString())
            );
        }

        return rootView;
    }

    public static Place getmItem() {
        return mItem;
    }

    public static void setmItem(Place mItem) {
        ItemDetailFragment.mItem = mItem;
    }
}

package com.example.api_in_wild_ad;

import android.content.ClipData;
import android.content.ClipDescription;
import android.os.Build;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.api_in_wild_ad.databinding.FragmentItemListBinding;
import com.example.api_in_wild_ad.databinding.ItemListContentBinding;

import com.example.api_in_wild_ad.placeholder.PlaceholderContent;

import java.util.List;

/**
 * A fragment representing a list of Items. This fragment
 * has different presentations for handset and larger screen devices. On
 * handsets, the fragment presents a list of items, which when touched,
 * lead to a {@link ItemDetailFragment} representing
 * item details. On larger screens, the Navigation controller presents the list of items and
 * item details side-by-side using two vertical panes.
 */
public class ItemListFragment extends Fragment {

    ProgressBar pgB;
    View whiteBar;

    /**
     * Method to intercept global key events in the
     * item list fragment to trigger keyboard shortcuts
     * Currently provides a toast when Ctrl + Z and Ctrl + F
     * are triggered
     */
    /**ViewCompat.OnUnhandledKeyEventListenerCompat unhandledKeyEventListenerCompat = (v, event) -> {
        if (event.getKeyCode() == KeyEvent.KEYCODE_Z && event.isCtrlPressed()) {
            Toast.makeText(
                    v.getContext(),
                    "Undo (Ctrl + Z) shortcut triggered",
                    Toast.LENGTH_LONG
            ).show();
            return true;
        } else if (event.getKeyCode() == KeyEvent.KEYCODE_F && event.isCtrlPressed()) {
            Toast.makeText(
                    v.getContext(),
                    "Find (Ctrl + F) shortcut triggered",
                    Toast.LENGTH_LONG
            ).show();
            return true;
        }
        return false;
    };*/

    private FragmentItemListBinding binding;

    private static HearthstoneAPIVolleyContent hearthstoneAPIVolleyContent = new HearthstoneAPIVolleyContent();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentItemListBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        pgB = view.findViewById(R.id.progressBar);
        whiteBar = view.findViewById(R.id.whiteBarThing);
        if(!hearthstoneAPIVolleyContent.doneLoading){
            hearthstoneAPIVolleyContent.doneLoading = true;
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    pgB.setVisibility(View.GONE);
                    whiteBar.setVisibility(View.GONE);
                }
            }, 10000);
        }
        else{
            pgB.setVisibility(View.GONE);
            whiteBar.setVisibility(View.GONE);
        }
        //ViewCompat.addOnUnhandledKeyEventListener(view, unhandledKeyEventListenerCompat);

        RecyclerView recyclerView = binding.itemList;

        // Leaving this not using view binding as it relies on if the view is visible the current
        // layout configuration (layout, layout-sw600dp)
        View itemDetailFragmentContainer = view.findViewById(R.id.item_detail_nav_container);

        setupRecyclerView(recyclerView, itemDetailFragmentContainer);
    }

    private void setupRecyclerView(
            RecyclerView recyclerView,
            View itemDetailFragmentContainer
    ) {

        hearthstoneAPIVolleyContent.HearthStoneAPITesting(getActivity());
        recyclerView.setAdapter(new SimpleItemRecyclerViewAdapter(
                hearthstoneAPIVolleyContent.HEARTHSTONE_API_STUFFS,
                itemDetailFragmentContainer
        ));
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    public static class SimpleItemRecyclerViewAdapter
            extends RecyclerView.Adapter<SimpleItemRecyclerViewAdapter.ViewHolder> {

        private final List<HearthstoneAPIModel> mValues;
        private final View mItemDetailFragmentContainer;

        SimpleItemRecyclerViewAdapter(List<HearthstoneAPIModel> items,
                                      View itemDetailFragmentContainer) {
            mValues = items;
            mItemDetailFragmentContainer = itemDetailFragmentContainer;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

            ItemListContentBinding binding =
                    ItemListContentBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
            return new ViewHolder(binding);

        }

        @Override
        public void onBindViewHolder(final ViewHolder holder, int position) {
            //Title of the thing in list
            holder.mIdView.setText(mValues.get(position).getClassName());
            //Extra details about it
            //holder.mContentView.setText(mValues.get(position).content);

            holder.itemView.setTag(mValues.get(position));
            holder.itemView.setOnClickListener(itemView -> {
                PlaceholderContent.PlaceholderItem item =
                        (PlaceholderContent.PlaceholderItem) itemView.getTag();
                Bundle arguments = new Bundle();
                arguments.putString(ItemDetailFragment.ARG_ITEM_ID, item.id);
                if (mItemDetailFragmentContainer != null) {
                    Navigation.findNavController(mItemDetailFragmentContainer)
                            .navigate(R.id.fragment_item_detail, arguments);
                } else {
                    Navigation.findNavController(itemView).navigate(R.id.show_item_detail, arguments);
                }
            });
        }

        @Override
        public int getItemCount() {
            return mValues.size();
        }

        class ViewHolder extends RecyclerView.ViewHolder {
            final TextView mIdView;
            final TextView mContentView;

            ViewHolder(ItemListContentBinding binding) {
                super(binding.getRoot());
                mIdView = binding.idText;
                mContentView = binding.content;
            }

        }
    }
}
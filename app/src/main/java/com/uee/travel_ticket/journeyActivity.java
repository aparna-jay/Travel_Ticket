package com.uee.travel_ticket;

import androidx.appcompat.app.AppCompatActivity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;

import com.uee.travel_ticket.Models.journeyclass;

import java.util.ArrayList;
import java.util.List;


public class journeyActivity extends AppCompatActivity {

    String names[] = {"Apple", "Banana", "Kiwi", "Oranges", "Watermelon"};
    String emails[] = {"This is apple", "This is banana", "This is kiwi", "This is oranges", "This is watermelon"};
    List<journeyclass> itemsModelList = new ArrayList<>();
    ListView listView;
    CustomAdapter customAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_journey);

        listView = findViewById(R.id.listview);

        for (int i = 0; i < names.length; i++) {
            journeyclass itemsModel = new journeyclass(names[i], emails[i]);
            itemsModelList.add(itemsModel);
        }
        customAdapter = new CustomAdapter(itemsModelList, this);
        listView.setAdapter(customAdapter);


//        @Override
//        public boolean onCreateOptionsMenu(Menu menu) {
//            getMenuInflater().inflate(R.menu.menu,menu);
//            MenuItem menuItem = menu.findItem(R.id.searchView);
//            SearchView searchView = (SearchView) menuItem.getActionView();
//            searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
//                @Override
//                public boolean onQueryTextSubmit(String query) {
//                    return false;
//                }
//                @Override
//                public boolean onQueryTextChange(String newText) {
//                    Log.e("Main"," data search"+newText);
//                    customAdapter.getFilter().filter(newText);
//                    return true;
//                }
//            });
//            return true;
//        }


//        @Override
//        public boolean onOptionsItemSelected (@NonNull MenuItem item){
//            int id = item.getItemId();
//            if (id == R.id.searchView) {
//                return true;
//            }
//            return super.onOptionsItemSelected(item);
//        }
//
    }


    public class CustomAdapter extends BaseAdapter implements Filterable {
        private List<journeyclass> itemsModelsl;
        private List<journeyclass> itemsModelListFiltered;
        private Context context;


        public CustomAdapter(List<journeyclass> itemsModelsl, Context context) {
            this.itemsModelsl = itemsModelsl;
            this.itemsModelListFiltered = itemsModelsl;
            this.context = context;
        }


        @Override
        public int getCount() {
            return itemsModelListFiltered.size();
        }


        @Override
        public Object getItem(int position) {
            return itemsModelListFiltered.get(position);
        }


        @Override
        public long getItemId(int position) {
            return position;
        }


        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            View view = getLayoutInflater().inflate(R.layout.my_journey_row, null);

            TextView names = view.findViewById(R.id.name);
            TextView emails = view.findViewById(R.id.email);

            names.setText(itemsModelListFiltered.get(position).getName());
            emails.setText(itemsModelListFiltered.get(position).getStart());


            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
//                        Log.e("main activity","item clicked");
//                        startActivity(new Intent(MainActivity.this,ItemsPreviewActivity.class).putExtra("items",itemsModelListFiltered.get(position)));
//
                }
            });
            return view;
        }


        @Override
        public Filter getFilter() {
            Filter filter = new Filter() {
                @Override
                protected FilterResults performFiltering(CharSequence constraint) {
                    int resultcount = 0;
                    FilterResults filterResults = new FilterResults();
                    if (constraint == null || constraint.length() == 0) {
                        filterResults.count = itemsModelsl.size();
                        filterResults.values = itemsModelsl;


                    } else {
                        List<journeyclass> resultsModel = new ArrayList<>();
                        String searchStr = constraint.toString().toLowerCase();


                        for (journeyclass itemsModel : itemsModelsl) {
                            if (itemsModel.getName().contains(searchStr)) {
                                resultsModel.add(itemsModel);
                                filterResults.count = resultsModel.size();
                                filterResults.values = resultsModel;
                                resultcount++;
                            }
                        }
                        if (resultcount == 0) {
                            filterResults.count = itemsModelsl.size();
                            filterResults.values = itemsModelsl;
                        }
                    }


                    return filterResults;
                }

                @Override
                protected void publishResults(CharSequence constraint, FilterResults results) {
                    itemsModelListFiltered = (List<journeyclass>) results.values;


                    notifyDataSetChanged();
                }
            };
            return filter;
        }
    }
}

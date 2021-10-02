package com.uee.travel_ticket;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

    import com.uee.travel_ticket.Models.journeyclass;

    import java.util.List;

    public class JourneyListAdapter extends ArrayAdapter<journeyclass> {
        private Activity context;
        List<journeyclass> tracks;

        public JourneyListAdapter(Activity context, List<journeyclass> tracks) {
            super(context, R.layout.my_journey_row, tracks);
            this.context = context;
            this.tracks = tracks;
        }


        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            LayoutInflater inflater = context.getLayoutInflater();
            View listViewItem = inflater.inflate(R.layout.my_journey_row, null, true);

            TextView textViewName = (TextView) listViewItem.findViewById(R.id.journeyID);
            TextView textViewRating = (TextView) listViewItem.findViewById(R.id.start);
            TextView end = (TextView) listViewItem.findViewById(R.id.end);
            TextView date = (TextView) listViewItem.findViewById(R.id.date);
            TextView time = (TextView) listViewItem.findViewById(R.id.time);

            journeyclass track = tracks.get(position);
            textViewName.setText(track.getJourneyID());
            textViewRating.setText(String.valueOf(track.getStart()));
            end.setText(String.valueOf(track.getEnd()));
            date.setText(String.valueOf(track.getDate()));
            time.setText(String.valueOf(track.getTime()));

            return listViewItem;
        }
    }


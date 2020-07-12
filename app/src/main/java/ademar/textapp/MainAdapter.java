package ademar.textapp;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import ademar.textapp.MainViewHolder.ReceivedMainViewHolder;
import ademar.textapp.MainViewHolder.SentMainViewHolder;

class MainAdapter extends RecyclerView.Adapter<MainViewHolder> {

    private static final int VIEW_TYPE_SENT = 1;
    private static final int VIEW_TYPE_RECEIVED = 2;

    private List<TextEntry> entries;

    public MainAdapter(List<TextEntry> entries) {
        this.entries = entries;
        setHasStableIds(true);
    }

    @NonNull
    @Override
    public MainViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        MainViewHolder holder;
        if (viewType == VIEW_TYPE_SENT) {
            holder = new SentMainViewHolder(inflater.inflate(R.layout.message_sent, parent, false));
        } else {
            holder = new ReceivedMainViewHolder(inflater.inflate(R.layout.message_received, parent, false));
        }
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MainViewHolder holder, int position) {
        holder.bind(entries.get(position));
    }

    @Override
    public long getItemId(int position) {
        return entries.get(position).hashCode();
    }

    @Override
    public int getItemViewType(int position) {
        return entries.get(position).userIsAuthor ? VIEW_TYPE_SENT : VIEW_TYPE_RECEIVED;
    }

    @Override
    public int getItemCount() {
        return entries.size();
    }

    public void setEntries(List<TextEntry> entries) {
        this.entries.clear();
        this.entries.addAll(entries);
        notifyDataSetChanged();
    }
}

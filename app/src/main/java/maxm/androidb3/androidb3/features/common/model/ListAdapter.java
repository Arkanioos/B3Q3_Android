package maxm.androidb3.androidb3.features.common.model;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.androidb3.R;

import java.util.List;

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ListViewHolder> {
    private List<ListItem> itemsList = null;
    private AdapterHandler handler = null;

    public ListAdapter(List<ListItem> itemsList, AdapterHandler handler){
        this.itemsList = itemsList;
        this.handler = handler;
    }

    public static class ListViewHolder extends RecyclerView.ViewHolder{
        private TextView name = null;

        public ListViewHolder(View itemView, List<ListItem> items, AdapterHandler handler){
            super(itemView);
            LinearLayout layout = itemView.findViewById(R.id.listItem);
            name = itemView.findViewById(R.id.name);

            layout.setOnClickListener(v -> {
                int position = getBindingAdapterPosition();
                ListItem clickedItem = items.get(position);
                handler.onItemClicked(clickedItem);
            });
        }
    }

    @Override
    public ListViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item, parent, false);
        return new ListViewHolder(view, itemsList, handler);
    }

    @Override
    public void onBindViewHolder(ListViewHolder holder, int position){
        ListItem item = itemsList.get(position);
        holder.name.setText(item.toString());
    }

    @Override
    public int getItemCount(){ return itemsList.size(); }

    public void addItem(ListItem item){
        itemsList.add(item);
        notifyItemInserted(itemsList.size() - 1);
    }

    public List<ListItem> getItems() { return itemsList; }
}

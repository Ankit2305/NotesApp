package com.example.notes;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class NoteAdapter extends ListAdapter<Note, NoteAdapter.NoteViewHolder> {
    private OnItemClickListner listener;

    public NoteAdapter() {
        super(DIFF_CALLBACK);
    }

    private static DiffUtil.ItemCallback<Note> DIFF_CALLBACK = new DiffUtil.ItemCallback<Note>() {

        @Override
        public boolean areItemsTheSame(@NonNull Note oldItem, @NonNull Note newItem) {
            return oldItem.getId() == newItem.getId();
        }

        @Override
        public boolean areContentsTheSame(@NonNull Note oldItem, @NonNull Note newItem) {
            return oldItem.getName().equals(newItem.getName()) &&
                    oldItem.getDescription().equals(newItem.getDescription()) &&
                    oldItem.getPriority() == newItem.getPriority();
        }
    };

    @NonNull
    @Override
    public NoteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new NoteViewHolder(
                LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.note_item, parent, false)
        );
    }

    @Override
    public void onBindViewHolder(@NonNull NoteViewHolder holder, int position) {
        Note currentNote = getItem(position);
        holder.priorityTextView.setText(String.valueOf(currentNote.getPriority()));
        holder.titleTextView.setText(currentNote.getName());
        holder.descriptionTextView.setText(currentNote.getDescription());
    }

    public Note getNoteAt(int position) {
        return getItem(position);
    }

    class NoteViewHolder extends RecyclerView.ViewHolder {
        TextView priorityTextView, titleTextView, descriptionTextView;

        public NoteViewHolder(@NonNull final View itemView) {
            super(itemView);
            priorityTextView = itemView.findViewById(R.id.priority);
            titleTextView = itemView.findViewById(R.id.title);
            descriptionTextView = itemView.findViewById(R.id.description);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onItemClicked(getNoteAt(getAdapterPosition()));
                }
            });
        }
    }

    public interface OnItemClickListner {
        void onItemClicked(Note note);
    }

    public void setOnItemClickListner(OnItemClickListner listener) {
        this.listener = listener;
    }
}

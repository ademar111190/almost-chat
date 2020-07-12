package ademar.textapp;

import android.graphics.Paint;
import android.graphics.Typeface;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

class MainViewHolder extends RecyclerView.ViewHolder {

    private TextView text;

    public MainViewHolder(@NonNull View itemView) {
        super(itemView);
        text = itemView.findViewById(R.id.text);
    }

    public void bind(TextEntry entry) {
        text.setText(entry.text);
        Typeface typeface;
        if (entry.bold && entry.italic) {
            typeface = Typeface.defaultFromStyle(Typeface.BOLD_ITALIC);
        } else if (entry.bold) {
            typeface = Typeface.defaultFromStyle(Typeface.BOLD);
        } else if (entry.italic) {
            typeface = Typeface.defaultFromStyle(Typeface.ITALIC);
        } else {
            typeface = Typeface.defaultFromStyle(Typeface.NORMAL);
        }
        text.setTypeface(typeface);
        text.setPaintFlags(entry.strikeThrough ? Paint.STRIKE_THRU_TEXT_FLAG : 0);
    }

    static class ReceivedMainViewHolder extends MainViewHolder {
        public ReceivedMainViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }

    static class SentMainViewHolder extends MainViewHolder {
        public SentMainViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}

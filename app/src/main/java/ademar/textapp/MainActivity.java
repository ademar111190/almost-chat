package ademar.textapp;

import android.graphics.Paint;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;

import static java.lang.System.currentTimeMillis;

class MainActivity extends AppCompatActivity implements MainView {

    private Toolbar toolbar;
    private EditText input;
    private MainPresenter presenter;
    private ImageView emptyIcon;
    private TextView emptyText;
    private RecyclerView list;
    private MainAdapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        input = findViewById(R.id.input);
        emptyIcon = findViewById(R.id.empty_icon);
        emptyText = findViewById(R.id.empty_text);
        toolbar = findViewById(R.id.toolbar);
        list = findViewById(R.id.list);

        adapter = new MainAdapter(new ArrayList<TextEntry>());
        list.setAdapter(adapter);

        presenter = new MainPresenter(getApplicationContext(), this);

        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                if (item.getItemId() == R.id.clear) {
                    presenter.clear();
                    return true;
                }
                return false;
            }
        });

        findViewById(R.id.bold).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.toggleBold();
            }
        });

        findViewById(R.id.italic).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.toggleItalic();
            }
        });

        findViewById(R.id.strike_through).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.toggleStrikeThrough();
            }
        });

        findViewById(R.id.clear_format).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.clearFormat();
            }
        });

        findViewById(R.id.send).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                send();
            }
        });

        input.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEND) {
                    send();
                    return true;
                }
                return false;
            }
        });
    }

    private void send() {
        presenter.send(input.getText().toString(), currentTimeMillis());
        input.setText("");
    }

    @Override
    public void render(final MainState state) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                emptyIcon.setVisibility(state.empty ? View.VISIBLE : View.INVISIBLE);
                emptyText.setVisibility(state.empty ? View.VISIBLE : View.INVISIBLE);
                toolbar.getMenu().findItem(R.id.clear).setVisible(!state.empty);
                adapter.setEntries(state.entries);
                list.smoothScrollToPosition(state.entries.size());

                Typeface typeface;
                if (state.bold && state.italic) {
                    typeface = Typeface.defaultFromStyle(Typeface.BOLD_ITALIC);
                } else if (state.bold) {
                    typeface = Typeface.defaultFromStyle(Typeface.BOLD);
                } else if (state.italic) {
                    typeface = Typeface.defaultFromStyle(Typeface.ITALIC);
                } else {
                    typeface = Typeface.defaultFromStyle(Typeface.NORMAL);
                }
                input.setTypeface(typeface);
                input.setPaintFlags(state.strikeThrough ? Paint.STRIKE_THRU_TEXT_FLAG : 0);
            }
        });
    }
}

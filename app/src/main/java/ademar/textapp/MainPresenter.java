package ademar.textapp;

import android.content.Context;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import static java.lang.System.currentTimeMillis;

class MainPresenter {

    private final MainView view;
    private final Context context;
    private MainState state;

    MainPresenter(Context context, MainView view) {
        this.context = context;
        this.view = view;
        this.state = new MainState(false, false, false, true, new ArrayList<TextEntry>());
        render();
    }

    public void toggleBold() {
        state = new MainState(!state.bold, state.italic, state.strikeThrough, state.empty, new ArrayList<>(state.entries));
        render();
    }

    public void toggleItalic() {
        state = new MainState(state.bold, !state.italic, state.strikeThrough, state.empty, new ArrayList<>(state.entries));
        render();
    }

    public void toggleStrikeThrough() {
        state = new MainState(state.bold, state.italic, !state.strikeThrough, state.empty, new ArrayList<>(state.entries));
        render();
    }

    public void clearFormat() {
        state = new MainState(false, false, false, state.empty, new ArrayList<>(state.entries));
        render();
    }

    public void clear() {
        state = new MainState(false, false, false, true, new ArrayList<TextEntry>());
        render();
    }

    public void send(String text, long timestamp) {
        if (text.length() == 0) return;
        List<TextEntry> entries = new ArrayList<>(state.entries);
        entries.add(new TextEntry(state.bold, state.italic, state.strikeThrough, true, text, timestamp));
        state = new MainState(state.bold, state.italic, state.strikeThrough, false, entries);
        render();
        simulateSomeoneElseAnswering();
    }

    private void simulateSomeoneElseAnswering() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1000L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                Random random = new Random();
                List<String> answers = Arrays.asList(context.getResources().getStringArray(R.array.answers));
                Collections.shuffle(answers, random);

                List<TextEntry> entries = new ArrayList<>(state.entries);
                entries.add(new TextEntry(random.nextBoolean(), random.nextBoolean(), random.nextBoolean(), false, answers.get(0), currentTimeMillis()));
                state = new MainState(state.bold, state.italic, state.strikeThrough, false, entries);
                render();
            }
        }).start();
    }

    private void render() {
        view.render(state);
    }
}

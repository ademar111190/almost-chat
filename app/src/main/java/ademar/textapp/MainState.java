package ademar.textapp;

import java.util.List;

class MainState {
    final boolean bold;
    final boolean italic;
    final boolean strikeThrough;
    final boolean empty;
    final List<TextEntry> entries;

    public MainState(boolean bold, boolean italic, boolean strikeThrough, boolean empty, List<TextEntry> entries) {
        this.bold = bold;
        this.italic = italic;
        this.strikeThrough = strikeThrough;
        this.empty = empty;
        this.entries = entries;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MainState mainState = (MainState) o;

        if (bold != mainState.bold) return false;
        if (italic != mainState.italic) return false;
        if (strikeThrough != mainState.strikeThrough) return false;
        if (empty != mainState.empty) return false;
        return entries.equals(mainState.entries);
    }

    @Override
    public int hashCode() {
        int result = (bold ? 1 : 0);
        result = 31 * result + (italic ? 1 : 0);
        result = 31 * result + (strikeThrough ? 1 : 0);
        result = 31 * result + (empty ? 1 : 0);
        result = 31 * result + entries.hashCode();
        return result;
    }
}

package ademar.textapp;

class TextEntry {

    final long timestamp;
    final boolean bold;
    final boolean italic;
    final boolean strikeThrough;
    final boolean userIsAuthor;
    final CharSequence text;

    public TextEntry(boolean bold, boolean italic, boolean strikeThrough, boolean userIsAuthor, CharSequence text, long timestamp) {
        this.bold = bold;
        this.italic = italic;
        this.strikeThrough = strikeThrough;
        this.userIsAuthor = userIsAuthor;
        this.text = text;
        this.timestamp = timestamp;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TextEntry textEntry = (TextEntry) o;

        if (timestamp != textEntry.timestamp) return false;
        if (bold != textEntry.bold) return false;
        if (italic != textEntry.italic) return false;
        if (strikeThrough != textEntry.strikeThrough) return false;
        if (userIsAuthor != textEntry.userIsAuthor) return false;
        return text.equals(textEntry.text);
    }

    @Override
    public int hashCode() {
        int result = (int) (timestamp ^ (timestamp >>> 32));
        result = 31 * result + (bold ? 1 : 0);
        result = 31 * result + (italic ? 1 : 0);
        result = 31 * result + (strikeThrough ? 1 : 0);
        result = 31 * result + (userIsAuthor ? 1 : 0);
        result = 31 * result + text.hashCode();
        return result;
    }
}

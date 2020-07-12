package ademar.textapp;

import android.content.Context;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import java.util.Collections;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.MockitoAnnotations.openMocks;

public class MainPresenterTest {

    @Mock Context context;
    @Mock MainView view;

    @Before
    public void setUp() {
        openMocks(this);
    }

    @Test
    public void initialState() {
        make().toggleBold();
        verify(view).render(new MainState(false, false, false, true, Collections.<TextEntry>emptyList()));
    }

    @Test
    public void toggleBold() {
        make().toggleBold();
        verify(view).render(new MainState(true, false, false, true, Collections.<TextEntry>emptyList()));
    }

    @Test
    public void toggleItalic() {
        make().toggleItalic();
        verify(view).render(new MainState(false, true, false, true, Collections.<TextEntry>emptyList()));
    }

    @Test
    public void toggleStrikeThrough() {
        make().toggleStrikeThrough();
        verify(view).render(new MainState(false, false, true, true, Collections.<TextEntry>emptyList()));
    }

    @Test
    public void clearFormat() {
        make().clearFormat();
        verify(view, times(2)).render(new MainState(false, false, false, true, Collections.<TextEntry>emptyList()));
    }

    @Test
    public void clear() {
        make().clear();
        verify(view, times(2)).render(new MainState(false, false, false, true, Collections.<TextEntry>emptyList()));
    }

    @Test
    public void send() {
        make().send("A message", 1);
        verify(view).render(new MainState(false, false, false, false, Collections.singletonList(
                new TextEntry(false, false, false, true, "A message", 1))));
    }

    private MainPresenter make() {
        return new MainPresenter(context, view);
    }
}

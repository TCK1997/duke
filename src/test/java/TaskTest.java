import org.junit.jupiter.api.Test;


import static java.time.LocalDateTime.now;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskTest {
    @Test
    public void testTest() {
        Task a = new Event("Event 1", now());
        Task b = new ToDo("JUST SOME STRING");
        Task c = new Deadline("poplock rocks", now());
        assertEquals(a.getTaskLetter(),"E");
        assertEquals(b.getTaskLetter(),"T");
        assertEquals(c.getTaskLetter(),"D");
    }
}

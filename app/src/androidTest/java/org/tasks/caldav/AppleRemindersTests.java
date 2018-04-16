package org.tasks.caldav;

import static junit.framework.Assert.assertEquals;
import static org.tasks.TestUtilities.vtodo;

import android.support.test.runner.AndroidJUnit4;
import com.todoroo.astrid.data.Task;
import java.util.TimeZone;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.tasks.time.DateTime;

@RunWith(AndroidJUnit4.class)
public class AppleRemindersTests {

  private TimeZone defaultTimeZone = TimeZone.getDefault();

  @Before
  public void before() {
    TimeZone.setDefault(TimeZone.getTimeZone("America/Chicago"));
  }

  @After
  public void after() {
    TimeZone.setDefault(defaultTimeZone);
  }

  @Test
  public void readTitle() {
    assertEquals("Test title", vtodo("apple/basic_no_due_date.txt").getTitle());
  }

  @Test
  public void readDescription() {
    assertEquals("Test description", vtodo("apple/basic_no_due_date.txt").getNotes());
  }

  @Test
  public void readCreationDate() {
    assertEquals(
        new DateTime(2018, 4, 16, 17, 24, 10).getMillis(),
        (long) vtodo("apple/basic_no_due_date.txt").getCreationDate());
  }

  @Test
  public void readDueDate() {
    assertEquals(
        new DateTime(2018, 4, 16, 18, 0, 1, 0).getMillis(),
        (long) vtodo("apple/basic_due_date.txt").getDueDate());
  }

  @Test
  public void completed() {
    assertEquals(
        new DateTime(2018, 4, 17, 13, 43, 2).getMillis(),
        (long) vtodo("apple/basic_completed.txt").getCompletionDate());
  }

  @Test
  public void repeatDaily() {
    assertEquals("RRULE:FREQ=DAILY;INTERVAL=1", vtodo("apple/repeat_daily.txt").getRecurrence());
  }

  @Test
  public void noPriority() {
    assertEquals(Task.IMPORTANCE_NONE, (int) vtodo("apple/priority_none.txt").getImportance());
  }

  @Test
  public void lowPriority() {
    assertEquals(
        Task.IMPORTANCE_SHOULD_DO, (int) vtodo("apple/priority_low.txt").getImportance());
  }

  @Test
  public void mediumPriority() {
    assertEquals(
        Task.IMPORTANCE_MUST_DO, (int) vtodo("apple/priority_medium.txt").getImportance());
  }

  @Test
  public void highPriority() {
    assertEquals(
        Task.IMPORTANCE_DO_OR_DIE, (int) vtodo("apple/priority_high.txt").getImportance());
  }
}

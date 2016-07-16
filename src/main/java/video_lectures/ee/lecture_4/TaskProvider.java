package video_lectures.ee.lecture_4;

import java.util.List;

public interface TaskProvider<T> {

    List<Task<T>> getAllTasks();
}

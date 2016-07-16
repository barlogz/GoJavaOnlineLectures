package video_lectures.ee.lecture_4;

public interface Validator<T> {
    boolean isValid(T result);
}
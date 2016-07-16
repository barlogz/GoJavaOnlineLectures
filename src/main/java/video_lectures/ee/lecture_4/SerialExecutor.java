package video_lectures.ee.lecture_4;

import java.util.ArrayList;
import java.util.List;

public class SerialExecutor<T> implements Executor<T> {

    private List<TaskAndValidator<T>> tasks = new ArrayList<>();
    private List<T> validResults = new ArrayList<T>();
    private List<T> invalidResults = new ArrayList<T>();

    private boolean executed = false;

    private static final Validator<Object> DEFAULT_VALIDATOR = value -> true;

    @Override
    public void addTask(Task<? extends T> task) {
        addTask(task, DEFAULT_VALIDATOR);
    }

    @Override
    public void addTask(Task<? extends T> task, Validator<? super T> validator) {
        checkNotExecuted();
        tasks.add(new TaskAndValidator<T>(task, validator));
    }

    @Override
    public void execute() {
        checkNotExecuted();
        for (TaskAndValidator<T> taskAndValidator : tasks) {
            Task<? extends T> task = taskAndValidator.task;
            task.execute();

            if (taskAndValidator.validator.isValid(task.getResult())) {
                validResults.add(task.getResult());
            } else {
                invalidResults.add(task.getResult());
            }
        }
        executed = true;
    }

    @Override
    public List<T> getValidResults() {
        checkExecuted();
        return validResults;
    }

    @Override
    public List<T> getInvalidResults() {
        checkExecuted();
        return invalidResults;
    }

    private void checkNotExecuted() {
        if (executed) {
            throw new IllegalStateException("Executor already executed");
        }
    }

    private void checkExecuted() {
        if (!executed) {
            throw new IllegalStateException("Executor already executed");
        }
    }

    private static class TaskAndValidator<T> {

        private Task<? extends T> task;
        private Validator<? super T> validator;

        public TaskAndValidator(Task<? extends T> task, Validator<? super T> validator) {
            this.task = task;
            this.validator = validator;
        }
    }
}
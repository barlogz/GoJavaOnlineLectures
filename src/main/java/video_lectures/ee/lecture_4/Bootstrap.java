package video_lectures.ee.lecture_4;

import enterprise.module2.generics.ExecutorImpl;
import enterprise.module2.generics.Task;

public class Bootstrap {
    public static void main(String[] args) {
        ExecutorImpl<Integer> executor = new ExecutorImpl<>();
        executor.addTask(new AddTask(1, -2));
        executor.addTask(new AddTask(1, 2), result -> result >= 0);
        executor.addTask(new AddTask(1, -2), result -> result >= 0);
        executor.addTask(new AddTask(Integer.MAX_VALUE, 1), result -> result >= 0);

        executor.execute();

        System.out.println("Valid results:");
        executor.getValidResults().forEach(System.out::println);
        System.out.println("Invalid results:");
        executor.getInvalidResults().forEach(System.out::println);
    }

    private static class AddTask implements Task<Integer> {

        private int value1;
        private int value2;
        private int result;

        public AddTask(int value1, int value2) {
            this.value1 = value1;
            this.value2 = value2;
        }

        @Override
        public void execute() {
            result = value1 + value2;
        }

        @Override
        public Integer getValue() {
            return result;
        }

        @Override
        public Integer getResult() {
            return result;
        }
    }

}

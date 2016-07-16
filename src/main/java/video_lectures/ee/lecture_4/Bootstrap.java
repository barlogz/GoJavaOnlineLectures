package video_lectures.ee.lecture_4;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Bootstrap {

    private TaskProvider<Integer> taskProvider;
    private Executor<Integer> executor;

    public static void main(String[] args) {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("application-context.xml");
        Bootstrap bootstrap = applicationContext.getBean("bootstrap", Bootstrap.class);
        bootstrap.execute();
        bootstrap.execute();

    }

    public void execute(){
        taskProvider.getAllTasks().forEach(executor::addTask);
        executor.execute();
        System.out.println("Valid results:");
        executor.getValidResults().forEach(System.out::println);
        System.out.println("Invalid results:");
        executor.getInvalidResults().forEach(System.out::println);
    }

    public void setTaskProvider(TaskProvider<Integer> taskProvider) {
        this.taskProvider = taskProvider;
    }

    public void setExecutor(Executor<Integer> executor) {
        this.executor = executor;
    }
}

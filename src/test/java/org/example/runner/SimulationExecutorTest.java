package org.example.runner;
import org.junit.jupiter.api.Test;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.Future;
import static org.junit.jupiter.api.Assertions.*;

class SimulationExecutorTest {

    @Test
    void testSubmitTasks() throws Exception {
        SimulationExecutor executor = new SimulationExecutor();
        Callable<Boolean> task = () -> true;
        List<Future<Boolean>> results = executor.submitTasks(task, 10);
        int completed = executor.collectResults(results);
        assertEquals(10, completed);
    }
}

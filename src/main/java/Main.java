import lombok.extern.slf4j.Slf4j;
import org.example.GameRunner;
import org.example.strategy.RandomCardStrategy;
import org.example.strategy.SimpleMoveStrategy;
import org.example.strategy.SimulationStrategy;

@Slf4j
public class Main {

	public static void main(String[] args) {

		GameRunner gameRunner = new GameRunner();
		int simulations = 1000;

		int success = gameRunner.runSimulations(new SimulationStrategy(), simulations);
		logCompletionRate(success, simulations);

	}


	private static void logCompletionRate(int success, int simulations) {
		log.info(String.format("Completed %d out of %d. Success rate: %.2f%%", success, simulations, ((float) success / simulations) * 100));

	}
}
import lombok.extern.slf4j.Slf4j;
import org.example.runner.GameRunner;
import org.example.strategy.RandomCardStrategy;
import org.example.strategy.SimpleMoveStrategy;
import org.example.strategy.SimulationStrategy;

@Slf4j
public class Main {

	public static void main(String[] args) {
		GameRunner gameRunner = new GameRunner();
		int simulations = 1000;
		int success = gameRunner.runSimulations(new SimpleMoveStrategy(), simulations);
		logCompletionRate(success, simulations);

	}


	private static void logCompletionRate(int success, int simulations) {
		float successRate = ( (float) success / simulations) * 100;
		log.info("Completed {} out of {}. Success rate: {}%", success, simulations, String.format("%.2f", successRate));
	}
}
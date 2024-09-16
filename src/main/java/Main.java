import lombok.extern.slf4j.Slf4j;
import org.example.runner.GameRunner;
import org.example.strategy.BasicMoveStrategy;
import org.example.strategy.CardMoveStrategy;
import org.example.strategy.MonteCarloStrategy;
import org.example.strategy.RandomCardStrategy;

@Slf4j
public class Main {
	public static void main(String[] args) {
		int simulations = 10000;
		CardMoveStrategy strategy = new BasicMoveStrategy();

		GameRunner gameRunner = new GameRunner();
		int success = gameRunner.runSimulations(strategy, simulations);
		logCompletionRate(success, simulations, strategy);
	}

	private static void logCompletionRate(int success, int simulations, CardMoveStrategy strategy) {
		double winRate = ( (double) success / simulations) * 100;
		log.info("Completed {} out of {} simulations with the algorithm '{}'. Win rate: {}%", success,simulations, strategy.toString(), String.format("%.2f", winRate));
	}
}
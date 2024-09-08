import org.example.GameRunner;
import org.example.strategy.RandomCardStrategy;
import org.example.strategy.SimpleMoveStrategy;
import org.example.strategy.SimulationStrategy;

public class Main {

	public static void main(String[] args) {

		GameRunner gameRunner = new GameRunner();
		int simulations = 10000;
		int success = gameRunner.runSimulations(new SimpleMoveStrategy(), simulations);
		System.out.println("Completed " + success + " out of " + simulations +
				". Thus a success-rate of " + (( (float) success) / ((float) simulations)) * 100 + "%");

	}
}
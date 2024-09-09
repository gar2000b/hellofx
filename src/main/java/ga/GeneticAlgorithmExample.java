package ga;

import org.uncommons.maths.binary.BitString;
import org.uncommons.maths.random.Probability;
import org.uncommons.watchmaker.framework.*;
import org.uncommons.watchmaker.framework.operators.BitStringCrossover;
import org.uncommons.watchmaker.framework.operators.BitStringMutation;
import org.uncommons.watchmaker.framework.operators.EvolutionPipeline;
import org.uncommons.watchmaker.framework.selection.RouletteWheelSelection;
import org.uncommons.watchmaker.framework.termination.GenerationCount;

import java.util.*;

public class GeneticAlgorithmExample {

    private static final int BITS = 24;
    private static final int POPULATION_SIZE = 200;
    private static final int MAX_GENERATIONS = 400;
    private static final double CROSSOVER_PROBABILITY = 0.5;
    private static final double MUTATION_PROBABILITY = 0.1;
    private static final int ELITISM_COUNT = (int) Math.max(1, Math.round(POPULATION_SIZE * 0.05));

    private static int bitsToInt(BitString bitString, int start, int end) {
        int value = 0;
        for (int i = start; i < end; i++) {
            if (bitString.getBit(i)) {
                value |= 1 << (i - start);
            }
        }
        return value;
    }

    private static class FitnessFunction implements FitnessEvaluator<BitString> {
        @Override
        public double getFitness(BitString candidate, List<? extends BitString> population) {
            int fastLength = bitsToInt(candidate, 0, 8);
            int slowLength = bitsToInt(candidate, 8, 16);
            int signalLength = bitsToInt(candidate, 16, 24);

            if (fastLength == 0 || slowLength == 0 || signalLength == 0) {
                return 0;
            }

            double result = (fastLength - signalLength) * slowLength;
            return Math.max(result, 0);  // Ensure non-negative fitness score
        }

        @Override
        public boolean isNatural() {
            return true;
        }
    }

    private static class ProgressListener implements EvolutionObserver<BitString> {
        private BitString bestCandidate;

        @Override
        public void populationUpdate(PopulationData<? extends BitString> data) {
            bestCandidate = data.getBestCandidate();
            int fastLength = bitsToInt(bestCandidate, 0, 8);
            int slowLength = bitsToInt(bestCandidate, 8, 16);
            int signalLength = bitsToInt(bestCandidate, 16, 24);
            System.out.printf("Generation %d: Best fitness: %f, Solution (fast, slow, signal): %d, %d, %d%n",
                    data.getGenerationNumber(), data.getBestCandidateFitness(), fastLength, slowLength, signalLength);
        }

        public BitString getBestCandidate() {
            return bestCandidate;
        }
    }

    private static class BitStringFactory implements CandidateFactory<BitString> {
        private final int length;

        public BitStringFactory(int length) {
            this.length = length;
        }

        @Override
        public BitString generateRandomCandidate(Random rng) {
            return new BitString(length, rng);
        }

        @Override
        public List<BitString> generateInitialPopulation(int populationSize, Random rng) {
            List<BitString> population = new ArrayList<>(populationSize);
            for (int i = 0; i < populationSize; i++) {
                population.add(generateRandomCandidate(rng));
            }
            return population;
        }

        @Override
        public List<BitString> generateInitialPopulation(int populationSize, Collection<BitString> seedCandidates, Random rng) {
            List<BitString> population = new ArrayList<>(populationSize);
            population.addAll(seedCandidates);
            for (int i = seedCandidates.size(); i < populationSize; i++) {
                population.add(generateRandomCandidate(rng));
            }
            return population;
        }
    }

    public static void main(String[] args) {
        CandidateFactory<BitString> candidateFactory = new BitStringFactory(BITS);
        EvolutionaryOperator<BitString> pipeline = new EvolutionPipeline<>(Arrays.asList(
                new BitStringCrossover(),
                new BitStringMutation(new Probability(MUTATION_PROBABILITY))
        ));

        FitnessEvaluator<BitString> fitnessEvaluator = new FitnessFunction();
        ProgressListener progressListener = new ProgressListener();
        EvolutionEngine<BitString> engine = new GenerationalEvolutionEngine<>(
                candidateFactory,
                pipeline,
                fitnessEvaluator,
                new RouletteWheelSelection(),
                new Random()
        );

        engine.addEvolutionObserver(progressListener);
        long startTime = System.currentTimeMillis();
        System.out.println("GA start...");
        engine.evolve(POPULATION_SIZE, ELITISM_COUNT, new GenerationCount(MAX_GENERATIONS));
        System.out.println("GA end...");
        long endTime = System.currentTimeMillis();

        BitString bestSolution = progressListener.getBestCandidate();
        int fastLength = bitsToInt(bestSolution, 0, 8);
        int slowLength = bitsToInt(bestSolution, 8, 16);
        int signalLength = bitsToInt(bestSolution, 16, 24);

        System.out.printf("Best solution (fast, slow, signal): %d, %d, %d%n", fastLength, slowLength, signalLength);
        System.out.printf("GA execution time: %d milliseconds%n", (endTime - startTime));
    }
}

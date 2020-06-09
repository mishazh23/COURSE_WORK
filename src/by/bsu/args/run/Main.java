package by.bsu.args.run;

import by.bsu.args.algorithm.ClassicGeneticAlgorithm;
import by.bsu.args.algorithm.GenitorGeneticAlgorithm;
import by.bsu.args.entity.Constant;
import by.bsu.args.entity.Population;



public class Main {

    public static void main(String[] args) {
        GenitorGeneticAlgorithm algorithm = new GenitorGeneticAlgorithm();
        //ClassicGeneticAlgorithm algorithm = new ClassicGeneticAlgorithm();
        Population population = new Population(5);
        population.initialize();
        int generationCounter = 0;
        while (generationCounter != Constant.SIMULATION_LENGTH){
            ++generationCounter;
            System.out.println("\n"+"Generation " + generationCounter+":" + population.getFittestIndividual().getFitness() + "- fitness" );
            System.out.println("The fittest: "+population.getFittestIndividual().toString());
            System.out.println("Population:");
            for(int i=0; i<population.size();i++){
                System.out.println(population.getIndividual(i).toString());
            }
            population = algorithm.evolvePopulation(population);
        }
        System.out.println("The minimum is found!");
        System.out.println(population.getFittestIndividual());

    }
}

package by.bsu.args.algorithm;

import by.bsu.args.entity.Chromosome;
import by.bsu.args.entity.Constant;
import by.bsu.args.entity.Population;

import java.util.Random;

public class GenitorGeneticAlgorithm {
    private Random randomGenerator;

    public GenitorGeneticAlgorithm(){
        this.randomGenerator = new Random();
    }

    public Population evolvePopulation(Population population){
        for(int i = 0; i<population.size(); ++i){
            Chromosome firstChromosome = randomSelection(population);
            Chromosome secondChromosome = randomSelection(population);
            Chromosome newChromosome = crossover(firstChromosome, secondChromosome);
            int index = findWeakest(population);
            population.saveIndividual(index, newChromosome);
        }

        for(int i=0; i< population.size();++i){
            mutate(population.getIndividual(i));
        }
        return population;
    }

    public int findWeakest(Population population){
        int temp=0;
        Chromosome min = population.getIndividual(0);
            for(int i=1; i<population.size();i++){
                if(population.getIndividual(i).getFitness()>min.getFitness()){
                    temp = i;
                    min=population.getIndividual(i);
                }
            }
        return temp;
    }
    public void mutate(Chromosome chromosome){
        for(int i = 0; i< Constant.CHROMOSOME_LENGTH; ++i){
            if(Math.random() <= Constant.MUTATION_RATE){
                int gene = randomGenerator.nextInt(2);
                chromosome.setGene(i,gene);
            }
        }
    }

    public Chromosome crossover(Chromosome firstChromosome, Chromosome secondChromosome){
        Chromosome newSolution = new Chromosome();
        for(int i=0; i< Constant.CHROMOSOME_LENGTH; ++i){
          if(Math.random() <= Constant.CROSSOVER_RATE){
                newSolution.setGene(i, firstChromosome.getGene(i));
            }  else{
              newSolution.setGene(i, secondChromosome.getGene(i));
          }
        }
        return newSolution;
        }

    public Chromosome randomSelection(Population population){
        Population newPopulation = new Population(Constant.TOURNAMENT_SIZE);
        for(int i=0; i<Constant.TOURNAMENT_SIZE; ++i){
            int  randomIndex = (int) (Math.random()*population.size());
            newPopulation.saveIndividual(i, population.getIndividual(randomIndex));
        }
        Chromosome fittestChromosome = newPopulation.getFittestIndividual();
        return fittestChromosome;
    }
}

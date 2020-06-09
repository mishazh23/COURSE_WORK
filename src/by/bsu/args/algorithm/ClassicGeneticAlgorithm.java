package by.bsu.args.algorithm;

import by.bsu.args.entity.Chromosome;
import by.bsu.args.entity.Constant;
import by.bsu.args.entity.Population;

import java.util.Random;

public class ClassicGeneticAlgorithm {
    private Random randomGenerator;

    public ClassicGeneticAlgorithm(){
        this.randomGenerator = new Random();
    }

    public Population evolvePopulation(Population population){
        Population children = new Population(population.size());
        for(int i = 0; i<population.size(); i++){
            Chromosome firstChromosome = randomSelection(population);
            Chromosome secondChromosome = randomSelection(population);
            Chromosome newChromosome = crossover(firstChromosome, secondChromosome);
            children.saveIndividual(i, newChromosome);
        }
        for(int i=0; i< children.size();++i){
            mutate(children.getIndividual(i));
        }
        Population newPopulation = new Population(4);
        newPopulation = selection(population, children);

        return newPopulation;
    }

    public Population selection(Population population, Population children){
        Population newPopulation = new Population(Constant.TOURNAMENT_SIZE*2);
         for(int i=0; i< population.size();i++){
             newPopulation.saveIndividual(i,population.getIndividual(i));
         }
        for(int i=population.size(); i< newPopulation.size();i++){
            newPopulation.saveIndividual(i,children.getIndividual(i-Constant.TOURNAMENT_SIZE));
        }
        sort(newPopulation);
        Population sortedPopulation = new Population(population.size());
        for(int i=0; i< population.size();i++){
            sortedPopulation.saveIndividual(i, newPopulation.getIndividual(i));
        }
        return sortedPopulation;
    }

    public void sort(Population population){
        boolean isSorted = false;
        Chromosome temp;
        while (!isSorted){
            isSorted = true;
            for(int i=0; i<population.size()-1;i++){
                if(population.getIndividual(i).getFitness()>population.getIndividual(i+1).getFitness()){
                    isSorted = false;
                    temp = population.getIndividual(i);
                    population.saveIndividual(i,population.getIndividual(i+1));
                    population.saveIndividual(i+1,temp);
                }
            }
        }
    }

    public Chromosome randomSelection(Population population){
        int  randomIndex = (int) (Math.random()*population.size());
        return population.getIndividual(randomIndex);
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
}

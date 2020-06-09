package by.bsu.args.entity;

public class Population {
    private Chromosome[] chromosomes;

    public Population(int populationSize){
        chromosomes = new Chromosome[populationSize];
    }

    public void initialize(){
        for(int i = 0; i< chromosomes.length; ++i){
            Chromosome newChromosome = new Chromosome();
            newChromosome.generateIndividual();
            saveIndividual(i, newChromosome);
        }
    }

    public Chromosome getIndividual(int index){
        return this.chromosomes[index];
    }

    public Chromosome getFittestIndividual(){
        Chromosome fittest = chromosomes[0];
        for(int i = 1; i< chromosomes.length; ++i){
            if(getIndividual(i).getFitness() < fittest.getFitness())
                fittest=getIndividual(i);
        }
        return fittest;
    }

    public int size(){
        return chromosomes.length;
    }
    public void saveIndividual(int index, Chromosome chromosome){
        this.chromosomes[index]= chromosome;
    }
}

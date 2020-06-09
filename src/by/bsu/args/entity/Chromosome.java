package by.bsu.args.entity;


import java.util.Random;

public class Chromosome {
    private int[] genes;
    private double fitness;
    private Random randomGenerator;

    public Chromosome(){
        this.genes = new int[Constant.CHROMOSOME_LENGTH];
        this.randomGenerator = new Random();
    }

    public void generateIndividual(){
        for (int i=0; i<Constant.CHROMOSOME_LENGTH;i++){
            int gene = randomGenerator.nextInt(2); // only 0 and 1
            genes[i]=gene;
        }
    }

    public double f(int x){
        //return 5-24*x+17*x*x-3.66666666667*x*x*x+0.25*x*x*x*x;
        //return x+2;
        //return 17*x*x + 3*x +1;
        return Math.sin(x+3)*5 + x*x*x;
    }
    public int genesToInt(){
        int base = 1;
        int geneInInt = 0;

        for(int i=0; i<Constant.GENE_LENGTH;++i){
            if(this.genes[i] == 1) {
                geneInInt += base;
            }

            base=base*2;
        }
        return geneInInt;
    }

    public double getFitness(){
       int genesToInt = genesToInt();
        return f(genesToInt);
    }

    public int getGene(int index){
        return this.genes[index];
    }

    public void setGene(int index, int value){
        this.genes[index] = value;
        this.fitness = 0;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        for(int i =genes.length-1; i>=0;i--){
            result.append(genes[i]);
        }
        return result.toString();
    }
}

import java.util.Scanner;
import java.util.ArrayList;

public class Main{
    public static void main(String[] args){
        polynome p = new polynome();
        polynome q = new polynome();
        p.ToString();
        q.ToString();
        p.evalPoly(2);
        q.evalPoly(2);
        System.out.println("Evaluation du polynome en récursivité : "+p.evalPolynomeRec(2,0));
        System.out.println("Evaluation du polynome en récursivité : "+q.evalPolynomeRec(2,0));
        polynome.additionPolynomes(p, q).ToString();
        p.additionnerA(q).ToString();
        p.produitExterne(2).ToString();
        polynome.derivPolynome(p).ToString();
        polynome.primitivePolynome(p).ToString();
        System.out.println("Recherche du zéro par dichotomie : "+p.rechercheZeroDicho(-10, 10, 0.0001));
        System.out.println("Recherche du zéro par Newton : "+p.rechercheZeroNewton(p, 1, 0.0001));
        }
    }

class polynome{                     //Définition de la classe
    int P1;
    double[] P2;


    polynome() {                                    //Constructeur
        int a;
        Scanner sc = new Scanner(System.in);
        System.out.println("Degré du polynome : ");
        a = sc.nextInt();
        double b[] = new double[a+1];
        for(int i = 0; i < a+1; i++){
            System.out.println("Coefficient de degré " + i + " : ");
            b[i] = sc.nextDouble();
        }
        this.P1 = a;
        this.P2 = b;
        sc.close(); // Close the Scanner object
    }

    polynome(int a, double[] b) {                   //Constructeur
        this.P1 = a;
        this.P2 = b;
    }


    public void ToString(){                                 //Méthode affichage polynome
        System.out.print("Polynome : " + this.P2[0]);
            for(int i = 1; i < this.P1+1; i++){
                System.out.print(" + " + this.P2[i]+"*x^"+i);
            }
            System.out.println();
    }

    public void evalPoly(double X){
    double temp;
    temp = 0;
    for(int i = 0; i < this.P1+1; i++){
        temp = temp + this.P2[i]*Math.pow(X,i);
    }
    System.out.println("Evaluation du polynome = "+temp);
    }

    public double evalPolynomeRec(double X, int degre){
        
        if(degre < this.P1){
            return this.P2[degre] + X*evalPolynomeRec(X, degre+1);
        }
        else{
            return this.P2[degre];
        }
    }

    public static polynome additionPolynomes(polynome P, polynome Q){
        int temp;
        if(P.P1 > Q.P1){
            temp = P.P1;
        }
        else{
            temp = Q.P1;
        }
        double[] c = new double[temp+1];
        for(int i = 0; i <= temp-1; i++){
            c[i] = P.P2[i] + Q.P2[i];
        }
    polynome R = new polynome(temp, c);
    return R;
    }

    //Ecrivez un méthode additonnerA() qui somme le polynome courant avec un autre polynome passé en paramètre.

    public polynome additionnerA(polynome P){
        int temp;
        if(this.P1 > P.P1){
            temp = this.P1;
        }
        else{
            temp = P.P1;
        }
        double[] c = new double[temp+1];
        for(int i = 0; i <= temp; i++){
            c[i] = this.P2[i] + P.P2[i];
        }
        polynome R = new polynome(temp, c);
        return R;
    }

    public polynome produitExterne(double a){
        double[] c = new double[this.P1+1];
        for(int i = 0; i<= this.P1; i++){
            c[i] = this.P2[i]*a;
        }
        polynome R = new polynome(this.P1, c);
        return R;
    }

    public static polynome derivPolynome(polynome P){
        double[] c = new double[P.P1];
        int a;
        a = P.P1-1;
        for(int i = 1; i <= P.P1; i++){
            c[i-1] = P.P2[i]*i;
        }
        polynome R = new polynome(a, c);
        return R;
    }

    public static polynome primitivePolynome(polynome P){
        double[] c = new double[P.P1+2];
        int a;
        a = P.P1+1;
        for(int i = 0; i <= P.P1; i++){
            c[i+1] = P.P2[i]/(i+1);
        }
        polynome R = new polynome(a, c);
        return R;
    }
    

    public double rechercheZeroDicho(double a, double b, double epsilon){
        double c;
        c = (a+b)/2;
        if(Math.abs(b-a) < epsilon){
            return c;
        }
        else{
            if(this.evalPolynomeRec(a, 0)*this.evalPolynomeRec(c, 0) < 0){
                return this.rechercheZeroDicho(a, c, epsilon);
            }
            else{
                return this.rechercheZeroDicho(c, b, epsilon);
            }
        }
        
    }

    public double rechercheZeroNewton(polynome p,double a, double epsilon){
        double b =0 ;
        polynome dP = derivPolynome(p);
        while(Math.abs(b-a) > epsilon){
            b = a - dP.evalPolynomeRec(a, 0)/p.evalPolynomeRec(a, 1);
            a = b;
        }
        return a;
    }

    public polynome[] tableauPolynome(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Nombre de polynomes : ");
        int a = sc.nextInt();
        polynome[] tab = new polynome[a];
        for(int i = 0; i < a; i++){
            tab[i] = new polynome();
        }
        sc.close(); // Close the Scanner object
        return tab;
    }

    public ArrayList<polynome> tableauPolynomeArray(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Nombre de polynomes : ");
        int a = sc.nextInt();
        ArrayList<polynome> tab = new ArrayList<polynome>();
        for(int i = 0; i < a; i++){
            tab.set(i, new polynome());
        }
        sc.close(); // Close the Scanner object
        return tab;
    } 

    public polynome[] sommeTableauP(polynome[] tab1, polynome[] tab2){
        int a;
        if(tab1.length > tab2.length){
            a = tab1.length;
        }
        else{
            a = tab2.length;
        }
        polynome[] tab3 = new polynome[a];
        polynome[] tab4 = new polynome[a];
        for(int i = 0; i < a; i++){
            tab3[i] = tab1[i].additionnerA(tab2[i]);
            tab4[i] = tab1[i].produitExterne(tab2[i].P2[0]);
        }
        System.out.println("Somme des polynomes : "+tab3+"Produit externe des polynomes : "+tab4);
        return tab3;
        
    }

}

class polynomeCreux {
    double[][] P1;

    polynomeCreux() {
        int a;
        Scanner sc = new Scanner(System.in);
        System.out.println("Nombre de valeur diff de 0 : ");
        a = sc.nextInt();
        double b[][] = new double[a+1][a+1];
        for(int i = 0; i < a+1; i++){
            System.out.println("Coefficient de degré :");
            b[i][1] = sc.nextDouble();
            System.out.println("Valeure ");
            b[i][0] = sc.nextDouble();
        }
        sc.close(); // Close the Scanner object
        this.P1 = b;
    }

    public void ToString(){              
        int a = P1.length;
        System.out.print("Polynome : " + this.P1[0][0]);
        for(int i = 1; i < a+1; i++){
            System.out.print(" + " + this.P1[i][0]+"*x^"+ this.P1[i][1]);
        }
        System.out.println();
    }
}




package section3;

public class Polynomial3 {
	
	public char name; 
	public int nTerms;
	public Term3[] terms;	
	
	public Polynomial3() {
		nTerms = 0;
		terms = new Term3[100];
	}
	
	public Polynomial3(char name) {
		this.name = name;
		nTerms = 0;
		terms = new Term3[100];
	}
	
	public int clacPolynomial(int x) {
		int result = 0;
		for(int i = 0; i<nTerms; i++)
			result += terms[i].calcTerm(x);
		return result;
	}

	public void printPolynomial() {
		for(int i= 0; i<nTerms; i++) {
			terms[i].printTerm();
			System.out.print("+");
		}
		System.out.println();
	}
	

	public void addTerm(int c, int e) {
		int index = findTerm(e);
		if(index != -1)
		{
			terms[index].coef += c;
		}
		else
		{
			int i = nTerms-1;
			while(i >= 0 && terms[i].expo < e)
			{
				terms[i+1] = terms[i];
				i--;
			}
			terms[i+1] = new Term3(c ,e);
			nTerms++;
		}
	}

	public int findTerm(int e) {
		for(int i = 0; i<nTerms && terms[i].expo >= e; i++)
			if(terms[i].expo == e)
				return i;
		return -1;
	}

}

package plic.arbre.expression;

/**
 * 3 déc. 2015
 *
 * @author brigitte wrobel-dautcourt
 */

public class ConstanteBool extends Constante {
    
    public ConstanteBool(String texte, int n) {
        super(texte, n) ;
    }

	@Override
	public int getType() {
		return Expression.BOOL;
	}

	@Override
	public void verifier() {
		// TODO Auto-generated method stub
		
	}

	
	@Override
    public String toMIPS(){
    	return "li $v0, "+ (cste.equals("true")?"1":"0")  + "\n";
    }

}

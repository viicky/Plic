package plic.arbre.instruction;

import plic.arbre.BlocDInstructions;
import plic.arbre.expression.Expression;

public class Si extends Instruction {
	protected Expression expr;
	protected BlocDInstructions blocSi;
	protected BlocDInstructions blocSinon;
	protected static int blocGlob = 0;
	protected int blocActu;
	
	public Si(Expression e, BlocDInstructions b1, BlocDInstructions b2) {
		super(e.getNoLigne());
		expr=e;
		blocSi=b1;
		blocSinon=b2;
		blocActu=blocGlob++;
	}

	@Override
	public void verifier() {
		expr.verifier();
		blocSi.verifier();
		if(blocSinon != null){
			blocSinon.verifier();
		}
	}

	@Override
	public String toMIPS() {
		StringBuilder sb = new StringBuilder();
		sb.append(expr.toMIPS());
		if(blocSinon != null){
			sb.append(
			"bnez $v0, si"+blocActu+"\n"
			+ "b sinon"+blocActu+"\n"
			+ "si"+blocActu+":");
			sb.append(blocSi.toMIPS());
			sb.append("b fin"+blocActu+"\n"
			+ "sinon"+blocActu+":");
			sb.append(blocSinon.toMIPS());
			sb.append("fin"+blocActu+":\n");
		}else{
			sb.append("beqz $v0, fin"+blocActu+"\n");
			sb.append(blocSi.toMIPS());
			sb.append("fin"+blocActu+":\n");
		}
		
		return sb.toString();
	}

	
	
}

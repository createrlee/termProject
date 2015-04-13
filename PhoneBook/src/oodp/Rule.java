package oodp;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;


public class Rule
{
	public Predicate<String> Match;
	public Function<String, String[]> Split;
	public Consumer<String[]> Execute;

	static String[] Split_Basic(String input)
	{
		return input.split("\\s+");
	}

	static String[] Split_Raw(String input)
	{
		return new String[]	{ input };
	}

	public Rule()
	{
		Split = Rule::Split_Basic;
	}
	
	public Rule(boolean isUsingRawTextMode)
	{
		if ( isUsingRawTextMode == true )
			Split = Rule::Split_Raw;
		else
			Split = Rule::Split_Basic;
	}
}

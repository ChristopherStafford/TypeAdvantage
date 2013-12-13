package com.hedronco.typeadvantage;


import java.util.ArrayList;
import java.util.List;


import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;


public class MainActivity extends Activity{
	
	int numTypes = 19;
	
	String typeNames[] = {
		"None",
		"Normal",
		"Grass",
		"Water",
		"Fire",
		"Electric",
		"Fighting",
		"Flying",
		"Poison",
		"Ground",
		"Rock",
		"Bug",
		"Ghost",
		"Steel",
		"Psychic",
		"Ice",
		"Dragon",
		"Dark",
		"Fairy"	
	};
	
	enum typeNum{
		None,
		Normal,
		Grass,
		Water,
		Fire,
		Electric,
		Fighting,
		Flying,
		Poison,
		Ground,
		Rock,
		Bug,
		Ghost,
		Steel,
		Psychic,
		Ice,
		Dragon,
		Dark,
		Fairy
	}
	
	float typeChart[][] = {// row is defending type
			//  n	gr	wa	fi	el	ft	fl	po	gr	ro	bu	gh	st	ps	ic	dr	da	fa
			{1,	1,	1,	1,	1,	1,	1,	1,	1,	1,	1,	1,	1,	1,	1,	1,	1,	1,	1}, //none
			{1,	1,	1,	1,	1,	1,	2,	1,	1,	1,	1,	1,	0,	1,	1,	1,	1,	1,	1}, //normal
			{1,	1, .5f,.5f, 2, .5f,	1,	2,	2, .5f,	1,	2,	1,	1,	1,	2,	1,	1,	1}, //grass
			{1,	1,	2, .5f,.5f, 2,	1,	1,	1,	1,	1,	1,	1, .5f,	1, .5f,	1,	1,	1}, //water
			{1,	1, .5f,	2, .5f,	1,	1,	1,	1,	2,	2, .5f,	1, .5f,	1, .5f,	1,	1, .5f}, //fire
			{1,	1,	1,	1,	1, .5f,	1, .5f,	1,	2,	1,	1,	1, .5f,	1,	1,	1,	1,	1}, //electric
			{1,	1,	1,	1,	1,	1,	1,	2,	1,	1, .5f,.5f,	1,	1,	2,	1,	1, .5f,	2}, //fighting
			{1,	1, .5f,	1,	1,	2, .5f,	1,	1,	0,	2, .5f,	1,	1,	1,	2,	1,	1,	1}, //flying
			{1,	1, .5f,	1,	1,	1, .5f,	1, .5f,	2,	1, .5f,	1,	1, .5f,	1,	1,	1, .5f}, //poison
			{1,	1,	2,	2,	1,	0,	1,	1, .5f,	1, .5f,	1,	1,	1,	1,	2,	1,	1,	1}, //ground
			{1,.5f,	2,	2, .5f,	1,	2, .5f,.5f,	2,	1,	1,	1,	2,	1,	1,	1,	1,	1}, //rock
			{1,	1, .5f,	1,	2,	1, .5f,	2,	1, .5f,	2,	1,	1,	1,	1,	1,	1,	1,	1}, //bug
			{1,	0,	1,	1,	1,	1,	0,	1, .5f,	1,	1, .5f,	2,	1,	1,	1,	1,	2,	1}, //ghost
			{1,.5f,.5f,	1,	2,	1,	2, .5f,	0,	2, .5f,.5f,	1, .5f,.5f,.5f,.5f,	1, .5f}, //steel
			{1,	1,	1,	1,	1,	1, .5f,	1,	1,	1,	1, .5f,.5f,	1, .5f,	1,	1, .5f,	1}, //psychic
			{1,	1,	1,	1,	2,	1,	2,	1,	1,	1,	2,	1,	1,	2,	1, .5f,	1,	1,	1}, //ice
			{1,	1, .5f,.5f,.5f,.5f,	1,	1,	1,	1,	1,	1,	1,	1,	1,	2,	2,	1,	2}, //dragon
			{1,	1,	1,	1,	1,	1,	2,	1,	1,	1,	1,	2, .5f,	1,	0,	1,	1, .5f,	2}, //dark
			{1,	1,	1,	1,	1,	1, .5f,	1,	2,	1,	1, .5f,	1,	2,	1,	1,	0, .5f,	1}, //fairy
			
	};
	
	
	private TypeContainer[] types = {null, null, null, null};
	
	private TextView advantageText;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		
		for(int i = 0; i < 4; i++)
		{
			int theTarget = 0;
			
			switch(i){
			case 0:
				theTarget = R.id.type1;
				break;
			case 1:
				theTarget = R.id.type2;
				break;
			case 2:
				theTarget = R.id.type3;
				break;
			case 3:
				theTarget = R.id.type4;
				break;
			}
			
			types[i]= new TypeContainer(makeSpinner(theTarget), this);
		}
		
		
		advantageText = (TextView) findViewById(R.id.advantage_string);
		calculateAdvantage();
		
		
		
	}
	
	private Spinner makeSpinner(int theId)
	{
		Spinner typeSpinner = (Spinner) findViewById(theId);
		
		List<String> list = new ArrayList<String>();
		
		for (int i = 0; i < numTypes; i++)
		{
			list.add(typeNames[i]);			
		}
		
		ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
				R.layout.type_spinner_layout, list);
		
		dataAdapter.setDropDownViewResource(R.layout.type_spinner_layout);
		typeSpinner.setAdapter(dataAdapter);
		
		return typeSpinner;
	}

	
	public void calculateAdvantage()
	{
		float advantages[] = {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1};
		
		for(int i=0; i < advantages.length; i++)
		{
			advantages[i] = 1;
		}
		
		for(int i=0; i < types.length; i++)
		{
			for(int j=0; j < numTypes; j++)
			{
				advantages[j] = advantages[j] * typeChart[ types[i].getTypeNum() ][j];
			}
			
		}
		
		String temp = "";
		
		for(int i=1; i < typeNames.length; i++)
		{
			temp += typeNames[i] + ":";
			temp += advantages[i] + "    ";
		}
		
		advantageText.setText(temp);
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	
	public interface NewsUpdateListener 
    {
        void onNewsUpdate();
    }
	
	
	
	

}





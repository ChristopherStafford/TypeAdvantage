package com.hedronco.typeadvantage;


import android.view.View;
import android.widget.AdapterView;

import android.widget.Spinner;
import android.widget.AdapterView.OnItemSelectedListener;



public class TypeContainer{
	
	private Spinner typeSpinner;
	private int typeNum;
	private MainActivity parent;
	
	public TypeContainer(Spinner theSpinner, MainActivity theParent)
	{
		typeNum = 0;
		parent=theParent;
		typeSpinner = theSpinner;
		typeSpinner.setOnItemSelectedListener(new OnItemSelectedListener() {
			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub
				typeNum = typeSpinner.getSelectedItemPosition();
				parent.calculateAdvantage();
				
			}
			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub
				
			}
			
		});
	}
	
	public int getTypeNum()
	{
		return typeNum;
	}

}

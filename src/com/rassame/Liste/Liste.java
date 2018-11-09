package com.rassame.Liste;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;
import java.util.function.Function;
import java.util.function.Predicate;


public class Liste<T> extends ArrayList<T> implements Serializable  {

	private static final long serialVersionUID = 1000L;

	public Liste<T> where(Predicate<T> predicate)
	{
		Liste<T> result=new Liste<T>();
		for(T item : this)
		{
			if(predicate.test(item))
			{
				result.add(item);
			}
		}
		return result;
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Liste<T> orderBy(Function<T,Comparable> function)
	{
		Liste<T> r=new Liste<T>();
		this.forEach(r::add);
		for(int i=0;i<r.size() - 1;i++)
		{
			for(int j = i + 1;j<r.size(); j++)
			{
				if(function.apply(r.get(j)).compareTo(function.apply(r.get(i))) < 0)
				{
					T temp=r.get(i);
					r.set(i, r.get(j));
					r.set(j, temp);
				}
			}
		}
		return r;
	}
	
	@SuppressWarnings("rawtypes")
	public Liste getRandomElements(int nombre)
	{
		Liste<T> result=new Liste<T>();
		if(nombre > size())
			nombre=size();
		for(int i=0;result.size()<nombre && i<100;i++)
		{
			Random rn = new Random();
			int r=rn.nextInt();
			int x = r % size();
			if(x<0)
				x=0-x;
			if(!result.contains(get(x)))
			{
				result.add(get(x));
			}
		}
		return result;
	}
	
	//from methods
	//fromIterable
	//fromPage
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public  static Liste fromIterable(Iterable iterable)
	{
		Liste result=new Liste();
		if(iterable!=null)
			iterable.forEach(result::add);
		return result;
	}

}

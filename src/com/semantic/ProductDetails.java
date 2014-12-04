package com.semantic;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.org.json.JSONArray;
import com.org.json.JSONObject;
import com.semantics3.api.Products;

import oauth.signpost.exception.OAuthCommunicationException;
import oauth.signpost.exception.OAuthExpectationFailedException;
import oauth.signpost.exception.OAuthMessageSignerException;

import org.json.simple.*;


public class ProductDetails {

	public static void main(String[] args) throws OAuthMessageSignerException, OAuthExpectationFailedException, OAuthCommunicationException, IOException {

		Products products = new Products( "SEM36741DF90D40214E83E651954C5BAF779", 
											"MWNmMzM2NjY4N2JkYTg2NjkzYjgxYmRkNjgwMWRlOTQ" );
		products
		    .field("cat_id", 9359)
		    .field("brand", "Apple");

		/* Make the query */
		com.org.json.JSONObject results = products.getProducts();
		System.out.println(results);
		System.out.println("Results Length - "+results.length());
		System.out.println("Results Json array Length - "+results.getJSONArray("results").length());
		
//		com.org.json.JSONObject results1 = products.getOffers();
//		System.out.println(results1);
//		
		for(int p=0;p<results.length();p++){
			
			System.out.println(p+") "+ results.getJSONArray("results").getJSONObject(p));
	
		
		JSONArray jsonarray = results.getJSONArray("results").getJSONObject(p).getJSONArray("sitedetails");
		
//		System.out.println("sitedetails size for "+p+jsonarray.length());
//		System.out.println("sitedetails.getJSONObject size for "+p+jsonarray.getJSONObject(0).length());
//		

		JSONArray gtins = results.getJSONArray("results").getJSONObject(p).getJSONArray("gtins");
		
		System.out.println("gtins - \n"+gtins);
		System.out.println("gtins Length - \n"+gtins.length());
		
		for(int s=0;s<gtins.length();s++)
			{
				System.out.println("gtin["+s+"] - "+gtins.getString(s));
			}
		
		JSONObject features = results.getJSONArray("results").getJSONObject(p).getJSONObject("features");
		
		ReadJSON readFeatures = new ReadJSON();
		System.out.println("Keys = "+readFeatures.readJsonValues(features.toString()));
		
		System.out.println("Features - \n"+features);
		System.out.println("Features Length - \n"+features.length());
//		
//		for(int s=0;s<features.length();s++)
//		{
//			System.out.println("Features - \n"+features);
//		}
		
		System.out.println("Here are the product details \n");
		
		for(int i=0;i<jsonarray.length();i++)
			{
//		System.out.println("values Present - "+jsonarray.getJSONObject(i).getJSONArray("latestoffers").length()+"\n Object - "+
//											   jsonarray.getJSONObject(i).getJSONArray("latestoffers"));
//		
		int j = jsonarray.getJSONObject(i).getJSONArray("latestoffers").length();
		
		for(int k=0;k<j;k++)
				{
				System.out.println(jsonarray.getJSONObject(i).getJSONArray("latestoffers").getJSONObject(k).get("seller")+" - "+
										jsonarray.getJSONObject(i).getJSONArray("latestoffers").getJSONObject(k).get("price"));
				try{
					System.out.println(jsonarray.getJSONObject(i).getJSONArray("latestoffers").getJSONObject(k).get("availability"));
				}catch (Exception e){
					
				}
				}
			}
		}
		 


	}

}

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


public class ProductCatalog {

	public static void main(String[] args) throws OAuthMessageSignerException, OAuthExpectationFailedException, OAuthCommunicationException, IOException {

		Products products = new Products( "SEM36741DF90D40214E83E651954C5BAF779", 
											"MWNmMzM2NjY4N2JkYTg2NjkzYjgxYmRkNjgwMWRlOTQ" );
		products
		    .field("cat_id", 9359)
		    .field("search", "Apple iphone5 features");

		/* Make the query */
		com.org.json.JSONObject results = products.getProducts();
		System.out.println("Results - "+results);
		System.out.println("Results Length - "+results.get("results_count"));
		
		JSONObject features = null;
		JSONObject featuresTemp = null;
		int numberOfFeatures = 0;
		
	try{
		for(int p=0;p<results.getInt("results_count");p++){
			
			System.out.println(p+") "+ results.getJSONArray("results").getJSONObject(p).getJSONObject("features"));
	
		 try{
			 featuresTemp = results.getJSONArray("results").getJSONObject(p).getJSONObject("features");
		 }catch(Exception e){
			 
		 }
		 
		 	if(featuresTemp.length() > numberOfFeatures){
		 		
		 		features = results.getJSONArray("results").getJSONObject(p).getJSONObject("features");
		 		numberOfFeatures = features.length();
		 		JSONArray gtins = results.getJSONArray("results").getJSONObject(p).getJSONArray("gtins");
				
				System.out.println("gtins - \n"+gtins);
				System.out.println("gtins Length - \n"+gtins.length());
				for(int s=0;s<gtins.length();s++)
				{
					System.out.println("gtin["+s+"] - "+gtins.getString(s));
				}
		 	}

		}
		ReadJSON readFeatures = new ReadJSON();
		System.out.println("Keys = "+readFeatures.readJsonValues(features.toString()));
		
		System.out.println("Features - \n"+features);
		System.out.println("Features Length - \n"+features.length());

		
		
		System.out.println("UPC - \n"+results.getJSONArray("results").getJSONObject(0).getString("upc"));
		
		

		}catch(Exception e){
			System.out.println("Inside the Exception \n ");
			e.printStackTrace();
		}
	}

}

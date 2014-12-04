package com.semantics3.utils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.org.json.JSONArray;
import com.org.json.JSONObject;
import com.semantics3.api.Categories;
import com.semantics3.api.Products;

import oauth.signpost.exception.OAuthCommunicationException;
import oauth.signpost.exception.OAuthExpectationFailedException;
import oauth.signpost.exception.OAuthMessageSignerException;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;


public class ProductCategory {
	
	public static JSONObject findCategoryTree(int cat_id) throws OAuthMessageSignerException, OAuthExpectationFailedException, OAuthCommunicationException{
		
		JSONObject category_tree = null;
		try {
		Categories cat = new Categories( "SEM36741DF90D40214E83E651954C5BAF779", 
				"MWNmMzM2NjY4N2JkYTg2NjkzYjgxYmRkNjgwMWRlOTQ" );
		
		cat.field("parent_cat_id", cat_id);

		/* Make the query */
		
		 category_tree =  cat.getCategories();
	
		}  catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return category_tree;
		
	}
	
	public static void printCategoryTree(JSONArray Category_List) throws OAuthMessageSignerException, OAuthExpectationFailedException, OAuthCommunicationException{
		
		if(Category_List!=null && Category_List.length()>0){
		for(int i=0;i<Category_List.length();i++)
		{
		System.out.println("INSERT INTO BLC_CATEGORY (CATEGORY_ID,DESCRIPTION,NAME,URL,DEFAULT_PARENT_CATEGORY_ID,ACTIVE_START_DATE) VALUES ("+
				Category_List.getJSONObject(i).get("cat_id")+ ","+"\'"+
				Category_List.getJSONObject(i).get("name")+"\'"+","+"\'"+
				Category_List.getJSONObject(i).get("name")+"\'"+","+"\'"+"/"+
				Category_List.getJSONObject(i).get("name").toString().toLowerCase().trim().replace(" ", "").replace("&", "-") +"\'"+","+
				Category_List.getJSONObject(i).get("parent_cat_id")+","	+ "CURRENT_TIMESTAMP"+")"+";"	  );
		
		int cat_id =   Integer.parseInt(Category_List.getJSONObject(i).get("cat_id")+"");
		//System.out.println("cat-id - " +  jsonarray.getJSONObject(i).get("cat_id"));
		JSONObject sub_categories = findCategoryTree(cat_id);
		
		//System.out.println("sub_categories - "+sub_categories);
		
		JSONArray subCategory_array = sub_categories.getJSONArray("results");
		
		if(sub_categories!=null && subCategory_array.length()>0){
			
			printCategoryTree(subCategory_array);
			}
		}
	}
		
	}

	public static void main(String[] args) throws OAuthMessageSignerException, OAuthExpectationFailedException, OAuthCommunicationException, IOException {

		
		Products products = new Products( "SEM36741DF90D40214E83E651954C5BAF779", 
											"MWNmMzM2NjY4N2JkYTg2NjkzYjgxYmRkNjgwMWRlOTQ" );
		
		
		JSONObject results = findCategoryTree(915);
		JSONArray category_array = results.getJSONArray("results");
		//System.out.println("jsonArray - "+category_array);
		
		printCategoryTree(category_array);
		/*
		for(int i=0;i<category_array.length();i++)
			{
			// Print Category tree values
			System.out.println("INSERT INTO BLC_CATEGORY (CATEGORY_ID,DESCRIPTION,NAME,URL,DEFAULT_PARENT_CATEGORY_ID,ACTIVE_START_DATE) VALUES ("+
					category_array.getJSONObject(i).get("cat_id")+ ","+"\'"+
					category_array.getJSONObject(i).get("name")+"\'"+","+"\'"+
					category_array.getJSONObject(i).get("name")+"\'"+","+"\'"+"/"+
					category_array.getJSONObject(i).get("name").toString().toLowerCase().trim().replace(" ", "").replace("&", "-") +"\'"+","+
					category_array.getJSONObject(i).get("parent_cat_id")+","	+ "CURRENT_TIMESTAMP"+")"+";"	  );
			
			int cat_id =   Integer.parseInt(category_array.getJSONObject(i).get("cat_id")+"");
			//System.out.println("cat-id - " +  jsonarray.getJSONObject(i).get("cat_id"));
			JSONObject sub_categories = findCategoryTree(cat_id);
			
			//System.out.println("sub_categories - "+sub_categories);
			
			JSONArray subCategory_array = sub_categories.getJSONArray("results");
			
			if(sub_categories!=null && subCategory_array.length()>0){
				
				
			
					// Print Sub-Category tree values
					
				
			}
		}
		
		*/
	}

}

package _02_cat_facts_API;

import _01_intro_to_APIs.data_transfer_objects.CatFact;

public class CatFactsRunner {

    public static void main(String[] args) {
        CatFactsApi catFactsApi = new CatFactsApi();
        catFactsApi.testRequest();
        CatFact catFact = catFactsApi.getCatFact();
        
        for(String s: catFact.getData()) {
        	 System.out.println(s);
        }
       
    }

}
